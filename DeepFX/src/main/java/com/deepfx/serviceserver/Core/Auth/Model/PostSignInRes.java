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
public class PostSignInRes {
    @ApiModelProperty(name = "jwtToken", example = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjozLCJpYXQiOjE2NzM5MzI3OTIsImV4cCI6NjAyNjE1ODA1MjU0MjgwMH0.wN0PTiTZhgsOQbav3B1bbb1wuu0My4H4lB7B_CIOc2s", required = true)
    private String jwtToken;
    @ApiModelProperty(name = "refreshToken", example = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzM5MzI3OTIsImV4cCI6LTI5NDYxNTkzMjQ3NjEyMTJ9.bm13QexX3BLrVaTbpDMLrN4QAURQWWzqHRO8xoCP6zg", required = true)
    private String refreshToken;
}
