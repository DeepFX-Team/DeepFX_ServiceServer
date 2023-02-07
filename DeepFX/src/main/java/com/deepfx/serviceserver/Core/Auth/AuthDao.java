package com.deepfx.serviceserver.Core.Auth;

import com.deepfx.serviceserver.Core.Auth.Model.PostSignInReq;
import com.deepfx.serviceserver.Core.Auth.Model.PostSignInRes;
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

    /**
     * 회원가입 API - Dao
     * */
    public PostSignupRes userSignUp(PostSignupReq postSignupReq) {
        String queryString = "insert into User (userId,password, name, `group`, email) values(?, ?, ?, ?, ?);";
        String planInsertString = "insert into PlanMatching (userIdx, planIdx, planEnd) values (last_insert_id(), 0, now());";
        Object[] queryParams = new Object[] {
                postSignupReq.getId(),
                postSignupReq.getPassword(),
                postSignupReq.getName(),
                postSignupReq.getGroup(),
                postSignupReq.getEmail()
        };


        this.jdbcTemplate.update(queryString, queryParams);
        this.jdbcTemplate.update(planInsertString);

        return new PostSignupRes(true);
    }

    /**
     * 로그인 API - Dao 로그인 처리
     * */
    public boolean userSignIn(PostSignInReq postSignInReq) {
        String queryString = "select count(userIdx) from User where userId = ? and password = ?";
        Object[] queryParams = new Object[] {
                postSignInReq.getId(),
                postSignInReq.getPassword()
        };

        return this.jdbcTemplate.queryForObject(queryString, int.class, queryParams) > 0;
    }

    /**
     * 로그인 API - Dao 토큰 발급 userIdx 처리
     * */
    public int getUserIdx(PostSignInReq postSignInReq) {
        String queryString = "select userIdx from User where userId = ? and password = ?";
        Object[] queryParams = new Object[] {
                postSignInReq.getId(),
                postSignInReq.getPassword()
        };

        return this.jdbcTemplate.queryForObject(queryString, int.class ,queryParams);
    }

    /**
     * 로그인 API - Dao 리프래시 토큰 업데이트
     * */
    public void updateRefreshToken(int userIdx, String refreshToken) {
        String queryString = "update User set refreshToken = ? where userIdx = ?";

        this.jdbcTemplate.update(queryString, refreshToken, userIdx);
    }

    public boolean checkRefreshToken(int userIdx, String refreshToken) {
        String queryString = "select count(userIdx) from User where userIdx = ? and refreshToken = ?";
        Object[] queryParams = new Object[] {
                userIdx,
                refreshToken
        };

        return this.jdbcTemplate.queryForObject(queryString, int.class, queryParams) > 0;
    }
}
