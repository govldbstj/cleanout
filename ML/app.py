from __future__ import absolute_import, division
from flask import Flask, request
# from flask_ngrok import run_with_ngrok
import os
import torch
import torch.nn as nn
import numpy as np
from PIL import Image
from io import BytesIO
import requests
import torchvision.transforms as transforms
import json
import base64
from models import *
import warnings

warnings.filterwarnings("ignore")


def get_transform_2():
    return transforms.Compose([
            transforms.Resize((512, 512)),
            transforms.ToTensor(),
            transforms.Normalize( mean=[0.485, 0.456, 0.406],\
                                std=[0.229, 0.224, 0.225]),
            ])

def make_json(pred):
    # {'소파': 0, '의자': 1, '책상': 2}
    print(pred)
    if int(pred) == 0:
        json_string = {
            "wasteName" : "소파",
            "price" : "10000"
        }

    elif int(pred) == 1:
        json_string = {
            "wasteName" : "의자",
            "price" : "3000"
        }
    else:
        json_string = {
            "wasteName" : "책상",
            "price" : "5000"
        }
    
    json_object = json.dumps(json_string)
    return json_object

def pred_image(img):
    model = infer_model()
    model.eval()
    model.load_state_dict(torch.load("checkpoint/model.pt", map_location="cpu"))        
    transform = get_transform_2()
    temp_img = transform(img)
    temp_img = temp_img.unsqueeze(0)
    a = model(temp_img)
    w = torch.argmax(a)
    json_object = make_json(w)

    return json_object

app = Flask(__name__)
# run_with_ngrok(app)

@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"

@app.route("/submit", methods = ['GET', 'POST'],)
def get_output():
    if request.method == "POST":
        params = request.get_json()
        img = Image.open(BytesIO(base64.b64decode(params["value"][0])))
        # img.save(str(a) + "temp.jpg")
        print(img)
        
        json_object = pred_image(img)
        # url = "http://43.200.115.73:8080"
        # headers = {
        #     "Content-Type": "application/json"
        # }
        # # response = requests.post(url, headers=headers, data=json_object)

        # # print(response)
        return json_object

if __name__ == "__main__":
    app.run(host = "0.0.0.0", port = "8080", debug = True)
    # ngrok http 8080
    # dataset/data/upholstery/소파/11_X001_C039_1102_0.jpg
    # dataset/data/upholstery/의자/11_X001_C013_1030_2.jpg
    # dataset/data/upholstery/책상/11_X001_C033_1014_1.jpg
