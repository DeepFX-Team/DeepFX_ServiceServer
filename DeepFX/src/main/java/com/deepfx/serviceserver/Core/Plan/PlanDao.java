package com.deepfx.serviceserver.Core.Plan;

import com.deepfx.serviceserver.Core.Plan.Model.GetPlanList;
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

    public List<GetPlanList> getPlanList() {
        String queryString = "select planIdx, planName, planPrice, planDesc from plan";

        return this.jdbcTemplate.query(queryString, (rs, rowNum) ->
                new GetPlanList(
                        rs.getInt("planIdx"),
                        rs.getString("planName"),
                        rs.getFloat("planPrice"),
                        rs.getString("planDesc")
                ));

    }
}
