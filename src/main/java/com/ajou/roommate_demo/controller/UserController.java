package com.ajou.roommate_demo.controller;

import com.ajou.roommate_demo.model.User;
import com.ajou.roommate_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.controller
 * @CLASS_NAME: UserController
 * @USER: BTS&ARMY
 * @Date 2024/11/5 9:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;



}