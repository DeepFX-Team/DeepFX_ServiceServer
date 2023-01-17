package com.deepfx.serviceserver.Base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.deepfx.serviceserver.Base.BaseServerStatus.SUCCESS;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "returnCode", "returnMessage", "result"})
public class BaseResponse<T> {
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final int returnCode;
    private final String returnMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    //요청 성공
    public BaseResponse(T result) {
        this.isSuccess = SUCCESS.isSuccess();
        this.returnCode = SUCCESS.getReturnCode();
        this.returnMessage = SUCCESS.getReturnMessage();
        this.result = result;
    }

    //요청 실패
    public BaseResponse(BaseServerStatus status){
        this.isSuccess = status.isSuccess();
        this.returnCode = status.getReturnCode();
        this.returnMessage = status.getReturnMessage();
    }
}
