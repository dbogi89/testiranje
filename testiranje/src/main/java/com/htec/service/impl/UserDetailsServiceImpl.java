package com.htec.service.impl;

import com.htec.entity.User;
import com.htec.entity.UserPrinciple;
import com.htec.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
            User user = userRepository.findByUsername(username);
            if (user == null) throw new UsernameNotFoundException("User not exist");
            return UserPrinciple.build(user);
    }
}