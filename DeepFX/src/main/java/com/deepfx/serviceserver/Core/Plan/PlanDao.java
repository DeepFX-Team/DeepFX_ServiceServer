package com.deepfx.serviceserver.Core.Plan;

import com.deepfx.serviceserver.Core.Plan.Model.GetPlanList;
import com.deepfx.serviceserver.Core.Plan.Model.PatchPlanSelectRes;
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
        String queryString = "select planIdx, planName, planPrice, planDesc from Plan";

        return this.jdbcTemplate.query(queryString, (rs, rowNum) ->
                new GetPlanList(
                        rs.getInt("planIdx"),
                        rs.getString("planName"),
                        rs.getFloat("planPrice"),
                        rs.getString("planDesc")
                ));

    }

    /**
     * 플랜 선택 API - Dao
     * */
    public PatchPlanSelectRes selectPlan(int planIdx, int userIdx) {
        String queryString = "update User set planIdx = ? where userIdx = ?";
        Object[] queryParams = new Object[] {
                planIdx,
                userIdx
        };

        return new PatchPlanSelectRes(
                this.jdbcTemplate.update(queryString, queryParams) > 0
        );
    }
}
