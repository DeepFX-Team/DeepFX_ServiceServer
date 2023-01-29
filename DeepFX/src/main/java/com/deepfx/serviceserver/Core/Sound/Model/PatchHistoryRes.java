package com.deepfx.serviceserver.Core.Sound.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchHistoryRes {
    @ApiModelProperty(name = "patchSuccess", example = "true", required = true)
    private boolean patchSuccess;
}
