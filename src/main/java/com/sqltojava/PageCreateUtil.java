package com.sqltojava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageCreateUtil {
	
	/**
	 * 创建jsp
	 * @param temple
	 * @param route
	 * @param packName
	 */
	public static void templeTOJsp(Temple temple,String route,String packName){
		Map<String,String> dbInfoMap = new HashMap<String, String>();
		dbInfoMap.put("route", route);
		dbInfoMap.put("packName", packName);
		dbInfoMap.put("columnKey", temple.getColumnKey());
		 
		createJsp(temple.getTable(), temple.getSx(), temple.getLx(),temple.getZx(), dbInfoMap);
	}
	
	/**
	 * 创建JavaBean文件
	 * @param tbName
	 * @param sxList
	 * @param lxList
	 * @return
	 */
	public static void createJsp(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		createDlgJsp(tbName, sxList, lxList,zxList, infoMap);
		createBaseDlgBackJsp(tbName, sxList, lxList,zxList, infoMap);
		createBaseIndexJsp(tbName, sxList, lxList,zxList, infoMap);
		createSearchJsp(tbName, sxList, lxList,zxList, infoMap);
		
	}
	
	
	public static void createSearchJsp(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		
		String columnKey = infoMap.get("columnKey");
		StringBuilder classInfo = new StringBuilder();
		classInfo.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\r\n");
		classInfo.append("<%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\r\n");
		classInfo.append("<%@taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>\r\n");
		classInfo.append("<!-- 高级查询  "+upperFirestChar(tbName)+"SearchDlg-->\r\n");
		classInfo.append("<div id=\""+lowerFirestChar(tbName)+"SearchDlg\">\r\n");
		classInfo.append("	<form id=\""+lowerFirestChar(tbName)+"SearchFm\" method=\"post\">\r\n");
		classInfo.append("		<table>\r\n");
		classInfo.append("			<tr>\r\n");
		classInfo.append("				<th>条件</th>\r\n");
		classInfo.append("				<th>字段名</th>\r\n");
		classInfo.append("				<th>条件</th>\r\n");
		classInfo.append("				<th>值</th>\r\n");
		classInfo.append("			</tr>\r\n");
		classInfo.append("			<tr>\r\n");
		classInfo.append("				<td>\r\n");
		classInfo.append("					<select name=\"searchAnds\">\r\n");
		classInfo.append("						<option value=\"&&\"></option>\r\n");
		classInfo.append("						<option value=\"||\">或者</option>\r\n");
		classInfo.append("						<option value=\"&&\">并且</option>\r\n");
		classInfo.append("					</select>\r\n");
		classInfo.append("				</td>\r\n");
		classInfo.append("				<td>\r\n");
		classInfo.append("					<select name=\"searchColumnNames\">\r\n");
		for (int i = 0; i < sxList.size(); i++) {
			
			String attribute = sxList.get(i);
			String  note = zxList.get(i);
			classInfo.append("			 <option value=\""+attribute+"\">"+note+"</option>\r\n");
			
		}
	/*	classInfo.append("						<option value=\"id\"></option>\r\n");
		classInfo.append("						<option value=\"id\">主键</option>\r\n");
		classInfo.append("						<option value=\"createTime\">创建时间</option>\r\n");
		classInfo.append("						<option value=\"name\">名称</option>\r\n");*/
		classInfo.append("					</select>\r\n");
		classInfo.append("				</td>\r\n");
		classInfo.append("				<td>\r\n");
		classInfo.append("					<select name=\"searchConditions\">\r\n");
		classInfo.append("						<option value=\"=\"></option>\r\n");
		classInfo.append("						<option value=\"=\">等于</option>\r\n");
		classInfo.append("						<option value=\"<>\">大于小于</option>\r\n");
		classInfo.append("						<option value=\"<\">小于</option>\r\n");
		classInfo.append("						<option value=\">\">大于</option>\r\n");
		classInfo.append("						<option value=\"Like\">模糊</option>\r\n");
		classInfo.append("					</select>\r\n");
		classInfo.append("				</td>\r\n");
		classInfo.append("					<td><input name=\"searchVals\" size=\"18\"> <a href=\"javascript:void(0)\">日期框</a> <a href=\"javascript:void(0)\" onclick=\""+lowerFirestChar(tbName)+"SearchRemove(this)\">删除</a>\r\n");
		classInfo.append("				</td>\r\n");
		classInfo.append("			</tr>\r\n");
		classInfo.append("		</table>\r\n");
		classInfo.append("	</form>\r\n");
		classInfo.append("</div>\r\n");
		File file = new File(infoMap.get("route")+"//jsp//"+upperFirestChar(tbName), "searchDlg.jsp");
		 
	    String filePath = file.getParent(); 
	    File filePatha = new File(filePath);
	    if(!filePatha.exists())
	    	filePatha.mkdirs(); 
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void createBaseIndexJsp(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		String columnKey = infoMap.get("columnKey");
		StringBuilder classInfo = new StringBuilder();
		classInfo.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\r\n");
		classInfo.append("<%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\r\n");
		classInfo.append("<%@taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <script type=\"text/javascript\">\r\n");
		classInfo.append("  //Add and Edit\r\n");
		
		classInfo.append("  function "+lowerFirestChar(tbName)+"GridAddAndEdit(title, url, type) {\r\n");
		classInfo.append("    if (type == 1) { //edit\r\n");
		classInfo.append("    var rows = $('#"+lowerFirestChar(tbName)+"Grid').datagrid('getSelections');\r\n");
		classInfo.append("    if (rows.length != 1) {\r\n");
		classInfo.append("    $.messager.alert('消息', '请钩择一行数据!', 'info');\r\n");
		classInfo.append("    return;\r\n");
		classInfo.append("    }\r\n");
		classInfo.append("  }\r\n");
		
		classInfo.append("  $('<div/>').dialog({\r\n");
		classInfo.append("			href : '${demoPath}admin/"+upperFirestChar(tbName)+"/baseDlg.html',\r\n");
		classInfo.append("			modal : true,\r\n");
		classInfo.append("			title : title,\r\n");
		classInfo.append("			top : '15%',\r\n");
		classInfo.append("			left : '30%',\r\n");
		classInfo.append("			width : 600,\r\n");
		classInfo.append("			resizable:true,\r\n");
		classInfo.append("			buttons : [ {\r\n");
		classInfo.append("				text : '确定',\r\n");
		classInfo.append("				iconCls : 'icon-ok',\r\n");
		classInfo.append("				handler : function() {\r\n");
		classInfo.append("					"+lowerFirestChar(tbName)+"GridSubmit(url);\r\n");
		classInfo.append("					$(this).closest('.window-body').dialog('destroy');\r\n");
		classInfo.append("				}\r\n");
		classInfo.append("			}, {\r\n");
		classInfo.append("				text : '取消',\r\n");
		classInfo.append("				iconCls : 'icon-cancel',\r\n");
		classInfo.append("				handler : function() {\r\n");
		classInfo.append("					$(this).closest('.window-body').dialog('destroy');\r\n");
		classInfo.append("				}\r\n");
		classInfo.append("			} ],\r\n");
		classInfo.append("			onClose : function() {\r\n");
		classInfo.append("				$(this).dialog('destroy');\r\n");
		classInfo.append("			},\r\n");
		classInfo.append("			onLoad : function() {\r\n");
		classInfo.append("				if (type == 0) {\r\n");
		classInfo.append("					$('#"+lowerFirestChar(tbName)+"Fm').form('clear');\r\n");
		classInfo.append("				} else {\r\n");
		classInfo.append("					var rows = $('#"+lowerFirestChar(tbName)+"Grid').datagrid('getSelections');\r\n");
		classInfo.append("					if (rows.length == 1) {\r\n");
		classInfo.append("						$('#"+lowerFirestChar(tbName)+"Fm').form('load', rows[0]);\r\n");
		classInfo.append("					} else {\r\n");
		classInfo.append("						$.messager.alert('消息', '请钩择一行数据!', 'info');\r\n");
		classInfo.append("					}\r\n");
		classInfo.append("				}\r\n");
		classInfo.append("			}\r\n");
		classInfo.append("		});\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	\r\n");
		classInfo.append("	//Del\r\n");
		classInfo.append("	function "+lowerFirestChar(tbName)+"GridDel() {\r\n");
		classInfo.append("		var rows = $('#"+lowerFirestChar(tbName)+"Grid').datagrid('getSelections');\r\n");
		classInfo.append("		if (rows.length > 0) {\r\n");
		classInfo.append("		    var ids = '';\r\n");
		classInfo.append("			for ( var i = 0; i < rows.length; i++) {\r\n");
		classInfo.append("						ids += 'ids=' + rows[i].id + '&';\r\n");
		classInfo.append("			}\r\n");
		classInfo.append("			ids = ids.substring(0, ids.length - 1);\r\n");
		classInfo.append("			var url = '${demoPath}admin/"+upperFirestChar(tbName)+"/del.html?' + ids;\r\n");
		classInfo.append("			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {\r\n");
		classInfo.append("				if (r) {\r\n");
		classInfo.append("				    $.get(url, function(result){\r\n");
		classInfo.append("						if (result.success){ \r\n");
		classInfo.append("							$('#"+lowerFirestChar(tbName)+"Grid').datagrid('reload');\r\n");
		classInfo.append("							$('#"+lowerFirestChar(tbName)+"Grid').datagrid('clearSelections');\r\n");
		classInfo.append("						} else {  \r\n");
		classInfo.append("						    $.messager.show({ title: 'Error', msg : result.msg }); \r\n");
		classInfo.append("						} \r\n");
		classInfo.append("				    }, 'json');\r\n");
		classInfo.append("				}\r\n");
		classInfo.append("			});\r\n");
		classInfo.append("		} else {\r\n");
		classInfo.append("			$.messager.alert('消息', '请选择要删除的数据!','info');\r\n");
		classInfo.append("		}\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	\r\n");
		classInfo.append("	//Reload\r\n");
		classInfo.append("	function "+lowerFirestChar(tbName)+"GridReload() {\r\n");
		classInfo.append("		$('#"+lowerFirestChar(tbName)+"Grid').datagrid('options').pageNumber=1;\r\n");
		classInfo.append("		$('#"+lowerFirestChar(tbName)+"Grid').datagrid('reload',{});\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	\r\n");
		classInfo.append("	//"+lowerFirestChar(tbName)+"GridSubmit  submit\r\n");
		classInfo.append("	function "+lowerFirestChar(tbName)+"GridSubmit(url) {\r\n");
		classInfo.append("	    $('#"+lowerFirestChar(tbName)+"Fm').form('submit',{  \r\n");
		classInfo.append("	        url: url,  \r\n");
		classInfo.append("	        onSubmit: function(){  \r\n");
		classInfo.append("	            return $(this).form('validate');  \r\n");
		classInfo.append("	        },  \r\n");
		classInfo.append("	        success: function(result){  \r\n");
		classInfo.append("	            var result = eval('('+result+')');  \r\n");
		classInfo.append("	            if (result.success){  \r\n");
		classInfo.append("	                $('#"+lowerFirestChar(tbName)+"Dlg').dialog('close');      // close the dialog \r\n");
		classInfo.append("					$('#"+lowerFirestChar(tbName)+"Grid').datagrid('reload');    // reload the user data\r\n");
		classInfo.append("	            } else {  \r\n");
		classInfo.append("	            	$.messager.show({ title: 'Error',msg: result.msg }); \r\n");
		classInfo.append("	            }  \r\n");
		classInfo.append("	        }  \r\n");
		classInfo.append("	    });\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	\r\n");
		classInfo.append("	//高级搜索 del row\r\n");
		classInfo.append("	function "+lowerFirestChar(tbName)+"SearchRemove(curr) {\r\n");
		classInfo.append("		if ($(curr).closest('table').find('tr').size() > 2) {\r\n");
		classInfo.append("			$(curr).closest('tr').remove();\r\n");
		classInfo.append("		} else {\r\n");
		classInfo.append("			alert('该行不允许删除');\r\n");
		classInfo.append("		}\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	\r\n");
		classInfo.append("	//高级查询\r\n");
		classInfo.append("	function "+lowerFirestChar(tbName)+"Search() {\r\n");
		classInfo.append("		$('<div/>').dialog({\r\n");
		classInfo.append("			href : '${demoPath}admin/"+upperFirestChar(tbName)+"/searchDlg.html',\r\n");
		classInfo.append("			modal : true,\r\n");
		classInfo.append("			title : '高级查询',\r\n");
		classInfo.append("			top : 120,\r\n");
		classInfo.append("			width : 480,\r\n");
		classInfo.append("			buttons : [ {\r\n");
		classInfo.append("				text : '增加一行',\r\n");
		classInfo.append("				iconCls : 'icon-add',\r\n");
		classInfo.append("				handler : function() {\r\n");
		classInfo.append("					var currObj = $(this).closest('.panel').find('table');\r\n");
		classInfo.append("					currObj.find('tr:last').clone().appendTo(currObj);\r\n");
		classInfo.append("					//currObj.find('tr:last td *[disabled]').removeAttr(\"disabled\");\r\n");
		classInfo.append("				}\r\n");
		classInfo.append("			}, {\r\n");
		classInfo.append("				text : '确定',\r\n");
		classInfo.append("				iconCls : 'icon-ok',\r\n");
		classInfo.append("				handler : function() {\r\n");
		classInfo.append("					$('#"+lowerFirestChar(tbName)+"Grid').datagrid('options').pageNumber=1;\r\n");
		classInfo.append("					$('#"+lowerFirestChar(tbName)+"Grid').datagrid('reload',serializeObjectEx($('#"+lowerFirestChar(tbName)+"SearchFm')));\r\n");
		classInfo.append("				}\r\n");
		classInfo.append("			}, {\r\n");
		classInfo.append("				text : '取消',\r\n");
		classInfo.append("				iconCls : 'icon-cancel',\r\n");
		classInfo.append("				handler : function() {\r\n");
		classInfo.append("					$(this).closest('.window-body').dialog('destroy');\r\n");
		classInfo.append("				}\r\n");
		classInfo.append("			} ],\r\n");
		classInfo.append("			onClose : function() {\r\n");
		classInfo.append("				$(this).dialog('destroy');\r\n");
		classInfo.append("			}\r\n");
		classInfo.append("		});\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	\r\n");
		classInfo.append("	//导出\r\n");
		classInfo.append("	function "+lowerFirestChar(tbName)+"GridExport(){\r\n");
		classInfo.append("		window.location=\"${demoPath}admin/"+upperFirestChar(tbName)+"/export.html\";\r\n");
		classInfo.append("		//var panel=$('#tbcTempGrid').datagrid('getPanel');\r\n");
		classInfo.append("		//var options=panel.panel('options');\r\n");
		classInfo.append("		//alert(options.method);\r\n");
		classInfo.append("		//console.dir(options);\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	\r\n");
		classInfo.append("	//导入\r\n");
		classInfo.append("	function "+lowerFirestChar(tbName)+"GridImport(){\r\n");
		classInfo.append("		$('<div/>').dialog({\r\n");
		classInfo.append("			href : '${demoPath}admin/"+upperFirestChar(tbName)+"/importDlg.html',\r\n");
		classInfo.append("			modal : true,\r\n");
		classInfo.append("			title : '导入',\r\n");
		classInfo.append("			top : '15%',\r\n");
		classInfo.append("			left : '30%',\r\n");
		classInfo.append("			width : 600,\r\n");
		classInfo.append("			height: 300,\r\n");
		classInfo.append("			resizable:true,\r\n");
		classInfo.append("			buttons : [ {\r\n");
		classInfo.append("				text : '确定',\r\n");
		classInfo.append("				iconCls : 'icon-ok',\r\n");
		classInfo.append("				handler : function() {\r\n");
		classInfo.append("					//$(this).closest('.window-body').dialog('destroy');\r\n");
		classInfo.append("				}\r\n");
		classInfo.append("			}, {\r\n");
		classInfo.append("				text : '取消',\r\n");
		classInfo.append("				iconCls : 'icon-cancel',\r\n");
		classInfo.append("				handler : function() {\r\n");
		classInfo.append("					$(this).closest('.window-body').dialog('destroy');\r\n");
		classInfo.append("				}\r\n");
		classInfo.append("			} ],\r\n");
		classInfo.append("			onClose : function() {\r\n");
		classInfo.append("				$(this).dialog('destroy');\r\n");
		classInfo.append("			},\r\n");
		classInfo.append("			onLoad : function() {\r\n");
		classInfo.append("				\r\n");
		classInfo.append("			}\r\n");
		classInfo.append("		});\r\n");
		classInfo.append("	}\r\n");
		classInfo.append("	\r\n");
		classInfo.append("</script>\r\n");
		classInfo.append("    \r\n");
		classInfo.append("\r\n");
		classInfo.append("	<!-- 中  datagrid-->\r\n");
		classInfo.append("    <div data-options=\"region:\'center\',border : false\">\r\n");
		classInfo.append("		<!-- datagrid toolbar -->\r\n");
		classInfo.append("		<table id=\""+lowerFirestChar(tbName)+"Grid\"  class=\"easyui-datagrid\"  data-options=\"	\r\n");
		classInfo.append("			url:'${demoPath}admin/"+upperFirestChar(tbName)+"/data.html',\r\n");
		classInfo.append("			frozenColumns : [ [ {field : 'ck',checkbox : true}] ],\r\n");
		classInfo.append("			columns:[ [  \r\n");
		classInfo.append("\r\n");
		classInfo.append("			{field:'id',title:'主键',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){\r\n");
		classInfo.append("			    return value;\r\n");
		classInfo.append("			}},			\r\n");
		classInfo.append("\r\n");
		classInfo.append("			{field:'name',title:'名称',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){\r\n");
		classInfo.append("			    return value;\r\n");
		classInfo.append("			}},			\r\n");
		classInfo.append("\r\n");
		classInfo.append("			{field:'createTime',title:'创建时间',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){\r\n");
		classInfo.append("			    return value;\r\n");
		classInfo.append("			}}			\r\n");
		classInfo.append("			] ],\r\n");
		classInfo.append("			toolbar:'#"+lowerFirestChar(tbName)+"GridToolbar'\r\n");
		classInfo.append("		\"/>\r\n");
		classInfo.append("		\r\n");
		classInfo.append("		<!-- datagrid toolbar -->\r\n");
		classInfo.append("		<div id=\""+lowerFirestChar(tbName)+"GridToolbar\">\r\n");
		classInfo.append("			<div style=\"margin-bottom:5px\">\r\n");
		classInfo.append("				<c:forEach items=\"${buttons}\" var=\"button\">\r\n");
		classInfo.append("			         ${button}\r\n");
		classInfo.append("			    </c:forEach>\r\n");
		classInfo.append("				<%-- \r\n");
		classInfo.append("				<a href=\"javascript:void(0)\" onclick=\"javascript:"+lowerFirestChar(tbName)+"GridAddAndEdit('添加  "+lowerFirestChar(tbName)+"','${demoPath}admin/"+upperFirestChar(tbName)+"/add.html',0)\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-add'\">添加</a>\r\n");
		classInfo.append("				<a href=\"javascript:void(0)\" onclick=\"javascript:"+lowerFirestChar(tbName)+"GridAddAndEdit('修改  "+lowerFirestChar(tbName)+"','${demoPath}admin/"+upperFirestChar(tbName)+"/save.html',1)\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-edit'\">编辑 </a>  \r\n");
		classInfo.append("				<a href=\"javascript:void(0)\" onclick=\"javascript:"+lowerFirestChar(tbName)+"GridDel()\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-remove'\">删除</a>\r\n");
		classInfo.append("				<a href=\"javascript:void(0)\" onclick=\"javascript:"+lowerFirestChar(tbName)+"GridReload()\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-reload'\">刷新</a>\r\n");
		classInfo.append("				<a href=\"javascript:void(0)\" onclick=\"javascript:"+lowerFirestChar(tbName)+"GridExport()\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-reload'\">导出</a>\r\n");
		classInfo.append("				<a href=\"javascript:void(0)\" onclick=\"javascript:"+lowerFirestChar(tbName)+"GridImport()\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-reload'\">导入</a>\r\n");
		classInfo.append("				\r\n");
		classInfo.append("				<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-undo'\">后退</a>\r\n");
		classInfo.append("				<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-redo'\">前进</a>\r\n");
		classInfo.append("				--%>\r\n");
		classInfo.append("				<!-- "+lowerFirestChar(tbName)+"GridToolbarSearch -->\r\n");
		classInfo.append("				<input class=\"easyui-searchbox\" data-options=\"\r\n");
		classInfo.append("					menu :'#"+lowerFirestChar(tbName)+"GridToolbarSearch',\r\n");
		classInfo.append("					prompt :'模糊查询',\r\n");
		classInfo.append("					searcher : function(value,name){\r\n");
		classInfo.append("						var str='{searchType:1,'+name+':\'+value+\'}';\r\n");
		classInfo.append("				        var obj = eval('('+str+')');\r\n");
		classInfo.append("				        $('#"+lowerFirestChar(tbName)+"Grid').datagrid('options').pageNumber=1;\r\n");
		classInfo.append("						$('#"+lowerFirestChar(tbName)+"Grid').datagrid('reload',obj);\r\n");
		classInfo.append("					}\r\n");
		classInfo.append("				\"/>\r\n");
		classInfo.append("				<div id=\""+lowerFirestChar(tbName)+"GridToolbarSearch\">\r\n");
			for (int i = 0; i < sxList.size(); i++) {
				
				String attribute = sxList.get(i);
				String  note = zxList.get(i);
				classInfo.append("					<div name=\""+attribute+"\">"+note+"</div>\r\n");
			}
		
	/*	classInfo.append("					<div name=\"name\">名称</div>\r\n");
		classInfo.append("					<div name=\"createTime\">创建时间</div>\r\n");*/
		classInfo.append("				</div>\r\n");
		classInfo.append("				<a href=\"javascript:void(0)\" onclick=\"javascript:"+lowerFirestChar(tbName)+"Search()\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-search'\">高级查询</a>\r\n");
		classInfo.append("			</div>\r\n");
		classInfo.append("		</div>\r\n");
		classInfo.append("	</div>\r\n");
		classInfo.append("<!--  <div>-->\r\n");
		File file = new File(infoMap.get("route")+"//jsp//"+upperFirestChar(tbName), "index.jsp");
		 
	    String filePath = file.getParent(); 
	    File filePatha = new File(filePath);
	    if(!filePatha.exists())
	    	filePatha.mkdirs(); 
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
 
	}
	
	public static void createBaseDlgBackJsp(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		String columnKey = infoMap.get("columnKey");
		StringBuilder classInfo = new StringBuilder();
		classInfo.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\r\n");
		classInfo.append("<%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\r\n");
		classInfo.append("<%@taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>\r\n");
		classInfo.append("<!-- 弹出框 "+lowerFirestChar(tbName)+"Dlg -->\r\n");
		classInfo.append("  \r\n");
		classInfo.append("  <div id=\""+lowerFirestChar(tbName)+"Dlg\">\r\n");
		classInfo.append("  <form id=\""+lowerFirestChar(tbName)+"Fm\" method=\"post\">\r\n");
		classInfo.append("  	<table>\r\n");
		classInfo.append("  \r\n");
		
		for (int i = 0; i < sxList.size(); i++) {
			String note = zxList.get(i);
			String attribute = sxList.get(i);
			classInfo.append("  <tr>\r\n");
			classInfo.append("    <td><label> "+note+": </label></td>\r\n");
			classInfo.append("	  <td >\r\n");
			classInfo.append("		<input name=\""+attribute+"\" class=\"easyui-validatebox\"  />\r\n");
			
			classInfo.append("	  </td>\r\n");
			i++;
			if(i < sxList.size()){
				note = zxList.get(i);
				attribute = sxList.get(i);
				classInfo.append("	  <td><label> "+note+": </label></td>\r\n");
				classInfo.append("	  <td >\r\n");
			 
				classInfo.append("		<input name=\""+attribute+"\" class=\"easyui-validatebox\" />\r\n");
				 
				classInfo.append("     </td>\r\n");
			}
			classInfo.append("  </tr>\r\n");
		}
		classInfo.append("  </table>\r\n");
		classInfo.append("  </form>\r\n");
		classInfo.append("  </div>\r\n");
		classInfo.append("  \r\n");
			
		 File file = new File(infoMap.get("route")+"//jsp//"+upperFirestChar(tbName), "baseDlgBack.jsp");
		 
		    String filePath = file.getParent(); 
		    File filePatha = new File(filePath);
		    if(!filePatha.exists())
		    	filePatha.mkdirs(); 
			try {
				OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
				fw.write(classInfo.toString());
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
/*
 
*
*/
	public static void createDlgJsp(String tbName, List<String> sxList,
			List<String> lxList,List<String> zxList,Map<String,String> infoMap) {
		StringBuilder classs = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		String columnKey = infoMap.get("columnKey");
		StringBuilder classInfo = new StringBuilder();
	 
		classInfo.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\r\n");
		classInfo.append("<%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\r\n");
		classInfo.append("<%@taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>\r\n");
		classInfo.append("<!-- 弹出框 "+lowerFirestChar(tbName)+"Dlg -->\r\n");
		classInfo.append("\r\n");
		classInfo.append("<div id=\""+lowerFirestChar(tbName)+"Dlg\">\r\n");
		classInfo.append("<form id=\""+lowerFirestChar(tbName)+"Fm\" method=\"post\">\r\n");
		classInfo.append("<table>\r\n");
		for (int i = 0; i < sxList.size(); i++) {
			String note = zxList.get(i);
			String attribute = sxList.get(i);
			classInfo.append("  <tr>\r\n");
			classInfo.append("    <td><label> "+note+": </label></td>\r\n");
			classInfo.append("	  <td >\r\n");
			if(columnKey.equals(attribute)){
				classInfo.append("		<input name=\""+attribute+"\" class=\"easyui-validatebox\"  readonly=\"readonly\" />\r\n");
			} else{
				classInfo.append("		<input name=\""+attribute+"\" class=\"easyui-validatebox\" type=\"text\" />\r\n");
			}
			
			classInfo.append("	  </td>\r\n");
			i++;
			if(i < sxList.size()){
				note = zxList.get(i);
				attribute = sxList.get(i);
				classInfo.append("	  <td><label> "+note+": </label></td>\r\n");
				classInfo.append("	  <td >\r\n");
				if(columnKey.equals(attribute)){  
					classInfo.append("		<input name=\""+attribute+"\" class=\"easyui-validatebox\"  readonly=\"readonly\" />\r\n");
				} else{
					classInfo.append("		<input name=\""+attribute+"\" class=\"easyui-validatebox\"  type=\"text\" />\r\n");
				}
				classInfo.append("     </td>\r\n");
			}
			classInfo.append("  </tr>\r\n");
		}
		
		classInfo.append("		</table>\r\n");
		classInfo.append("</form>\r\n");
		classInfo.append("</div>\r\n");
 

	    File file = new File(infoMap.get("route")+"//jsp//"+upperFirestChar(tbName), "baseDlg.jsp");
		 
	    String filePath = file.getParent(); 
	    File filePatha = new File(filePath);
	    if(!filePatha.exists())
	    	filePatha.mkdirs(); 
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
/*	
 
 
	

	
	*/
	
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
	
}
