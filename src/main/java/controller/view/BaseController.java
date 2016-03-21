package controller.view;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.TbcContentModel;
import model.TbcMenuHelpModel;
import model.TbcMenuModel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import service.TbcContentService;
import service.TbcMenuHelpService;
import service.TbcMenuService;
import util.core.MethodUtil;

/**
 * <br>
 * <b>功能：</b>类功能描述<br>
 * <b>作者：</b> <br>
 * <b>日期：</b> 2012-6-18 <br>
 * <b>版权所有：<b>版权所有(C) 2012 QQ  <br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class BaseController extends MultiActionController{
	protected final Logger log = Logger.getLogger(BaseController.class);
	//工具类
	protected static  MethodUtil util = new MethodUtil();
	// 服务类
	@Autowired
	protected TbcMenuService<TbcMenuModel> tbcMenuService;
	@Autowired
	protected TbcMenuHelpService<TbcMenuHelpModel> tbcMenuHelpService;
	
	@Autowired
	protected TbcContentService<TbcContentModel> tbcContentService;
	
     	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>加载头部通用<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-7-19 <br>
	 * @param modelMap
	 */
	protected void loadHead(ModelMap modelMap){
		TbcMenuModel tbcMenuModel=new TbcMenuModel();
		tbcMenuModel.getPageUtil().setAndCondition("parentId IS NULL");
		tbcMenuModel.getPageUtil().setOrderByCondition("sortNumber");
		try {
			modelMap.addAttribute("listTbcMenuModel", tbcMenuService.selectByChild(tbcMenuModel));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>加载左边<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-7-21 <br>
	 * @param modelMap
	 */
	protected void loadLeft(ModelMap modelMap){
		TbcMenuHelpModel tbcMenuHelpModel=new TbcMenuHelpModel();
		tbcMenuHelpModel.getPageUtil().setAndCondition("parentId IS NULL");
		tbcMenuHelpModel.getPageUtil().setOrderByCondition("sortNumber");
		try {
			modelMap.addAttribute("listTbcMenuHelpModel", tbcMenuHelpService.selectByChild(tbcMenuHelpModel));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出JSON<br>
	 * <b>作者：</b> <br>
	 * <b>日期：</b> 2012-6-18 <br>
	 * @param t
	 * @param response
	 */
	/*
	public <T> void toJson(T t,HttpServletResponse response){
		 try {
			PrintWriter pw=response.getWriter();
			pw.write(JSONUtil.toJSONString(t));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e);
		}
	}*/
}

