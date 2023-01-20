package com.deepfx.serviceserver.Core.Sound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoundProvider {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final SoundDao soundDao;

    public SoundProvider(SoundDao soundDao){
        this.soundDao = soundDao;
    }

}
