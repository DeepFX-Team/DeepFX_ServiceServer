package com.deepfx.serviceserver.Core.User;

import com.deepfx.serviceserver.Core.User.Model.GetUserHistoryRes;
import com.deepfx.serviceserver.Core.User.Model.GetUserInfoRes;
import com.deepfx.serviceserver.Core.User.Model.PatchUserInfoReq;
import com.deepfx.serviceserver.Core.User.Model.PatchUserInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource);}

    /**
     * 유저 정보 조회 API - Dao
     * */
    public GetUserInfoRes getUserInfo(int userIdx) {
        String queryString =
                "select userIdx, email, planName, name, `group`\n" +
                "from User left join Plan P on User.planIdx = P.planIdx\n" +
                "where userIdx = ?;";

        return this.jdbcTemplate.queryForObject(queryString, (rs, rowNum)
                -> new GetUserInfoRes(
                        rs.getInt("userIdx"),
                        rs.getString("email"),
                        rs.getString("planName"),
                        rs.getString("name"),
                        rs.getString("group")
                ), userIdx);
    }

    /**
     * 유저 히스토리 조회 API - Dao
     * */
    public List<GetUserHistoryRes> getUserHistory(int userIdx) {
        String queryString =
                "select S.soundIdx, soundName, soundURL\n" +
                "from Sound S left join History H on S.soundIdx = H.soundIdx\n" +
                "where userIdx = ? and H.status = 'ACTIVE' and S.status = 'ACTIVE'\n" +
                "order by H.createdAt;";

        return this.jdbcTemplate.query(queryString, (rs, rowNum) ->
                new GetUserHistoryRes(
                      rs.getInt("soundIdx"),
                      rs.getString("soundName"),
                      rs.getString("soundURL")
                ), userIdx);
    }

    /**
     * 유저 정보 수정 API - Dao
     * */
    public PatchUserInfoRes patchUserInfo(int userIdx, PatchUserInfoReq patchUserInfoReq) {
        String queryString = "update User set email = ?, `group` = ? where userIdx = ?;";
        Object[] queryParams = new Object[] {
                patchUserInfoReq.getEmail(),
                patchUserInfoReq.getGroup(),
                userIdx
        };

        return new PatchUserInfoRes(
                this.jdbcTemplate.update(queryString, queryParams) > 0
        );
    }
}
