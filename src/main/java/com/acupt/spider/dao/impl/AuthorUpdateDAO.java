package com.acupt.spider.dao.impl;

import com.acupt.spider.dao.BaseDAO;
import com.acupt.spider.model.Author;
import org.apache.ibatis.session.SqlSession;

public class AuthorUpdateDAO extends BaseDAO {

    public void insert(Author record) {
        SqlSession session = BaseDAO.sqlSessionFactory.openSession();
        try {
            com.acupt.spider.dao.AuthorUpdateDAO mapper = session.getMapper(com.acupt.spider.dao.AuthorUpdateDAO.class);
            mapper.insertSelective(record);
            session.commit();
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void update(Author record) {
        SqlSession session = BaseDAO.sqlSessionFactory.openSession();
        try {
            com.acupt.spider.dao.AuthorUpdateDAO mapper = session.getMapper(com.acupt.spider.dao.AuthorUpdateDAO.class);
            mapper.updateByPrimaryKeySelective(record);
            session.commit();
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
    }

}
