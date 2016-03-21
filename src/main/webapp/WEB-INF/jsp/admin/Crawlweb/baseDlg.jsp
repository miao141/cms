<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 crawlwebDlg -->

<div id="crawlwebDlg">
<form id="crawlwebFm" method="post">
<table>
  <tr>
    <td><label> 主键: </label></td>
	  <td >
		<input name="id" class="easyui-validatebox" type="text" />
	  </td>
	  <td><label> 网站名称: </label></td>
	  <td >
		<input name="name" class="easyui-validatebox"  type="text" />
     </td>
  </tr>
  <tr>
    <td><label> 网站域名: </label></td>
	  <td >
		<input name="domain" class="easyui-validatebox" type="text" />
	  </td>
	  <td><label> 网站类型: </label></td>
	  <td >
		<input name="type" class="easyui-validatebox"  type="text" />
     </td>
  </tr>
  <tr>
    <td><label> 状态: </label></td>
	  <td >
		<input name="status" class="easyui-validatebox" type="text" />
	  </td>
	  <td><label> server: </label></td>
	  <td > 
			<input name="server" class="easyui-validatebox"  type="text" />
     </td>
  </tr>
  <tr>
    <td><label> 关闭: </label></td>
	  <td >
		<input name="source" class="easyui-validatebox" type="text" />
	  </td>
	  <td><label> 创建时间: </label></td>
	  <td >
		<input name="create_time" class="easyui-datetimebox"  type="text" />
	
     </td>
  </tr>
  <tr>
    <td><label> 完整域名: </label></td>
	  <td >
		<input name="onwer" class="easyui-validatebox" type="text" />
	  </td>
  </tr>
		</table>
</form>
</div>
