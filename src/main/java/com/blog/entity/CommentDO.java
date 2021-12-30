package com.blog.entity;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

@Data
public class CommentDO {

  public CommentDO(Comment comment){
    BeanUtil.copyProperties(comment, this, "id");
    this.setUserAvatar("");
  }
  private Long blogId;
  private String createUser;
  private String userAvatar;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date createTime;
  private String content;
  private Reply reply;

}
