package com.deepfx.serviceserver.Core.Plan;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.Plan.Model.GetPlanList;
import com.deepfx.serviceserver.Core.User.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanProvider {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PlanDao planDao;

    public PlanProvider(PlanDao planDao) {
        this.planDao = planDao;
    }

    /**
     * 플랜 리스트 조회 API - Provider
     * */
    public List<GetPlanList> getPlanList() throws BaseException {
        try{
            return planDao.getPlanList();
        }catch (Exception exception) {
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }
}
