
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 高级查询  Order_mainSearchDlg-->
<div id="orderMainSearchDlg">
	<form id="orderMainSearchFm" method="post">
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
					 
						<option value="id">id</option>
						<option value="orderSn">orderSn</option>
						<option value="userId">userId</option>
						<option value="sellerPrice">sellerPrice</option>
						<option value="comPrice">comPrice</option>
						<option value="postFee">postFee</option>
						<option value="totalPrice">totalPrice</option>
						<option value="updateTime">updateTime</option>
						<option value="closeTime">closeTime</option>
						<option value="endTime">endTime</option>
						<option value="createTime">createTime</option>
						<option value="invoiceContent">invoiceContent</option>
						<option value="invoiceTitle">invoiceTitle</option>
						<option value="invoiceType">invoiceType</option>
						<option value="isInvoice">isInvoice</option>
						<option value="payStatus">payStatus</option>
						<option value="paymentTime">paymentTime</option>
						<option value="buyerMessage">buyerMessage</option>
						<option value="refundStatus">refundStatus</option>
						<option value="merSn">merSn</option>
						<option value="deleteFlag">deleteFlag</option>
						<option value="status">status</option>
			  
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
					<td><input name="searchVals" size="18"> <a href="javascript:void(0)">日期框</a>
					 <a href="javascript:void(0)" onclick="orderMainSearchRemove(this)">删除</a>
				</td>
			</tr>
		</table>
	</form>
</div>
