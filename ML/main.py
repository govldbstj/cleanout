from __future__ import absolute_import, division
import os
import logging
import argparse
import torch
import torch.nn as nn
import torch.optim as optim

from utils import *
from trainer import *
from dataset import *
from models import *
import warnings
warnings.filterwarnings("ignore")

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--seed", type = int, required = True)         
    # parser.add_argument("--dataset", type = str, required = True)
    parser.add_argument("--imgsize", type = int, default = 512, required = False)
    # parser.add_argument("--model", type = str, required = True)
    parser.add_argument("--epochs", type = int, default = 50, required = False)
    parser.add_argument("--batchsize", type = int, default = 32, required = False)
    parser.add_argument("--lr", type = float, default = 1e-2, required = False)
    parser.add_argument("--momentum", type = float, default = 0.9, required = False)
    parser.add_argument("--weight_decay", type = float, default = 5e-4, required = False)
    parser.add_argument("--gpu_ids", type = str, required = True)
    parser.add_argument("--num_classes", type = int, default = 14, required = False)

    # python main.py --seed 0 --gpu_ids 2
    args = parser.parse_args()

    os.environ["CUDA_DEVICE_ORDER"] = "PCI_BUS_ID"
    os.environ["CUDA_VISIBLE_DEVICES"] = str(args.gpu_ids)
    args.device = torch.device("cuda") if torch.cuda.is_available() else torch.device("cpu")
    seed_everything(args.seed)

    train_dl = get_loader(args)
    model = load_model(args)
    loss_fn = nn.CrossEntropyLoss().to(args.device)
    optimizer = optim.SGD(params = model.parameters(), lr = args.lr, momentum = args.momentum, weight_decay = args.weight_decay)
    Container = Fitter(args, model, optimizer, loss_fn)
    Container.fit(train_dl)