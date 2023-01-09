package com.deepfx.serviceserver.Core.Test;

import com.deepfx.serviceserver.Base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Operation(summary = "Test Api", description = "Hello world")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping("/")
    public BaseResponse<String> testApi(){
        return new BaseResponse<>("Hello World!");
    }
}
