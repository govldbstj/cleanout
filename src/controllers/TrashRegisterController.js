import Result, { invalidateErrorCode } from '../data/Result';
import * as Api from '../data/Api';

/**
 * 쓰레기 이미지 업로드
 * @param {string[]} images 이미지 uri 리스트
 * @returns {Promise<Result>} 쓰레기 이미지 업로드 결과
 */
export async function uploadWasteImages(images) {
    if (images.length === 0) {
        return Result.failure(invalidateErrorCode, '이미지를 등록해주세요.');
    }

    let promises = [];
    for (const image of images) {
        let imageBody = new FormData();

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

        promises.push(
            Api.postImage('waste-management/image', {
                body: imageBody,
            })
        );
    }

    const results = await Promise.all(promises);
    return results.reduce((acc, cur) => {
        if (acc.isFailure() || cur.isFailure()) {
            return Result.failure(cur.errorCode, cur.errorMessage);
        }
        return cur;
    }, Result.success(null));
}
