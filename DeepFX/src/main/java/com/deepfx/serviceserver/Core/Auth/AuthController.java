package com.deepfx.serviceserver.Core.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


}
