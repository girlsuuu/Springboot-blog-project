package com.blog.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "comment",autoResultMap = true)
public class Comment implements Serializable {

  private static final long serialVersionUID = 1L;


  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Long blogId;

  private String createUser;

  private String userAvatar;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date createTime;

  @NotNull(message = "内容不能为空")
  private String content;

  @TableField(typeHandler = JacksonTypeHandler.class)
  private Reply reply;


}
