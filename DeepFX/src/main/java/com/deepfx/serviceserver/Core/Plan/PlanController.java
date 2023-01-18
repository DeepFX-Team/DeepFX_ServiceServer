package com.deepfx.serviceserver.Core.Plan;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseResponse;
import com.deepfx.serviceserver.Core.Plan.Model.GetPlanList;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PlanService planService;
    @Autowired
    private final PlanProvider planProvider

    public PlanController(PlanService planService, PlanProvider planProvider) {
        this.planService = planService;
        this.planProvider = planProvider;
    }

    /**
     * 플랜 리스트 조회 API -Controller
     * */
    @ApiOperation(value = "플랜 리스트 조회 API", notes = "플랜 리스트 조회 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "Request Success"),
            @ApiResponse(code = 3000, message = "Database Error")
    })
    @GetMapping("/list")
    public BaseResponse<List<GetPlanList>> getPlanList() {
        try{
            return new BaseResponse<>(planProvider.getPlanList());
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
