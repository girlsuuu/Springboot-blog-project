# Springboot-blog-project

前端仓库: [blog-fe](https://github.com/Xinjiann/blog-fe)

项目地址: 

国内访问：[http://47.104.92.236/blogs](http://47.104.92.236/blogs)

国外访问：[www.helloworld-blog.co.uk](www.helloworld-blog.co.uk)

初始账号密码：guest

## 使用技术：

- 前端：Vue/ElementUI/Axios

- 后台：MybatisPlus/shiro/jwt/Mysql/redis/SpringBoot

## 效果展示:

#### 博客主页
![](https://github.com/Xinjiann/Springboot-blog-project/blob/main/img/%E6%95%88%E6%9E%9C1.png)

#### 博客详情页
![](https://github.com/Xinjiann/Springboot-blog-project/blob/main/img/%E6%95%88%E6%9E%9C4.png)

#### 个人主页
![](https://github.com/Xinjiann/Springboot-blog-project/blob/main/img/%E6%95%88%E6%9E%9C2.png)

#### 评论区

![](https://github.com/Xinjiann/Springboot-blog-project/blob/main/img/%E6%95%88%E6%9E%9C3.png)

## 开发方式简述：

- 前端
  1. vue整合element-ui，axios
  2. store状态管理储存jwt
  3. axios全局拦截
  4. 博客编辑/发布接入mavon-editor
  5. 文章详情markdown渲染

  
- 后台

  1. SpringBoot整合MybatisPlus
  3. 整合shiro-redis，会话共享
  4. shiro整合jwt，身份校验
  5. 实体校验
  6. 跨域
