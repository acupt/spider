package com.acupt.spider.service;

import com.acupt.spider.dao.impl.VideoDAO;
import com.acupt.spider.dao.impl.VideoUpdateDAO;
import com.acupt.spider.model.Video;
import com.acupt.spider.util.BeanFactory;

import java.util.List;

/**
 * Created by liujie on 2017/5/11.
 */
public class VideoService {

    private VideoDAO videoDAO = BeanFactory.getBean(VideoDAO.class);

    private VideoUpdateDAO videoUpdateDAO = BeanFactory.getBean(VideoUpdateDAO.class);

    public void updateVideo(List<Video> records) {
        for (Video record : records) {
            videoUpdateDAO.insert(record);
            Video video = videoDAO.exist(record);
            if (video != null) {
                record.id = video.id;
                videoDAO.update(record);
            } else {
                videoDAO.insert(record);
            }
        }
    }
}
