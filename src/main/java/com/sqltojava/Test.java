package com.sqltojava;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String ss = "bigint";
		//System.out.println(ss.contains("int"));
		//String str = " PRIMARY KEY (`id`)) ";//"<a href=\"javascript:openCodeWin('/tmois/wszhcx_getDetailByRegInt.xhtml?regNum=1788715&amp;intcls=10&amp;seriaNum=1','1')\">1788715</a>";
		//String regex = "(?<=PRIMARY\\sKEY\\(`)\\w*?(?=`\\))";
		// PRIMARY KEY  (`id`)) 
		// openCodeWin(`id`)) 
		//(?<=openCodeWin(`)\\w*?(?=`\\))
		//(?<=PRIMARY\\sKEY\\(`)\\w*?(?=`\\))
		//(?<=openCodeWin(')\\w*?(?='\\))
		/*Pattern pA = Pattern.compile(regex);
		Matcher mA = pA.matcher(str);
		while (mA.find()) {
			System.out.println(mA.group());
		}*/
		/*
		  String sql = "CREATE TABLE `TbsA` ( " 
		  + "`id` char(20) collate utf8_unicode_ci NOT NULL COMMENT '主键'," 
		  + " `name` varchar(50) collate utf8_unicode_ci default NULL COMMENT '名称',"
		  + "`createTime` timestamp NOT NULL default CURRENT_TIMESTAMP  '创建时间',"
		  + "PRIMARY KEY  (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT '测试表A'"; 
		 
		 
		  
		  String attributeReg = "[`][a-zA-Z_]+[`][\\s][a-z]+[\\s|\\S&&[^,]]+[,]";//[(] 
		  Pattern p = Pattern.compile(attributeReg);
		  Matcher m = p.matcher(sql);
		  List<String> list = new ArrayList<String>(); 
		  while (m.find()) {
		  list.add(m.group());
		  System.out.println(m.group()); 
		  }*/
		  
		  /*String routes = "F:\\u\\a";
		  SqlUtil.createA(sql, routes);*/
		 String sqlA = "  PRIMARY KEY(`id`))";
		 sqlA = "<a href=\"javascript:openCodeWin('/tmois/wszhcx_getDetailByRegInt.xhtml?regNum=1788715&amp;intcls=10&amp;seriaNum=1','1')\">1788715</a>";
//`name` varchar(50) collate utf8_unicode_ci default NULL COMMENT '名称',  PRIMARY KEY(`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT '测试表A'
	  
		String regex = "(?<=PRIMARY\\sKEY\\(`)\\w*?(?=`\\))";
		regex = "(?<=openCodeWin\\(').*?(?='\\,)";//(.*?)
	  // 
	   //(?<=openCodeWin(\\(')\\w*?(?='\\))
		System.out.println(Test.lastRegex(sqlA, regex));
		 
		 Pattern pA = Pattern.compile(regex);
		Matcher mA = pA.matcher(sqlA);
		while (mA.find()) {
			System.out.println(mA.group());
			//String[] s = mA.group().split("[\\s]");
			// System.out.println(s[1]);
		} 
	}
	/*
	String sqlA = " `name` varchar(50) collate utf8_unicode_ci default NULL COMMENT '名称',";
	String attributeRegA = "[`][a-zA-Z_]+[`][\\s][a-z]+";// +[`][\\s][a-z]+
	String regex = "[\u4E00-\u9FA5]+";// +[`][\\s][a-z]+
	System.out.println(Test.lastRegex(sqlA, regex));
	Pattern pA = Pattern.compile(attributeRegA);
	Matcher mA = pA.matcher(sqlA);
	while (mA.find()) {
		System.out.println(mA.group());
		String[] s = mA.group().split("[\\s]");
		// System.out.println(s[1]);
	}*/

	/** 返回最后一个匹配的字符串 
	 *  @param string 
	 *  @return 
	 *  */
	public static String lastRegex(String string, String regex) {
		String returnString = new String();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(string);
		while (m.find()) {
			returnString = m.group();
		}
		return returnString;
	}

}
