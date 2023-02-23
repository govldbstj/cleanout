import Result, { invalidateErrorCode } from '../data/Result';
import * as Api from '../data/Api';

/**
 * 이메일 로그인
 * @param {string} email 이메일
 * @param {string} password 비밀번호
 * @returns {Promise<Result>} 로그인 결과
 */
export async function emailSignIn(email, password) {
    if (email.length === 0) {
        return Result.failure(invalidateErrorCode, '이메일을 입력해주세요.');
    }
    if (password.length < 8) {
        return Result.failure(invalidateErrorCode, '비밀번호를 8자 이상 입력해주세요.');
    }

    return await Api.post('login', {
        body: {
            email: email,
            password: password,
        },
    });
}

/**
 * 이메일 회원가입
 * @param {string} name 이름
 * @param {string} email 이메일
 * @param {string} address 주소
 * @param {string} info 상세 주소
 * @param {string} password 비밀번호
 * @param {string} passwordRemind 비밀번호 확인
 * @returns {Promise<Result>} 회원가입 결과
 */
export async function emailSignUp(name, email, address, info, password, passwordRemind) {
    if (name.length === 0) {
        return Result.failure(invalidateErrorCode, '이름을 입력해주세요.');
    }
    if (email.length === 0) {
        return Result.failure(invalidateErrorCode, '이메일을 입력해주세요.');
    }
    if (address.length === 0) {
        return Result.failure(invalidateErrorCode, '주소를 입력해주세요.');
    }
    if (password.length < 8) {
        return Result.failure(invalidateErrorCode, '비밀번호를 8자 이상 입력해주세요.');
    }
    if (password !== passwordRemind) {
        return Result.failure(invalidateErrorCode, '비밀번호가 일치하지 않습니다.');
    }

    return await Api.post('signup', {
        body: {
            nickname: name,
            email: email,
            password: password,
            address: `${address} ${info}`,
        },
    });
}

/**
 * 로그아웃
 * @returns {Promise<Result>} 로그아웃 결과
 */
export async function signOut() {
    return await Api.post('logout', {});
}

/**
 * 회원 정보 조회
 * @returns {Promise<Result>} 회원 정보 조회 결과
 */
export async function getUserInfo() {
    const result = await Api.get('member', {});
    
    if (result.isSuccess) {
        const data = result.tryGetValue();
        return Result.success({
            name: data.nickname,
            address: data.address,
        });
    } else {
        return result;
    }
}
