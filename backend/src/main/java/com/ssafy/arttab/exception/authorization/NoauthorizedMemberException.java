package com.ssafy.arttab.exception.authorization;

import com.ssafy.arttab.exception.CustomException;
import org.springframework.http.HttpStatus;

/**
 * @packageName : com.ssafy.arttab.exception.authorization
 * @fileName : NoauthorizedMemberException
 * @date : 2022-02-14
 * @language : JAVA
 * @classification :
 * @time_limit : 2sec
 * @required_time : 00:40 ~ 01:22
 * @submissions : 1
 * @description :
 **/
public class NoauthorizedMemberException extends CustomException {
    private static final String MESSAGE = "인증되지 않는 회원";

    public NoauthorizedMemberException() {
        super(MESSAGE, HttpStatus.UNAUTHORIZED);
    }
}
