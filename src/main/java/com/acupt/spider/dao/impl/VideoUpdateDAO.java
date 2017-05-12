package com.acupt.spider.dao.impl;

import com.acupt.spider.dao.BaseDAO;
import com.acupt.spider.model.Video;
import org.apache.ibatis.session.SqlSession;

public class VideoUpdateDAO extends BaseDAO {

    public void insert(Video video) {
        SqlSession session = BaseDAO.sqlSessionFactory.openSession();
        try {
            com.acupt.spider.dao.VideoUpdateDAO mapper = session.getMapper(com.acupt.spider.dao.VideoUpdateDAO.class);
            mapper.insertSelective(video);
            session.commit();
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
    }

}
