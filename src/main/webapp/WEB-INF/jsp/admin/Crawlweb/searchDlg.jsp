<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 高级查询  CrawlwebSearchDlg-->
<div id="crawlwebSearchDlg">
	<form id="crawlwebSearchFm" method="post">
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
			 <option value="name">网站名称</option>
			 <option value="domain">网站域名</option>
			 <option value="type">网站类型</option>
			 <option value="status">状态</option>
			 <option value="create_time">开启</option>
			 <option value="source">关闭</option>
			 <option value="server">创建时间</option>
			 <option value="onwer">完整域名</option>
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
					<td><input name="searchVals" size="18"> <a href="javascript:void(0)">日期框</a> <a href="javascript:void(0)" onclick="crawlwebSearchRemove(this)">删除</a>
				</td>
			</tr>
		</table>
	</form>
</div>