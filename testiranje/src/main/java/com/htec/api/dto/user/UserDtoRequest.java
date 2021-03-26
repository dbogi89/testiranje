package com.htec.api.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;



}