<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  
  
  <script type="text/javascript">
  //Add and Edit
  function crawljobGridAddAndEdit(title, url, type) {
    if (type == 1) { //edit
    var rows = $('#crawljobGrid').datagrid('getSelections');
    if (rows.length != 1) {
    $.messager.alert('消息', '请钩择一行数据!', 'info');
    return;
    }
  }
  $('<div/>').dialog({
			href : '${demoPath}admin/Crawljob/baseDlg.html',
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
					crawljobGridSubmit(url);
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
					$('#crawljobFm').form('clear');
				} else {
					var rows = $('#crawljobGrid').datagrid('getSelections');
					if (rows.length == 1) {
						$('#crawljobFm').form('load', rows[0]);
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function crawljobGridDel() {
		var rows = $('#crawljobGrid').datagrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '${demoPath}admin/Crawljob/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#crawljobGrid').datagrid('reload');
							$('#crawljobGrid').datagrid('clearSelections');
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
	function crawljobGridReload() {
		$('#crawljobGrid').datagrid('options').pageNumber=1;
		$('#crawljobGrid').datagrid('reload',{});
	}
	
	//crawljobGridSubmit  submit
	function crawljobGridSubmit(url) {
	    $('#crawljobFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#crawljobDlg').dialog('close');      // close the dialog 
					$('#crawljobGrid').datagrid('reload');    // reload the user data
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function crawljobSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function crawljobSearch() {
		$('<div/>').dialog({
			href : '${demoPath}admin/Crawljob/searchDlg.html',
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
					$('#crawljobGrid').datagrid('options').pageNumber=1;
					$('#crawljobGrid').datagrid('reload',serializeObjectEx($('#crawljobSearchFm')));
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
	function crawljobGridExport(){
		window.location="${demoPath}admin/Crawljob/export.html";
		//var panel=$('#tbcTempGrid').datagrid('getPanel');
		//var options=panel.panel('options');
		//alert(options.method);
		//console.dir(options);
	}
	
	//导入
	function crawljobGridImport(){
		$('<div/>').dialog({
			href : '${demoPath}admin/Crawljob/importDlg.html',
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
		<!-- datagrid toolbar -->
		<table id="crawljobGrid"  class="easyui-datagrid"  data-options="	
			url:'${demoPath}admin/Crawljob/data.html',
			frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
			columns:[ [  

			{field:'id',title:'主键',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'job_name',title:'名称',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'target_domain',title:'网站域名',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},		
			{field:'start_url',title:'网站起点',hidden:false,width:'235',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},
			{field:'create_time',title:'创建时间',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},
			{field:'rule_key',title:'rule_key',hidden:false,width:'235',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}}
			
			] ],
			toolbar:'#crawljobGridToolbar'
		"/>
		
		<!-- datagrid toolbar -->
		<div id="crawljobGridToolbar">
			<div style="margin-bottom:5px">
				<c:forEach items="${buttons}" var="button">
			         ${button}
			    </c:forEach>
				<%-- 
				<a href="javascript:void(0)" onclick="javascript:crawljobGridAddAndEdit('添加  crawljob','${demoPath}admin/Crawljob/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="javascript:void(0)" onclick="javascript:crawljobGridAddAndEdit('修改  crawljob','${demoPath}admin/Crawljob/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="javascript:void(0)" onclick="javascript:crawljobGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="javascript:void(0)" onclick="javascript:crawljobGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="javascript:void(0)" onclick="javascript:crawljobGridExport()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="javascript:void(0)" onclick="javascript:crawljobGridImport()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- crawljobGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#crawljobGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
						var str='{searchType:1,'+name+':'+value+'}';
				        var obj = eval('('+str+')');
				        $('#crawljobGrid').datagrid('options').pageNumber=1;
						$('#crawljobGrid').datagrid('reload',obj);
					}
				"/>
				<div id="crawljobGridToolbarSearch">
					<div name="id">主键</div>
					<div name="job_type">任务类型</div>
					<div name="job_name">任务名称</div>
					<div name="rule_key">抓取规则</div>
					<div name="target_domain">目标网站</div>
					<div name="protocol">网络协议</div>
					<div name="start_url">抓取起始</div>
					<div name="data_type">数据请求类型</div>
					<div name="job_tag">任务</div>
					<div name="client_id">使用</div>
					<div name="load_class">状态</div>
					<div name="status">开启</div>
				</div>
				<a href="javascript:void(0)" onclick="javascript:crawljobSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->
