package model;

/**
**@author miao
**@       2016011314
*/

public class TbsAModel extends BaseModel{
	private String id;//����
	private String name;//����
	private String createTime;//����ʱ��

	public TbsAModel( ){
		
	}
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}

}