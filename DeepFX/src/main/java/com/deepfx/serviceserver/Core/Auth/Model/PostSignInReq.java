package com.deepfx.serviceserver.Core.Auth.Model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostSignInReq {
    @ApiModelProperty(name = "id", example = "DeepFXTeam", required = true)
    private String id;
    @ApiModelProperty(name = "password", example = "123456", required = true)
    private String password;
}
