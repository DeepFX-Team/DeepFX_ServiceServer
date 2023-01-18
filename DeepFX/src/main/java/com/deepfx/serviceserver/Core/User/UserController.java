package com.deepfx.serviceserver.Core.User;

import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.User.Model.GetUserHistoryRes;
import com.deepfx.serviceserver.Core.User.Model.PatchUserInfoReq;
import com.deepfx.serviceserver.Core.User.Model.PatchUserInfoRes;
import com.deepfx.serviceserver.Util.JwtUtility;
import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseResponse;
import com.deepfx.serviceserver.Core.User.Model.GetUserInfoRes;
import com.deepfx.serviceserver.Util.RegxUtility;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;

     public UserController(UserProvider userProvider, UserService userService) {
         this.userProvider = userProvider;
         this.userService = userService;
     }

     /**
      * 유저 정보 조회 API
      * [GET]localhost:4000/api/user/info
      * */
     @ApiOperation(value = "유저 정보 조회 API", notes = "마이페이지 뷰 구성 API 입니다. JWT 필수")
     @ApiResponses(value = {
             @ApiResponse(code = 1000, message = "Request Success"),
             @ApiResponse(code = 3000, message = "Database Error"),
             @ApiResponse(code = 9004, message = "Jwt Token Expired")
     })
     @GetMapping("/info")
    public BaseResponse<GetUserInfoRes> getUserInfo() {
        try{
            String jwtToken = JwtUtility.getJwt();

            if(JwtUtility.isJwtExpired(jwtToken)){
                throw new BaseException(BaseServerStatus.EXPIRED_TOKEN);
            }

            int userIdx = JwtUtility.getUserIdx(jwtToken);

            return new BaseResponse<>(userProvider.getUserInfo(userIdx));

        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 유저 히스토리 조회 API
     * [GET]localhost:4000/api/user/history
     * */
    @ApiOperation(value = "유저 히스토리 조회 API", notes = "유저 사운드 선택 히스토리 API JWT 필수")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "Request Success"),
            @ApiResponse(code = 3000, message = "Database Error"),
            @ApiResponse(code = 9004, message = "Jwt Token Expired")
    })
    @GetMapping("/history")
    public BaseResponse<List<GetUserHistoryRes>> getUserHistory() {
        try{
            String jwtToken = JwtUtility.getJwt();

            if(JwtUtility.isJwtExpired(jwtToken)){
                throw new BaseException(BaseServerStatus.EXPIRED_TOKEN);
            }

            int userIdx = JwtUtility.getUserIdx(jwtToken);

            return new BaseResponse<>(userProvider.getUserHistory(userIdx));
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 유저 정보 변경 API - Controller
     * [PATCH]localhost:4000/api/user/modify
     * */
    @ApiOperation(value = "유저 정보 수정 API", notes = "유저 정보 수정 API: 수정 없는 경우 원래 데이터를 보내주세요, JWT 필수")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "Request Success"),
            @ApiResponse(code = 3000, message = "Database Error"),
            @ApiResponse(code = 4000, message = "Check Email Pattern"),
            @ApiResponse(code = 9004, message = "Jwt Token Expired")
    })
    @PatchMapping("/modify")
    public BaseResponse<PatchUserInfoRes> patchUserInfo(@RequestBody PatchUserInfoReq patchUserInfoReq) {
        try{
            String jwtToken = JwtUtility.getJwt();

            if(JwtUtility.isJwtExpired(jwtToken)){
                throw new BaseException(BaseServerStatus.EXPIRED_TOKEN);
            }

            int userIdx = JwtUtility.getUserIdx(jwtToken);

            if(!RegxUtility.emailChecker(patchUserInfoReq.getEmail())) throw new BaseException(BaseServerStatus.NOT_A_EMAIL);

            return new BaseResponse<>(userService.patchUserInfo(userIdx, patchUserInfoReq));
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
