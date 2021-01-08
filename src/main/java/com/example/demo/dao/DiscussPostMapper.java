package com.example.demo.dao;

import com.example.demo.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper
{
    //以下两条用于帖子查询
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);
    int selectDiscussPostRows(@Param("userId") int userId);//如果需要动态拼接条件，且只有一种条件时，必须使用参数param取别名

}
