import unittest

from Cam_PI import save_face
from Cam_PI import load_faces

class MyTestCase(unittest.TestCase):

    def test_save_face(self):
        self.assertEqual(save_face('models/Jack.npy','data/Jack.jpg'), True)

if __name__ == '__main__':
    unittest.main()
