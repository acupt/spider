package com.acupt.spider.dao;

import com.acupt.spider.model.Video;

import java.util.Map;

public interface VideoDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    Video exist(Map<String, Object> map);
}