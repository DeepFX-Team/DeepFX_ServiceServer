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
public class PostJwtRefreshRes {
    @ApiModelProperty(name = "userIdx", example = "3", required = true)
    private int userIdx;
    @ApiModelProperty(name = "jwtToken", example = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjozLCJpYXQiOjE2NzM5MzQwMDAsImV4cCI6NjAyNjE2MjQwMTk2NTYwMH0.MH96HLpG-KK8gXDOg6KvsNIWD8Lop9YTZg-cLbQ4DJY", required = true)
    private String jwtToken;
    @ApiModelProperty(name = "refreshToken", example = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzM5MzQwMDAsImV4cCI6LTI5NDYwNTQ5NDEwMzMyMTJ9.jlFm-ZhXxIfw1gYsMIuIVayNJ7FsqUcgpWp3yKXmdjw", required = true)
    private String refreshToken;
}
