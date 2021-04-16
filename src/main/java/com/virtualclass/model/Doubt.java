package com.virtualclass.model;

public class Doubt{

	String did,dquest,uid,cid,pdt;

	public Doubt(String did,String dquest,String uid,String cid,String pdt){
		this.did=did;
		this.dquest=dquest;
		this.uid=uid;
		this.cid=cid;
		this.pdt=pdt;
	}

	public void setDid(String did){ this.did=did; }

	public void setDquest(String dquest){ this.dquest=dquest; }

	public void setUid(String uid){ this.uid=uid; }

	public void setCid(String cid){ this.cid=cid; }

	public void setDdt(String pdt){ this.pdt=pdt; }

	public String getDid(){ return this.did; }

	public String getDquest(){ return this.dquest; }

	public String getUid(){ return this.uid; }

	public String getCid(){ return this.cid; }

	public String getDdt(){ return this.pdt; }

}