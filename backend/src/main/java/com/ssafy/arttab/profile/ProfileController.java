package com.ssafy.arttab.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private final ProfileService profileService;



}
