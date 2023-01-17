package com.deepfx.serviceserver.Core.Auth.Model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostSignupReq {
    @ApiModelProperty(name = "id",  example = "DeepFXTeam")
    private String id;

    @ApiModelProperty(name = "password",  example = "123456")
    private String password;

    @ApiModelProperty(name = "name",  example = "Jack")
    private String name;

    @ApiModelProperty(name = "group",  example = "PeopleSpace")
    private String group;

    @ApiModelProperty(name = "email",  example = "example@example.com")
    private String email;
}
