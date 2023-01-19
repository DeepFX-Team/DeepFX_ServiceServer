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
        //유저 이름 받아와 soundOwner로
        //Sound Table에 새 사운드 row insert하고
        //방금 insert한 인덱스를 받아와서
        //히스토리 테이블에 insert
        //성공여부 판별하여 리턴
    }
}
