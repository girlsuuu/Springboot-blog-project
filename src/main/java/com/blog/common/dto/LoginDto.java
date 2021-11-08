package com.blog.common.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto implements Serializable {

  @NotNull(message = "昵称不能为空")
  private String username;

  @NotNull(message = "密码不能为空")
  private String password;
}
