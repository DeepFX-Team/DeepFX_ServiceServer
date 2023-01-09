package com.deepfx.serviceserver.Base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class BaseException extends Exception{
    private BaseServerStatus status;
}
