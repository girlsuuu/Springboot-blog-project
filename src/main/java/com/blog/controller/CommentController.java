package com.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.lang.Result;
import com.blog.entity.Comment;
import com.blog.entity.CommentDO;
import com.blog.entity.Reply;
import com.blog.entity.Reply.SingleReply;
import com.blog.entity.ReplyDO;
import com.blog.service.CommentService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
  public Result commentDetail(@PathVariable(name = "id") Long id){

    IPage<Comment> page = new Page<>(1, 10);
    QueryWrapper<Comment> wrapper=new QueryWrapper<>();
    wrapper.eq("blog_id", id);

    IPage<Comment> list= commentService.page(page,wrapper);
    //这里是实际查询出来的集合
    List<Comment> gc=list.getRecords();

    return Result.success(gc);
  }


  @PostMapping("comment/comment")
  public Result comment(@Validated @RequestBody CommentDO commentDO){

    Comment temp = new Comment();


    BeanUtil.copyProperties(commentDO, temp);
    temp.setCreateTime(new Date());

    Reply reply = new Reply();
    reply.setReplies(Collections.emptyList());

    temp.setReply(reply);
    commentService.save(temp);

    return Result.success(null);
  }

  @PostMapping("comment/reply")
  public Result reply(@Validated @RequestBody ReplyDO replyDO){

    Reply.SingleReply temp = new Reply.SingleReply();

    BeanUtil.copyProperties(replyDO, temp);
    temp.setCreateTime(new Date());

    Comment comment = commentService.getById(replyDO.getCommentId());
    List<SingleReply> replyList = comment.getReply().getReplies();
    replyList.add(temp);
    comment.getReply().setReplies(replyList);
    String json= JSON.toJSONString(comment.getReply());

    UpdateWrapper updateWrapper = new UpdateWrapper();
    updateWrapper.eq("id", replyDO.getCommentId());
    updateWrapper.set("reply", json);
    commentService.update(comment, updateWrapper);

    return Result.success(null);
  }
}
