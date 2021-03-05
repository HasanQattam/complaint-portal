package com.pwc.complaint_portal;

import com.pwc.complaint_portal.security.services.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

public final class PrincipalUtil {

    private PrincipalUtil(){

    }

    public static UserDetailsImpl parse(Principal principal) {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        return (UserDetailsImpl) token.getPrincipal();
    }
}
