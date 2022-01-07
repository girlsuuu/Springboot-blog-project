package com.blog.util;

public class RedisKeyUtils {

  /**
   * 保存用户点赞数据的key
   *
   * @date 2022/1/04 14:44
   */
  public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
  /**
   * 保存用户被点赞数量的key
   *
   * @date 2022/1/04 14:44
   */
  public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";

  public static final String FAVORITE_BLOGS = "FAVORITE_BLOGS";

  /**
   * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
   *
   * @param likedUserId 被点赞的人id
   * @param likedPostId 点赞的人的id
   * @return
   */
  public static String getLikedKey(String likedUserId, String likedPostId) {
    return likedUserId +
        "::" +
        likedPostId;
  }
}

