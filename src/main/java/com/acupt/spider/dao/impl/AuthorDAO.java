package com.acupt.spider.dao.impl;

import com.acupt.spider.dao.BaseDAO;
import com.acupt.spider.model.Author;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class AuthorDAO extends BaseDAO{

    public Author selectById(Integer id) {
        SqlSession session = sqlSessionFactory.openSession();
        Author author = null;
        try {
            com.acupt.spider.dao.AuthorDAO mapper = session.getMapper(com.acupt.spider.dao.AuthorDAO.class);
            author = mapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
        return author;
    }

    public void insert(Author record) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            com.acupt.spider.dao.AuthorDAO mapper = session.getMapper(com.acupt.spider.dao.AuthorDAO.class);
            mapper.insertSelective(record);
            session.commit();
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void update(Author record) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            com.acupt.spider.dao.AuthorDAO mapper = session.getMapper(com.acupt.spider.dao.AuthorDAO.class);
            mapper.updateByPrimaryKeySelective(record);
            session.commit();
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Author updateWorks(Integer webId, String uid, int works) {
        SqlSession session = sqlSessionFactory.openSession();
        Author get = null;
        try {
            com.acupt.spider.dao.AuthorDAO mapper = session.getMapper(com.acupt.spider.dao.AuthorDAO.class);
            Map<String, Object> map = new HashMap<>();
            map.put("webId", webId);
            map.put("uid", uid);
            get = mapper.exist(map);
            if (get != null && get.getWorks() != null && get.getWorks() != works) {
                get.setWorks(works);
                mapper.updateByPrimaryKeySelective(get);
            }
            session.commit();
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
        return get;
    }

    /**
     * webId,uid
     *
     * @param record
     * @return
     * @author LiuJie
     * @time 2016年6月22日 下午4:07:26
     */
    public Author exist(Author record) {
        Author get = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            com.acupt.spider.dao.AuthorDAO mapper = session.getMapper(com.acupt.spider.dao.AuthorDAO.class);
            Map<String, Object> map = new HashMap<>();
            map.put("webId", record.getWebId());
            map.put("uid", record.getUid());
            get = mapper.exist(map);
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
        return get;
    }

}
