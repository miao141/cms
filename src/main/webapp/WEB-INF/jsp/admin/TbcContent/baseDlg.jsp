<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 弹出框 tbcContentDlg -->
<div id="tbcContentDlg">
	<form id="tbcContentFm" method="post">
		<table>
			<tr>
				<td><label> 主键: </label></td>
    			<td >
					<input name="id" class="easyui-validatebox"  readonly="readonly"  />
				</td>
            </tr>
            <tr>
				<td><label> 关联导航: </label></td>
    			<td >
				<input name="menuId" class="easyui-validatebox" type="text"  />
				</td>

	 		</tr>
	        <tr>

				<td><label> 标题: </label></td>
    			<td >
				<input name="title" class="easyui-validatebox" type="text"  />
				</td>
			</tr>
			<tr>
				<td><label> 标签: </label></td>
    			<td >
				<input name="tags" class="easyui-validatebox" type="text"  />
				</td>

	 		</tr>
	        <tr height="100">

				<td><label> 内容: </label></td>
    			<td >
					<script type="text/plain" id="myEditor" class="myEditor">
					
  					</script>
					<script type="text/javascript">
                        var editor = new baidu.editor.ui.Editor();
                        editor.render('myEditor');
						//editor.setContent($("content"));
                    </script> 
				<input name="content" class="easyui-validatebox" type="text"/><!-- -->
				<input id="editor"  type="text"/>
				</td>

			</tr>
			<tr>
				<td><label> 状态: </label></td>
    			<td >
				<input name="status" class="easyui-validatebox" type="text"  />
				</td>

	 		</tr>
	        <tr>

				<td><label> 排序: </label></td>
    			<td >
				<input name="orderByNumber" class="easyui-validatebox" type="text"  />
				</td>
			</tr>
			<tr>

				<td><label> 创建时间: </label></td>
    			<td >
				<input name="createTime"  class="easyui-datetimebox" />
				</td>


		</table>
	</form>
</div>
