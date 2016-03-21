<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  
  
  <script type="text/javascript">
  //Add and Edit
  function crawlruleGridAddAndEdit(title, url, type) {
    if (type == 1) { //edit
    var rows = $('#crawlruleGrid').treegrid('getSelections');
    if (rows.length != 1) {
    $.messager.alert('消息', '请钩择一行数据!', 'info');
    return;
    }
  }
  $('<div/>').dialog({
			href : '${demoPath}admin/Crawlrule/baseDlg.html',
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
					crawlruleGridSubmit(url);
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
					$('#crawlruleFm').form('clear');
				} else {
					var rows = $('#crawlruleGrid').treegrid('getSelections');
					if (rows.length == 1) {
						$('#crawlruleFm').form('load', rows[0]);
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function crawlruleGridDel() {
		var rows = $('#crawlruleGrid').treegrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '${demoPath}admin/Crawlrule/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#crawlruleGrid').treegrid('reload');
							$('#crawlruleGrid').treegrid('clearSelections');
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
	function crawlruleGridReload() {
		$('#crawlruleGrid').treegrid('options').pageNumber=1;
		$('#crawlruleGrid').treegrid('reload',{});
	}
	
	//crawlruleGridSubmit  submit
	function crawlruleGridSubmit(url) {
	    $('#crawlruleFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#crawlruleDlg').dialog('close');      // close the dialog 
					$('#crawlruleGrid').treegrid('reload');    // reload the user data
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function crawlruleSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function crawlruleSearch() {
		$('<div/>').dialog({
			href : '${demoPath}admin/Crawlrule/searchDlg.html',
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
					$('#crawlruleGrid').treegrid('options').pageNumber=1;
					$('#crawlruleGrid').treegrid('reload',serializeObjectEx($('#crawlruleSearchFm')));
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
	function crawlruleGridExport(){
		window.location="${demoPath}admin/Crawlrule/export.html";
		//var panel=$('#tbcTempGrid').treegrid('getPanel');
		//var options=panel.panel('options');
		//alert(options.method);
		//console.dir(options);
	}
	
	//导入
	function crawlruleGridImport(){
		$('<div/>').dialog({
			href : '${demoPath}admin/Crawlrule/importDlg.html',
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
		<table id="crawlruleGrid"   class="easyui-treegrid"  data-options="	
			url:'${demoPath}admin/Crawlrule/data.html?gridName=treegrid',
			frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
			columns:[ [  

			{field:'id',title:'主键',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'name',title:'名称',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},			

			{field:'match_expr',title:'抽取方法',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},
			
			{field:'target',title:'target',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}},
			{field:'data_info',title:'data_info',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
			    return value;
			}}
			
			] ],
			toolbar:'#crawlruleGridToolbar'
		"/>
		
		<!-- treegrid toolbar -->
		<div id="crawlruleGridToolbar">
			<div style="margin-bottom:5px">
				<c:forEach items="${buttons}" var="button">
			         ${button}
			    </c:forEach>
				<%-- 
				<a href="javascript:void(0)" onclick="javascript:crawlruleGridAddAndEdit('添加  crawlrule','${demoPath}admin/Crawlrule/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="javascript:void(0)" onclick="javascript:crawlruleGridAddAndEdit('修改  crawlrule','${demoPath}admin/Crawlrule/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="javascript:void(0)" onclick="javascript:crawlruleGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="javascript:void(0)" onclick="javascript:crawlruleGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="javascript:void(0)" onclick="javascript:crawlruleGridExport()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="javascript:void(0)" onclick="javascript:crawlruleGridImport()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- crawlruleGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#crawlruleGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
						var str='{searchType:1,'+name+':'+value+'}';
				        var obj = eval('('+str+')');
				        $('#crawlruleGrid').treegrid('options').pageNumber=1;
						$('#crawlruleGrid').treegrid('reload',obj);
					}
				"/>
				<div id="crawlruleGridToolbarSearch">
					<div name="id">主键</div>
					<div name="name">规则名称</div>
					<div name="match_expr">规则内容</div>
					<div name="target">命名规则</div>
					<div name="data_info">下级规则</div>
				</div>
				<a href="javascript:void(0)" onclick="javascript:crawlruleSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->
