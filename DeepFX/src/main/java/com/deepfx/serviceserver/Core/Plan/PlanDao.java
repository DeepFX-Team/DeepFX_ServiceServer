package com.deepfx.serviceserver.Core.Plan;

import com.deepfx.serviceserver.Core.Plan.Model.GetPlanList;
import com.deepfx.serviceserver.Core.Plan.Model.PatchPlanSelectRes;
import com.deepfx.serviceserver.Core.Plan.Model.PlanDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PlanDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource);}

    /**
     * 플랜 리스트 조회 API - Dao
     * */
    public List<GetPlanList> getPlanList() {
        String queryString = "select P.planIdx, P.planName, P.planPrice, P.planDesc from Plan P;";

        return this.jdbcTemplate.query(queryString, (rs, rowNum) ->
                new GetPlanList(
                        rs.getInt("planIdx"),
                        rs.getString("planName"),
                        rs.getFloat("planPrice"),
                        rs.getString("planDesc").split(",")
                ));

    }

    /**
     * 플랜 선택 API - Dao
     * */
    public PatchPlanSelectRes selectPlan(int planIdx, int userIdx) {

        String checkExistQuery = "select count(*) from PlanMatching where userIdx = ?";
        String insertQuery = "insert into PlanMatching (planIdx, userIdx, planEnd) values (?, ?, date_add(now(), interval 1 month));";
        String updateQuery = "update PlanMatching set planIdx = ?, planEnd = date_add(now(), interval 1 month) where userIdx = ?;";
        String getPlanDetailQuery =
                "select P.planName, P.planPrice, P.planDesc, PM.planEnd " +
                        "from Plan P left join PlanMatching PM on P.planIdx = PM.planIdx where P.planIdx = ? and userIdx = ?";


        boolean flag = this.jdbcTemplate.queryForObject(checkExistQuery, int.class, userIdx) > 0;

        Object[] queryParams = new Object[] {
                planIdx,
                userIdx
        };

        //데이터가 있을때 - update
        if(flag) this.jdbcTemplate.update(updateQuery, queryParams);
        //데이터가 없을때 - insert
        else this.jdbcTemplate.update(insertQuery, queryParams);

        PlanDetail planDetail = this.jdbcTemplate.queryForObject(getPlanDetailQuery, (rs, rowNum) ->
                new PlanDetail(
                        rs.getString("planName"),
                        rs.getFloat("planPrice"),
                        rs.getString("planEnd"),
                        rs.getString("planDesc").split(",")
                ), queryParams);

        return new PatchPlanSelectRes(
                true,
                planIdx,
                planDetail.getPlanName(),
                planDetail.getPlanPrice(),
                planDetail.getPlanDesc(),
                planDetail.getPlanEnd()
        );
    }
}
