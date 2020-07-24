import cv2 as c
from mtcnn.mtcnn import MTCNN
import numpy as np
import tensorflow as tf
from scipy.spatial.distance import cosine
from keras_vggface.vggface import VGGFace
from keras_vggface.utils import preprocess_input
import os

mod_name = 'senet50'
path_features = 'models/'
threshold = 0.5
face_d = MTCNN()
face_r = VGGFace(include_top=False, model=mod_name, input_shape=(224,224,3), pooling='avg')

def load_faces(path):
    feats = list()
    for root, folder, files in os.walk(path, topdown=False):
        for file in files:
            f_feat = list()
            f_feat.append(str(os.path.splitext(file)[0]))
            f_feat.append(np.load(os.path.join(root,file)))
            feats.append(f_feat)

    return np.asarray(feats)

def save_face(path, image, f_d=face_d, f_r=face_r):
    face_image = c.imread(image)
    image_pixels = np.asarray(face_image)
    face_box = f_d.detect_faces(image_pixels)

    if len(face_box) == 0:
        print("No faces detected.")
        return False
    elif len(face_box) > 1:
        print("More than one face detected.")
        return False
    else:
        f_x1, f_y1, f_w, f_h = face_box[0]['box']
        face_pixels = image_pixels[f_y1:(f_y1+f_h),f_x1:(f_x1+f_w)]
        face_pixels = c.resize(face_pixels, (224,224), interpolation=c.INTER_AREA)
        buff = list()
        if mod_name == 'vgg16':
            face_pixels = preprocess_input(np.asarray(face_pixels).astype('float64'))
        else:
            face_pixels = preprocess_input(np.asarray(face_pixels).astype('float64'), version=2)
        buff.append(face_pixels)
        buff = np.asarray(buff)
        feat = f_r.predict(buff)
        np.save(path,feat)
        return True


vc = c.VideoCapture(0)
f = False

def classify_face(image, f_d=face_d, f_r=face_r):
    all_f_features = load_faces(path_features)
    face_image = c.imread(image)
    image_pixels = np.asarray(face_image)
    face_box = f_d.detect_faces(image_pixels)

    if len(face_box) == 0:
        print("No faces detected.")
    elif len(face_box) > 1:
        print("More than one face detected.")
    else:
        f_x1, f_y1, f_w, f_h = face_box[0]['box']
        face_pixels = image_pixels[f_y1:(f_y1+f_h),f_x1:(f_x1+f_w)]
        face_pixels = c.resize(face_pixels, (224,224), interpolation=c.INTER_AREA)
        buff = list()
        if mod_name == 'vgg16':
            face_pixels = preprocess_input(np.asarray(face_pixels).astype('float64'))
        else:
            face_pixels = preprocess_input(np.asarray(face_pixels).astype('float64'), version=2)
        buff.append(face_pixels)
        buff = np.asarray(buff)
        feat = f_r.predict(buff)
        min_match = 1.0
        f_name = 'Unknown'
        for feature in all_f_features:
            match = cosine(feat, feature[1])
            if match <= threshold:
                if match < min_match:
                    min_match = match
                    f_name = feature[0]
        return f_name



def cameraFeed():
    all_f_features = load_faces(path_features)
    if vc.isOpened():
        c.namedWindow("view")
        f, frame = vc.read()
        f_count = 0
        while f:
            pix = np.asarray(frame)
            faces = face_d.detect_faces(pix)
            face_pix = list()

            for face in faces:
                x1, y1, width, height = face['box']
                x1, y1 = abs(x1), abs(y1)
                x2, y2 = x1 + width, y1 + height
                start = (x1,y1)
                end = (x2,y2)
                face_pix.append(pix[y1:y2, x1:x2])
                frame = c.rectangle(frame,start,end,(0,0,255),1)

            count = 0
            inp_features = list()
            for face in face_pix:
                face = c.resize(face, (224,224), interpolation=c.INTER_AREA)
                if mod_name == 'vgg16':
                    inp_features.append(preprocess_input(np.asarray(face).astype('float64')))
                else:
                    inp_features.append(preprocess_input(np.asarray(face).astype('float64'), version=2))
                c.imwrite('../data/test'+str(f_count)+'_'+str(count)+'.jpg',face)
                count += 1

            inp_features = np.asarray(inp_features)

            if len(inp_features) > 0:
                features = face_r.predict(inp_features)
                for face in features:
                    min_match = 1.0
                    f_name = 'Unknown'
                    for feat in all_f_features:
                        match = cosine(face, feat[1])
                        if match <= threshold:
                            if match < min_match:
                                min_match = match
                                f_name = feat[0]

                    frame = c.putText(frame,f_name, (0,20), c.FONT_HERSHEY_DUPLEX, 1, (0,0,255), thickness=2)

            c.imshow("view",frame)
            f_count += 1
            f, frame = vc.read()
            key = c.waitKey(5)
            if key == 27:
                vc.release()
                c.destroyWindow("view")
                break

save_face('models/Jack.npy','data/Jack.jpg')

