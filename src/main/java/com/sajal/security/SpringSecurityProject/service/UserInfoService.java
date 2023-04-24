package com.sajal.security.SpringSecurityProject.service;

import com.sajal.security.SpringSecurityProject.dto.UserInfoConvertUserDetailsDto;
import com.sajal.security.SpringSecurityProject.entity.UserInfo;
import com.sajal.security.SpringSecurityProject.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);

        return userInfo
                .map(UserInfoConvertUserDetailsDto::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " - User not found"));

    }

    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        log.info("saved userInfo - {}", userInfo);
        return userInfoRepository.save(userInfo);
    }
}
