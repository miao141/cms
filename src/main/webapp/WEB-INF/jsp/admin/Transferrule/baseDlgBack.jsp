<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 transferruleDlg -->
  
  <div id="transferruleDlg">
  <form id="transferruleFm" method="post">
  	<table>
  
  <tr>
    <td><label> 主键: </label></td>
	  <td >
		<input name="id" class="easyui-validatebox"  />
	  </td>
	  <td><label> 规则名字: </label></td>
	  <td >
		<input name="name" class="easyui-validatebox" />
     </td>
  </tr>
  <tr>
    <td><label> 匹配表达式: </label></td>
	  <td >
		<input name="match_expr" class="easyui-validatebox"  />
	  </td>
	  <td><label> 目标节点名字: </label></td>
	  <td >
		<input name="target" class="easyui-validatebox" />
     </td>
  </tr>
  <tr>
    <td><label> 动作: </label></td>
	  <td >
		<input name="action" class="easyui-validatebox"  />
	  </td>
	  <td><label> 节点类型: </label></td>
	  <td >
		<input name="type" class="easyui-validatebox" />
     </td>
  </tr>
  <tr>
    <td><label> 虚节点列表: </label></td>
	  <td >
		<input name="next" class="easyui-validatebox"  />
	  </td>
	  <td><label> 虚节点: </label></td>
	  <td >
		<input name="dataprocess" class="easyui-validatebox" />
     </td>
  </tr>
  </table>
  </form>
  </div>
  
