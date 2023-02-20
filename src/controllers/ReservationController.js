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
    return await Api.get(`waste-management/waste/${id}`, {});
}