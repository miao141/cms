<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 高级查询  TransferruleSearchDlg-->
<div id="transferruleSearchDlg">
	<form id="transferruleSearchFm" method="post">
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
			 <option value="name">规则名字</option>
			 <option value="match_expr">匹配表达式</option>
			 <option value="target">目标节点名字</option>
			 <option value="action">动作</option>
			 <option value="type">节点类型</option>
			 <option value="next">虚节点列表</option>
			 <option value="dataprocess">虚节点</option>
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
					<td><input name="searchVals" size="18"> <a href="javascript:void(0)">日期框</a> <a href="javascript:void(0)" onclick="transferruleSearchRemove(this)">删除</a>
				</td>
			</tr>
		</table>
	</form>
</div>
