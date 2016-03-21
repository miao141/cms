package model;

/**
**@author miao
**@       2016011314
*/

public class Crawl_webModel extends BaseModel{
	private int id;//主键
	private String name;//网站名称
	private String domain;//网站域名
	private String type;//网站类型
	private String status;//状态
	private String create_time;//开启
	private String source;//关闭
	private String server;//创建时间
	private String onwer;//完整域名

public Crawl_webModel( ){
		
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getDomain(){
		return this.domain;
	}
	public void setDomain(String domain){
		this.domain=domain;
	}
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}
	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source=source;
	}
	public String getServer(){
		return this.server;
	}
	public void setServer(String server){
		this.server=server;
	}
	public String getOnwer(){
		return this.onwer;
	}
	public void setOnwer(String onwer){
		this.onwer=onwer;
	}

}