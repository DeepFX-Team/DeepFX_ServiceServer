package com.deepfx.serviceserver.Core.Auth.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostJwtRefreshReq {
    @ApiModelProperty(name = "userIdx", example = "3", required = true)
    private int userIdx;
    @ApiModelProperty(name = "refreshToken", example = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzM5MzI3OTIsImV4cCI6LTI5NDYxNTkzMjQ3NjEyMTJ9.bm13QexX3BLrVaTbpDMLrN4QAURQWWzqHRO8xoCP6zg", required = true)
    private String refreshToken;
}
