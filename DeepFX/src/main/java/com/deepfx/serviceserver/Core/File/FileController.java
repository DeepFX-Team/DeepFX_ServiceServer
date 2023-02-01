package com.deepfx.serviceserver.Core.File;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Util.JwtUtility;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "사운드 파일 다운로드 API", notes = "한번의 하나의 사운드 파일 다운 가능합니다. 다른 API하고 리턴 구조가 다르니 테스트 해보시고 진행해 주세요 예시 파일 인덱스 : 3")
    @GetMapping("/{soundIdx}")
    public ResponseEntity<Resource> getSoundFile(@PathVariable("soundIdx") int soundIdx) throws AuthException {
        try{
            String jwtToken = JwtUtility.getJwt();
            int userIdx = JwtUtility.getUserIdx(jwtToken);

            if(JwtUtility.isJwtExpired(jwtToken)){
                throw new BaseException(BaseServerStatus.EXPIRED_TOKEN);
            }
        }catch (BaseException exception) {
            throw new AuthException((exception.getMessage()));
        }
        return fileService.getSoundFile(soundIdx);
    }
}
