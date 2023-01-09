package com.deepfx.serviceserver.Base;

import lombok.Getter;

@Getter
public enum BaseServerStatus {


    /**
     * 200 : 성공
     * */
    SUCCESS(true, 200, "Request Success");

    private final boolean isSuccess;
    private final int returnCode;
    private final String returnMessage;

    BaseServerStatus(boolean isSuccess, int returnCode, String returnMessage){
        this.isSuccess = isSuccess;
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }
}
