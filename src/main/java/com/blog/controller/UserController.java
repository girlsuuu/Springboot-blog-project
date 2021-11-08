package com.blog.controller;


import com.blog.common.lang.Result;
import com.blog.entity.User;
import com.blog.service.UserService;
import javax.jws.soap.SOAPBinding.Use;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-11-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @RequiresAuthentication
  @GetMapping("/index")
  public Object index(){
    User user = userService.getById(1L);

    return Result.success(user);
  }
}
