package com.deepfx.serviceserver.Core.Sound.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostUploadRes {
    int soundIdx;
    String soundUrl;
    String fileName;
}
