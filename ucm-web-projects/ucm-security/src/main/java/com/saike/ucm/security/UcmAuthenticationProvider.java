package com.saike.ucm.security;

import com.saike.ucm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
public class UcmAuthenticationProvider implements AuthenticationProvider {

    private static Logger logger = LoggerFactory.getLogger(UcmAuthenticationProvider.class);

    private AuthenticationProvider delegate;

    private UserService userService;

    public UcmAuthenticationProvider(AuthenticationProvider delegate) {
        this.delegate= delegate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticationDelegate = null;
        try{
            authenticationDelegate = this.delegate.authenticate(authentication);
        }catch(Exception e) {
            e.printStackTrace();
        }

        if (authenticationDelegate == null) {
            return authenticationDelegate;
        }
        try{
            userService.addUser(authentication.getPrincipal().toString());
        }catch(Exception e) {
            logger.error("", e);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return delegate.supports(authentication);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
