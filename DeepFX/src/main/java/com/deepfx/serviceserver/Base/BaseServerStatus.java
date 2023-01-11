package com.deepfx.serviceserver.Base;

import lombok.Getter;

@Getter
public enum BaseServerStatus {


    /**
     * 1000 : 성공
     * */
    SUCCESS(true, 1000, "Request Success"),

    /**
     * 9000: Jwt
     * */
    JWT_NOT_EXIST(true, 9001, "Jwt Token Not Exist"),
    INVALID_SIGNATURE(true, 9002, "Invalid Signature"),
    INVALID_JWT(true, 9003, "Invalid Jwt Token"),
    EXPIRED_TOKEN(true, 9004, "Jwt Token Expired"),
    UNSUPPORTED_JWT(true, 9005, "Not Our Token");

    private final boolean isSuccess;
    private final int returnCode;
    private final String returnMessage;

    BaseServerStatus(boolean isSuccess, int returnCode, String returnMessage){
        this.isSuccess = isSuccess;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }
}
