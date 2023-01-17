package com.deepfx.serviceserver.Core.Auth;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.Auth.Model.PostSignupReq;
import com.deepfx.serviceserver.Core.Auth.Model.PostSignupRes;
import com.deepfx.serviceserver.Util.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final AuthDao authDao;

    public AuthService(AuthDao authDao){
        this.authDao = authDao;
    }

    /**
     * 회원가입 API - Service
     * */
    public PostSignupRes userSignUp(PostSignupReq postSignupReq) throws BaseException {
        //비밀번호 암호화
        try{
            postSignupReq.setPassword(SHA256.encrypt(postSignupReq.getPassword()));
        }catch (NoSuchAlgorithmException exception) {
            logger.error(exception.getMessage(), "Error in signup encrypt");
            throw new BaseException(BaseServerStatus.FAIL_TO_ENCRYPT);
        }

        try{
            return authDao.userSignUp(postSignupReq);
        }catch (Exception exception) {
            logger.error(exception.getMessage(), "Error in signup");
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }
}
