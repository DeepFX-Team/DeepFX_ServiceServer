package com.deepfx.serviceserver.Core.Plan;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.Plan.Model.PatchPlanSelectRes;
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

    /**
     * 플랜 선택 API - Service
     * */
    public PatchPlanSelectRes selectPlan(int planIdx, int userIdx) throws BaseException {
        try{
            return planDao.selectPlan(planIdx, userIdx);
        }catch (Exception exception) {
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }
}
