package com.deepfx.serviceserver.Core.User.Model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoRes {
    @ApiModelProperty(name = "userIdx", example = "3", required = true)
    private int userIdx;
    @ApiModelProperty(name = "email", example = "example@exxample.com", required = true)
    private String email;
    @ApiModelProperty(name = "planName", example = "planName", required = true)
    private String planName;
    @ApiModelProperty(name = "userName", example = "Jack", required = true)
    private String userName;
    @ApiModelProperty(name = "groupName", example = "PeopleSpace", required = true)
    private String groupName;
}
