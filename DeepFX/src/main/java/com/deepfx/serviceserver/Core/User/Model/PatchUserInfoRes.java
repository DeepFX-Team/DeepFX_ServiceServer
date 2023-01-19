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
public class PatchUserInfoRes {
    @ApiModelProperty(name = "updateSuccess", example = "true", required = true)
    private boolean updateSuccess;
}
