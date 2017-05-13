package com.acupt.spider.processor;

import com.acupt.spider.model.Author;
import com.acupt.spider.model.Video;
import com.acupt.spider.rule.BilibiliRule;
import com.acupt.spider.service.AuthorService;
import com.acupt.spider.service.VideoService;
import com.acupt.spider.util.BeanFactory;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2017/4/19.
 */
public class VideoProcessor implements PageProcessor {

    /**
     * 获取用户的视频列表，GET
     */
    public static final String BILIBILI_VIDEO_INFO = "http://space\\.bilibili\\.com/ajax/member/getSubmitVideos\\?.*";

    /**
     * 获取用户资料，POST
     */
    public static final String BILIBILI_AUTHOR_INFO = "http://space\\.bilibili\\.com/ajax/member/GetInfo\\?mid=[0-9]+";

    private Logger webmagicLogger = LoggerFactory.getLogger("webmagic");

    private Site site;

    private AuthorService authorService = BeanFactory.getBean(AuthorService.class);

    private VideoService videoService = BeanFactory.getBean(VideoService.class);

    /**
     * 源码不支持get（或者我没找到正确途径），重写一下
     */
    private Downloader postDownloader = new HttpClientDownloader() {
        @Override
        protected HttpUriRequest getHttpUriRequest(Request request, Site site, Map<String, String> headers, HttpHost proxy) {
            String url = request.getUrl();
            if (url.indexOf("GetInfo") < 0) {
                return super.getHttpUriRequest(request, site, headers, proxy);//按照父类的原逻辑
            }
            //获取用户资料要用POST
            int i = url.indexOf('?');
            if (i > 0) {
                HttpPost httpPost = new HttpPost(url.substring(0, i));
                String[] param = url.substring(i + 1).split("&");
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (String p : param) {
                    int j = p.indexOf('=');
                    if (j > 0) {
                        nvps.add(new BasicNameValuePair(p.substring(0, j), p.substring(j + 1)));
                    }
                }
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                } catch (UnsupportedEncodingException e) {
                    webmagicLogger.error(url, e);
                    return super.getHttpUriRequest(request, site, headers, proxy);
                }
                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        httpPost.addHeader(entry.getKey(), entry.getValue());
                    }
                }
                return httpPost;
            }
            return super.getHttpUriRequest(request, site, headers, proxy);//按照父类的原逻辑
        }
    };

    public VideoProcessor() {
        site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
                .addHeader("Referer", "http://space.bilibili.com/21848241/")
                .addHeader("Content-Type", "application/x-www-form-urlencoded");
    }

    /**
     * 处理每个爬取到的网址
     *
     * @param page 包含页面的URL和页面内容（源码）
     */
    @Override
    public void process(Page page) {
        try {
            doProcess(page);
        } catch (Exception e) {
            webmagicLogger.error("process error " + page.getUrl() + " " + e.getMessage(), e);
        }
    }

    private void doProcess(Page page) throws Exception {
        webmagicLogger.info("process: " + page.getUrl());//打印日志
        BilibiliRule rule = new BilibiliRule();//处理规则类，根据页面返回内容生成对象
        if (page.getUrl().regex(BILIBILI_AUTHOR_INFO).match()) {
            Author author = rule.jsonToAuthor(page.getRawText());//根据页面内容提取作者对象
            if (author != null) {
                if (author.getAttentions() != null && !author.getAttentions().isEmpty()) {
                    for (Object uid : author.getAttentions()) {
                        page.addTargetRequest("http://space.bilibili.com/ajax/member/GetInfo?mid=" + uid);//关注用户加入待爬取URL队列，告诉框架一会爬这个网址
                    }
                }
                page.addTargetRequest("http://space.bilibili.com/ajax/member/getSubmitVideos?mid=" + author.getUid());//告诉框架一会爬去这个网址（该用户的视频列表）
                authorService.updateAuthor(author);//把这个用户存到数据库
            }
        } else if (page.getUrl().regex(BILIBILI_VIDEO_INFO).match()) {
            String url = page.getUrl().toString();
            if (url.contains("tid=")) {
                List<Video> videos = rule.jsonToVideo(page.getRawText(), page.getUrl().regex("tid=[0-9]+").get().substring(4));//获取视频列表
                if (!videos.isEmpty()) {
                    int i = url.indexOf("&page=");
                    if (i > 0) {
                        try {
                            int pageNo = Integer.valueOf(url.substring(i + "&page=".length())) + 1;
                            url = url.substring(0, i) + "&page=" + pageNo;
                            page.addTargetRequest(url);//告诉框架一会爬取下一页的视频列表
                        } catch (Exception e) {
                            webmagicLogger.error("video page for url: " + url, e);
                        }
                    }
                    videoService.updateVideo(videos);//视频存到数据库
                }
            } else {
                List<String> types = rule.jsonToTypeIds(page.getRawText());//获取这个作者所有视频类型
                for (String t : types) {
                    page.addTargetRequest(url + "&tid=" + t + "&page=1");//告诉框架一会"单独"爬取这些类型的视频
                }
            }
        } else {
            page.getResultItems().setSkip(true);
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        String url = "http://space.bilibili.com/ajax/member/getSubmitVideos?mid=8011552";
        String url = "http://space.bilibili.com/ajax/member/GetInfo?mid=21848241";//种子URL
        VideoProcessor processor = new VideoProcessor();
        Spider.create(processor)/*设置自定义爬虫*/
                .setDownloader(processor.postDownloader)/*设置自定义下载器*/
                .addUrl(url)/*种子Url*/
                .thread(1)/*开启线程数*/
                .run();/*启动爬虫*/
    }
}
