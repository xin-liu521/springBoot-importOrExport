package com.velvol.salary.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtHelper {


    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            return (Claims) Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security)).parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception ex) {
        }
        return null;
    }

    public static String createJWT(String user_name, String user_id, String worker_id, long TTLMillis, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);


        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim("user_name", user_name).claim("user_id", user_id).claim("worker_id", worker_id).signWith(signatureAlgorithm, signingKey);
        if (TTLMillis >= 0L) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        return builder.compact();
    }
}
