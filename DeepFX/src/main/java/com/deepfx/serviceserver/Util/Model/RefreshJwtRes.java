package com.deepfx.serviceserver.Util.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RefreshJwtRes {
    private int userIdx;
    private String accessToken;
    private String refreshToken;
}
