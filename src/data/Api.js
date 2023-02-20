import Result from './Result';

const baseUrl = 'http://43.200.115.73:8080/';
const baseHeaders = {
    'Content-Type': 'application/json',
};
const baseImageHeaders = {
    'Content-Type': 'multipart/form-data',
};

/**
 * GET 요청
 * @param {string} path 요청 경로 (URL 뒷부분)
 * @param {object?} headers 요청 헤더 (Content-Type 등은 자동으로 설정됨)
 * @returns {Promise<Result>} 요청 결과
 */
export async function get(path, { headers = {} }) {
    const result = await fetch(baseUrl + path, {
        method: 'GET',
        headers: {
            ...baseHeaders,
            ...headers,
        },
    });

    if (result.ok) {
        try {
            const data = await result.json();
            console.log('GET', path, 'SUCCESS:', data);
            return Result.success(data);
        } catch (e) {
            console.log('GET', path, 'SUCCESS');
            return Result.success(null);
        }
    } else {
        console.log('GET', path, 'FAILED:', result.status, result.statusText);
        return Result.failure(result.status, result.statusText);
    }
}

/**
 * POST 요청
 * @param {string} path 요청 경로 (URL 뒷부분)
 * @param {object?} headers 요청 헤더 (Content-Type 등은 자동으로 설정됨)
 * @param {object?} body 요청 바디 (JSON.stringify는 내부에서 수행됨)
 * @returns {Promise<Result>} 요청 결과
 */
export async function post(path, { headers = {}, body = {} }) {
    const result = await fetch(baseUrl + path, {
        method: 'POST',
        headers: {
            ...baseHeaders,
            ...headers,
        },
        body: JSON.stringify(body),
    });

    if (result.ok) {
        try {
            const data = await result.json();
            console.log('POST', path, 'SUCCESS:', data);
            return Result.success(data);
        } catch (e) {
            console.log('POST', path, 'SUCCESS');
            return Result.success(null);
        }
    } else {
        console.log('POST', path, 'FAILED:', result.status, result.statusText);
        return Result.failure(result.status, result.statusText);
    }
}

/**
 * 이미지 POST 요청
 * @param {string} path 요청 경로 (URL 뒷부분)
 * @param {object?} headers 요청 헤더 (Content-Type 등은 자동으로 설정됨)
 * @param {FormData?} body 요청 바디
 * @returns {Promise<Result>} 요청 결과
 */
export async function postImage(path, { headers = {}, body = {} }) {
    const result = await fetch(baseUrl + path, {
        method: 'POST',
        headers: {
            ...baseImageHeaders,
            ...headers,
        },
        body: body,
    });

    if (result.ok) {
        try {
            const data = await result.json();
            console.log('POST', path, 'SUCCESS:', data);
            return Result.success(data);
        } catch (e) {
            console.log('POST', path, 'SUCCESS');
            return Result.success(null);
        }
    } else {
        console.log('POST', path, 'FAILED:', result.status, result.statusText);
        return Result.failure(result.status, result.statusText);
    }
}
