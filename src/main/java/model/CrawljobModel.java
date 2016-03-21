package model;

/**
**@author miao
**@       2016011314
*/

public class CrawljobModel extends BaseModel{
	private Long id;//主键
	private String job_type;//任务类型
	private String job_name;//任务名称
	private String rule_key;//抓取规则
	private String target_domain;//目标网站
	private String protocol;//网络协议
	private String start_url;//抓取起始
	private String data_type;//数据请求类型
	private String job_tag;//任务
	private String client_id;//使用
	private String load_class;//状态
	private String status;//开启
	private String create_time;
public CrawljobModel( ){
		
	}

	public String getCreate_time() {
	return create_time;
}

public void setCreate_time(String create_time) {
	this.create_time = create_time;
}

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}
	public String getJob_type(){
		return this.job_type;
	}
	public void setJob_type(String job_type){
		this.job_type=job_type;
	}
	public String getJob_name(){
		return this.job_name;
	}
	public void setJob_name(String job_name){
		this.job_name=job_name;
	}
	public String getRule_key(){
		return this.rule_key;
	}
	public void setRule_key(String rule_key){
		this.rule_key=rule_key;
	}
	public String getTarget_domain(){
		return this.target_domain;
	}
	public void setTarget_domain(String target_domain){
		this.target_domain=target_domain;
	}
	public String getProtocol(){
		return this.protocol;
	}
	public void setProtocol(String protocol){
		this.protocol=protocol;
	}
	public String getStart_url(){
		return this.start_url;
	}
	public void setStart_url(String start_url){
		this.start_url=start_url;
	}
	public String getData_type(){
		return this.data_type;
	}
	public void setData_type(String data_type){
		this.data_type=data_type;
	}
	public String getJob_tag(){
		return this.job_tag;
	}
	public void setJob_tag(String job_tag){
		this.job_tag=job_tag;
	}
	public String getClient_id(){
		return this.client_id;
	}
	public void setClient_id(String client_id){
		this.client_id=client_id;
	}
	public String getLoad_class(){
		return this.load_class;
	}
	public void setLoad_class(String load_class){
		this.load_class=load_class;
	}
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status=status;
	}

}