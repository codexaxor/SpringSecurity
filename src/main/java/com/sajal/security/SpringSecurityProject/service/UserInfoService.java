package com.sajal.security.SpringSecurityProject.service;

import com.sajal.security.SpringSecurityProject.dto.UserInfoConvertUserDetailsDto;
import com.sajal.security.SpringSecurityProject.entity.UserInfo;
import com.sajal.security.SpringSecurityProject.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);

        return userInfo
                .map(UserInfoConvertUserDetailsDto::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " - User not found"));

    }
}
