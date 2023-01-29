package com.deepfx.serviceserver.Base;

import lombok.Getter;

@Getter
public enum BaseServerStatus {


    /**
     * 1000 : 성공
     * */
    SUCCESS(true, 1000, "Request Success"),

    /**
     * 3000: Database
     * */
    DATABASE_ERROR(false, 3000, "Database Error"),

    /**
     * 4000: Auth
     **/
    NOT_A_EMAIL(false, 4000, "Check Email Pattern"),
    FAIL_TO_ENCRYPT(false, 4001, "암호화에 실패하였습니다."),
    FAIL_TO_LOGIN(false, 4002, "아이디 비밀번호를 확인해 주세요"),

    /**
     * 5000: User
     * */

    /**
     * 6000: Plan
     * */

    /**
     * 7000: Sound
     * */
    FILE_UPLOAD_FAIL(false, 7000, "파일 업로드에 실패하였습니다."),
    HISTORY_NOT_EXIST(false, 7001, "이미 삭제된 히스토리 입니다."),
    /**
     * 9000: Jwt
     * */
    JWT_NOT_EXIST(false, 9001, "Jwt Token Not Exist"),
    INVALID_SIGNATURE(false, 9002, "Invalid Signature"),
    INVALID_JWT(false, 9003, "Invalid Jwt Token"),
    EXPIRED_TOKEN(false, 9004, "Jwt Token Expired"),
    UNSUPPORTED_JWT(false, 9005, "Not Our Token"),
    INVALID_REFRESH_TOKEN(false, 9006, "리프래시 토큰이 유효하지 않습니다 다시 로그인 해주세요");

    private final boolean isSuccess;
    private final int returnCode;
    private final String returnMessage;

    BaseServerStatus(boolean isSuccess, int returnCode, String returnMessage){
        this.isSuccess = isSuccess;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }
}
