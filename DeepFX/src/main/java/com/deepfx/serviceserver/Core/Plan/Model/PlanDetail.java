package com.deepfx.serviceserver.Core.Plan.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanDetail {
    private String planName;
    private float planPrice;
    private String planEnd;
    private String[] planDesc;
}
