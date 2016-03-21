<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 高级查询  CrawljobSearchDlg-->
<div id="crawljobSearchDlg">
	<form id="crawljobSearchFm" method="post">
		<table>
			<tr>
				<th>条件</th>
				<th>字段名</th>
				<th>条件</th>
				<th>值</th>
			</tr>
			<tr>
				<td>
					<select name="searchAnds">
						<option value="&&"></option>
						<option value="||">或者</option>
						<option value="&&">并且</option>
					</select>
				</td>
				<td>
					<select name="searchColumnNames">
			 <option value="id">主键</option>
			 <option value="job_type">任务类型</option>
			 <option value="job_name">任务名称</option>
			 <option value="rule_key">抓取规则</option>
			 <option value="target_domain">目标网站</option>
			 <option value="protocol">网络协议</option>
			 <option value="start_url">抓取起始</option>
			 <option value="data_type">数据请求类型</option>
			 <option value="job_tag">任务</option>
			 <option value="client_id">使用</option>
			 <option value="load_class">状态</option>
			 <option value="status">开启</option>
					</select>
				</td>
				<td>
					<select name="searchConditions">
						<option value="="></option>
						<option value="=">等于</option>
						<option value="<>">大于小于</option>
						<option value="<">小于</option>
						<option value=">">大于</option>
						<option value="Like">模糊</option>
					</select>
				</td>
					<td><input name="searchVals" size="18"> <a href="javascript:void(0)">日期框</a> <a href="javascript:void(0)" onclick="crawljobSearchRemove(this)">删除</a>
				</td>
			</tr>
		</table>
	</form>
</div>
