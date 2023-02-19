/**
 * @class Result API 전송의 결과를 담는 클래스
 * @param {any} value API 전송의 결과 값
 * @param {number} errorCode API 전송의 에러 코드
 * @param {string} errorMessage API 전송의 에러 메시지
 */
class Result {
    /**
     * @constructor Result 생성자
     * @param {any} value
     * @param {number} errorCode
     * @param {string} errorMessage
     */
    constructor(value, errorCode, errorMessage) {
        this.value = value;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * API 전송 성공
     * @param {any} value API 값
     * @returns {Result}
     */
    static success(value) {
        return new Result(value, undefined, undefined);
    }

    /**
     * API 전송 실패
     * @param {number} errorCode 에러 코드
     * @param {string} errorMessage 에러 메시지
     * @returns {Result}
     */
    static failure(errorCode, errorMessage) {
        return new Result(undefined, errorCode, errorMessage);
    }

    /**
     * API 전송이 성공했는지 확인
     * @returns {boolean} 성공 여부
     */
    isSuccess() {
        return this.errorCode === undefined;
    }

    /**
     * API 전송이 실패했는지 확인
     * @returns {boolean} 실패 여부
     *
     */
    isFailure() {
        return this.errorCode !== undefined;
    }

    /**
     * API 전송 결과 가져오기
     * @returns {any | undefined} API 전송 결과 (전송 실패 시 undefined)
     */
    tryGetValue() {
        if (this.isSuccess()) {
            return this.value;
        }
        return undefined;
    }

    /**
     * API 전송 에러 코드 가져오기
     * @returns {number | undefined} API 전송 에러 코드 (전송 성공 시 undefined)
     */
    tryGetErrorCode() {
        if (this.isFailure()) {
            return this.errorCode;
        }
        return undefined;
    }

    /**
     * API 전송 에러 메시지 가져오기
     * @returns {string | undefined} API 전송 에러 메시지 (전송 성공 시 undefined)
     */
    tryGetErrorMessage() {
        if (this.isFailure()) {
            return this.errorMessage;
        }
        return undefined;
    }
}

export default Result;
