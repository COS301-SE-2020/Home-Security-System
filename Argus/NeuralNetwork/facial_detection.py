import os
import cv2 as c
import base64
import numpy as np
import tensorflow as tf
import PIL.Image as Image
from mtcnn.mtcnn import MTCNN
from scipy.spatial.distance import cosine
from keras_vggface.vggface import VGGFace
from keras_vggface.utils import preprocess_input


# ====================
# Only if RTX card
# ====================

# phys = tf.config.experimental.list_physical_devices('GPU')
# tf.config.experimental.set_memory_growth(phys[0],True)

# ====================

def cameraFeed():
    tf = False
    mod_name = 'vgg16'
    frame_num = getNum()

    vc = c.VideoCapture(0)
    c.namedWindow("view")

    if not os.path.exists('video'):
        os.makedirs('video')

    fourcc = c.VideoWriter_fourcc(*'XVID')
    vw = c.VideoWriter('video/recording' + str(frame_num) + '.avi', fourcc, 20.0, (640, 480))

    if not os.path.exists('images'):
        os.makedirs('images')

    if vc.isOpened():
        face_detection = MTCNN()
        face_r = VGGFace(include_top=False, model=mod_name, input_shape=(224, 224, 3), pooling='avg')
        tf, frame = vc.read()

        while tf:
            pix = np.asarray(frame)
            faces = face_detection.detect_faces(pix)
            face_pix = list()

            grey = c.cvtColor(frame, c.COLOR_BGR2GRAY)
            rgb = c.cvtColor(frame, c.COLOR_BGR2RGB)

            for face in faces:
                x1, y1, w, h = face['box']
                x1, y1 = abs(x1), abs(y1)
                x2, y2 = x1 + w, y1 + h
                start = (x1, y1)
                end = (x2, y2)
                roi_grey = grey[y1:y1 + h, x1:x1 + w]
                roi_color = frame[y1:y1 + h, x1:x1 + w]
                face_pix.append(pix[y1:y2, x1:x2])
                frame = c.rectangle(frame, start, end, (0, 0, 255), 1)
                # monitorDetection(x1, y1, w, h)

            inp_features = list()
            pic_num = 0

            for face in face_pix:
                face = c.resize(face, (224, 224), interpolation=c.INTER_AREA)
                if mod_name == 'vgg16':
                    inp_features.append(preprocess_input(np.asarray(face).astype('float64')))
                else:
                    inp_features.append(preprocess_input(np.asarray(face).astype('float64'), version=2))
                c.imwrite('images/image' + str(frame_num) + '_' + str(pic_num) + '.jpg', face)
                pic_num += 1

            inp_features = np.asarray(inp_features)

            if len(inp_features) > 0:
                features = face_r.predict(inp_features)
                print(features)

            # record video
            # vw.write(grey)
            vw.write(frame)

            # display frames
            # c.imshow('frame', grey)
            c.imshow('view', frame)

            tf, frame = vc.read()
            frame_num += 1

            key = c.waitKey(5)
            if key == 81 or key == 113:
                close(vc, vw)
                break

# ====================
#	    Methods
# ====================

def monitorDetection(x, y, w, h):
    print(x, y, w, h)

# ====================

def getNum():
    cnt = 0
    return cnt
    # if os.path.exists('images'):
    #    BASE_DIR = os.path.dirname(os.path.abspath(__file__))
    #    image_dir = os.path.join(BASE_DIR, "images")
    #    for files in os.walk(image_dir):
    #        for file in files:
    #            for elem in file:
    #                if elem.endswith("png") or elem.endswith("jpg"):
    #                    cnt += 1
    #    cnt += 1
    #    return cnt
    # else:
    #    return cnt


# ====================

def close(vc, vw):
    vc.release()
    vw.release()
    c.destroyAllWindows()


# ====================

def extractFace(num, roi):
    try:
        img = str(num) + ".jpg"
        c.imwrite("images/" + img, roi)  # save image
        read_image = c.imread("images/" + img, c.IMREAD_GRAYSCALE)  # read image
        resized_image = c.resize(read_image, (224, 224))  # resize the image to 224x224
        c.imwrite("images/" + img, resized_image)  # save image again
    except Exception as e:
        print(str(e))


# ====================

def imageToBase64():
    if os.path.exists('images'):
        BASE_DIR = os.path.dirname(os.path.abspath(__file__))
        image_dir = os.path.join(BASE_DIR, "images")
        for files in os.walk(image_dir):
            for file in files:
                for elem in file:
                    if elem.endswith("png") or elem.endswith("jpg"):
                        print(elem)
                    # with open(elem, "rb") as imageFile:
                    #    encoded_string = base64.b64encode(imageFile.read())
                    #    print(encoded_string)


# ====================

cameraFeed()
