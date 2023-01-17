package com.deepfx.serviceserver.Core.Auth.Model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostSignupReq {
    @ApiModelProperty(name = "id",  example = "DeepFXTeam")
    @ApiParam(value = "사용자 ID", required = true)
    private String id;

    @ApiModelProperty(name = "password",  example = "123456")
    @ApiParam(value = "비밀번호", required = true)
    private String password;

    @ApiModelProperty(name = "name",  example = "Jack")
    @ApiParam(value = "사용자 이름", required = true, example = "Jack")
    private String name;

    @ApiModelProperty(name = "group",  example = "PeopleSpace")
    @ApiParam(value = "사용자 소속단체", required = true, example = "PeopleSpace")
    private String group;

    @ApiModelProperty(name = "email",  example = "example@example.com")
    @ApiParam(value = "사용자 이메일", required = true, example = "example@example.com")
    private String email;
}
