package com.acupt.spider.rule;


import com.acupt.spider.dao.impl.AuthorDAO;
import com.acupt.spider.dao.impl.AuthorUpdateDAO;
import com.acupt.spider.model.Author;
import com.acupt.spider.model.Video;
import com.acupt.spider.util.BeanFactory;
import com.acupt.spider.util.TypeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BilibiliRule implements Rule {

    private Logger logger = Logger.getLogger(getClass());

    private AuthorDAO authorDAO = BeanFactory.getBean(AuthorDAO.class);
    private AuthorUpdateDAO authorUpdateDAO = BeanFactory.getBean(AuthorUpdateDAO.class);

    private ThreadLocal<SimpleDateFormat> timeFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public List<String> jsonToTypeIds(String jsonString) {
        List<String> types = new ArrayList<String>();
        JSONObject jsonObject = JSON.parseObject(jsonString);
        if (!jsonObject.getBooleanValue("status")) {
            logger.error("for json string: " + jsonString);
            return types;
        }
        Map<String, Map<String, Object>> list = (Map<String, Map<String, Object>>) jsonObject.getJSONObject("data").get("tlist");
        if (list == null) {
            return types;
        }
        for (Map.Entry<String, Map<String, Object>> entry : list.entrySet()) {
            types.add(entry.getKey());
            TypeUtil.putBilibiliType(entry.getKey(), entry.getValue().get("name").toString());
        }
        return types;
    }

    public List<Video> jsonToVideo(String jsonString, String tid) {
        List<Video> videos = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(jsonString);
        if (!jsonObject.getBooleanValue("status")) {
            logger.error("for json string: " + jsonString);
            return videos;
        }
        JSONArray vList = jsonObject.getJSONObject("data").getJSONArray("vlist");
        if (vList == null) {
            return videos;
        }
        Author author = null;
        String uptime = timeFormat.get().format(new Date());
        for (int i = 0; i < vList.size(); i++) {
            JSONObject vj = vList.getJSONObject(i);
            Video video = new Video();
            video.setWebId(WEB_BILIBILI);
            video.setUptime(uptime);
            video.setUid(vj.get("mid").toString());
            video.setVid(vj.get("aid").toString());
            video.setUrl("http://www.bilibili.com/video/av" + video.getVid());
            video.setAuthor(vj.getString("author"));
            video.setDanmu(vj.getInteger("comment"));
            video.setCopyright(vj.getString("copyright"));
            video.setTime(timeFormat.get().format(new Date(vj.getLong("created") * 1000)));
            video.setDescription(vj.getString("description"));
            video.setFavorite(vj.getInteger("favorites"));
            video.setLength(vj.getString("length"));
            video.setImg(vj.getString("pic"));
            video.setClick(vj.getInteger("play"));
            video.setComment(vj.getInteger("review"));
            video.setTitle(vj.getString("title"));
            video.setTypeidweb(vj.getInteger("typeid"));
            video.setType(TypeUtil.getTypeByBilibiliTid(tid));
            if (author == null) {
                int works = jsonObject.getJSONObject("data").getInteger("count");
                author = authorDAO.updateWorks(Rule.WEB_BILIBILI, video.getUid(), works);
                if (author == null) {
                    author = new Author();
                    author.setName(video.getAuthor());
                    author.setUid(video.getUid());
                    author.setUrl("http://space.bilibili.com/" + author.getUid());
                    author.setWebId(WEB_BILIBILI);
                    author.setUptime(uptime);
                    author.setWorks(works);
                    authorDAO.insert(author);
                    authorUpdateDAO.insert(author);
                }
            }
            video.setAuthorId(author.getId());
            videos.add(video);
        }
        return videos;
    }

    public Author jsonToAuthor(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        if (!jsonObject.getBooleanValue("status")) {
            logger.error("for json string: " + jsonString);
            return null;
        }
        JSONObject data = jsonObject.getJSONObject("data");
        Author author = new Author();
        author.setWebId(WEB_BILIBILI);
        author.setUptime(timeFormat.get().format(new Date()));
        author.setUid(data.getString("mid"));
        author.setUrl("http://space.bilibili.com/" + author.getUid());
        author.setName(data.getString("name"));
        author.setAddress(data.getString("place"));
        author.setBirthday(data.getString("birthday"));
        if (author.getBirthday() == null || author.getBirthday().startsWith("0000")) {
            author.setBirthday(null);
        }
        if (author.getBirthday() != null) {
            author.setAge(Rule.getAgeByBirthday(author.getBirthday()));
        }
        author.setFans(data.getInteger("fans"));
        author.setAttention(data.getInteger("attention"));
        author.setGender(data.getString("sex"));
        author.setLevel(data.getJSONObject("level_info").getInteger("current_level").toString());
        author.setRegtime(timeFormat.get().format(new Date(data.getLong("regtime") * 1000)));
        author.setSlogan(data.getString("sign"));
        author.setDescription(data.getString("description"));
        author.setAttentions((List<Object>) data.get("attentions"));
        author.setImg(data.getString("face"));
        return author;
    }
}
