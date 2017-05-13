package com.acupt.spider.model;

import java.util.List;

public class Author {

    private Integer id;

    private Integer webId;

    private String uid;

    private String name;

    private String gender;

    private Integer age;

    private String url;

    private String level;

    private Integer fans;

    private Integer attention;

    private Integer works;

    private String birthday;

    private String regtime;

    private String lasttime;

    private String uptime;

    private String slogan;

    private String description;

    private String address;

    private String img;

    //不持久化
    private List<Object> attentions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWebId() {
        return webId;
    }

    public void setWebId(Integer webId) {
        this.webId = webId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    public Integer getWorks() {
        return works;
    }

    public void setWorks(Integer works) {
        this.works = works;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan == null ? null : slogan.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public List<Object> getAttentions() {
        return attentions;
    }

    public void setAttentions(List<Object> attentions) {
        this.attentions = attentions;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", webId=" + webId +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", url='" + url + '\'' +
                ", level='" + level + '\'' +
                ", fans=" + fans +
                ", attention=" + attention +
                ", works=" + works +
                ", birthday='" + birthday + '\'' +
                ", regtime='" + regtime + '\'' +
                ", lasttime='" + lasttime + '\'' +
                ", uptime='" + uptime + '\'' +
                ", slogan='" + slogan + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

}
