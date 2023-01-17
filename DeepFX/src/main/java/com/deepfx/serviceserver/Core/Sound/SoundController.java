package com.deepfx.serviceserver.Core.Sound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sound")
public class SoundController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final SoundService soundService;

    public SoundController(SoundService soundService) {
        this.soundService = soundService;
    }


}