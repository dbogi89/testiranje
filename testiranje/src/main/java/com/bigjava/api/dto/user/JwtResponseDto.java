package com.bigjava.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto {
    private String token;
    private String type = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;


    public JwtResponseDto(String jwt, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = jwt;
        this.username = username;
        this.authorities = authorities;
    }
}