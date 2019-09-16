package kr.gudi.web;

public class PanelBean {
	int no;
	String title;
	String url;
	String comment;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "PanelBean [no=" + no + ", title=" + title + ", url=" + url + ", comment=" + comment + "]";
	}	
}
