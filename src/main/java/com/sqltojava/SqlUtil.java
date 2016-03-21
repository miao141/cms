package com.sqltojava;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUtil {
 
	private static String packageName = "model";
	private static String packageControllerName = "com.controller";
	private static String route;
	
	
	public static void createA(String sql,String routes){
		route = routes;
		createSQL(sql);
		System.out.println("导出文件成功");
	}
	
	/**
	 * sql文件路径
	 * @param path   sql文件路径
	 * @param route  导出文件路径 
	 */
	public static void create(String path,String routes){
		route = routes;
		String sql = readSQL(path);
		createSQL(sql);
		System.out.println("导出文件成功");
	}
	
	/**
	 * 读取文件内容
	 * 
	 * @param fileName
	 *            文件路径
	 * @return
	 */
	private static String readSQL(String fileName) {
		StringBuilder str = new StringBuilder();
		BufferedReader in = null;
		try {

			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					fileName), "UTF-8"));
			String s;
			try {
				while ((s = in.readLine()) != null)
					str.append(s + '\n');
			} finally {
				in.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.toString();
	}

	/**
	 * 获取每条sql语句
	 * 
	 * @param sql
	 * @return
	 */
	private static void createSQL(String sql) {
		String sqlreg = "CREATE TABLE[\\S\\s&&[^;]]*"; // 获取sql语句
		Pattern p = Pattern.compile(sqlreg);
		Matcher m = p.matcher(sql);
		while (m.find()) {
			readTable(m.group(),route);
		}
	}

	/**
	 * 根据sql语句读取表的全部信息
	 * 
	 * @param sql
	 */
	private static void readTable(String sql,String route) {
		Temple bean = new Temple();
		readTableName(bean, sql);
		List<String> list = readAttribute(sql);
		readTOAttributeName(bean, list); // 需修改
		readTOAttributeType(bean, list);
		readTOAttributeNote(bean, list);
		readTOColumnKey(bean, sql);
		//主键
		ClassUtil.templeTOBean(bean, route, packageName);
		PageCreateUtil.templeTOJsp(bean, route, packageName);
	}

	/**
	 * 获取表名
	 * 
	 * @param bean
	 * @param sql
	 */
	private static void readTableName(Temple bean, String sql) {
		String tableReg = "[`][\\w]+[`]";
		Pattern p = Pattern.compile(tableReg);
		Matcher m = p.matcher(sql);
		if (m.find()) {
			bean.setTable(StringUtil.rmMark(m.group()));
		}
	}

	/**
	 * 获取sql语句关于字段的语句 例如：`authority` int(11) NOT NULL auto_increment COMMENT
	 * '权限编号',
	 * 
	 * @param sql
	 */
	private static List<String> readAttribute(String sql) {
		String attributeReg = "[`][a-zA-Z_]+[`][\\s][a-z]+[\\s|\\S&&[^,]]+[,]";//[(]
		Pattern p = Pattern.compile(attributeReg);
		Matcher m = p.matcher(sql);
		List<String> list = new ArrayList<String>();
		while (m.find()) {
			list.add(m.group());
		}
		return list;
	}

	/**
	 * 获取全部的属性字段名
	 * 
	 * @param bean
	 * @param list
	 */
	private static void readTOAttributeName(Temple bean, List<String> list) {
		String attributeReg = "[`][a-zA-Z_]+[`]";
		Pattern p = Pattern.compile(attributeReg);
		List<String> sxList = new ArrayList<String>();
		for (String sql : list) {
			Matcher m = p.matcher(sql);
			while (m.find()) {
				sxList.add(StringUtil.rmMark(m.group()));
			}
		}
		bean.setSx(sxList);
	}

	/**
	 * 获取全部的字段类型
	 * 
	 * @param bean
	 * @param list
	 */
	private static void readTOAttributeType(Temple bean, List<String> list) {
		String attributeReg = "[`][a-zA-Z_]+[`][\\s][a-z]+";
		Pattern p = Pattern.compile(attributeReg);
		List<String> lxList = new ArrayList<String>();
		for (String sql : list) {
			Matcher m = p.matcher(sql);
			while (m.find()) {
				String[] s = m.group().split("[\\s]");
				lxList.add(StringUtil.typeTrans(s[1]));
			}
		}
		bean.setLx(lxList);
	}
	
	private static void readTOAttributeNote(Temple bean, List<String> list) {
		String attributeReg = "[\u4E00-\u9FA5]+";
		Pattern p = Pattern.compile(attributeReg);
		List<String> zxList = new ArrayList<String>();
		for (String sql : list) {
			Matcher m = p.matcher(sql);
			while (m.find()) {
				//String[] s = m.group().split("[\\s]");
				zxList.add(m.group());
			}
		}
		bean.setZx(zxList);
	}
	
	private static void readTOColumnKey(Temple bean, String sql) {
		String attributeReg = "(?<=PRIMARY\\sKEY\\(`)\\w*?(?=`\\))";
		Pattern p = Pattern.compile(attributeReg);
		String resultString = new String();
			Matcher m = p.matcher(sql);
			while (m.find()) {
				resultString = m.group();
			} 
		bean.setColumnKey(resultString);
	}
	
}
