<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 orderMainDlg -->
  
  <div id="orderMainDlg">
  <form id="orderMainFm" method="post">
  	<table>
	<tr>
    <td><label> 主键: </label></td>
	  <td>
		<input name="id" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 平台订单ID: </label></td>
	  <td>
		<input name="orderSn" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 用户id: </label></td>
	  <td>
		<input name="userId" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单供应商总价格（结算用,价格快照）: </label></td>
	  <td>
		<input name="sellerPrice" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单商品价格: </label></td>
	  <td>
		<input name="comPrice" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单运费: </label></td>
	  <td>
		<input name="postFee" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单总价格: </label></td>
	  <td>
		<input name="totalPrice" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 最新修改时间: </label></td>
	  <td>
		<input name="updateTime" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单关闭时间: </label></td>
	  <td>
		<input name="closeTime" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单结束时间: </label></td>
	  <td>
		<input name="endTime" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单创建时间: </label></td>
	  <td>
		<input name="createTime" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 发票内容: </label></td>
	  <td>
		<input name="invoiceContent" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 发票抬头: </label></td>
	  <td>
		<input name="invoiceTitle" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 发票类型（0纸质，1电子: </label></td>
	  <td>
		<input name="invoiceType" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 是否要发票(0:不需要发票 1：个人发票，公司发票): </label></td>
	  <td>
		<input name="isInvoice" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单支付状态（0：未支付 1：全部支付2：部分支付 ）: </label></td>
	  <td>
		<input name="payStatus" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 支付时间: </label></td>
	  <td>
		<input name="paymentTime" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 买家留言: </label></td>
	  <td>
		<input name="buyerMessage" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 退款状态: </label></td>
	  <td>
		<input name="refundStatus" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 商户编号: </label></td>
	  <td>
		<input name="merSn" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单取消标识（1：已取消 0：正常订单）: </label></td>
	  <td>
		<input name="deleteFlag" class="easyui-validatebox"  />
	  </td>
	</tr>
	<tr>
    <td><label> 订单状态（0待审核，1审核拒绝，2待付款，3待发货，4已发货，5已完成，6交易关闭: </label></td>
	  <td>
		<input name="status" class="easyui-validatebox"  />
	  </td>
	</tr>
  </table>
  </form>
  </div>
  
