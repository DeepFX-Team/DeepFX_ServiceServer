package com.deepfx.serviceserver.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxUtility {

    /**
     * @return true: 이메일, false: 이메일 아님
     * */
    public static boolean emailChecker(String email) {
        String rawPattern = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9+-_.]+\\.[a-zA-Z0-9+-_.]+$";
        Pattern pattern = Pattern.compile(rawPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
