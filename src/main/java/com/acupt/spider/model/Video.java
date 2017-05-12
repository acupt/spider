package com.acupt.spider.model;

public class Video {

	public Integer id;

	public Integer webId;

	public Integer authorId;

	public Integer vlistId;

	public String vid;

	public String url;

	public String title;

	public String length;

	public String time;

	public String uptime;

	public Integer comment;//评论

	public Integer click;//点击，播放

	public Integer share;//分享

	public Integer danmu;//弹幕

	public Integer favorite;//收藏

	public Integer like;//赞

	public Integer dislike;//踩

	public Integer coin;//打赏

	public String type;

	public Integer typeidweb;

	public Integer typeId;

	public String author;

	public String uid;

	public String tag;

	public String copyright;

	public String img;

	public String director;

	public String actor;

	public String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getVlistId() {
		return vlistId;
	}

	public void setVlistId(Integer vlistId) {
		this.vlistId = vlistId;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid == null ? null : vid.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length == null ? null : length.trim();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	public Integer getDanmu() {
		return danmu;
	}

	public void setDanmu(Integer danmu) {
		this.danmu = danmu;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Integer getDislike() {
		return dislike;
	}

	public void setDislike(Integer dislike) {
		this.dislike = dislike;
	}

	public Integer getCoin() {
		return coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getTypeidweb() {
		return typeidweb;
	}

	public void setTypeidweb(Integer typeidweb) {
		this.typeidweb = typeidweb;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid == null ? null : uid.trim();
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag == null ? null : tag.trim();
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright == null ? null : copyright.trim();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director == null ? null : director.trim();
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor == null ? null : actor.trim();
	}

	@Override
	public String toString() {
		return "vid:" + vid + "\n标题：" + title + "\n时间：" + time + "\n评论：" + comment + "\n点击：" + click + "\n分享：" + share
				+ "\n弹幕：" + danmu + "\n收藏：" + favorite + "\n点赞：" + like + "\n点踩：" + dislike + "\n打赏:" + coin + "\n类型："
				+ type + "\n作者：" + author + "\n作者号：" + uid + "\n标签：" + tag;
	}

}
