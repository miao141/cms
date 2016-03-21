<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 crawljobDlg -->
  
  <div id="crawljobDlg">
  <form id="crawljobFm" method="post">
  	<table>
  
  <tr>
    <td><label> 主键: </label></td>
	  <td >
		<input name="id" class="easyui-validatebox"  />
	  </td>
	  <td><label> 任务类型: </label></td>
	  <td >
		<input name="job_type" class="easyui-validatebox" />
     </td>
  </tr>
  <tr>
    <td><label> 任务名称: </label></td>
	  <td >
		<input name="job_name" class="easyui-validatebox"  />
	  </td>
	  <td><label> 抓取规则: </label></td>
	  <td >
		<input name="rule_key" class="easyui-validatebox" />
     </td>
  </tr>
  <tr>
    <td><label> 目标网站: </label></td>
	  <td >
		<input name="target_domain" class="easyui-validatebox"  />
	  </td>
	  <td><label> 网络协议: </label></td>
	  <td >
		<input name="protocol" class="easyui-validatebox" />
     </td>
  </tr>
  <tr>
    <td><label> 抓取起始: </label></td>
	  <td >
		<input name="start_url" class="easyui-validatebox"  />
	  </td>
	  <td><label> 数据请求类型: </label></td>
	  <td >
		<input name="data_type" class="easyui-validatebox" />
     </td>
  </tr>
  <tr>
    <td><label> 任务: </label></td>
	  <td >
		<input name="job_tag" class="easyui-validatebox"  />
	  </td>
	  <td><label> 使用: </label></td>
	  <td >
		<input name="client_id" class="easyui-validatebox" />
     </td>
  </tr>
  <tr>
    <td><label> 状态: </label></td>
	  <td >
		<input name="load_class" class="easyui-validatebox"  />
	  </td>
	  <td><label> 开启: </label></td>
	  <td >
		<input name="status" class="easyui-validatebox" />
     </td>
  </tr>
  </table>
  </form>
  </div>
  
