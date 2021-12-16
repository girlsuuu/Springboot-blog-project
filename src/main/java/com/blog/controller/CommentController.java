package com.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.lang.Result;
import com.blog.entity.Blog;
import com.blog.entity.Comment;
import com.blog.service.CommentService;
import com.blog.util.ShiroUtil;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

  @Autowired
  CommentService commentService;

  @GetMapping("comment/{id}")
  public Result detail(@PathVariable(name = "id") Long id){

    IPage<Comment> page = new Page<>(1, 10);
    QueryWrapper<Comment> wrapper=new QueryWrapper<>();
    wrapper.eq("blog_id", id);

    IPage<Comment> list= commentService.page(page,wrapper);
    //这里是实际查询出来的集合
    List<Comment> gc=list.getRecords();

    return Result.success(gc);
  }
}
