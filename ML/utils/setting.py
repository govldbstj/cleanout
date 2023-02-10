import os
import numpy as np
import random
import torch
import torchvision
from tqdm import tqdm
import torch.nn.functional as F

def seed_everything(seed):
    random.seed(seed)
    os.environ['PYTHONHASHSEED'] = str(seed)
    np.random.seed(seed)
    torch.manual_seed(seed)
    torch.cuda.manual_seed(seed)
    torch.backends.cudnn.deterministic = True
    torch.backends.cudnn.benchmark = True
    torch.backends.cudnn.enabled = False

class Fitter:
    def __init__(self, args, model, optimizer, loss_fn):
        self.args = args
        self.model = model.to(args.device)
        self.optimizer = optimizer
        self.loss_fn = loss_fn
        # self.scaler = torch.cuda.amp.GradScaler(enabled=True)

        self.loss = 1e10

    def fit(self, train_dl):
        self.model.train()
        for _ in tqdm(range(self.args.epochs), desc = "train", position = 0, leave = False):
            for image, label in tqdm(train_dl, desc = "dataloader", position = 0, leave = False):
                image, label = image.to(self.args.device), label.to(self.args.device)
                self.optimizer.zero_grad()
                # with torch.cuda.amp.autocast(enabled=True):
                cl = self.model(image)
                loss = self.loss_fn(cl, label)
                loss.backward()
                self.optimizer.step()

                if self.loss > loss:
                    self.loss = loss
                    torch.save(self.model.state_dict(), "checkpoint/model.pt")
        print("done..!")