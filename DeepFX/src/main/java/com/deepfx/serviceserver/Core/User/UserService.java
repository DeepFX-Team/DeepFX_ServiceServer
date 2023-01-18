package com.deepfx.serviceserver.Core.User;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseResponse;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.User.Model.PatchUserInfoReq;
import com.deepfx.serviceserver.Core.User.Model.PatchUserInfoRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserDao userDao;

    /**
     * 유저 정보 수정 API - Service
     * */
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public PatchUserInfoRes patchUserInfo(int userIdx, PatchUserInfoReq patchUserInfoReq) throws BaseException {
        try{
            return userDao.patchUserInfo(userIdx, patchUserInfoReq);
        }catch (Exception exception) {
            logger.error(exception.getMessage(), "Error in PatchUserInfo - UserService");
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }
}
