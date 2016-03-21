package com.sqltojava;

import java.util.List;

public class Temple {

	private String table;   //表名
	private List<String> sx;  //属性
	private List<String> lx;   //类型
	private List<String> zx;   //注释
    private String columnKey; //主键
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<String> getSx() {
		return sx;
	}

	public void setSx(List<String> sx) {
		this.sx = sx;
	}

	public List<String> getLx() {
		return lx;
	}

	public void setLx(List<String> lx) {
		this.lx = lx;
	}

	public List<String> getZx() {
		return zx;
	}

	public void setZx(List<String> zx) {
		this.zx = zx;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
 

}
