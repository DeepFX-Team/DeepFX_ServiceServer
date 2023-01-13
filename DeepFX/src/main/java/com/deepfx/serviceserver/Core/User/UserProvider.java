package com.deepfx.serviceserver.Core.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProvider {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserDao userDao;

    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }

}
