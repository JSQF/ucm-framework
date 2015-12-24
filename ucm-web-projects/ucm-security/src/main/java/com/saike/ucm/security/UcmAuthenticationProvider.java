package com.saike.ucm.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by huawei on 12/24/15.
 */
@Component
public class UcmAuthenticationProvider implements AuthenticationProvider {

    private AuthenticationProvider delegate;

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


        return authenticationDelegate;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return delegate.supports(authentication);
    }
}
