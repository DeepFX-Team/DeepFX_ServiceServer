package com.deepfx.serviceserver.Core.User;

import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Util.JwtUtility;
import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseResponse;
import com.deepfx.serviceserver.Core.User.Model.GetUserInfoRes;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
      * [GET]localhost:4000/api/user/info/user
      * */
     @ApiOperation(value = "유저 정보 조회 API", notes = "마이페이지 뷰 구성 API 입니다.")
     @ApiResponses(value = {
             @ApiResponse(code = 1000, message = "Request Success"),
             @ApiResponse(code = 3000, message = "Database Error"),
             @ApiResponse(code = 9004, message = "Jwt Token Expired")
     })
     @GetMapping("/info/user")
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
}
