package com.deepfx.serviceserver.Core.Sound.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostHistoryReq {
    @ApiModelProperty(name = "soundFile", example = "File", required = true)
    private MultipartFile soundFile;
    @ApiModelProperty(name = "fileName", example = "ExampleFile", required = true)
    private String fileName;
}
