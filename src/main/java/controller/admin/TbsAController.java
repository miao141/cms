package controller.admin;

import com.alibaba.fastjson.JSON;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.multipart.MultipartFile; 
import org.springframework.web.multipart.MultipartHttpServletRequest; 
import org.springframework.web.servlet.ModelAndView; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.ui.ModelMap; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import java.util.Arrays; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 

import model.TbsAModel; 
import service.TbsAService;
import service.TestService;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.web.bind.WebDataBinder;	
import org.springframework.web.bind.annotation.InitBinder;	
import org.springframework.stereotype.Controller;	

import service.TbsAService;	
import util.core.MethodUtil;	
import util.spring.MyTimestampPropertyEdit;	
import util.spring.SessionUtil;	
import util.core.ExcelUtil;	
import util.core.PageParams;	

import java.io.ByteArrayInputStream;	
import java.io.IOException;	
import java.io.OutputStream;	
/**
**@author miao
**@       2016011314
*/

@Controller
@RequestMapping("/admin/TbsA/")
public class TbsAController extends BaseController{
	private final static Logger log= Logger.getLogger(TbsAController.class);
	private static  MethodUtil util = new MethodUtil();
	
	@Autowired
	private TbsAService<TbsAModel> tbsAService; 
	@Autowired
	private TestService<String> testService;
	
	@Autowired
	service.TbsMenuService<model.TbsMenuModel> tbsMenuService;

	@InitBinder//������һ������WebDataBinder ��������װ��
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Timestamp.class,new MyTimestampPropertyEdit()); //ʹ���Զ������Ա༭��
	}
	@RequestMapping("index.html")
	public ModelAndView index(String id, ModelMap modelMap, HttpServletRequest request) {
	List<String> buttons = new java.util.ArrayList<String>();
	model.TbsMenuModel tbsMenuModel=new model.TbsMenuModel();
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("parentId", id);
	map.put("orderCondition", "sortNumber");
	System.out.println("id:" + id);
	String isAdmin = (String) SessionUtil.getAttr(request, "isAdmin");
	List<model.TbsMenuModel> list=null;
	try {
	if (null != isAdmin && isAdmin.equals("0")) {// ���ܲ���Ҫ��֤Ȩ��
	list = tbsMenuService.selectByMap(map);
	} else {
	list = tbsMenuService.selectByButtons(map);
	}
	if (list != null && list.size() > 0) {
	for (int i = 0; i < list.size(); i++) {
		tbsMenuModel = list.get(i);
		String button = tbsMenuModel.getButton();
		if (null != button && "null" != button) {
			buttons.add(button);//.replaceAll("&apos;", "'").replaceAll("&quot;", "\")
		}
	}
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	modelMap.addAttribute("buttons", buttons);
	return new ModelAndView("admin/TbsA/index", modelMap);
 }
 
 
 @RequestMapping("data.html")
 @ResponseBody
 public String data(PageParams pageParams, TbsAModel tbsAModel) throws Exception {
	 testService.getSeq("12");
   tbsAModel.getPageUtil().setPaging(true);
 	 String result = "[]";
 	 if (pageParams.getPage() != null) {
 		try {
 			tbsAModel.getPageUtil().setPageId(Integer.parseInt(pageParams.getPage())); // ��ǰҳ
 		} catch (Exception e) {
 			log.error(e);
 		}
 	 }
   if (pageParams.getRows() != null) {
 		try {
 			tbsAModel.getPageUtil().setPageSize(Integer.parseInt(pageParams.getRows()));// ��ʾX��
 		} catch (Exception e) {
			log.error(e);
		}
    }
	 if (pageParams.getSort() != null) {
	  try {
 		 tbsAModel.getPageUtil().setOrderByCondition(pageParams.getSort()); // �����ֶ�����
 		} catch (Exception e) {
 			log.error(e);
 	    }
 	 }
 	 String str="";
 	 String suffix = "}";
 	 if(pageParams.getGridName() != null){
 	 	str="[";
 	 	suffix="]}";
 	 }
 	 List<TbsAModel> listTbsAModel = null;
 	 StringBuilder center = new StringBuilder();
 	 
 	 if (pageParams.getSearchType() != null) {
 	 	if (pageParams.getSearchType().equals("1")) { // ģ������
 	 		tbsAModel.getPageUtil().setLike(true);
 	 		listTbsAModel = tbsAService.selectByModel(tbsAModel);
    center.append("{\"total\":\""+ tbsAModel.getPageUtil().getRowCount() +"\",\"rows\":"+str);
    } else {
    StringBuffer sb = new StringBuffer(); // �߼���ѯ
    String[] searchColumnNameArray = pageParams.getSearchColumnNames().split("\\,");
    String[] searchAndsArray = pageParams.getSearchAnds().split("\\,");
    String[] searchConditionsArray = pageParams.getSearchConditions().split("\\,");
    String[] searchValsArray = pageParams.getSearchVals().split("\\,");
    System.out.println(Arrays.toString(searchColumnNameArray));
    for (int i = 0; i < searchColumnNameArray.length; i++) {
    	if (searchColumnNameArray[i].trim().length() > 0 && searchConditionsArray[i].trim().length() > 0) {
    		if (i == 0) {
    			sb.append(searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " '" + searchValsArray[i].trim() + "'");
 			} else { 				sb.append(" " + searchAndsArray[i].trim() + " " + searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " '" + searchValsArray[i].trim() + "'");
 			}
 		}
 	  }
 	  if (sb.length() > 0) {
 	  System.out.println("searchCondition:" + sb.toString());
 	  tbsAModel.getPageUtil().setAndCondition(sb.toString());
 	  listTbsAModel = tbsAService.selectByModel(tbsAModel);
    center.append("{\"total\":\""+ tbsAModel.getPageUtil().getRowCount() +"\",\"rows\":"+str);
 	  }
 	}
 	} else {
 	  if (pageParams.getGridName() == null) {
 	  listTbsAModel = tbsAService.selectByModel(tbsAModel);
    center.append("{\"total\":\""+ tbsAModel.getPageUtil().getRowCount() +"\",\"rows\":"+str);
 		suffix = "}";
 		} else {
 		}
 	}
 	if (pageParams.getGridName() == null) { //datagrid
 		center.append(JSON.toJSONString(listTbsAModel));
 		} else {
 		}
 		center.append(suffix);
 		result = center.toString();
 		System.out.println("json:" + result);
 		return result;
 		}
 		
 		
	@RequestMapping("charts.html")
	public String charts(){		
 		return "admin/TbsA/charts";		
 	}
 	
 	
 	
 	@RequestMapping("baseDlg.html")
 	public String baseDlg(){
 		return "admin/TbsA/baseDlg";
 	}
 	
 	
 	@RequestMapping("importDlg.html")
 	public String importDlg(){
 		return "admin/TbsA/importDlg";
 	}
 	
 	
 	@RequestMapping("add.html")
 	public void add(TbsAModel tbsAModel,HttpServletResponse response){
 	tbsAModel.setId(util.getUid());//��������
 	System.out.println("tbsBModel:"+tbsAModel.toString());
 	 try {
 		if(tbsAService.insert(tbsAModel)>0){
 		util.toJsonMsg(response, 0, null);
 		return;
 		};
 		} catch (Exception e) {
 		e.printStackTrace();
 		}
 	 util.toJsonMsg(response, 1, null);
 	}
 	
 	
 	@RequestMapping("save.html")
 	public void save(TbsAModel tbsAModel,HttpServletResponse response){
 		try{
 			if(tbsAService.updateByPrimaryKey(tbsAModel)>0){
 				 util.toJsonMsg(response, 0, null);
 	  			 return;
 	 		}
 		}catch(Exception e){
 			util.toJsonMsg(response, 1, null);
 			e.printStackTrace();
 		}
 	}
 	
 	
 	@RequestMapping("del.html") 
 	public void del(String[] ids,HttpServletResponse response){
 		System.out.println("del-ids:"+Arrays.toString(ids));
 		try{
 		 if(null!=ids && ids.length>0){
 		if(tbsAService.deleteByPrimaryKeys(ids)>0){
 		util.toJsonMsg(response, 0, null);
 		return;
 	 	}
 	 	}
 		}catch(Exception e){
 		util.toJsonMsg(response, 1, null);
 		log.error(e);
 		}
 	}
}
