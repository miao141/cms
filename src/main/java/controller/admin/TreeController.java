package controller.admin;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TbsLoginLogModel;
import model.TbsMenuModel;
import model.TbsUserModel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.TbsLoginLogService;
import service.TbsMenuService;
import service.TbsUserService;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import util.core.MethodUtil;

@Controller
@RequestMapping("/admin/tree/")
public class TreeController {
	
	private final static Logger log = Logger.getLogger(SystemIndexControllerAdmin.class);
	public static MethodUtil util = new MethodUtil();
	private StringBuffer sb = new StringBuffer();
	
	@Autowired
	private TbsUserService<TbsUserModel> tbsUserService;
	@Autowired
	private TbsMenuService<TbsMenuModel> tbsMenuService;
	@Autowired
	private TbsLoginLogService<TbsLoginLogModel> tbsLoginLogService;
	
	@RequestMapping("/asyJson.html")
	@ResponseBody
	public synchronized String asyJson(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String asyJson = null;
		String path = null;

		if (null == id) {
			path = request.getSession().getServletContext().getRealPath("/");
		} else {
			path = new String(new BASE64Decoder().decodeBuffer(URLDecoder.decode(id, "utf-8")));
		}
		System.out.println("id:" + id + "|asyJsonPath:" + path);
		if (sb.length() > 0)
			sb.delete(0, sb.length());// 清除缓存
		//asyJson = "[" + this.getAsyJsonData(new File(path)) + "]";
		System.out.println(asyJson);
		return asyJson;
	}
	
	private synchronized String getAsyJsonData(File file) throws UnsupportedEncodingException {
		if (!file.exists()) {
			return null;
		}
		File fileList[] = file.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				String fileNameLower = pathname.getName().toLowerCase();
				if (pathname.isHidden())
					return false;
				/*********** 隐藏文件过滤 ***********/
				if (fileNameLower.matches(".*(meta-inf|templates)$|.*.(gif|jpg|png|ico|class|.jar|.zip|.gz|.sql|.exe|.bt|.sh)$")) {
					return false;
				}
				return true;
			}
		});
		for (int i = 0; i < fileList.length; i++) {
			file = fileList[i];
			boolean isDir = file.isDirectory();
			String type = "file";
			String state = "open";
			if (isDir) {
				type = "folder";
				state = "closed";
			}
			String fileName = file.getName();
			String filePath = file.getPath();
			// String md5Str = utils.getMD5UTF8(filePath);
			String base64Encoder = URLEncoder.encode(new BASE64Encoder().encode(filePath.getBytes()), "UTF-8");
			String url = "admin/openFile.html"; // 路径
			// System.out.println(base64Encoder);
			sb.append("{"); // ,\"attributes\":{\"url\":\"/admin/tree/openFile.html\",\"target\":\"mainFrame\"
			sb.append("\"id\":\"" + base64Encoder + "\",\"text\":\"" + fileName + "\",\"state\":\"" + state + "\"");
			sb.append(",\"attributes\":{\"text\":\"" + fileName + "\",\"url\":\"" + url + "\",\"type\":\"" + type + "\",\"path\":\"" + base64Encoder + "\"}");
			sb.append("},");
		}
		if (fileList.length > 0) {
			sb.delete(sb.length() - 1, sb.length());
		} else {
			sb.append("");
		}
		return sb.toString();
	}
}
