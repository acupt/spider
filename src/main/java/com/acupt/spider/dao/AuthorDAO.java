package com.acupt.spider.dao;

import com.acupt.spider.model.Author;

import java.util.Map;

public interface AuthorDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Author record);

    int insertSelective(Author record);

    Author selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);

    Author exist(Map<String, Object> map);
}