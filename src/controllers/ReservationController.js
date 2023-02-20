import Result from '../data/Result';
import * as Api from '../data/Api';

/**
 * 예약 리스트 조회
 * @returns {Promise<Result>} 예약 리스트 조회 결과
 */
export async function getReservations() {
    return await Api.get('waste-management/waste', {});
}

/**
 * 예약 내역 상세 조회
 * @param {number} id 예약 내역 ID
 * @returns {Promise<Result>} 예약 내역 상세 조회 결과
 */
export async function getReservation(id) {
    const result = await Api.get(`waste-management/waste/${id}`, {});

    if (result.isSuccess()) {
        const data = result.tryGetValue();
        // const imageType = data.imageType.toLowerCase();

        return Result.success({
            name: data.memberName,
            address: data.address,
            wasteName: data.wasteName,
            price: data.price,
            status: data.status,
            // image: `data:image/${imageType};base64,${data.images}`,
        });
    } else {
        return result;
    }
}
