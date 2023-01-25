package com.deepfx.serviceserver.Util;

import com.deepfx.serviceserver.Base.BaseException;
import com.deepfx.serviceserver.Base.BaseServerStatus;
import com.deepfx.serviceserver.Config.SecretConstants;
import com.deepfx.serviceserver.Util.Model.RefreshJwtRes;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JwtUtility {
    /**
     * Jwt Token 생성 메서드
     * */
    public static String createToken(int userIdx) {

        Date now = new Date();
        Date expireDate = new Date(System.currentTimeMillis()*600000*6);


        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("userIdx", userIdx)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SecretConstants.JWT_KEY)
                .compact();

    }
    /**
     * Refresh Token 생성 매서드
     * */
    public static String createRefreshToken() {
        Date now = new Date();
        Date refreshExpireDate = new Date(System.currentTimeMillis()*600000*6*24);
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(refreshExpireDate)
                .signWith(SignatureAlgorithm.HS256, SecretConstants.JWT_KEY)
                .compact();
    }

    /**
     * 헤더에서 AccessToken 파싱 메서드
     * */
    public static String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    /**
     * AccessToken 재발급 메서드
     * */
    public RefreshJwtRes refreshToken(String accessToken, String refreshToken) throws BaseException{

        int userIdx = parseJwt(accessToken);

        return new RefreshJwtRes(userIdx, createToken(userIdx), createRefreshToken());
    }

    /**
     * AccessToken 만료 확인 메서드
     * @return 만료: true 아직 유효: false
     * */
    public static boolean isJwtExpired(String accessToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SecretConstants.JWT_KEY).parseClaimsJws(accessToken);
            return claims.getBody().getExpiration().before(new Date());
        } catch(ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * userIdx 추출
     * @return int
     * @throws BaseException
     * */
    public static int getUserIdx(String accessToken) throws BaseException{
        // JWT 가져오기
        if(accessToken == null || accessToken.length() == 0){
            throw new BaseException(BaseServerStatus.JWT_NOT_EXIST);
        }

        //JWT 파싱
        return parseJwt(accessToken);
    }

    /**
     * Jwt 파싱해서 userIdx를 리턴하는 메서드
     * */
    public static int parseJwt(String accessToken) throws BaseException{
        Jws<Claims> claimsJws;
        try{
            claimsJws = Jwts.parser()
                    .setSigningKey(SecretConstants.JWT_KEY)
                    .parseClaimsJws(accessToken);
        } catch (SignatureException exception){
            throw new BaseException(BaseServerStatus.INVALID_SIGNATURE);
        } catch (MalformedJwtException exception){
            throw new BaseException(BaseServerStatus.INVALID_JWT);
        } catch (ExpiredJwtException exception){
            throw new BaseException(BaseServerStatus.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException exception){
            throw new BaseException(BaseServerStatus.UNSUPPORTED_JWT);
        }

        //Return userIdx
        return claimsJws.getBody().get("userIdx", Integer.class);
    }
}
