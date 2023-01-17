package com.deepfx.serviceserver.Core.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file")
public class FileController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
}
