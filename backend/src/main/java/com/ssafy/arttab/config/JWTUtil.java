package com.ssafy.arttab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @packageName : com.ssafy.arttab.config
 * @fileName : JWTUtil
 * @date : 2022-02-07
 * @language : JAVA
 * @classification :
 * @time_limit : 2sec
 * @required_time : 00:40 ~ 01:22
 * @submissions : 1
 * @description :
 **/
@RequiredArgsConstructor
@Component
public class JWTUtil {

        private String secretKey = "banban";


        private long tokenValidTime = 60 * 60 * 1000L;
        private final UserDetailsService userDetailsService;



}
