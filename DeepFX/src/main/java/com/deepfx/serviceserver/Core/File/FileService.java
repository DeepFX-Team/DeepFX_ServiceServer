package com.deepfx.serviceserver.Core.File;

import com.deepfx.serviceserver.Core.File.Model.GetSoundMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final FileDao fileDao;

    @Autowired
    ResourceLoader resourceLoader;

    public FileService(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public ResponseEntity<Resource> getSoundFile(int soundIdx){
        GetSoundMeta soundMeta = fileDao.getSoundMeta(soundIdx);
        String fileName = soundMeta.getFileName() + "." + soundMeta.getFileExt();
        System.out.println(fileName);
        Resource resource = resourceLoader.getResource(soundMeta.getUrl());

        logger.info("File download from " + soundMeta.getUrl());

        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .cacheControl(CacheControl.noCache())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(resource);
    }
}
