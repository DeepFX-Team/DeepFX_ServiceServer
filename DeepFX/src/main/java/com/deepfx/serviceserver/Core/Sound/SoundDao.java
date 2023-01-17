package com.deepfx.serviceserver.Core.Sound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SoundDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource);}
}
