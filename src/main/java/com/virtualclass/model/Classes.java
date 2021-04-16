package com.virtualclass.model; 

public class Classes{

	String cid,cname,cdesc;

	public Classes() {
		
	}
	
	public Classes(String cid,String cname,String cdesc){
		this.cid=cid;
		this.cname=cname;
		this.cdesc=cdesc;
	}
	
	public Classes(String cid,String cname) {
		this.cid=cid;
		this.cname=cname;
	}
	

	public void setCid(String cid){ this.cid=cid; }

	public void setCname(String cname){ this.cname=cname; }

	public void setCdesc(String cdesc){ this.cdesc=cdesc; }

	public String getCid(){ return this.cid; }

	public String getCname(){ return this.cname; }

	public String getCdesc(){ return this.cdesc; }

}