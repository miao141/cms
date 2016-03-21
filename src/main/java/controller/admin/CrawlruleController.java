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

import model.CrawlruleModel; 
import model.TbsMenuModel;
import service.CrawlruleService;

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
@RequestMapping("/admin/Crawlrule/")
public class CrawlruleController extends BaseController{
	private final static Logger log= Logger.getLogger(CrawlruleController.class);
	private static  MethodUtil util = new MethodUtil();
	
	@Autowired
	private CrawlruleService<CrawlruleModel> crawlruleService; 

	@Autowired
	service.TbsMenuService<model.TbsMenuModel> tbsMenuService;

	@InitBinder//必须有一个参数WebDataBinder 日期类型装换
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Timestamp.class,new MyTimestampPropertyEdit()); //使用自定义属性编辑器
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
	if (null != isAdmin && isAdmin.equals("0")) {// 超管不需要验证权限
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
	return new ModelAndView("admin/Crawlrule/index", modelMap);
 }
 
 
 @RequestMapping("data.html")
 @ResponseBody
 public String data(PageParams pageParams, CrawlruleModel crawlruleModel) throws Exception {
   crawlruleModel.getPageUtil().setPaging(true);
 	 String result = "[]";
 	 if (pageParams.getPage() != null) {
 		try {
 			crawlruleModel.getPageUtil().setPageId(Integer.parseInt(pageParams.getPage())); // 当前页
 		} catch (Exception e) {
 			log.error(e);
 		}
 	 }
   if (pageParams.getRows() != null) {
 		try {
 			crawlruleModel.getPageUtil().setPageSize(Integer.parseInt(pageParams.getRows()));// 显示X条
 		} catch (Exception e) {
			log.error(e);
		}
    }
	 if (pageParams.getSort() != null) {
	  try {
 		 crawlruleModel.getPageUtil().setOrderByCondition(pageParams.getSort()); // 排序字段名称
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
 	 List<CrawlruleModel> listCrawlruleModel = null;
 	 StringBuilder center = new StringBuilder();
 	 
 	 if (pageParams.getSearchType() != null) {
 	 	if (pageParams.getSearchType().equals("1")) { // 模糊搜索
 	 		crawlruleModel.getPageUtil().setLike(true);
 	 		listCrawlruleModel = crawlruleService.selectByModel(crawlruleModel);
    center.append("{\"total\":\""+ crawlruleModel.getPageUtil().getRowCount() +"\",\"rows\":"+str);
    } else {
    StringBuffer sb = new StringBuffer(); // 高级查询
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
 	  crawlruleModel.getPageUtil().setAndCondition(sb.toString());
 	  listCrawlruleModel = crawlruleService.selectByModel(crawlruleModel);
    center.append("{\"total\":\""+ crawlruleModel.getPageUtil().getRowCount() +"\",\"rows\":"+str);
 	  }
 	}
 	} else {
 	  if (pageParams.getGridName() == null) {
 	  listCrawlruleModel = crawlruleService.selectByModel(crawlruleModel);
    center.append("{\"total\":\""+ crawlruleModel.getPageUtil().getRowCount() +"\",\"rows\":"+str);
 		suffix = "}";
 		} else {
 			if (crawlruleModel.getId() == null) { //null 首次打开
 				crawlruleModel.getPageUtil().setAndCondition("parentId IS NULL");
 				//crawlruleModel.getPageUtil().setOrderByCondition("sortNumber");
 				listCrawlruleModel = crawlruleService.selectByModel(crawlruleModel);
				center.append("{\"total\":\"" + crawlruleModel.getPageUtil().getRowCount() + "\",\"rows\":"+str);
			} else { // 子节点加载
				/*String reqResult = crawlruleModel.getData_info().toString();
				String getSignInfo = reqResult.substring(reqResult.indexOf("{") + 1, reqResult.indexOf("}"));
				String[] a = getSignInfo.split(",");
				String ab = a[1];
				String[] aab = ab.split("->");
				String value = aab[1];
				System.out.println(value);*/
				//crawlruleModel.setName(value);
				crawlruleModel.setParentId(crawlruleModel.getId());
				crawlruleModel.setId(null);
				listCrawlruleModel = crawlruleService.selectByModel(crawlruleModel);
				center.append("[");
				suffix = "]";
			}
 		}
 	}
 	if (pageParams.getGridName() == null) { //datagrid
 		center.append(JSON.toJSONString(listCrawlruleModel));
 		} else {
 			if (listCrawlruleModel.size() > 0) { // TreeGrid
 				for (int i = 0; i < listCrawlruleModel.size(); i++) {
 					CrawlruleModel model = listCrawlruleModel.get(i);
					String json = JSON.toJSONString(model);
					CrawlruleModel model2 = new CrawlruleModel();
 					model2.setParentId(model.getId());
					if (crawlruleService.selectByModelCount(model2) > 0) {
						json = json.substring(0, json.length() - 1);
						json += ",\"state\":\"closed\"}";
					}
					center.append(json + ",");
				}
				center.delete(center.length() - 1, center.length());
 			}
 		}
 		center.append(suffix);
 		result = center.toString();
 		System.out.println("json:" + result);
 		return result;
 	}
 		
 		
	@RequestMapping("charts.html")
	public String charts(){		
 		return "admin/Crawlrule/charts";		
 	}
 	
 	
 	
 	@RequestMapping("baseDlg.html")
 	public String baseDlg(){
 		return "admin/Crawlrule/baseDlg";
 	}
 	
 	
 	@RequestMapping("importDlg.html")
 	public String importDlg(){
 		return "admin/Crawlrule/importDlg";
 	}
 	
 	
 	@RequestMapping("add.html")
 	public void add(CrawlruleModel crawlruleModel,HttpServletResponse response){
 	//crawlruleModel.setId(util.getUid());//设置主键
 	System.out.println("tbsBModel:"+crawlruleModel.toString());
 	 try {
 		if(crawlruleService.insert(crawlruleModel)>0){
 		util.toJsonMsg(response, 0, null);
 		return;
 		};
 		} catch (Exception e) {
 		e.printStackTrace();
 		}
 	 util.toJsonMsg(response, 1, null);
 	}
 	
 	
 	@RequestMapping("save.html")
 	public void save(CrawlruleModel crawlruleModel,HttpServletResponse response){
 		try{
 			if(crawlruleService.updateByPrimaryKey(crawlruleModel)>0){
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
 		if(crawlruleService.deleteByPrimaryKeys(ids)>0){
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
