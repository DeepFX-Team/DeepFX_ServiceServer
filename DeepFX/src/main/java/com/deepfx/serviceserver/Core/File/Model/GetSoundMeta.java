package com.deepfx.serviceserver.Core.File.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetSoundMeta {
    private String url;
    private String fileName;
    private String fileExt;
}
