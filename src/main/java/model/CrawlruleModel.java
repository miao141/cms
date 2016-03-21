package model;

/**
**@author miao
**@       2016011314
*/

public class CrawlruleModel extends BaseModel{
	private String id;//主键
	private String name;//规则名称
	private String match_expr;//规则内容
	private String target;//命名规则
	private String data_info;//下级规则

	private int type;
	private int sortNumber;
	private int status;
	private String parentId;
	 
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public CrawlruleModel( ){
		
	}
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getMatch_expr(){
		return this.match_expr;
	}
	public void setMatch_expr(String match_expr){
		this.match_expr=match_expr;
	}
	public String getTarget(){
		return this.target;
	}
	public void setTarget(String target){
		this.target=target;
	}
	public String getData_info(){
		return this.data_info;
	}
	public void setData_info(String data_info){
		this.data_info=data_info;
	}

}