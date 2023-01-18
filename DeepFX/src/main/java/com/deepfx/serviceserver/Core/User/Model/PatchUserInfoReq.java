package com.deepfx.serviceserver.Core.User.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchUserInfoReq {
    @ApiModelProperty(name = "email", example = "example@example.com", required = true)
    private String email;
    @ApiModelProperty(name = "group", example = "PeopleSpace", required = true)
    private String group;
}
