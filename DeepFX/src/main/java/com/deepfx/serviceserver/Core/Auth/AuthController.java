package com.deepfx.serviceserver.Core.Auth;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseResponse;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.Auth.Model.*;
import com.deepfx.serviceserver.Util.RegxUtility;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 회원가입 API - Controller
     * [POST]localhost:4000/api/auth/signup
     * */
    @ApiOperation(value = "회원가입", notes = "사용자 회원가입 진행을 위한 API")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "Request Success"),
            @ApiResponse(code = 3000, message = "Database Error"),
            @ApiResponse(code = 4000, message = "Check Email Pattern"),
            @ApiResponse(code = 4000, message = "암호화에 실패하였습니다.")
    })
    @PostMapping("/signup")
    public BaseResponse<PostSignupRes> userSignUp(@RequestBody PostSignupReq postSignupReq) {
        try{
            // 이메일 체크
            if(!RegxUtility.emailChecker(postSignupReq.getEmail())) throw new BaseException(BaseServerStatus.NOT_A_EMAIL);

            return new BaseResponse<>(authService.userSignUp(postSignupReq));
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 로그인 API - Controller
     * [POST]localhost:4000/api/auth/signin
     * */
    @ApiOperation(value = "로그인", notes = "사용자 로그인 진행을 위한 API")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "Request Success"),
            @ApiResponse(code = 3000, message = "Database Error"),
            @ApiResponse(code = 4000, message = "암호화에 실패하였습니다."),
            @ApiResponse(code = 4002, message = "아이디와 비밀번호를 확인해주세요")

    })
    @PostMapping("/signin")
    public BaseResponse<PostSignInRes> userSignIn(@RequestBody PostSignInReq postSignInReq) {
        try{
            return new BaseResponse<>(authService.userSignIn(postSignInReq));
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * JWT 토큰 갱신 API - Controller
     * [POST]localhost:4000/api/auth/refresh
     * */
    @ApiOperation(value = "JWT Token 갱신", notes = "JWT 토큰 갱신을 위한 API")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "Request Success"),
            @ApiResponse(code = 3000, message = "Database Error"),
            @ApiResponse(code = 9006, message = "리프래시 토큰이 유효하지 않습니다 다시 로그인 해주세요")
    })
    @PostMapping("/refresh")
    public BaseResponse<PostJwtRefreshRes> refreshToken(@RequestBody PostJwtRefreshReq postJwtRefreshReq) {
        try{
            return new BaseResponse<>(authService.refreshToken(postJwtRefreshReq));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
