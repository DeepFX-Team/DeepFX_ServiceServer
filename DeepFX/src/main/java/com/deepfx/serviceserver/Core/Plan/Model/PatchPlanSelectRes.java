package com.deepfx.serviceserver.Core.Plan.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatchPlanSelectRes {
    @ApiModelProperty(name = "selectSuccess", example = "true", required = true)
    private boolean selectSuccess;
}
