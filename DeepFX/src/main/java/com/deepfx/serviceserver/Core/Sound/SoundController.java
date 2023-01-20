package com.deepfx.serviceserver.Core.Sound;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseResponse;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.Sound.Model.PostHistoryReq;
import com.deepfx.serviceserver.Core.Sound.Model.PostHistoryRes;
import com.deepfx.serviceserver.Util.JwtUtility;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/sound")
public class SoundController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final SoundService soundService;
    @Autowired
    private final SoundProvider soundProvider;

    public SoundController(SoundService soundService, SoundProvider soundProvider) {
        this.soundService = soundService;
        this.soundProvider = soundProvider;
    }

    @ApiOperation(value = "사운드 선택 히스토리 저장 API", notes = "이 API 에서 파일의 S3업로드 까지 같이 진행합니다., JWT 필수, 파일이름 확장자까지 붙여서 보내주세요")
    @ApiResponses(value = {
            @ApiResponse(code = 1000, message = "Request Success"),
            @ApiResponse(code = 3000, message = "Database Error"),
            @ApiResponse(code = 7000, message = "파일 업로드에 실패하였습니다."),
            @ApiResponse(code = 9004, message = "Jwt Token Expired")
    })
    @PostMapping("/history/save")
    public BaseResponse<PostHistoryRes> saveHistory(@RequestBody PostHistoryReq postHistoryReq) {

        try{
            String jwtToken = JwtUtility.getJwt();

            if(JwtUtility.isJwtExpired(jwtToken)){
                throw new BaseException(BaseServerStatus.EXPIRED_TOKEN);
            }

            int userIdx = JwtUtility.getUserIdx(jwtToken);

            return new BaseResponse<>(soundService.saveHistory(postHistoryReq.getSoundFile(), postHistoryReq.getFileName(), userIdx));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
