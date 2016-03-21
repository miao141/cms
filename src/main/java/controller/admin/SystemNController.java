package controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TbcContentModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqltojava.SqlUtil;

import util.core.MethodUtil;

@Controller
@RequestMapping("/admin/template/")
public class SystemNController extends BaseController{
	private static  MethodUtil util = new MethodUtil();
	
	@RequestMapping("index.html")
	public String index(){
		return "/admin/SystemTemplate/template";
	}
	
	@RequestMapping("save.html") 
	public void save(TbcContentModel tbcContentModel,HttpServletResponse response, HttpServletRequest request){
		try{
			String temaplateValue = request.getParameter("text");
			SqlUtil.createA(temaplateValue, "F:\\u");
			/*tbcContentModel.setContent(editorValue);
		 	if(tbcContentService.updateByPrimaryKey(tbcContentModel)>0){
			    util.toJsonMsg(response, 0, null);
			    return;
			 }*/
		}catch(Exception e){
			util.toJsonMsg(response, 1, null);
			e.printStackTrace();
		}
	}
}
