package com.deepfx.serviceserver.Core.Sound;

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

    public PostHistoryRes saveHistory(String fileName, String fileUrl, int userIdx) {

        String insertSoundQuery = "insert into Sound(soundOwnerIdx, soundName, soundURL) values(?, ?, ?);";
        String getInsertedIdxQuery = "select last_insert_id()";
        String insertHistoryQuery = "insert into History(userIdx, soundIdx) values(?, ?)";

        Object[] insertSoundParams = new Object[] {
                userIdx,
                fileName,
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
        //Sound Table에 새 사운드 row insert하고
        //방금 insert한 인덱스를 받아와서
        //히스토리 테이블에 insert
        //성공여부 판별하여 리턴
    }
}
