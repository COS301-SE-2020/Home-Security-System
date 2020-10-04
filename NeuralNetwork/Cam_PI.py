import cv2 as c
from mtcnn.mtcnn import MTCNN
import numpy as np
import tensorflow as tf
from scipy.spatial.distance import cosine
from keras_vggface.vggface import VGGFace
from keras_vggface.utils import preprocess_input
import os
import pika as pi
import time
import json
import base64 as b64
import threading
from flask import Flask, render_template, Response


phys = tf.config.experimental.list_physical_devices('GPU')
if len(phys) > 0:
    tf.config.experimental.set_memory_growth(phys[0], True)

mod_name = 'senet50'
path_features = './models/'
rabbit_host = 'amqps://ohskvfuw:HN8SBYNGPfuswoGySxiH0CyeC38v9oSP@rattlesnake.rmq.cloudamqp.com/ohskvfuw'
rabbit_url = os.environ.get('CLOUDAMQP_URL', rabbit_host)
rabbit_param = pi.URLParameters(rabbit_url)
threshold = 0.5
successive_detection_ignore = 30.0

rabbit_conn = pi.BlockingConnection(rabbit_param)
message_channel = rabbit_conn.channel()
message_channel.queue_declare(queue='alertQueue')
message_channel.queue_declare(queue='notifyQueue')
message_channel.queue_declare(queue='personQueue')
message_channel.queue_declare(queue='vehicleQueue')
message_channel.queue_declare(queue='featureQueue')
message_channel.queue_declare(queue='updatePersonQueue')
message_channel.queue_declare(queue='updateVehicleQueue')
face_d = MTCNN()
face_r = VGGFace(include_top=False, model=mod_name, input_shape=(224, 224, 3), pooling='avg')

cams = list()
num_cams = 0
cam_check = True
while cam_check:
    temp = c.VideoCapture(num_cams)
    if temp.isOpened():
        num_cams += 1
    else:
        cam_check = False
    temp.release()
up_face = False


def load_faces(path):
    success = False
    while not success:
        success = True
        feats = list()
        t_dict = dict()
        for root, folder, files in os.walk(path, topdown=False):
            for file in files:
                f_feat = list()
                f_feat.append(str(os.path.splitext(file)[0]))
                try:
                    f_feat.append(np.load(os.path.join(root, file)))
                except OSError:
                    success = False
                    print('File renamed, restarting')
                f_feat.append(root.split('/')[2])
                print(str(os.path.splitext(file)[0]))
                feats.append(f_feat)
                t_dict[f_feat[0]] = 0.0

    return np.asarray(feats), t_dict


all_f_features, time_dict = load_faces(path_features)


def save_face(path, image, f_d=face_d, f_r=face_r, decode=False):
    if decode:
        face_image = c.imdecode(image, c.IMREAD_COLOR)
    else:
        face_image = c.imread(image)
    image_pixels = np.asarray(face_image)
    face_box = f_d.detect_faces(image_pixels)

    if len(face_box) == 0:
        print("No faces detected.")
    elif len(face_box) > 1:
        print("More than one face detected.")
    else:
        f_x1, f_y1, f_w, f_h = face_box[0]['box']
        face_pixels = image_pixels[f_y1:(f_y1+f_h), f_x1:(f_x1+f_w)]
        face_pixels = c.resize(face_pixels, (224, 224), interpolation=c.INTER_AREA)
        buff = list()
        if mod_name == 'vgg16':
            face_pixels = preprocess_input(np.asarray(face_pixels).astype('float64'))
        else:
            face_pixels = preprocess_input(np.asarray(face_pixels).astype('float64'), version=2)
        buff.append(face_pixels)
        buff = np.asarray(buff)
        feat = f_r.predict(buff)
        np.save(path, feat)
        return feat
    return False


def cam_feed():
    global up_face, all_f_features, time_dict, cams
    windows = list()
    for x in range(num_cams):
        cams.append(c.VideoCapture(x))
        windows.append(str(x))
        c.namedWindow(str(x))

    f = True
    frames = list()
    for cam in cams:
        t, fr = cam.read()
        frames.append(fr)
        f = f and t

    while f:
        for w_num, frame in enumerate(frames):
            pix = np.asarray(frame)
            faces = face_d.detect_faces(pix)
            face_pix = list()

            for face in faces:
                x1, y1, width, height = face['box']
                x1, y1 = abs(x1), abs(y1)
                x2, y2 = x1 + width, y1 + height
                start = (x1, y1)
                end = (x2, y2)
                face_pix.append(pix[y1:y2, x1:x2])
                frame = c.rectangle(frame, start, end, (0, 0, 255), 1)

            inp_features = list()
            for face in face_pix:
                face = c.resize(face, (224, 224), interpolation=c.INTER_AREA)
                if mod_name == 'vgg16':
                    inp_features.append(preprocess_input(np.asarray(face).astype('float64')))
                else:
                    inp_features.append(preprocess_input(np.asarray(face).astype('float64'), version=2))

            inp_features = np.asarray(inp_features)

            if len(inp_features) > 0:
                features = face_r.predict(inp_features)
                for f_num, face in enumerate(features):
                    min_match = 1.0
                    f_name = 'Unknown'
                    f_type = 'Grey'
                    for feat in all_f_features:
                        match = cosine(face, feat[1])
                        if match <= threshold:
                            if match < min_match:
                                min_match = match
                                f_name = feat[0]
                                f_type = feat[2]

                    frame = c.putText(frame, f_name, (0, 20), c.FONT_HERSHEY_DUPLEX, 1, (0, 0, 255), thickness=2)
                    if f_name == 'Unknown':
                        temp_face = 'u' + str(time.time())
                        np.save(path_features + 'Grey/' + temp_face + '.npy', face)
                        message = {'personId': 0, 'tempId': temp_face,
                                   'type': 'Grey', 'exists': False,
                                   'imageStr': 'data:image/jpg;base64,' +
                                               str(b64.b64encode(c.imencode('.jpg',
                                                                            face_pix[f_num])[1]).decode('utf-8')),
                                   'features': False
                                   }
                        message_channel.basic_publish(exchange='sigma.direct',
                                                      routing_key='personKey',
                                                      body=json.dumps(message))
                        up_face = True
                    else:
                        if time.time() - time_dict[f_name] > successive_detection_ignore:
                            time_dict[f_name] = time.time()

                            if f_name[0] == 'u':
                                f_name = '0'

                            if f_type == 'Black':
                                message = {'personId': int(f_name), 'type': 'Black',
                                           'imageStr': 'data:image/jpg;base64,' +
                                                       str(b64.b64encode(c.imencode('.jpg', frame)[1]).decode('utf-8')),
                                           'networkId': 1}
                                message_channel.basic_publish(exchange='sigma.direct',
                                                              routing_key='alertKey',
                                                              body=json.dumps(message))
                            elif f_type == 'Grey' or f_type == 'Deleted':
                                message = {'personId': int(f_name), 'type': 'Grey',
                                           'imageStr': 'data:image/jpg;base64,' +
                                                       str(b64.b64encode(c.imencode('.jpg', frame)[1]).decode('utf-8')),
                                           'networkId': 1}
                                message_channel.basic_publish(exchange='sigma.direct',
                                                              routing_key='alertKey',
                                                              body=json.dumps(message))

            c.imshow(str(w_num), frame)

        if up_face:
            all_f_features, time_dict = load_faces(path_features)
            up_face = False

        frames = list()
        for cam in cams:
            t, fr = cam.read()
            frames.append(fr)
            f = f and t

        key = c.waitKey(5)
        if key == 27:
            for cam in cams:
                cam.release()
            c.destroyAllWindows()
            break


def rabbit_consume():
    t_conn = pi.BlockingConnection(rabbit_param)
    t_message_chn = t_conn.channel()

    def feature_update(ch, method, props, body):
        global up_face, all_f_features
        message = json.loads(body)
        if message['features'] is False:
            img = np.frombuffer(b64.b64decode(message['imageStr'][22:]), dtype=np.uint8)
            save_face(path_features + str(message['type']) + '/' + str(message['personId']) + '.npy', img, decode=True)
        else:
            if message['tempId'] == '0':
                for feat in all_f_features:
                    if str(message['personId']) == str(feat[0]):
                        if message['exists'] is True:
                            os.rename(path_features + feat[2] + '/' + str(feat[0]) + '.npy',
                                      path_features + message['type'] + '/' + str(feat[0]) + '.npy')
                        else:
                            os.rename(path_features + feat[2] + '/' + feat[0] + '.npy',
                                      path_features + 'Deleted/' + feat[0] + '.npy')
            else:
                named = False
                while not named:
                    try:
                        os.rename(path_features + 'Grey/' + str(message['tempId']) + '.npy',
                                  path_features + 'Grey/' + str(message['personId']) + '.npy')
                        named = True
                    except OSError:
                        pass
        up_face = True

    t_message_chn.basic_consume(queue='updatePersonQueue',
                                auto_ack=True,
                                on_message_callback=feature_update)
    t_message_chn.start_consuming()


def streaming():
    def gen(cam):
        t, fr = cam.read()
        while t:
            yield (b'--frame\r\n'
                   b'Content-Type: image/jpeg\r\n\r\n' + c.imencode('.jpg', fr)[1].tobytes() + b'\r\n')
            t, fr = cam.read()

    app = Flask(__name__)

    @app.route('/feed')
    def video_feed():
        return Response(gen(cams[0]),
                        mimetype='multipart/x-mixed-replace; boundary=frame')

    app.run(host='0.0.0.0')


consumer = threading.Thread(target=rabbit_consume, daemon=True)
stream = threading.Thread(target=streaming, daemon=True)
consumer.start()
stream.start()
cam_feed()
rabbit_conn.close()
