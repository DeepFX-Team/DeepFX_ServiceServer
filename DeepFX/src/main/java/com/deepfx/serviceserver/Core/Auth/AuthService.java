package com.deepfx.serviceserver.Core.Auth;

import com.deepfx.serviceserver.Core.Auth.Model.*;
import com.deepfx.serviceserver.Util.JwtUtility;
import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseServerStatus;
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

    /**
     * 로그인 API - Service
     * */
    public PostSignInRes userSignIn(PostSignInReq postSignInReq) throws BaseException{
        //비밀번호 암호화
        try{
            postSignInReq.setPassword(SHA256.encrypt(postSignInReq.getPassword()));
        }catch (NoSuchAlgorithmException exception) {
            logger.error(exception.getMessage(), "Error in sign in encrypt");
            throw new BaseException(BaseServerStatus.FAIL_TO_ENCRYPT);
        }

        //로그인 처리 (아이디, 비밀번호 일치 체크)
        boolean checkFlag;
        try{
            checkFlag = authDao.userSignIn(postSignInReq);
        }catch(Exception exception){
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }

        //만약 일치하지 않는다면
        if(!checkFlag) throw new BaseException(BaseServerStatus.FAIL_TO_LOGIN);

        //일치한다면 유저 인덱스를 뽑아온다.
        int userIdx;
        try{
            userIdx = authDao.getUserIdx(postSignInReq);
        }catch (Exception exception){
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }

        //jwt 토큰을 생성
        String jwtToken = JwtUtility.createToken(userIdx);
        String refreshToken = JwtUtility.createRefreshToken();

        try{
            authDao.updateRefreshToken(userIdx, refreshToken);
        }catch (Exception exception){
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
        //결과 리턴
        return new PostSignInRes(jwtToken, refreshToken);
    }

    /**
     * JWT 토큰 리프래시 API - Service
     * */
    public PostJwtRefreshRes refreshToken(PostJwtRefreshReq postJwtRefreshReq) throws BaseException {
        //리프래시 토큰 체크
        boolean refreshTokenValid;
        try{
            refreshTokenValid = authDao.checkRefreshToken(postJwtRefreshReq.getUserIdx(), postJwtRefreshReq.getRefreshToken());
        }catch (Exception exception){
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }

        if(!refreshTokenValid) throw new BaseException(BaseServerStatus.INVALID_REFRESH_TOKEN);

        //토큰 재발급
        String jwtToken = JwtUtility.createToken(postJwtRefreshReq.getUserIdx());
        String refreshToken = JwtUtility.createRefreshToken();

        //리프래시 토큰 업데이트
        try{
            authDao.updateRefreshToken(postJwtRefreshReq.getUserIdx(), refreshToken);
        }catch (Exception exception){
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }

        return new PostJwtRefreshRes(postJwtRefreshReq.getUserIdx(), jwtToken, refreshToken);
    }
}
