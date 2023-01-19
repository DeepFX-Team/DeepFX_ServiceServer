package com.deepfx.serviceserver.Core.User;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.User.Model.GetUserHistoryRes;
import com.deepfx.serviceserver.Core.User.Model.GetUserInfoRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProvider {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserDao userDao;

    public UserProvider(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 유저 정보 조회 API - Provider
     * */
    public GetUserInfoRes getUserInfo(int userIdx) throws BaseException {
        try{
            return userDao.getUserInfo(userIdx);
        }catch (Exception exception) {
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }

    /**
     * 유저 히스토리 조회 API - Provider
     * */
    public List<GetUserHistoryRes> getUserHistory(int userIdx) throws BaseException{
        try{
            return userDao.getUserHistory(userIdx);
        }catch (Exception exception) {
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }
}
