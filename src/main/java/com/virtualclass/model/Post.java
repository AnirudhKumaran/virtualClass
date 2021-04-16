package com.virtualclass.model;

public class Post{

	int ptype;
	String pid,ptitle,pcontent,uid,cid,pdt,gtype;

	public Post() {}
	
	public Post(String pid,int ptype,String ptitle,String pcontent,String uid,String cid,String pdt){
		this.ptype=ptype;
		this.pid=pid;
		this.ptitle=ptitle;
		this.pcontent=pcontent;
		this.uid=uid;
		this.cid=cid;
		this.pdt=pdt;
	}
	
	public Post(String pid,String gtype,String ptitle,String pcontent,String uid,String pdt) {
		this.pid=pid;
		this.gtype = gtype;
		this.ptitle=ptitle;
		this.pcontent=pcontent;
		this.uid=uid;
		this.pdt=pdt;
	}

	public void setPtype(int ptype){ this.ptype=ptype; }

	public void setPid(String pid){ this.pid=pid; }

	public void setPtitle(String ptitle){ this.ptitle=ptitle; }

	public void setPcontent(String pcontent){ this.pcontent=pcontent; }

	public void setUid(String uid){ this.uid=uid; }

	public void setCid(String cid){ this.cid=cid; }

	public void setPdt(String pdt){ this.pdt=pdt; }

	public int getPtype(){ return this.ptype; }

	public String getPid(){ return this.pid; }

	public String getPtitle(){ return this.ptitle; }

	public String getPcontent(){ return this.pcontent; }

	public String getUid(){ return this.uid; }

	public String getCid(){ return this.cid; }

	public String getPdt(){ return this.pdt; }

}