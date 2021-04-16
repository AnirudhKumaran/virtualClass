package com.virtualclass.model;

public class Video{

	int vstat;
	String vid,vtitle,vcont,vpath,uid,cid,pdt,gtype;

	public Video() {
		
	}
	
	public Video(String vid,String vtitle,String vcont,String vpath,String uid,String cid,int vstat,String pdt){
		this.vstat=vstat;
		this.vid=vid;
		this.vtitle=vtitle;
		this.vcont=vcont;
		this.vpath=vpath;
		this.uid=uid;
		this.cid=cid;
		this.pdt=pdt;
	}
	
	public Video(String vid,String vtitle,String vcont,String vpath,String uid,String pdt){
		this.vid=vid;
		this.vtitle=vtitle;
		this.vcont=vcont;
		this.vpath=vpath;
		this.uid=uid;
		this.cid=cid;
		this.pdt=pdt;
	}
	
	public Video(String vid,String vtitle,String vcont,String vpath,String uid,int vstat,String pdt){
		this.vstat=vstat;
		this.vid=vid;
		this.vtitle=vtitle;
		this.vcont=vcont;
		this.vpath=vpath;
		this.uid=uid;
		this.pdt=pdt;
	}
	
	public Video(String vid,String gtype,String vtitle,String vcont,String vpath,int vstat,String uid,String pdt){
		this.vid=vid;
		this.gtype=gtype;
		this.vtitle=vtitle;
		this.vcont=vcont;
		this.vpath=vpath;
		this.vstat=vstat;
		this.uid=uid;
		this.pdt=pdt;
	}

	public void setVstat(int vstat){ this.vstat=vstat; }

	public void setVid(String vid){ this.vid=vid; }

	public void setVtitle(String vtitle){ this.vtitle=vtitle; }

	public void setVcont(String vcont){ this.vcont=vcont; }

	public void setVpath(String vpath){ this.vpath=vpath; }

	public void setUid(String uid){ this.uid=uid; }

	public void setCid(String cid){ this.cid=cid; }

	public void setpdt(String pdt){ this.pdt=pdt; }

	public int getVstat(){ return this.vstat; }

	public String getVid(){ return this.vid; }

	public String getVtitle(){ return this.vtitle; }

	public String getVcont(){ return this.vcont; }

	public String getVpath(){ return this.vpath; }

	public String getUid(){ return this.uid; }

	public String getCid(){ return this.cid; }

	public String getpdt(){ return this.pdt; }

}