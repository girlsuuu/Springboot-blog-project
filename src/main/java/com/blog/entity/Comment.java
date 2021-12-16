package com.blog.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comment")
public class Comment implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Long blogId;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private LocalDateTime createTime;

  private Long createUser;


  @NotNull(message = "内容不能为空")
  private String content;

}
