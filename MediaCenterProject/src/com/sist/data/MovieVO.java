package com.sist.data;
/*
 * 
 */
public class MovieVO {
	
	private int mno; // 영화번호
	private int cno; // 카테고리 번호 
	private String title;
	private String regyear;
	private String score;
	private String actor;
	private String critics;
	private String story;
	private String poster;

	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getCritics() {
		return critics;
	}
	public void setCritics(String critics) {
		this.critics = critics;
	}
	public String getRegyear() {
		return regyear;
	}
	public void setRegyear(String regyear) {
		this.regyear = regyear;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	
	
}
