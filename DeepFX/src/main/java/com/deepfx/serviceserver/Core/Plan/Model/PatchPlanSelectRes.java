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
    @ApiModelProperty(name = "planIdx", example = "2", required = true)
    private int planIdx;
    @ApiModelProperty(name = "planName", example = "Individual Plan", required = true)
    private String planName;
    @ApiModelProperty(name = "planPrice", example = "9.99", required = true)
    private float planPrice;
    @ApiModelProperty(name = "planDesc", example = "String Array", required = true)
    private String[] planDesc;
    @ApiModelProperty(name = "planEnd", example = "2023-02-28", required = true)
    private String planEnd;
}
