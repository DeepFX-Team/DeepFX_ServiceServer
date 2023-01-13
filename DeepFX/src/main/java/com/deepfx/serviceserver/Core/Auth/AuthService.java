package com.deepfx.serviceserver.Core.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final AuthDao authDao;

    public AuthService(AuthDao authDao){
        this.authDao = authDao;
    }
}
