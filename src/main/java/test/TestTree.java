package test;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

 

/*import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;*/

public class TestTree {

	public static void main(String[] args) {
		 
		String reqResult = "{type->0,ruleKey->empty,value->${generator.generatorNode.rootNode.selectChild(\"title\").crawData.dataValue}}";//"{type->0,ruleKey->tb_cat}";
		//reqResult = reqResult.replaceAll("->", ":");
		String getSignInfo = reqResult.substring(reqResult.indexOf("{") + 1, reqResult.indexOf("}"));
		//JSONObject json = new JSONObject();
		String[] a = getSignInfo.split(",");
		
		//JSONObject obj = (JSONObject) com.alibaba.fastjson.JSONObject.parse(str);
		
		//{type->0,ruleKey->empty,value->${generator.generatorNode.rootNode.selectChild("title").crawData.dataValue}}
		String aa = a[0];
		String[] aaa = aa.split("->");
		String ab = a[1];
		String[] aab = ab.split("->");
		String stra = aaa[0]+"  "+aaa[1];
		String strb = aab[0]+"  "+aab[1];
		String value = aab[1];
		System.out.println(value);
		//System.out.println(strb);
		
		if(a.length > 2){
			//System.out.println(getSignInfo);
			String ac = a[2];
			String[] aac = ac.split("->");
			String strc = aac[0]+"  "+aac[1];
			//System.out.println(strc);
		}
		
		if("empty".equals(value)){
			
		} else {
			//getName(value) 
		}
	}
	
	public static List<String> getName(String value){
		
		return null;
	}
}
