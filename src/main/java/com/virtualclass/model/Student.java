package com.virtualclass.model;

public class Student{

	int uage;
	String uid,fname,uname,uaddr,ucity,ustate,ucountry,upincode,uemail;

	public Student() {
		
	}
	
	public Student(String uid,String fname,String uemail,String ucity) {
		this.uid=uid;
		this.fname=fname;
		this.uemail=uemail;
		this.ucity=ucity;
	}
	
	public Student(String uid,String fname,String uname,int uage,String uaddr,String ucity,String ustate,String ucountry,String upincode,String uemail){
		this.uage=uage;
		this.uid=uid;
		this.fname=fname;
		this.uname=uname;
		this.uaddr=uaddr;
		this.ucity=ucity;
		this.ustate=ustate;
		this.ucountry=ucountry;
		this.upincode=upincode;
		this.uemail=uemail;
	}

	public void setUage(int uage){ this.uage=uage; }

	public void setUid(String uid){ this.uid=uid; }

	public void setFname(String fname){ this.fname=fname; }

	public void setUname(String uname){ this.uname=uname; }

	public void setUaddr(String uaddr){ this.uaddr=uaddr; }

	public void setUcity(String ucity){ this.ucity=ucity; }

	public void setUstate(String ustate){ this.ustate=ustate; }

	public void setUcountry(String ucountry){ this.ucountry=ucountry; }

	public void setUpincode(String upincode){ this.upincode=upincode; }

	public void setUemail(String uemail){ this.uemail=uemail; }

	public int getUage(){ return this.uage; }

	public String getUid(){ return this.uid; }

	public String getFname(){ return this.fname; }

	public String getUname(){ return this.uname; }

	public String getUaddr(){ return this.uaddr; }

	public String getUcity(){ return this.ucity; }

	public String getUstate(){ return this.ustate; }

	public String getUcountry(){ return this.ucountry; }

	public String getUpincode(){ return this.upincode; }

	public String getUemail(){ return this.uemail; }

}