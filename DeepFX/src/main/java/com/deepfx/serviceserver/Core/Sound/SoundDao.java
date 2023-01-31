package com.deepfx.serviceserver.Core.Sound;

import com.deepfx.serviceserver.Core.Sound.Model.PatchHistoryRes;
import com.deepfx.serviceserver.Core.Sound.Model.PostHistoryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SoundDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource);}

    /**
     * 히스토리 생성 API - Dao
     * */
    public PostHistoryRes saveHistory(String fileName, String fileUrl, int userIdx) {

        String insertSoundQuery = "insert into Sound(soundOwnerIdx, soundName, soundExt, soundURL) values(?, ?, ?, ?);";
        String getInsertedIdxQuery = "select last_insert_id()";
        String insertHistoryQuery = "insert into History(userIdx, soundIdx) values(?, ?)";

        Object[] insertSoundParams = new Object[] {
                userIdx,
                fileName.split("\\.")[0],
                fileName.split("\\.")[1],
                fileUrl
        };
        this.jdbcTemplate.update(insertSoundQuery,insertSoundParams);

        int insertedId = this.jdbcTemplate.queryForObject(getInsertedIdxQuery, int.class);

        Object[] insertHistoryParams =  new Object[] {
                userIdx,
                insertedId
        };
        boolean isSuccess = this.jdbcTemplate.update(insertHistoryQuery, insertHistoryParams) > 0;

        return new PostHistoryRes(isSuccess);
    }

    /**
     * 히스토리 삭제 API - Dao
     * */
    public PatchHistoryRes removeHistory(int soundIdx, int userIdx) {
        String queryString = "update History set status = 'INACTIVE' where soundIdx = ? and userIdx = ?;";

        Object[] queryParams = new Object[] {
                soundIdx,
                userIdx
        };

        return new PatchHistoryRes(
                this.jdbcTemplate.update(queryString, queryParams) > 0
        );

    }

    /**
     * 사운드 히스토리 삭제 API - Sound 존재 Validation
     * @return true: 있음, false:없음
     */
    public boolean checkHistoryExist(int soundIdx, int userIdx) {
        String queryString =
                "select count(historyIdx) from History where soundIdx = ? and userIdx = ? and status = 'ACTIVE';";

        Object[] queryParams = new Object[] {
                soundIdx,
                userIdx
        };

        return this.jdbcTemplate.queryForObject(queryString, int.class, queryParams) > 0;
    }
}
