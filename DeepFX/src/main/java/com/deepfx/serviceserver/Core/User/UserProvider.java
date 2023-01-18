package com.deepfx.serviceserver.Core.User;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.User.Model.GetUserInfoRes;
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

    public GetUserInfoRes getUserInfo(int userIdx) throws BaseException {
        try{
            return userDao.getUserInfo(userIdx);
        }catch (Exception exception) {
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }
}
