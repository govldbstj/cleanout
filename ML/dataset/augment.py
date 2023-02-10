import torchvision.transforms as transforms

def get_transform(args) -> transforms:
    return transforms.Compose([
        transforms.ToTensor(),
        transforms.Normalize( mean=[0.485, 0.456, 0.406],\
                              std=[0.229, 0.224, 0.225]),
        ])