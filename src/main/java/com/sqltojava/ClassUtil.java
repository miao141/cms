package com.sqltojava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.miao.ChangeFileCode;

import service.BaseService;
 
import service.imp.BaseServiceImp;
import util.core.MethodUtil;
import util.core.PageParams;
import util.spring.MyTimestampPropertyEdit;
import util.spring.SessionUtil;
import controller.admin.BaseController;
import controller.admin.TbsBControllerAdmin;
import dao.BaseMapper;
import dao.TbsAMapper;
import model.BaseModel;
import model.TbsBModel;

public class ClassUtil {

	//分期项目
	public static String PACKAGE_SERVICE_IMP = "package com.ucf.staging.service.order.imp;";
	public static String PACKAGE_SERVICE = "package com.ucf.staging.service.order;";
	public static String PACKAGE_MODEL = "package com.ucf.staging.model.order;";
	public static String PACKAGE_DAO = "package com.ucf.staging.dao.order;";
	public static String PACKAGE_DAO_MAPPER = "package com.ucf.staging.dao.order.mapper;";
	public static String PACKAGE_CONTROLLER = "package com.ucf.staging.controller.order;";
	
	
	/**
	 * 创建javabean
	 * @param temple
	 * @param route
	 * @param packName
	 */
	public static void templeTOBean(Temple temple,String route,String packName){
		Map<String,String> dbInfoMap = new HashMap<String, String>();
		dbInfoMap.put("route", route);
		dbInfoMap.put("packName", packName);
		dbInfoMap.put("columnKey", temple.getColumnKey());
		
		createBean(temple.getTable(), temple.getSx(), temple.getLx(),temple.getZx(), dbInfoMap);
		createOtherBean(temple.getTable(), temple.getSx(), temple.getLx(),temple.getZx(), dbInfoMap);
	}
	
	/**
	 * 创建JavaBean文件
	 * @param tbName
	 * @param sxList
	 * @param lxList
	 * @return
	 */
	public static void createBean(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {

		StringBuilder classs = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder methods = new StringBuilder();

		StringBuilder classInfo = new StringBuilder("/**\r\n*");
		//classs.append(getClassStr(tbName));
		
		classs.append("public ").append(upperFirestChar(tbName)+"Model")
        .append("( ){\r\n\t\t\r\n\t}");
		
		for (int i = 0; i < sxList.size(); i++) {
			String field = sxList.get(i);
			String type = lxList.get(i);
			String notes = zxList.get(i);
			fields.append(getFieldStr(field, type, notes));
			methods.append(getMethodStr(field, type));

		}
		classInfo.append("*@author miao\r\n*");
		classInfo.append("*@       2016011314");
		classInfo.append("\r\n*/\r\n\r\n");
		classInfo.append("public class ").append(upperFirestChar(tbName)+"Model")
				.append(" extends BaseModel").append("{\r\n");
		classInfo.append(fields);
		classInfo.append("\r\n");
		classInfo.append(classs);
		classInfo.append("\r\n");
		classInfo.append(methods);
		classInfo.append("\r\n");
		classInfo.append("}");
		File file = new File(infoMap.get("route")+"//model", upperFirestChar(tbName)+"Model"
				+ ".java");
		   String filePath = file.getParent(); 
		    File filePatha = new File(filePath);
		    if(!filePatha.exists())
		    	filePatha.mkdirs(); 
		
		try {
			//FileWriter fw = new FileWriter(file);
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
		/*	if (infoMap.get("packName") == null
					|| infoMap.get("packName").toString().equals("")) {

			} else {
				String packageinfo = "package "
						+ infoMap.get("packName").toString() + ";\r\n\r\n";
				fw.write(packageinfo);
			}*/
			String packageinfo = PACKAGE_MODEL + "\r\n\r\n";
			fw.write(packageinfo);
			
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	
	public static void createOtherBean(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap){
		createControllerBean(tbName, sxList, lxList, zxList, infoMap);
		createDaoBean(tbName, sxList, lxList, zxList, infoMap);
		createServiceBean(tbName, sxList, lxList, zxList, infoMap);
		createServiceImpBean(tbName, sxList, lxList, zxList, infoMap);
		//xml的创建
		createDaoMapperBean(tbName, sxList, lxList, zxList, infoMap);
		//页面的创建
	}
	
	public static void createDaoBean(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		StringBuilder classs = new StringBuilder();
		StringBuilder classInfo = new StringBuilder();
		classs.append(getControllerClassStr(tbName));
		String packageName = "package dao;";
		packageName = PACKAGE_DAO;
		classInfo.append("public interface "+upperFirestChar(tbName)+"Mapper<T> extends BaseMapper<T> {\r\n");
		classInfo.append("\r\n");
		classInfo.append("}\r\n");
		File file = new File(infoMap.get("route")+"//dao", upperFirestChar(tbName)
				+"Mapper"+ ".java");
		String filePath = file.getParent(); 
		File filePatha = new File(filePath);
		if(!filePatha.exists())
		    	filePatha.mkdirs(); 
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			if (infoMap.get("packName") == null
					|| infoMap.get("packName").toString().equals("")) {

			} else {
				String packageinfo = packageName + "\r\n\r\n";
				fw.write(packageinfo);
			}

			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void createServiceImpBean(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		StringBuilder classs = new StringBuilder();
		StringBuilder classInfo = new StringBuilder();
		classs.append(getControllerClassStr(tbName));
		String packageName = "package service.imp;";
		packageName = PACKAGE_SERVICE_IMP;
		classInfo.append("import dao."+upperFirestChar(tbName)+"Mapper;\r\n");
		classInfo.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
		classInfo.append("import org.springframework.stereotype.Service;\r\n");
		classInfo.append("import service."+upperFirestChar(tbName)+"Service;\r\n");

		
		
		classInfo.append("@Service(\""+lowerFirestChar(tbName)+"Service\")\r\n");
		classInfo.append("public class "+upperFirestChar(tbName)+"ServiceImp<T>  extends BaseServiceImp<T> implements "+upperFirestChar(tbName)+"Service<T> {");
		classInfo.append("	 \r\n");
		classInfo.append("	@Autowired \r\n");
		classInfo.append("	private "+upperFirestChar(tbName)+"Mapper<T> mapper; \r\n");
		classInfo.append("	 \r\n");
		classInfo.append("	 public "+upperFirestChar(tbName)+"Mapper<T> getMapper() {\r\n");
		classInfo.append("	  return mapper;\r\n");
		classInfo.append("	 }\r\n");
		
		classInfo.append("	}\r\n");
		
		File file = new File(infoMap.get("route")+"//service//imp", upperFirestChar(tbName)
				+"ServiceImp"+ ".java");
		String filePath = file.getParent(); 
		File filePatha = new File(filePath);
		if(!filePatha.exists())
		    	filePatha.mkdirs();
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			if (infoMap.get("packName") == null
					|| infoMap.get("packName").toString().equals("")) {

			} else {
				String packageinfo = packageName + "\r\n\r\n";
				fw.write(packageinfo);
			}

			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createServiceBean(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		StringBuilder classs = new StringBuilder();
		StringBuilder classInfo = new StringBuilder();
		classs.append(getControllerClassStr(tbName));
		String packageName = "package service;";
		packageName = PACKAGE_SERVICE;
		classInfo.append("import dao."+upperFirestChar(tbName)+"Mapper;\r\n");
		classInfo.append("public interface "+upperFirestChar(tbName)+"Service<T>  extends BaseService<T> , "+upperFirestChar(tbName)+"Mapper<T> {");
		classInfo.append("	 \r\n");
		classInfo.append("	}\r\n");
		
		File file = new File(infoMap.get("route")+"//service", upperFirestChar(tbName)
				+"Service"+ ".java");
		String filePath = file.getParent(); 
		File filePatha = new File(filePath);
		if(!filePatha.exists())
		    	filePatha.mkdirs(); 
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			if (infoMap.get("packName") == null
					|| infoMap.get("packName").toString().equals("")) {

			} else {
				String packageinfo = packageName + "\r\n\r\n";
				fw.write(packageinfo);
			}

			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 创建JavaBean文件
	 * @param tbName
	 * @param sxList
	 * @param lxList
	 * @return
	 */
	public static void createControllerBean(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		StringBuilder classs = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		StringBuilder classInfo = new StringBuilder();
		classs.append(getControllerClassStr(tbName));
		
		String packageName = "package controller.admin;";
		packageName = PACKAGE_CONTROLLER;
		classInfo.append("import com.alibaba.fastjson.JSON;\r\n");
		classInfo.append("import org.apache.poi.ss.usermodel.Workbook;\r\n");
		classInfo.append("import org.springframework.web.bind.annotation.ResponseBody; \r\n");
		classInfo.append("import org.springframework.web.multipart.MultipartFile; \r\n");
		classInfo.append("import org.springframework.web.multipart.MultipartHttpServletRequest; \r\n");
		classInfo.append("import org.springframework.web.servlet.ModelAndView; \r\n");
		classInfo.append("import org.springframework.web.bind.annotation.RequestMapping; \r\n");
		classInfo.append("import org.springframework.ui.ModelMap; \r\n");
		classInfo.append("import javax.servlet.http.HttpServletRequest; \r\n");
		classInfo.append("import javax.servlet.http.HttpServletResponse; \r\n");
		classInfo.append("import java.util.Arrays; \r\n");
		classInfo.append("import java.util.HashMap; \r\n");
		classInfo.append("import java.util.List; \r\n");
		classInfo.append("import java.util.Map; \r\n");
		classInfo.append("import model."+upperFirestChar(tbName)+"Model; \r\n");
		classInfo.append("import service."+upperFirestChar(tbName)+"Service;\r\n");
		classInfo.append("import java.sql.Timestamp;\r\n");
		classInfo.append("import org.apache.log4j.Logger;\r\n");
		classInfo.append("import org.springframework.beans.factory.annotation.Autowired;	\r\n");
		classInfo.append("import org.springframework.web.bind.WebDataBinder;	\r\n");
		classInfo.append("import org.springframework.web.bind.annotation.InitBinder;	\r\n");
		classInfo.append("import org.springframework.stereotype.Controller;	\r\n");
		classInfo.append("import service.TbsAService;	\r\n");
		classInfo.append("import util.core.MethodUtil;	\r\n");
		classInfo.append("import util.spring.MyTimestampPropertyEdit;	\r\n");
		classInfo.append("import util.spring.SessionUtil;	\r\n");
		classInfo.append("import util.core.ExcelUtil;	\r\n");
		classInfo.append("import util.core.PageParams;	\r\n");
		classInfo.append("import java.io.ByteArrayInputStream;	\r\n");
		classInfo.append("import java.io.IOException;	\r\n");
		classInfo.append("import java.io.OutputStream;	\r\n");
		 
		classInfo.append("/**\r\n*");
		classInfo.append("*@author miao\r\n*");
		classInfo.append("*@       2016011314");
		classInfo.append("\r\n*/\r\n\r\n");
		classInfo.append("@Controller\r\n");
		classInfo.append("@RequestMapping(\"/admin/"+upperFirestChar(tbName)+"/\")\r\n");
		classInfo.append("public class ").append(upperFirestChar(tbName)+"Controller")
				.append(" extends BaseController").append("{\r\n");
		classInfo.append("	private final static Logger log= Logger.getLogger("
				+ upperFirestChar(tbName)+"Controller"
				+ ".class);\r\n");
		classInfo.append("	private static  MethodUtil util = new MethodUtil();\r\n");
		classInfo.append("	\r\n");
		classInfo.append("	@Autowired\r\n");
		classInfo.append("	private "
				+ upperFirestChar(tbName)+"Service<"+ upperFirestChar(tbName)+"Model> "
				+ lowerFirestChar(tbName)+"Service; \r\n");
		classInfo.append("\r\n");
		classInfo.append("	@Autowired\r\n");
		classInfo.append("	service.TbsMenuService<model.TbsMenuModel> tbsMenuService;\r\n");
		classInfo.append("\r\n");
		classInfo.append("	@InitBinder//必须有一个参数WebDataBinder 日期类型装换\r\n");
		classInfo.append("	public void initBinder(WebDataBinder binder) {\r\n");
		classInfo.append("		binder.registerCustomEditor(Timestamp.class,new MyTimestampPropertyEdit()); //使用自定义属性编辑器\r\n");
		classInfo.append("	}\r\n");
		
		classInfo.append("	@RequestMapping(\"index.html\")\r\n");
		classInfo.append("	public ModelAndView index(String id, ModelMap modelMap, HttpServletRequest request) {\r\n");
		classInfo.append("	List<String> buttons = new java.util.ArrayList<String>();\r\n");
		classInfo.append("	model.TbsMenuModel tbsMenuModel=new model.TbsMenuModel();\r\n");
		
		classInfo.append("	Map<String, Object> map = new HashMap<String, Object>();\r\n");
		classInfo.append("	map.put(\"parentId\", id);\r\n");
		classInfo.append("	map.put(\"orderCondition\", \"sortNumber\");\r\n");
		classInfo.append("	System.out.println(\"id:\" + id);\r\n");
		classInfo.append("	String isAdmin = (String) SessionUtil.getAttr(request, \"isAdmin\");\r\n");
		
		classInfo.append("	List<model.TbsMenuModel> list=null;\r\n");
		classInfo.append("	try {\r\n");
		classInfo.append("	if (null != isAdmin && isAdmin.equals(\"0\")) {// 超管不需要验证权限\r\n");
		classInfo.append("	list = tbsMenuService.selectByMap(map);\r\n");
		
		classInfo.append("	} else {\r\n");
		classInfo.append("	list = tbsMenuService.selectByButtons(map);\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	if (list != null && list.size() > 0) {\r\n");
		classInfo.append("	for (int i = 0; i < list.size(); i++) {\r\n");
		classInfo.append("		tbsMenuModel = list.get(i);\r\n");
		classInfo.append("		String button = tbsMenuModel.getButton();\r\n");
		classInfo.append("		if (null != button && \"null\" != button) {\r\n");
		classInfo.append("			buttons.add(button);//.replaceAll(\"&apos;\", \"'\").replaceAll(\"&quot;\", \"\\\")\r\n");
		classInfo.append("		}\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	} catch (Exception e) {\r\n");
		classInfo.append("	e.printStackTrace();\r\n");
		classInfo.append("	}\r\n");
		
		classInfo.append("	modelMap.addAttribute(\"buttons\", buttons);\r\n");
		classInfo.append("	return new ModelAndView(\"admin/"+ upperFirestChar(tbName)+"/index\", modelMap);\r\n");
		classInfo.append(" }\r\n");
		
		classInfo.append(" \r\n");
		classInfo.append(" \r\n");
		classInfo.append(" @RequestMapping(\"data.html\")\r\n");
		classInfo.append(" @ResponseBody\r\n");
		classInfo.append(" public String data(PageParams pageParams, "+ upperFirestChar(tbName)+"Model"+" "+lowerFirestChar(tbName)+"Model"+") throws Exception {\r\n");
		classInfo.append("   "+lowerFirestChar(tbName)+"Model"+".getPageUtil().setPaging(true);\r\n");
		classInfo.append(" 	 String result = \"[]\";\r\n");
		classInfo.append(" 	 if (pageParams.getPage() != null) {\r\n");
		classInfo.append(" 		try {\r\n");
		classInfo.append(" 			"+lowerFirestChar(tbName)+"Model"+".getPageUtil().setPageId(Integer.parseInt(pageParams.getPage())); // 当前页\r\n");
		classInfo.append(" 		} catch (Exception e) {\r\n");
		classInfo.append(" 			log.error(e);\r\n");
		classInfo.append(" 		}\r\n");
		classInfo.append(" 	 }\r\n");
		classInfo.append("   if (pageParams.getRows() != null) {\r\n");
		classInfo.append(" 		try {\r\n");
		classInfo.append(" 			"+lowerFirestChar(tbName)+"Model"+".getPageUtil().setPageSize(Integer.parseInt(pageParams.getRows()));// 显示X条\r\n");
		classInfo.append(" 		} catch (Exception e) {\r\n");
		classInfo.append("			log.error(e);\r\n");
		classInfo.append("		}\r\n");
		classInfo.append("    }\r\n");
		classInfo.append("	 if (pageParams.getSort() != null) {\r\n");
		classInfo.append("	  try {\r\n");
		classInfo.append(" 		 "+lowerFirestChar(tbName)+"Model"+".getPageUtil().setOrderByCondition(pageParams.getSort()); // 排序字段名称\r\n");
		classInfo.append(" 		} catch (Exception e) {\r\n");
		classInfo.append(" 			log.error(e);\r\n");
		classInfo.append(" 	    }\r\n");
		classInfo.append(" 	 }\r\n");
		classInfo.append(" 	 String str=\"\";\r\n");
		classInfo.append(" 	 String suffix = \"}\";\r\n");
		classInfo.append(" 	 if(pageParams.getGridName() != null){\r\n");
		classInfo.append(" 	 	str=\"[\";\r\n");
		classInfo.append(" 	 	suffix=\"]}\";\r\n");
		classInfo.append(" 	 }\r\n");
		classInfo.append(" 	 List<"+upperFirestChar(tbName)+"Model"+"> list"+upperFirestChar(tbName)+"Model"+" = null;\r\n");
		classInfo.append(" 	 StringBuilder center = new StringBuilder();\r\n");
		classInfo.append(" 	 \r\n");
		classInfo.append(" 	 if (pageParams.getSearchType() != null) {\r\n");
		classInfo.append(" 	 	if (pageParams.getSearchType().equals(\"1\")) { // 模糊搜索\r\n");
		classInfo.append(" 	 		"+lowerFirestChar(tbName)+"Model"+".getPageUtil().setLike(true);\r\n");
		classInfo.append(" 	 		list"+upperFirestChar(tbName)+"Model"+" = "+lowerFirestChar(tbName)+"Service"+".selectByModel("+lowerFirestChar(tbName)+"Model"+");\r\n");
		//center.append("{\"total\":\"" + tbsAModel.getPageUtil().getRowCount() + "\",\"rows\":"+str);
		//center.append("{"total/":/"		+ tbsBModel.getPageUtil().getRowCount() +		/",/"rows/":str    );"+
		classInfo.append("    center.append(");
		classInfo.append("\"{\\\"total\\\":\\\"\"");	
		classInfo.append("+ "+lowerFirestChar(tbName)+"Model"+".getPageUtil().getRowCount() +");
		classInfo.append("\"\\\",\\\"rows\\\":\""+"+str");
		classInfo.append(");\r\n");
		
		classInfo.append("    } else {\r\n");
		classInfo.append("    StringBuffer sb = new StringBuffer(); // 高级查询\r\n");
		classInfo.append("    String[] searchColumnNameArray = pageParams.getSearchColumnNames().split(\"\\\\,\");\r\n");
		classInfo.append("    String[] searchAndsArray = pageParams.getSearchAnds().split(\"\\\\,\");\r\n");
		classInfo.append("    String[] searchConditionsArray = pageParams.getSearchConditions().split(\"\\\\,\");\r\n");
		classInfo.append("    String[] searchValsArray = pageParams.getSearchVals().split(\"\\\\,\");\r\n");
		classInfo.append("    System.out.println(Arrays.toString(searchColumnNameArray));\r\n");
		classInfo.append("    for (int i = 0; i < searchColumnNameArray.length; i++) {\r\n");
		classInfo.append("    	if (searchColumnNameArray[i].trim().length() > 0 && searchConditionsArray[i].trim().length() > 0) {\r\n");
		classInfo.append("    		if (i == 0) {\r\n");
		classInfo.append("    			sb.append(searchColumnNameArray[i].trim() + \" \" + searchConditionsArray[i].trim() + \" '\" + searchValsArray[i].trim() + \"'\");\r\n");
		classInfo.append(" 			} else {");
		classInfo.append(" 				sb.append(\" \" + searchAndsArray[i].trim() + \" \" + searchColumnNameArray[i].trim() + \" \" + searchConditionsArray[i].trim() + \" '\" + searchValsArray[i].trim() + \"'\");\r\n");
		classInfo.append(" 			}\r\n");
		classInfo.append(" 		}\r\n");
		classInfo.append(" 	  }\r\n");
		classInfo.append(" 	  if (sb.length() > 0) {\r\n");
		classInfo.append(" 	  System.out.println(\"searchCondition:\" + sb.toString());\r\n");
		classInfo.append(" 	  "+lowerFirestChar(tbName)+"Model"+".getPageUtil().setAndCondition(sb.toString());\r\n");
		classInfo.append(" 	  list"+upperFirestChar(tbName)+"Model"+" = "+lowerFirestChar(tbName)+"Service"+".selectByModel("+lowerFirestChar(tbName)+"Model);\r\n");
		classInfo.append("    center.append(");
		classInfo.append("\"{\\\"total\\\":\\\"\"");	
		classInfo.append("+ "+lowerFirestChar(tbName)+"Model"+".getPageUtil().getRowCount() +");
		classInfo.append("\"\\\",\\\"rows\\\":\""+"+str");
		classInfo.append(");\r\n");
		classInfo.append(" 	  }\r\n");
		classInfo.append(" 	}\r\n");
		classInfo.append(" 	} else {\r\n");
		classInfo.append(" 	  if (pageParams.getGridName() == null) {\r\n");
		classInfo.append(" 	  list"+upperFirestChar(tbName)+"Model"+" = "+lowerFirestChar(tbName)+"Service"+".selectByModel("+lowerFirestChar(tbName)+"Model"+");\r\n");
		classInfo.append("    center.append(");
		classInfo.append("\"{\\\"total\\\":\\\"\"");	
		classInfo.append("+ "+lowerFirestChar(tbName)+"Model"+".getPageUtil().getRowCount() +");
		classInfo.append("\"\\\",\\\"rows\\\":\""+"+str");
		classInfo.append(");\r\n");
		classInfo.append(" 		suffix = \"}\";\r\n");
		classInfo.append(" 		} else {\r\n");
		classInfo.append(" 		}\r\n");
		classInfo.append(" 	}\r\n");
		classInfo.append(" 	if (pageParams.getGridName() == null) { //datagrid\r\n");
		classInfo.append(" 		center.append(JSON.toJSONString(list"+upperFirestChar(tbName)+"Model"+"));\r\n");
		classInfo.append(" 		} else {\r\n");
		classInfo.append(" 		}\r\n");
		classInfo.append(" 		center.append(suffix);\r\n");
		classInfo.append(" 		result = center.toString();\r\n");
		classInfo.append(" 		System.out.println(\"json:\" + result);\r\n");
		classInfo.append(" 		return result;\r\n");
		classInfo.append(" 		}\r\n");
		
		classInfo.append(" 		\r\n");
		classInfo.append(" 		\r\n");
		classInfo.append("	@RequestMapping(\"charts.html\")\r\n");
		classInfo.append("	public String charts(){		\r\n");
		classInfo.append(" 		return \"admin/"+upperFirestChar(tbName)+"/charts\";		\r\n");
		classInfo.append(" 	}\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	@RequestMapping(\"baseDlg.html\")\r\n");
		classInfo.append(" 	public String baseDlg(){\r\n");
		classInfo.append(" 		return \"admin/"+upperFirestChar(tbName)+"/baseDlg\";\r\n");
		classInfo.append(" 	}\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	@RequestMapping(\"importDlg.html\")\r\n");
		classInfo.append(" 	public String importDlg(){\r\n");
		classInfo.append(" 		return \"admin/"+upperFirestChar(tbName)+"/importDlg\";\r\n");
		classInfo.append(" 	}\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	@RequestMapping(\"add.html\")\r\n");
		classInfo.append(" 	public void add("+upperFirestChar(tbName)+"Model"+" "+lowerFirestChar(tbName)+"Model"+",HttpServletResponse response){\r\n");
		classInfo.append(" 	"+lowerFirestChar(tbName)+"Model"+".setId(util.getUid());//设置主键\r\n");
		classInfo.append(" 	System.out.println(\"tbsBModel:\"+"+lowerFirestChar(tbName)+"Model"+".toString());\r\n");
		classInfo.append(" 	 try {\r\n");
		classInfo.append(" 		if("+lowerFirestChar(tbName)+"Service"+".insert("+lowerFirestChar(tbName)+"Model"+")>0){\r\n");
		classInfo.append(" 		util.toJsonMsg(response, 0, null);\r\n");
		classInfo.append(" 		return;\r\n");
		classInfo.append(" 		};\r\n");
		classInfo.append(" 		} catch (Exception e) {\r\n");
		classInfo.append(" 		e.printStackTrace();\r\n");
		classInfo.append(" 		}\r\n");
		classInfo.append(" 	 util.toJsonMsg(response, 1, null);\r\n");
		classInfo.append(" 	}\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	@RequestMapping(\"save.html\")\r\n");
		classInfo.append(" 	public void save("+upperFirestChar(tbName)+"Model"+" "+lowerFirestChar(tbName)+"Model"+",HttpServletResponse response){\r\n");
		classInfo.append(" 		try{\r\n");
		classInfo.append(" 			if("+lowerFirestChar(tbName)+"Service"+".updateByPrimaryKey("+lowerFirestChar(tbName)+"Model"+")>0){\r\n");
		classInfo.append(" 				 util.toJsonMsg(response, 0, null);\r\n");
		classInfo.append(" 	  			 return;\r\n");
		classInfo.append(" 	 		}\r\n");
		classInfo.append(" 		}catch(Exception e){\r\n");
		classInfo.append(" 			util.toJsonMsg(response, 1, null);\r\n");
		classInfo.append(" 			e.printStackTrace();\r\n");
		classInfo.append(" 		}\r\n");
		classInfo.append(" 	}\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	\r\n");
		classInfo.append(" 	@RequestMapping(\"del.html\") \r\n");
		classInfo.append(" 	public void del(String[] ids,HttpServletResponse response){\r\n");
		classInfo.append(" 		System.out.println(\"del-ids:\"+Arrays.toString(ids));\r\n");
		classInfo.append(" 		try{\r\n");
		classInfo.append(" 		 if(null!=ids && ids.length>0){\r\n");
		classInfo.append(" 		if("+lowerFirestChar(tbName)+"Service"+".deleteByPrimaryKeys(ids)>0){\r\n");	 		 	
		classInfo.append(" 		util.toJsonMsg(response, 0, null);\r\n");
		classInfo.append(" 		return;\r\n");
		classInfo.append(" 	 	}\r\n");
		classInfo.append(" 	 	}\r\n");		   
		classInfo.append(" 		}catch(Exception e){\r\n");		   
		classInfo.append(" 		util.toJsonMsg(response, 1, null);\r\n");		   
		classInfo.append(" 		log.error(e);\r\n");		   
		classInfo.append(" 		}\r\n");		   
		classInfo.append(" 	}\r\n");		   
		
 
		classInfo.append("}\r\n");
		File file = new File(infoMap.get("route")+"//controller//admin", upperFirestChar(tbName)
				+"Controller"+ ".java");
			 
	    String filePath = file.getParent(); 
	    File filePatha = new File(filePath);
	    if(!filePatha.exists())
	    	filePatha.mkdirs(); 
		
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			if (infoMap.get("packName") == null
					|| infoMap.get("packName").toString().equals("")) {

			} else {
				String packageinfo = packageName + "\r\n\r\n";
				fw.write(packageinfo);
			}

			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void createDaoMapperBean(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		StringBuilder classs = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		StringBuilder classInfo = new StringBuilder();
		classs.append(getControllerClassStr(tbName));
		
		 
		
		String fileTop = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \r\n";
		classInfo.append(fileTop);
		classInfo.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
		classInfo.append("\r\n");
		classInfo.append("<mapper namespace=\"dao."+upperFirestChar(tbName)+"Mapper\" > \r\n");
		 
		classInfo.append("<!-- Result Map 数据库映射到实体类  --> \r\n");
		classInfo.append("<resultMap id=\"tableMapToModel\" type=\"model."+upperFirestChar(tbName)+"Model\" >\r\n");
		for (int i = 0; i < sxList.size(); i++) {
			String attribute = sxList.get(i);
			classInfo.append("<result column=\""+attribute+"\" property=\""+attribute+"\"/> \r\n");
		}
		classInfo.append("</resultMap>\r\n");
		classInfo.append(" \r\n");	
		classInfo.append("<!-- 所有条件分页 --> \r\n");
		classInfo.append("<sql id=\"model\"> \r\n");
		classInfo.append("  <include refid=\"modelCount\"/>\r\n");
		classInfo.append("  ${pageUtil.orderByCondition}\r\n");
		classInfo.append("  <if test=\"pageUtil.paging == true\" >\r\n");
		classInfo.append(" ${pageUtil.limit}\r\n");
		classInfo.append(" </if>\r\n");
		classInfo.append(" </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append(" <!-- 总记录数 --> \r\n");
		classInfo.append("  <sql id=\"modelCount\">\r\n");
		classInfo.append("  <where>\r\n");
		classInfo.append("  	<choose>\r\n");
		classInfo.append("  	<when test=\"pageUtil.like==true\">\r\n");
		classInfo.append("  	 <include refid=\"andOtherAllLike\"/>\r\n");
		classInfo.append("  	 </when>\r\n");
		classInfo.append("   <otherwise>\r\n");
		classInfo.append("  	 <include refid=\"andOtherAll\"/>\r\n");
		classInfo.append("   </otherwise>\r\n");
		classInfo.append("   </choose>\r\n");
		classInfo.append("   ${pageUtil.andCondition}\r\n");
		classInfo.append("  </where>\r\n");
		classInfo.append("  ${pageUtil.queryCondition}\r\n");
		classInfo.append("  </sql> \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <sql id=\"tbaleColumns\"> \r\n");
		classInfo.append("  <trim suffix=\"\" suffixOverrides=\",\"> \r\n");
		for (int i = 0; i < sxList.size(); i++) {
			String attribute = sxList.get(i);
			classInfo.append(attribute+",");
		}
		classInfo.append("  \r\n");
		classInfo.append("  </trim>\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <sql id=\"insertColumns\">\r\n");
		classInfo.append("  <trim suffix=\"\" suffixOverrides=\",\">\r\n");
		for (int i = 0; i < sxList.size(); i++) {
			String attribute = sxList.get(i);
			
			classInfo.append("<if test=\""+attribute+" != null and "+attribute+" != '' \" > \r\n");
			classInfo.append(attribute+",");
			classInfo.append(" </if> \r\n");
		}
		
		classInfo.append("   </trim>\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <sql id=\"insertParams\">\r\n");
		classInfo.append("  <trim suffix=\"\" suffixOverrides=\",\">\r\n");
		for (int i = 0; i < sxList.size(); i++) {
			String attribute = sxList.get(i);
			classInfo.append("<if test=\""+attribute+" != null and "+attribute+" != '' \" > \r\n");
			classInfo.append("#{"+attribute+"},");
			classInfo.append(" </if> \r\n");
		}
		classInfo.append("  </trim>\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <sql id=\"updateParams\">\r\n");
		classInfo.append("  <trim suffix=\"\" suffixOverrides=\",\">\r\n");
		for (int i = 0; i < sxList.size(); i++) {
			String attribute = sxList.get(i);
			classInfo.append("<if test=\""+attribute+" != null and "+attribute+" != '' \" > \r\n");
			classInfo.append(""+attribute+"=#{"+attribute+"},");
			classInfo.append(" </if> \r\n");
		}
		classInfo.append("  </trim>\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <sql id=\"andOther\">\r\n");
		classInfo.append("  <trim  suffixOverrides=\",\" >\r\n");
		for (int i = 0; i < sxList.size(); i++) {
			String attribute = sxList.get(i);
			classInfo.append(" <if test=\""+attribute+" != null \" > \r\n");
			classInfo.append("   and "+attribute+" =  #{"+attribute+"}\r\n");
			classInfo.append(" </if> \r\n");
		}
		classInfo.append("  </trim>\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <sql id=\"andOtherAll\">\r\n");
		classInfo.append("  <trim  suffixOverrides=\",\" >\r\n");
		for (int i = 0; i < sxList.size(); i++) {
			String attribute = sxList.get(i);
			classInfo.append("<if test=\""+attribute+" != null and "+attribute+" != '' \" > \r\n");
			classInfo.append("and "+attribute+" =  #{"+attribute+"}");
			classInfo.append(" </if> \r\n");
		}
		classInfo.append("  </trim>\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 模糊查询判断 -->\r\n");
		classInfo.append("  <sql id=\"andOtherAllLike\">\r\n");
		classInfo.append("    <trim  suffixOverrides=\",\" >\r\n");
		for (int i = 0; i < sxList.size(); i++) {
			String attribute = sxList.get(i);
			classInfo.append("	<if test=\""+attribute+" != null and "+attribute+" != '' \" > \r\n");
			classInfo.append("	and id like  &apos;%${"+attribute+"}%&apos;");
			classInfo.append("  </if> \r\n");
		}
		classInfo.append("    </trim>\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- where 条件  -->\r\n");
		classInfo.append("  <sql id=\"where\">\r\n");
		classInfo.append("   where 1=1\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append(" <!-- columnKey  主键 列名称 ,视图获取不到主键 需要设置 -->\r\n");
		classInfo.append("  <sql id=\"columnKey\">\r\n");
		classInfo.append("   and id=#{id}\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 自定义查询 -->\r\n");
		classInfo.append("  <sql id=\"sql\">\r\n");
		classInfo.append("   <if test=\"sql != null and sql !=''\">\r\n");
		classInfo.append("  ${sql}\r\n");
		classInfo.append("  </if>\r\n");
		classInfo.append("  <if test=\"sql == null || sql==''\"> \r\n");
		classInfo.append("   select <include refid=\"tbaleColumns\"/> from "+upperFirestChar(tbName)+" <include refid=\"where\"/>\r\n");
		classInfo.append("  </if>\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 查询条件 -->\r\n");
		classInfo.append("  <sql id=\"queryCondition\">\r\n");
		classInfo.append("    <if test=\"queryCondition != null and queryCondition != ''\">\r\n");
		classInfo.append("   	${queryCondition}\r\n");
		classInfo.append("    </if>\r\n");
		classInfo.append("  </sql>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 添加,插入记录   -->\r\n");
		classInfo.append("  <insert id=\"insert\"  parameterType=\"model."+upperFirestChar(tbName)+"Model\" >\r\n");
		classInfo.append("    insert into "+upperFirestChar(tbName)+" (<include refid=\"insertColumns\"/>) values(<include refid=\"insertParams\"/>);\r\n");
		classInfo.append("  </insert>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 添加,SQL添加   -->\r\n");
		classInfo.append("  <insert id=\"insertBySql\" parameterType=\"java.lang.String\">\r\n");
		classInfo.append("   <include refid=\"sql\"/>\r\n");
		classInfo.append("  </insert>\r\n");
		classInfo.append("  <!-- 删除,主键删除   -->\r\n");
		classInfo.append("  <delete id=\"deleteByPrimaryKey\" parameterType=\"model."+upperFirestChar(tbName)+"Model\">\r\n");
		classInfo.append("  delete from "+upperFirestChar(tbName)+"<include refid=\"where\"/><include refid=\"columnKey\"/>\r\n");
		classInfo.append("  </delete>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 删除,实体删除   -->\r\n");
		classInfo.append("  <delete id=\"deleteByEntity\" parameterType=\"model."+upperFirestChar(tbName)+"Model\">\r\n");
		classInfo.append("  	delete from "+upperFirestChar(tbName)+"<include refid=\"where\"/><include refid=\"andOtherAll\"/>\r\n");
		classInfo.append("  </delete>\r\n");
		classInfo.append("  \r\n");
		classInfo.append(" <!-- 删除,SQL删除   --> \r\n");
		classInfo.append("  <delete id=\"deleteBySql\" parameterType=\"java.lang.String\">\r\n");
		classInfo.append("   <include refid=\"sql\"/> \r\n");
		classInfo.append("  </delete>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 修改,主键更新  -->\r\n");
		classInfo.append(" <update id=\"updateByPrimaryKey\" parameterType=\"model."+upperFirestChar(tbName)+"Model\" > \r\n");
		classInfo.append("  	update "+upperFirestChar(tbName)+" <set><include refid=\"updateParams\"/></set><include refid=\"where\"/><include refid=\"columnKey\"/>\r\n");
		classInfo.append("  </update>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 修改,SQL更新 -->\r\n");
		classInfo.append("  <update id=\"updateBySql\" parameterType=\"java.lang.String\">\r\n");
		classInfo.append("   <include refid=\"sql\"/>\r\n");
		classInfo.append("  </update>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 查询,主键查询   -->\r\n");
		classInfo.append("  <select id=\"selectByPrimaryKey\"  resultMap=\"tableMapToModel\" parameterType=\"java.lang.Object\">\r\n");
		classInfo.append("  	select <include refid=\"tbaleColumns\"/> from "+upperFirestChar(tbName)+" <include refid=\"where\"/> <include refid=\"columnKey\"/>\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 查询,实体查询   -->\r\n");
		classInfo.append("  <select id=\"selectByEntiry\"  resultMap=\"tableMapToModel\" parameterType=\"model."+upperFirestChar(tbName)+"Model\">\r\n");
		classInfo.append("  	select <include refid=\"tbaleColumns\"/> from "+upperFirestChar(tbName)+" <include refid=\"where\"/> <include refid=\"andOtherAll\"/>\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 查询,SQL -->\r\n");
		classInfo.append("  <select id=\"selectBySql\"  resultMap=\"tableMapToModel\" parameterType=\"java.lang.String\">\r\n");
		classInfo.append("  <include refid=\"sql\"/>\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 查询 ,总行数,分页 ,模型 -->\r\n");
		classInfo.append("  <select id=\"selectByModelCount\" resultType=\"java.lang.Integer\"  parameterType=\"model."+upperFirestChar(tbName)+"Model\">\r\n");
		classInfo.append("  select count(1) from "+upperFirestChar(tbName)+" <include refid=\"modelCount\"/>\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 查询,排序,分页,模型  -->\r\n");
		classInfo.append("  <select id=\"selectByModel\" resultMap=\"tableMapToModel\"  parameterType=\"model."+upperFirestChar(tbName)+"Model\">\r\n");
		classInfo.append("  select <include refid=\"tbaleColumns\"/>from "+upperFirestChar(tbName)+" <include refid=\"model\"/>\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 查询,总行数,Map -->\r\n");
		classInfo.append("  <select id=\"selectByMapCount\" resultType=\"java.lang.Integer\"  parameterType=\"java.util.Map\">\r\n");
		classInfo.append("  select count(1) from "+upperFirestChar(tbName)+" <include refid=\"where\"/><include refid=\"andOtherAll\"/>\r\n");
		classInfo.append("  	    ${queryCondition}\r\n");
		classInfo.append("  <if test=\"andCondition !=null and andCondition !=''\">\r\n");
		
		classInfo.append("   and ${andCondition}\r\n");
		classInfo.append("  </if>\r\n");
		classInfo.append("  <if test=\"orderCondition != null and queryCondition != ''\" >\r\n");
		classInfo.append("  order by ${orderCondition}\r\n");
		classInfo.append("  </if>\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append(" <!-- 查询,参数查询,Map --> \r\n");
		classInfo.append("  <select id=\"selectByMap\" resultMap=\"tableMapToModel\"  parameterType=\"java.util.Map\">\r\n");
		classInfo.append("  select <include refid=\"tbaleColumns\"/>from "+upperFirestChar(tbName)+" <include refid=\"where\"/><include refid=\"andOtherAll\"/>\r\n");
		classInfo.append("  ${queryCondition}\r\n");
		classInfo.append("  <if test=\"andCondition !=null and andCondition !=''\">\r\n");
		classInfo.append("  and ${andCondition}\r\n");
		classInfo.append(" </if> \r\n");
		classInfo.append("  <if test=\"orderCondition != null and queryCondition != ''\" >\r\n");
		classInfo.append("  order by ${orderCondition}\r\n");
		classInfo.append("   </if>\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!-- 查询,总行数,Map分页 -->\r\n");
		classInfo.append("  <select id=\"selectByMapPagingCount\" resultType=\"java.lang.Integer\"  parameterType=\"java.util.Map\">\r\n");
		classInfo.append("  select count(0) from "+upperFirestChar(tbName)+" <include refid=\"where\"/><include refid=\"andOtherAll\"/>\r\n");
		classInfo.append("  ${queryCondition}\r\n");
		classInfo.append("  <if test=\"andCondition !=null and andCondition !=''\">\r\n");
		classInfo.append("  and ${andCondition}\r\n");
		classInfo.append("  </if>\r\n");
		classInfo.append("  ${pageUtil.queryCondition}\r\n");
		classInfo.append("  ${pageUtil.andCondition}\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
			
		classInfo.append("  <!-- 查询,参数查询,Map分页 -->\r\n");
		classInfo.append("  <select id=\"selectByMapPaging\" resultMap=\"tableMapToModel\"  parameterType=\"java.util.Map\">\r\n");
		classInfo.append("  select <include refid=\"tbaleColumns\"/>from "+upperFirestChar(tbName)+" <include refid=\"where\"/><include refid=\"andOtherAll\"/>\r\n");
		classInfo.append("  ${queryCondition}\r\n");
		classInfo.append("  <if test=\"andCondition !=null and andCondition !=''\">\r\n");
		classInfo.append("   and ${andCondition}\r\n");
		classInfo.append("  </if>\r\n");
		classInfo.append("  <if test=\"orderCondition != null and queryCondition != ''\" >\r\n");
		classInfo.append("  order by ${orderCondition}\r\n");
		classInfo.append("   </if>\r\n");
		classInfo.append("  	${pageUtil.queryCondition}\r\n");
		classInfo.append("  	${pageUtil.andCondition}\r\n");
		classInfo.append("  	${pageUtil.orderByCondition}\r\n");
		classInfo.append("  	${pageUtil.limit}\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <!--查询, 图表 -->\r\n");
		classInfo.append("  <select id=\"charts\" parameterType=\"java.util.Map\" resultType=\"java.util.Map\">\r\n");
		classInfo.append("  <!-- '%Y-%m-%d %H:%i:%s' -->\r\n");
		classInfo.append("  	SELECT COUNT(*),DATE_FORMAT(createTime,'${groupTimeFormat}') as createTime  FROM "+upperFirestChar(tbName)+" \r\n");
		classInfo.append("  <if test=\"startTimeFormat!=null and endTimeFormat!=null\">\r\n");
		classInfo.append("  WHERE \r\n");
		classInfo.append("  createTime&gt;=DATE_FORMAT(NOW(), '${startTimeFormat}') AND createTime&lt;=DATE_FORMAT(NOW(), '${endTimeFormat}')\r\n");
		classInfo.append("  </if> \r\n");
		classInfo.append("  GROUP BY DATE_FORMAT(createTime,'${groupTimeFormat}')\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append(" <!--/////////////// 级联递归查询  一对多 ///////////////--> \r\n");
		classInfo.append("  <resultMap type=\"model."+upperFirestChar(tbName)+"Model\" id=\"tableMapToModelChild\" extends=\"tableMapToModel\">\r\n");
		classInfo.append("    <!-- 一对一关联   注意 顺序       需要用时开启 -->\r\n");
		classInfo.append("   <!--  \r\n");
		classInfo.append("   <association property=\""+upperFirestChar(tbName)+"Model\" column=\"parentId\" select=\"getParent\">\r\n");
		classInfo.append("   </association>\r\n");
		classInfo.append("   -->\r\n");
		classInfo.append("   <!-- 一对多关联  -->\r\n");
		classInfo.append("  <collection property=\"list"+upperFirestChar(tbName)+"Model\" column=\"id\" select=\"getChild\">\r\n");
		classInfo.append("   <!-- 对象的属性 -->\r\n");
		classInfo.append("   </collection>\r\n");

		classInfo.append("  </resultMap>\r\n");
		classInfo.append("  <!--  \r\n");
		classInfo.append("  <select id=\"getParent\" resultMap=\"tableMapToModelChild\" parameterType=\"String\">\r\n");
		classInfo.append("  SELECT * FROM "+upperFirestChar(tbName)+" where id=#{parentId}\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  -->\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <select id=\"getChild\" resultMap=\"tableMapToModelChild\" parameterType=\"String\">\r\n");
		classInfo.append("   SELECT * FROM "+upperFirestChar(tbName)+" where parentId=#{id}\r\n");
		classInfo.append("  </select>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <select id=\"selectByChild\" resultMap=\"tableMapToModelChild\"  parameterType=\"model."+upperFirestChar(tbName)+"Model\">\r\n");
		classInfo.append("  SELECT <include refid=\"tbaleColumns\"/>FROM "+upperFirestChar(tbName)+"\r\n");
		classInfo.append("   <where>\r\n");
		  
		classInfo.append("  <include refid=\"andOtherAll\"/>\r\n");
		classInfo.append("  	${pageUtil.andCondition}\r\n");
		classInfo.append("    </where>\r\n");
		classInfo.append("  ${pageUtil.queryCondition}\r\n");
		classInfo.append("  ${pageUtil.orderByCondition}\r\n");
		classInfo.append("   <if test=\"pageUtil.paging == true\" >\r\n");
		classInfo.append("  ${pageUtil.limit}\r\n");
		classInfo.append("   </if>\r\n");
		classInfo.append("  </select>\r\n");

		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  </mapper>   \r\n");
	 
		
		
	//	File file = new File(infoMap.get("route"), upperFirestChar(tbName) +"Mapper"+ ".xml");
	
	    File file = new File(infoMap.get("route")+"//dao//mapper", upperFirestChar(tbName)
				+"Mapper"+ ".xml");
		 
	    String filePath = file.getParent(); 
	    File filePatha = new File(filePath);
	    if(!filePatha.exists())
	    	filePatha.mkdirs(); 
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			System.out.println("::: "+fw.getEncoding()); 
			fw.write(classInfo.toString() );
			fw.flush();
			fw.close();
			
			
		  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 
	}
    /**
     * 首字母大写
     * @param src
     * @return
     */
    public static String upperFirestChar(String src) {
        return src.substring(0, 1).toUpperCase().concat(src.substring(1));
    }
 
    /**
     * 首字母小写
     * @param src
     * @return
     */
    public static String lowerFirestChar(String src) {
        return src.substring(0, 1).toLowerCase().concat(src.substring(1));
    }
    /**
     * 获取构造函数的方法
     * @param tbName
     * @return
     */
    private static String getClassStr(String tbName) {
    	 StringBuilder sb = new StringBuilder();
         sb.append("\t").append("public ").append(upperFirestChar(tbName))
         .append("( ){\r\n\t\t\r\n\t}");
         return sb.toString();
	}
    /**
     * 获取构造函数的方法
     * @param tbName
     * @return
     */
    private static String getControllerClassStr(String tbName) {
    	 StringBuilder sb = new StringBuilder();
         sb.append("\t").append("public ").append(upperFirestChar(tbName)+"Controller")
         .append("( ){\r\n\t\t\r\n\t}");
         return sb.toString();
	}
    
    /**
     * 获取字段
     * @param field
     * @param type
     * @return
     */
    private static String getFieldStr(String field, String type, String notes) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t").append("private ").append(type).append(" ")
                .append(field).append(";");
        sb.append("//"+notes);
        sb.append("\r\n");
        return sb.toString();
    }
    
    /**
	  * 获取方法字符串 
	  * @param field
	  * @param type
	  * @return
	  */
   private static String getMethodStr(String field, String type) {
       StringBuilder get = new StringBuilder("\tpublic ");
       get.append(type).append(" ");
       if (type.equals("boolean")) {
           get.append(field);
       } else {
           get.append("get");
           get.append(upperFirestChar(field));
       }
       get.append("(){").append("\r\n\t\treturn this.").append(field)
               .append(";\r\n\t}\r\n");
       StringBuilder set = new StringBuilder("\tpublic void ");

       if (type.equals("boolean")) {
           set.append(field);
       } else {
           set.append("set");
           set.append(upperFirestChar(field));
       }
       set.append("(").append(type).append(" ").append(field)
               .append("){\r\n\t\tthis.").append(field).append("=")
               .append(field).append(";\r\n\t}\r\n");
       get.append(set);
       return get.toString();
   }
}
