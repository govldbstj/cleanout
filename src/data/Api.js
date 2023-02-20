import Result, { networkErrorCode } from './Result';

const baseUrl = 'http://43.200.115.73:8080/';
const baseHeaders = {
    'Content-Type': 'application/json',
};
const baseImageHeaders = {
    'Content-Type': 'multipart/form-data',
};

const defaultValue = {
    headers: {},
    body: {},
};
const defaultApiValue = {
    headers: {},
    body: "",
};

/**
 * GET 요청
 * @param {string} path 요청 경로 (URL 뒷부분)
 * @param {object?} headers 요청 헤더 (Content-Type 등은 자동으로 설정됨)
 * @returns {Promise<Result>} 요청 결과
 */
export async function get(path, { headers } = defaultValue) {
    return await sendApi('GET', path, { headers: { ...baseHeaders, ...headers } });
}

/**
 * POST 요청
 * @param {string} path 요청 경로 (URL 뒷부분)
 * @param {object?} headers 요청 헤더 (Content-Type 등은 자동으로 설정됨)
 * @param {object?} body 요청 바디 (JSON.stringify 필요 없음)
 * @returns {Promise<Result>} 요청 결과
 */
export async function post(path, { headers, body } = defaultValue) {
    return await sendApi('POST', path, { headers: { ...baseHeaders, ...headers }, body: JSON.stringify(body) });
}

/**
 * 이미지 POST 요청
 * @param {string} path 요청 경로 (URL 뒷부분)
 * @param {object?} headers 요청 헤더 (Content-Type 등은 자동으로 설정됨)
 * @param {FormData?} body 요청 바디
 * @returns {Promise<Result>} 요청 결과
 */
export async function postImage(path, { headers, body } = defaultValue) {
    return await sendApi('POST', path, { headers: { ...baseImageHeaders, ...headers }, body: body });
}

/**
 * API 요청 (Method 문자열로 지정)
 * @param {string} method HTTP Method
 * @param {string} path 요청 경로 (URL 뒷부분)
 * @param {object?} headers 요청 헤더
 * @param {object?} body 요청 바디
 * @returns
 */
export async function sendApi(method, path, { headers, body } = defaultApiValue) {
    try {
        console.log(baseUrl + path, method, headers, body);
        const result = await fetch(baseUrl + path, {
            method: method,
            headers: headers,
            body: body,
        });

        if (result.ok) {
            try {
                const data = await result.json();
                console.log(method, path, 'SUCCESS:', data);
                return Result.success(data);
            } catch (e) {
                console.log(method, path, 'SUCCESS');
                return Result.success(null);
            }
        } else {
            console.log(method, path, 'FAILED:', result.status, result.statusText);
            return Result.failure(result.status, result.statusText);
        }
    } catch (e) {
        console.log(method, path, 'FAILED: 네트워크 연결이 원활하지 않습니다.');
        return Result.failure(networkErrorCode, '네트워크 연결이 원활하지 않습니다.');
    }
}
