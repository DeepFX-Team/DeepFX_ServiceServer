package com.deepfx.serviceserver.Core.Plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PlanDao planDao;

    public PlanService(PlanDao planDao) {
        this.planDao = planDao;
    }

}
