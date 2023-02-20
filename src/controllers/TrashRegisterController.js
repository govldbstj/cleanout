import Result, { invalidateErrorCode } from '../data/Result';
import * as Api from '../data/Api';

/**
 * 쓰레기 이미지 업로드
 * @param {string[]} images 이미지 uri 리스트
 * @returns {Promise<Result>} 쓰레기 이미지 업로드 결과
 */
export async function uploadWasteImage(images) {
    if (images.length === 0) {
        return Result.failure(invalidateErrorCode, '이미지를 등록해주세요.');
    }

    let imageBody = new FormData();

    for (const image of images) {
        const imageName = image.split('/').pop();
        const match = /\.(\w+)$/.exec(imageName ?? '');
        const imageType = match ? `image/${match[1]}` : `image`;

        imageBody.append(
            'image',
            {
                uri: image,
                name: 'image',
                type: imageType,
                filename: imageName,
            },
            imageName
        );
    }

    return await Api.postImage('waste-management/image', {
        body: imageBody,
    });
}
