package com.ssafy.relpl.controller.rest;

import com.ssafy.relpl.dto.request.*;
import com.ssafy.relpl.service.UserService;
import com.ssafy.relpl.service.result.CommonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResult> save(@RequestBody UserSignupRequest request) {
        return userService.save(request);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResult> userLogin(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/autologin")
    public ResponseEntity<CommonResult> autologin(@RequestBody UserAutoLoginRequest request) {
        return userService.autologin(request);
    }

    @PostMapping("/token/reissue")
    public ResponseEntity<CommonResult> reissue(@RequestBody UserReissueRequest request) {
        return userService.reissue(request);
    }

    @GetMapping("/isExist/nickname/{nickname}")
    public ResponseEntity<CommonResult> duplicateNickname(@PathVariable("nickname") String nickname) {
        return userService.duplicateNickname(nickname);
    }

    @PostMapping("/isExist/phone")
    public ResponseEntity<CommonResult> duplicatePhone(@RequestBody UserDuplicatePhoneRequest request){
        return userService.duplicateUserPhone(request);
    }

    @PostMapping("/isExist/uid")
    public ResponseEntity<CommonResult> duplicateUserId(@RequestBody UserDuplicateIdRequest request){
        return userService.duplicateUserId(request);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return userService.test();
    }
}
