package com.quizmate.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>quizmate-backend</h3>
 * <p>面试伙伴——用户服务接口控制器</p>
 *
 * @author moru
 * @since 2025-09-20 15:08
 */
@RestController
@Slf4j
@RequestMapping("/quizmate-user")
public class UserController {

    @RequestMapping("/test")
    public String test() {
        return "user service test";
    }
}
