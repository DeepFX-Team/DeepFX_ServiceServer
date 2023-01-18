package com.deepfx.serviceserver.Core.User;

import com.deepfx.serviceserver.Core.User.Model.GetUserInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource);}

    public GetUserInfoRes getUserInfo(int userIdx) {
        String queryString =
                "select userIdx, email, planName\n" +
                "from User left join Plan P on User.planIdx = P.planIdx\n" +
                "where userIdx = ?;";

        return this.jdbcTemplate.queryForObject(queryString, (rs, rowNum)
                -> new GetUserInfoRes(
                        rs.getInt("userIdx"),
                        rs.getString("email"),
                        rs.getString("planName")
                ), userIdx);
    }


}
