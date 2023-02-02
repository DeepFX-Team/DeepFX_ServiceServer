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
public class GetPlanList {
    @ApiModelProperty(name = "planIdx", example = "1", required = true)
    private int planIdx;
    @ApiModelProperty(name = "planName", example = "basic", required = true)
    private String planName;
    @ApiModelProperty(name = "planPrice", example = "19.99", required = true)
    private float planPrice;
    @ApiModelProperty(name = "planDesc", example = "Test", required = true)
    private String[] planDesc;
}
