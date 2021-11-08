package com.blog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.common.dto.LoginDto;
import com.blog.common.lang.Result;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.util.JwtUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  @Autowired
  UserService userService;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/login")
  public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
    User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
    Assert.notNull(user, "用户不存在");

    if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
      return Result.fail("密码不正确");
    }

    String jwt = jwtUtils.generateToken(user.getId());
    response.setHeader("Authorization", jwt);
    response.setHeader("Access-control-Expose-Header", "Authorization");
    return Result.success(MapUtil.builder()
        .put("id", user.getId())
        .put("username", user.getUsername())
        .map()
    );
  }

  @RequiresAuthentication
  @GetMapping("logout")
  public Result logOut(){

    SecurityUtils.getSubject().logout();
    return Result.success(null);
  }

}
