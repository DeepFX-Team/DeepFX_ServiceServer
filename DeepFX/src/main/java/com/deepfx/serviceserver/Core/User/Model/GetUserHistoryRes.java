package com.deepfx.serviceserver.Core.User.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserHistoryRes {
    @ApiModelProperty(name = "soundIdx", example = "1", required = true)
    private int soundIdx;
    @ApiModelProperty(name = "soundName", example = "soundName", required = true)
    private String soundName;
    @ApiModelProperty(name = "soundURL", example = "soundURL", required = true)
    private String soundURL;
}
