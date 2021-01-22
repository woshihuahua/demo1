package com.example.community.util;

public interface CommunityConstant {

    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 1;

    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;

    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    int REMEMBER_EXPIRED_SECONDS = 3600*24*100;

    /**
     * 实体类:帖子
     */
     int ENTITY_TYPE_POST = 1;
    /**
     * 实体类:评论
     */
    int ENTITY_TYPE_COMMENT = 2;
}
