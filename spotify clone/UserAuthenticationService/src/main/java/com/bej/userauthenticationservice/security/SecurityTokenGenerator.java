package com.bej.userauthenticationservice.security;



import com.bej.userauthenticationservice.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String,String> createToken(User user);
}
