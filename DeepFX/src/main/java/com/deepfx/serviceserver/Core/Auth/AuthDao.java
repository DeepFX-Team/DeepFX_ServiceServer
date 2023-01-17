package com.deepfx.serviceserver.Core.Auth;

import com.deepfx.serviceserver.Util.SHA256;
import com.deepfx.serviceserver.Core.Auth.Model.PostSignupReq;
import com.deepfx.serviceserver.Core.Auth.Model.PostSignupRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AuthDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource);}

    public PostSignupRes userSignUp(PostSignupReq postSignupReq) {
        String queryString = "insert into User (userId,password, name, `group`, email) values(?, ?, ?, ?, ?);";
        Object[] queryParams = new Object[] {
                postSignupReq.getId(),
                postSignupReq.getPassword(),
                postSignupReq.getName(),
                postSignupReq.getGroup(),
                postSignupReq.getEmail()
        };

        this.jdbcTemplate.update(queryString, queryParams);

        return new PostSignupRes(true);
    }
}
