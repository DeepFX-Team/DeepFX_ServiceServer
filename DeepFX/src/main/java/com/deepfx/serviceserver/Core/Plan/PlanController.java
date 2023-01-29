package com.deepfx.serviceserver.Core.Plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

}
