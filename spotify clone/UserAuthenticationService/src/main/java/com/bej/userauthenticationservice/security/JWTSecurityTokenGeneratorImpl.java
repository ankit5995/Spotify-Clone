package com.bej.userauthenticationservice.security;


import com.bej.userauthenticationservice.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    public Map<String,String> createToken(User user) {
        Map<String, String> tokenMap = new HashMap<>();
        user.setPassword("");
        Map<String, Object> userData = new HashMap<>();
        userData.put("email", user.getEmail());
        String jwtTokenString = Jwts.builder().setClaims(userData).setSubject(user.getEmail()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512, "secret").compact();
        tokenMap.put("token", jwtTokenString);
        tokenMap.put("message", "Login Successful");
        tokenMap.put("emailId", user.getEmail());
        return tokenMap;
    }


}
