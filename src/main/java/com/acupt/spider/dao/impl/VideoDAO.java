package com.acupt.spider.dao.impl;

import com.acupt.spider.dao.BaseDAO;
import com.acupt.spider.model.Video;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class VideoDAO extends BaseDAO {

    public void insert(Video video) {
        SqlSession session = BaseDAO.sqlSessionFactory.openSession();
        try {
            com.acupt.spider.dao.VideoDAO mapper = session.getMapper(com.acupt.spider.dao.VideoDAO.class);
            mapper.insertSelective(video);
            session.commit();
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void update(Video video) {
        SqlSession session = BaseDAO.sqlSessionFactory.openSession();
        try {
            com.acupt.spider.dao.VideoDAO mapper = session.getMapper(com.acupt.spider.dao.VideoDAO.class);
            mapper.updateByPrimaryKeySelective(video);
            session.commit();
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Video exist(Video video) {
        Video getVideo = null;
        SqlSession session = BaseDAO.sqlSessionFactory.openSession();
        try {
            com.acupt.spider.dao.VideoDAO mapper = session.getMapper(com.acupt.spider.dao.VideoDAO.class);
            Map<String, Object> map = new HashMap<>();
            map.put("webId", video.webId);
            map.put("vid", video.vid);
            getVideo = mapper.exist(map);
        } catch (Exception e) {
            System.out.println("mybatis error:\n\t" + e.getMessage());
        } finally {
            session.close();
        }
        return getVideo;
    }

}
