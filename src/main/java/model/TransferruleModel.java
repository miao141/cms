package model;

/**
**@author miao
**@       2016011314
*/

public class TransferruleModel extends BaseModel{
	private int id;//主键
	private String name;//规则名字
	private String match_expr;//匹配表达式
	private String target;//目标节点名字
	private String action;//动作
	private String type;//节点类型
	private String next;//虚节点列表
	private String dataprocess;//虚节点

public TransferruleModel( ){
		
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
	public String getAction(){
		return this.action;
	}
	public void setAction(String action){
		this.action=action;
	}
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getNext(){
		return this.next;
	}
	public void setNext(String next){
		this.next=next;
	}
	public String getDataprocess(){
		return this.dataprocess;
	}
	public void setDataprocess(String dataprocess){
		this.dataprocess=dataprocess;
	}

}