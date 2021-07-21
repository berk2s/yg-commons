package com.yataygecisle.commons.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class TokenClaimsConverter {

    public static Converter<Jwt, AbstractAuthenticationToken> converter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (String authority : jwt.getClaimAsStringList("scopes")) {
                grantedAuthorities.add(
                        new SimpleGrantedAuthority(authority.toUpperCase(Locale.ROOT)));
            }

            for (String role : jwt.getClaimAsStringList("roles")) {
                grantedAuthorities.add(
                        new SimpleGrantedAuthority("ROLE_" + role.toUpperCase(Locale.ROOT)));
            }

            return grantedAuthorities;
        });

        return converter;
    }

}
