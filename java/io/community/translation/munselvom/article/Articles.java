package io.community.translation.munselvom.article;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Articles {

	@Id
	private String articleid;
	
	private String articlename;
	private String articletext;
	private String articletrans;
	private String transstat;
	
	public Articles(String id, String name, String atext, String atrans, String stat) {
		this.articleid = id;
		this.articlename = name;
		this.articletext = atext;
		this.articletrans = atrans;
		this.transstat = stat;
	}
	
	public Articles() {

		}
	
	public String getArticleid() {
		return articleid;
	}
	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}
	public String getArticlename() {
		return articlename;
	}
	public void setArticlename(String articlename) {
		this.articlename = articlename;
	}
	public String getArticletext() {
		return articletext;
	}
	public void setArticletext(String articletext) {
		this.articletext = articletext;
	}
	public String getArticletrans() {
		return articletrans;
	}
	public void setArticletrans(String articletrans) {
		this.articletrans = articletrans;
	}
	public String getTransstat() {
		return transstat;
	}
	public void setTransstat(String transstat) {
		this.transstat = transstat;
	}
}
