package com.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    @NotNull(message = "标题不能为空")
    private String title;

    @NotNull(message = "描述不能为空")
    private String description;

    @NotNull(message = "内容不能为空")
    private String content;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime created;

    private Integer status;


}
