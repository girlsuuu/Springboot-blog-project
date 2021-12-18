package com.blog.entity;

import lombok.Data;

@Data
public class CommentDO {

  private Long blogId;

  private String createUser;

  private String userAvatar;

  private String content;

  private Reply reply;

}
