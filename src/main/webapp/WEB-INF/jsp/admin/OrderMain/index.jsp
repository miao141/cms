
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  
  
  <script type="text/javascript">
  //Add and Edit
  function orderMainGridAddAndEdit(title, url, type) {
    if (type == 1) { //edit
    var rows = $('#orderMainGrid').treegrid('getSelections');
    if (rows.length != 1) {
    $.messager.alert('消息', '请钩择一行数据!', 'info');
    return;
    }
  }
  $('<div/>').dialog({
			href : '${demoPath}admin/OrderMain/baseDlg.html',
			modal : true,
			title : title,
			top : '15%',
			left : '30%',
			width : 600,
			resizable:true,
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					orderMainGridSubmit(url);
					$(this).closest('.window-body').dialog('destroy');
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				if (type == 0) {
					$('#orderMainFm').form('clear');
				} else {
					var rows = $('#orderMainGrid').treegrid('getSelections');
					if (rows.length == 1) {
						$('#orderMainFm').form('load', rows[0]);
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function orderMainGridDel() {
		var rows = $('#orderMainGrid').treegrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '${demoPath}admin/OrderMain/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#orderMainGrid').treegrid('reload');
							$('#orderMainGrid').treegrid('clearSelections');
						} else {  
						    $.messager.show({ title: 'Error', msg : result.msg }); 
						} 
				    }, 'json');
				}
			});
		} else {
			$.messager.alert('消息', '请选择要删除的数据!','info');
		}
	}
	
	//Reload
	function orderMainGridReload() {
		$('#orderMainGrid').treegrid('options').pageNumber=1;
		$('#orderMainGrid').treegrid('reload',{});
	}
	
	//orderMainGridSubmit  submit
	function orderMainGridSubmit(url) {
	    $('#orderMainFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#orderMainDlg').dialog('close');      // close the dialog 
					$('#orderMainGrid').treegrid('reload');    // reload the user data
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function orderMainSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function orderMainSearch() {
		$('<div/>').dialog({
			href : '${demoPath}admin/OrderMain/searchDlg.html',
			modal : true,
			title : '高级查询',
			top : 120,
			width : 480,
			buttons : [ {
				text : '增加一行',
				iconCls : 'icon-add',
				handler : function() {
					var currObj = $(this).closest('.panel').find('table');
					currObj.find('tr:last').clone().appendTo(currObj);
					//currObj.find('tr:last td *[disabled]').removeAttr("disabled");
				}
			}, {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					$('#orderMainGrid').treegrid('options').pageNumber=1;
					$('#orderMainGrid').treegrid('reload',serializeObjectEx($('#orderMainSearchFm')));
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	
	//导出
	function orderMainGridExport(){
		window.location="${demoPath}admin/orderMainme}/export.html";
		//var panel=$('#tbcTempGrid').treegrid('getPanel');
		//var options=panel.panel('options');
		//alert(options.method);
		//console.dir(options);
	}
	
	//导入
	function orderMainGridImport(){
		$('<div/>').dialog({
			href : '${demoPath}admin/OrderMain/importDlg.html',
			modal : true,
			title : '导入',
			top : '15%',
			left : '30%',
			width : 600,
			height: 300,
			resizable:true,
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					//$(this).closest('.window-body').dialog('destroy');
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				
			}
		});
	}
	
</script>
    

	<!-- 中  datagrid-->
    <div data-options="region:'center',border : false">
		<!-- datagrid toolbar --><!-- class="easyui-datagrid" -->
		<table id="orderMainGrid"   class="easyui-treegrid"  data-options="	
			url:'${demoPath}admin/OrderMain/data.html?gridName=treegrid',
			frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
			
			columns:[ [  
			{field:'id',title:'主键',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'orderSn',title:'平台订单ID',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'userId',title:'用户id',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'sellerPrice',title:'订单供应商总价格（结算用,价格快照）',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'comPrice',title:'订单商品价格',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'postFee',title:'订单运费',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'totalPrice',title:'订单总价格',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'updateTime',title:'最新修改时间',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'closeTime',title:'订单关闭时间',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'endTime',title:'订单结束时间',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'createTime',title:'订单创建时间',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'invoiceContent',title:'发票内容',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'invoiceTitle',title:'发票抬头',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'invoiceType',title:'发票类型（0纸质，1电子',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'isInvoice',title:'是否要发票(0:不需要发票 1：个人发票，公司发票)',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'payStatus',title:'订单支付状态（0：未支付 1：全部支付2：部分支付 ）',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'paymentTime',title:'支付时间',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'buyerMessage',title:'买家留言',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'refundStatus',title:'退款状态',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'merSn',title:'商户编号',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'deleteFlag',title:'订单取消标识（1：已取消 0：正常订单）',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'status',title:'订单状态（0待审核，1审核拒绝，2待付款，3待发货，4已发货，5已完成，6交易关闭',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}}		
			
			] ],
			toolbar:'#orderMainGridToolbar'
		"/>
		
		<!-- treegrid toolbar -->
		<div id="orderMainGridToolbar">
			<div style="margin-bottom:5px">
				<c:forEach items="${buttons}" var="button">
			         ${button}
			    </c:forEach>
				<%-- 
				<a href="javascript:void(0)" onclick="javascript:orderMainGridAddAndEdit('添加  orderMain','${demoPath}admin/OrderMain/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="javascript:void(0)" onclick="javascript:orderMainGridAddAndEdit('修改  orderMain','${demoPath}admin/OrderMain/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="javascript:void(0)" onclick="javascript:orderMainGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="javascript:void(0)" onclick="javascript:orderMainGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="javascript:void(0)" onclick="javascript:orderMainGridExport()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="javascript:void(0)" onclick="javascript:orderMainGridImport()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- orderMainGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#orderMainGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
						var str='{searchType:1,'+name+':'+value+'}';
				        var obj = eval('('+str+')');
				        $('#orderMainGrid').treegrid('options').pageNumber=1;
						$('#orderMainGrid').treegrid('reload',obj);
					}
				"/>
				<div id="orderMainGridToolbarSearch">
					<div name="id">主键</div>
					<div name="name">规则名称</div>
					<div name="match_expr">规则内容</div>
					<div name="target">命名规则</div>
					<div name="data_info">下级规则</div>
				</div>
				<a href="javascript:void(0)" onclick="javascript:orderMainSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->
