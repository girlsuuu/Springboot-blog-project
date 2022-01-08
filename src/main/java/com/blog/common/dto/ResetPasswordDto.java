package com.blog.common.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResetPasswordDto implements Serializable  {
  @NotNull(message = "昵称不能为空")
  private String username;

  @NotNull(message = "初始密码不能为空")
  private String password;

  @NotNull(message = "新密码不能为空")
  private String newPassword;

  private String newPassword2;

}
