package com.eugene.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DCLab on 12/25/2015.
 */
@Component
public class UserContextImpl implements UserContext {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null)
            return null;
        return (User) authentication.getPrincipal();
    }

    @Override
    public void setCurrentUser(User user) {
//        throw new UnsupportedOperationException();
        //TODO 根据roles角色来创建AuthorityList
        String[] roles = user.getRoles();
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, user.getPassword(),authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
