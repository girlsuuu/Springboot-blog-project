package com.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.common.lang.Result;
import com.blog.entity.User;
import com.blog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @RequiresAuthentication
  @GetMapping("/index")
  public Object index() {
    User user = userService.getById(1L);

    return Result.success(user);
  }

  @GetMapping("/getUser/{id}")
  public Object getUser(@PathVariable(name = "id") Long id) {
    User user = userService.getById(id);

    return Result.success(user);
  }

  @PostMapping("/save")
  public Object save(@Validated @RequestBody User user) {

    return Result.success(user);
  }

  @GetMapping("/getUserByName/{username}")
  public Object getUserByName(@PathVariable(name = "username") String username) {

    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("username", username);
    User user = userService.getOne(wrapper);
    return Result.success(user);
  }

  @GetMapping("/editUsername/{id}")
  public Object editUsername(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username){

    User user = userService.getOne(
        new QueryWrapper<User>().eq("username", username));
    if (user != null) {
      return Result.fail("用户名已存在");
    } else {
      User userToSave = userService.getById(id);
      userToSave.setUsername(username);
      userService.saveOrUpdate(userToSave);
    }
    return Result.success(null);
  }
}
