<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 crawlruleDlg -->

<div id="crawlruleDlg">
<form id="crawlruleFm" method="post">
<table>
  <tr>
    <td><label> 主键: </label></td>
	  <td >
		<input name="id" class="easyui-validatebox" type="text" />
	  </td>
	  <td><label> 规则名称: </label></td>
	  <td >
		<input name="name" class="easyui-validatebox"  type="text" />
     </td>
  </tr>
  <tr>
    <td><label> 规则内容: </label></td>
	  <td >
		<input name="match_expr" class="easyui-validatebox" type="text" />
	  </td>
	  <td><label> 命名规则: </label></td>
	  <td >
		<input name="target" class="easyui-validatebox"  type="text" />
     </td>
  </tr>
  <tr>
    <td><label> 下级规则: </label></td>
	  <td >
		<input name="data_info" class="easyui-validatebox" type="text" />
	  </td>
  </tr>
		</table>
</form>
</div>
