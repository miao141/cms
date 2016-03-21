package controller.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.TbcContentModel;
import model.TbcMenuModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SystemIndexControllerView extends BaseController{
	
	@RequestMapping("index")
	public String index(ModelMap modelMap) {
		super.loadHead(modelMap);
		super.loadLeft(modelMap);
		return "index";
	}
	
	@RequestMapping("download.html")
	public String download(){
		System.out.println("download.html");
		return "download";
	}
	
	// http://json20080301.iteye.com/blog/1874074
	//http://jackyrong.iteye.com/blog/1806326
	@RequestMapping("{dir}/")
	public String path(@PathVariable String dir,ModelMap modelMap){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", dir+"/");
		System.out.println("{dir}/:"+dir);
		try {
			List<TbcMenuModel> listTbcMenuModel=super.tbcMenuService.selectByMap(map);
			if(listTbcMenuModel==null || listTbcMenuModel.size()==0){
				return "404";
			}else{
				map.clear();
				map.put("id", listTbcMenuModel.get(0).getId());
				List<TbcContentModel> listTbcContentModel=tbcContentService.selectByMap(map);
				modelMap.put("listTbcContentModel", listTbcContentModel);
				//通用加载
				super.loadHead(modelMap);
				super.loadLeft(modelMap);
				return "list";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "500";
	}
	
	
	
}
