import torch.nn as nn
from torchvision import models

def load_model(args, pretrained = True, require_grad = True):
    model = models.mobilenet.mobilenet_v2(pretrained = pretrained)
    model.classifier = nn.Sequential(
        nn.Linear(1280, args.num_classes)
    )
    return model