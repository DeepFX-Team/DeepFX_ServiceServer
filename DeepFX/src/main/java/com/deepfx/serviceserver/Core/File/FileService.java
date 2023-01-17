package com.deepfx.serviceserver.Core.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final FileDao fileDao;

    public FileService(FileDao fileDao) {
        this.fileDao = fileDao;
    }
}
