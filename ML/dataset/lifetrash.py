from torchvision.datasets import ImageFolder
from torch.utils.data import DataLoader
from .augment import *

def get_trash(args):
    dataset = ImageFolder(root="dataset/data/trash/training", transform = get_transform(args))
    lifetrash_dl = DataLoader(dataset, batch_size = args.batchsize, shuffle = True, pin_memory = True, num_workers = 4)

    return lifetrash_dl