package com.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.lang.Result;
import com.blog.entity.Blog;
import com.blog.service.BlogService;
import com.blog.service.RecordService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class BlogController {

  @Autowired
  BlogService blogService;

  @Autowired
  RecordService recordService;

  @GetMapping("blogs")
  public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
    Page<Blog> page = new Page<>(currentPage, 5);
    QueryWrapper<Blog> wrapper = new QueryWrapper<>();
    wrapper.eq("status", 1);
    IPage<Blog> pageData = blogService.page(page, wrapper.orderByDesc("created"));
    return Result.success(pageData);
  }

  @GetMapping("blogsByUserId/{id}")
  public Result listByUserId(@PathVariable(name = "id") Long id,
      @RequestParam(defaultValue = "1") Integer currentPage) {
    Page<Blog> page = new Page<>(currentPage, 6);
    QueryWrapper<Blog> wrapper = new QueryWrapper<>();
    wrapper.eq("user_id", id);
    wrapper.eq("status", 1);
    IPage<Blog> pageData = blogService.page(page, wrapper.orderByDesc("created"));
    return Result.success(pageData);

  }


  @GetMapping("blog/{id}")
  public Result detail(@PathVariable(name = "id") Long id) throws Exception {
    Blog blog = blogService.getById(id);
    Assert.notNull(blog, "该博客不存在");
    if (blog.getStatus() == 0) {
      return Result.fail("博客不存在");
    }
    return Result.success(blog);
  }

  @PostMapping("blog/search")
  public Result search(@Validated @RequestBody String text) {
    QueryWrapper<Blog> wrapper = new QueryWrapper<>();
    wrapper.eq("status", 1);
    wrapper.like("title", text);
    List<Blog> blogs = blogService.list(wrapper);
    return Result.success(blogs);
  }

  @RequiresAuthentication
  @PostMapping("blog/edit")
  public Result edit(@Validated @RequestBody Blog blog) {
    Blog temp = null;
    if (blog.getId() != null) {
      temp = blogService.getById(blog.getId());
      // 只能编辑自己的文章
//      Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
    } else {
      temp = new Blog();
      temp.setUserId(ShiroUtil.getProfile().getId());
      temp.setCreated(LocalDateTime.now());
    }
    BeanUtil.copyProperties(blog, temp, "id", "userId", "created");
    blogService.saveOrUpdate(temp);
    return Result.success(null);
  }

  @RequiresAuthentication
  @GetMapping("blog/delete/{id}")
  public Result edit(@PathVariable(name = "id") Long id) {

    Blog temp = blogService.getById(id);
    temp.setStatus(0);
    blogService.saveOrUpdate(temp);
    return Result.success(null);
  }


}
