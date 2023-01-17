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
    /**
     * 9000: Jwt
     * */
    JWT_NOT_EXIST(false, 9001, "Jwt Token Not Exist"),
    INVALID_SIGNATURE(false, 9002, "Invalid Signature"),
    INVALID_JWT(false, 9003, "Invalid Jwt Token"),
    EXPIRED_TOKEN(false, 9004, "Jwt Token Expired"),
    UNSUPPORTED_JWT(false, 9005, "Not Our Token");

    private final boolean isSuccess;
    private final int returnCode;
    private final String returnMessage;

    BaseServerStatus(boolean isSuccess, int returnCode, String returnMessage){
        this.isSuccess = isSuccess;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }
}
