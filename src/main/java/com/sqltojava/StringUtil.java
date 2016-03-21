package com.sqltojava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 删除单词前后的引号
	 * @return
	 */
	public static String rmMark(String word){
		String reg = "[a-zA-Z_]+";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(word);
		if (m.find()) {
			return m.group();
		}
		return null;
	}
	
	/**
	 * 数据库字段类型与JAVA类型转换
	 * @param type
	 * @return
	 */
    public static String typeTrans(String type) {
        if (type.contains("tinyint")) {
            return "boolean";
        } else if (type.contains("datetime")) {
            return "Date";
        } else if (type.contains("BIGINT") || type.contains("bigint")) {
            return "Long";
        } else if (type.contains("int")) {
            return "int";
        } else if (type.contains("varchar") || type.contains("date")
                || type.contains("time") || type.contains("timestamp")
                || type.contains("text") || type.contains("enum")
                || type.contains("set")) {
            return "String";
        } else if (type.contains("binary") || type.contains("blob")) {
            return "byte[]";
        } else {
            return "String";
        }
    }
  
}
