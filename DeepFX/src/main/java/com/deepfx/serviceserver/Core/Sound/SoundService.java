package com.deepfx.serviceserver.Core.Sound;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Core.Sound.Model.PatchHistoryRes;
import com.deepfx.serviceserver.Core.Sound.Model.PostHistoryRes;
import com.deepfx.serviceserver.Core.Sound.Model.PostUploadRes;
import com.deepfx.serviceserver.Util.S3Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class SoundService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final SoundDao soundDao;

    @Autowired
    private final S3Utility s3Util;

    public SoundService(SoundDao soundDao, S3Utility s3Util) {
        this.soundDao = soundDao;
        this.s3Util = s3Util;
    }

    /**
     * 파일 업로드
     * */
    public PostUploadRes uploadFile(MultipartFile soundFile, int userIdx) throws BaseException{

        String fileUrl = "test";

        try{
            fileUrl = s3Util.upload2S3(soundFile, "sound");
        }catch (IOException exception){
            logger.error(exception.getMessage(), "Error in File upload to S3");
            throw new BaseException(BaseServerStatus.FILE_UPLOAD_FAIL);
        }

        try{
            return soundDao.uploadFile(soundFile.getOriginalFilename(),userIdx, fileUrl);
        }catch (Exception exception){
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }
    /**
     * 사운드 히스토리 생성 API - Service
     * */
    public PostHistoryRes saveHistory(int soundIdx, int userIdx) throws BaseException {

        try{
            return soundDao.saveHistory(soundIdx, userIdx);
        }catch (Exception exception) {
            logger.error(exception.getMessage(), "Error in History save");
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }

    /**
     * 사운드 히스토리 삭제 API - Service
     * */
    public PatchHistoryRes removeHistory(int soundIdx, int userIdx) throws BaseException {
        try{

            if(!soundDao.checkHistoryExist(soundIdx, userIdx)) throw new BaseException(BaseServerStatus.HISTORY_NOT_EXIST);

            return soundDao.removeHistory(soundIdx, userIdx);

        }catch (Exception exception) {
            logger.error(exception.getMessage(), "Error in History remove");
            throw new BaseException(BaseServerStatus.DATABASE_ERROR);
        }
    }

}
