package com.bigjava.service.impl;

import com.bigjava.api.dto.document.Response;
import com.bigjava.api.dto.user.UserDtoRequest;
import com.bigjava.constants.Constants;
import com.bigjava.entity.Role;
import com.bigjava.entity.User;
import com.bigjava.entity.UserPrinciple;
import com.bigjava.mapper.UserMapper;
import com.bigjava.repository.RoleRepository;
import com.bigjava.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
            User user = userRepository.findByUsername(username);
            if (user == null) throw new UsernameNotFoundException("User not exist");
            return UserPrinciple.build(user);
    }

    @Transactional
    public Response saveUser(UserDtoRequest userDtoRequest) {
        try {
            User user = userMapper.toUser(userDtoRequest);
            Set<Role> set = roleRepository.findAll().stream().filter(role -> String.valueOf(role.getRoleName()).equals("ROLE_USER")
            ).collect(Collectors.toSet());
            user.setRoles(set);
            userRepository.save(user);
            return Response.builder().content("OK").code(Constants.OK).description("Create user").build();
        }catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException("The user not insert ");
        }

    }
}