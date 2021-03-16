package com.htec.api.v1;

/**
 * Created by dbogicevic
 */
import com.htec.api.dto.document.Response;
import com.htec.api.dto.user.JwtResponseDto;
import com.htec.api.dto.user.LoginCredentials;
import com.htec.api.dto.user.UserDtoRequest;
import com.htec.security.jwt.JwtProvider;
import com.htec.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class PublicApi {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl userService;



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginCredentials loginCredentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginCredentials.getUsername(),
                        loginCredentials.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponseDto(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createUser(@RequestBody UserDtoRequest userDtoRequest) {
        return userService.saveUser(userDtoRequest);
    }
}