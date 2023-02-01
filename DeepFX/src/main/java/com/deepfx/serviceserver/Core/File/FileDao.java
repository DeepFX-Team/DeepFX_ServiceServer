package com.deepfx.serviceserver.Core.File;

import com.deepfx.serviceserver.Core.File.Model.GetSoundMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class FileDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void getDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource);}
    public GetSoundMeta getSoundMeta(int soundIdx) {
        String queryString = "select soundURL, soundName, soundExt from Sound where soundIdx = ?";
        String countString = "update Sound set downCount = downCount + 1 where soundIdx = ?";

        this.jdbcTemplate.update(countString, soundIdx);

        return this.jdbcTemplate.queryForObject(queryString, (rs, rowNum) ->
                new GetSoundMeta(
                        rs.getString("soundURL"),
                        rs.getString("soundName"),
                        rs.getString("soundExt")
                ), soundIdx);
    }
}
