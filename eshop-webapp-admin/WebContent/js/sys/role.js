$(function(){
	function removeRole(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$.messager.confirm('Confirm','确定要删除?',function(r){
				if (r){
					$.post('remove_Role.php',{id:row.id},function(result){
						if (result.success){
							$('#dg').datagrid('reload');	// reload the Role data
						} else {
							$.messager.show({	// show error message
								title: 'Error',
								msg: result.msg
							});
						}
					},'json');
				}
			});
		}
	}
});

function newRole(){
	$('#roleDialog').dialog('open').dialog('setTitle','添加');
	$('#fm').form('clear');
	$("#saveButn").unbind().click(function(){
		var url = ctx+"/sys/saveRole.action";
		saveRole(url);
	});
}

function saveRole(url){
	$("#saveButn").unbind();
	$('#fm').form('submit',{
		url:url ,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(data){
			var result = eval('('+data+')');		
			if (result.status=="success"){
				$('#roleDialog').dialog('close');	// close the dialog
				$('#roleList').datagrid('reload');	// reload the Role data
			} else {
				$.messager.alert('错误',result.msg,'error');
				$("#saveButn").unbind().click(function(){
					saveRole(url);
				});
				return;
			}
		}
	});
}

function editRole(){
	var rows = $('#roleList').datagrid('getSelections');
	if(rows.length !=1){
		$.messager.alert('错误','请选择一条需要编辑的记录','error');
		return;
	}
	$("#fm").form('load',rows[0]);
	$("#saveButn").unbind().click(function(){
		var url = ctx+"/sys/updateRole.action?roleId="+rows[0].id;
		saveRole(url);
	});
	$('#roleDialog').dialog('open').dialog('setTitle','编辑');
}

function deleteRoles(){
	var rows = $('#roleList').datagrid('getSelections');
	if(rows.length){
		var roleIds = new Array();
		for(var i=0;i<rows.length;i++){
			roleIds.push(rows[i].id);
		}
		$.ajax({
			url:ctx+"/sys/deleteRole.action",
			type:"post",
			data:$.param({roleIds:roleIds,anticache:Math.floor(Math.random()*1000)},true),
			dataType:"json",
			success:function(data){
				if(data.status =="success"){
					$.messager.alert('成功','删除角色成功','info');
					$('#roleList').datagrid('clearSelections');
					$('#roleList').datagrid('reload');
				}else{
					$.messager.alert('错误',data.msg,'error');
				}
			}
		});
	}else{
		$.messager.alert('错误','请选择需要删除的记录','error');
		return;
	}
}

/**
 * 分配权限
 */
function grantPermission(roleId){
	var rows = $('#roleList').datagrid('getSelections');
	if(rows.length != 1){
		$.messager.alert("错误","请选择需要分配权限的角色！","error");
		return false;
	}
	var roleId = rows[0].id;
	$('#grantPermissionDg').dialog('open').dialog('setTitle','给角色分配权限');
	$('#listMenus').treegrid({
		width:800,
		height:450,
		animate:true,
		nowrap: false,
		collapsible:true,
		singleSelect:false,
		collapseAll:true,
		checkRecursive:true,
		url: ctx+'/sys/listRolsMenu.action?roleId='+roleId,
		idField:'id',
		treeField:'id',
		frozenColumns:[[
            {title:'id',field:'id',width:160}
		]],
		columns:[[
			{field:'chName',title:'菜单名称',width:180,align:'left'},
		    {field:'levelDesc',title:'菜单级别',width:60,align:'center'},
		    {field:'statusDesc',title:'类别',width:60,align:'center'},
		    {field:'menuUri',title:'菜单URL',width:280,align:'center'},
		    {field:'isCheck',title:'多选',width:60,align:'left' ,checkbox:true}
		]],
		onLoadSuccess:function(){
			var rows = $('#listMenus').treegrid('getChildren');
			for(var i=0;i<rows.length;i++){
				if(rows[i].isCheck == 1){
					$('#listMenus').treegrid('select',rows[i].id);
				}
			}
		}
	});
}

/**
 * 授权保存
 */
function saveGrantMenu(){
	var menuRows = $('#listMenus').treegrid('getSelections'); //菜单id
	var roleRows = $('#roleList').datagrid('getSelections'); //角色id
	if(menuRows.length ==0){
		$.messager.alert('错误','请选择菜单！','error');
		return false;
	}
	var menuIds = new Array();
	menuIds.push(roleRows[0].id);//角色id
	for(var i=0;i<menuRows.length;i++){
		menuIds.push(menuRows[i].id);
	}
	$.ajax({
		url : ctx + '/sys/saveGrantMenus.action',
		data : $.param({menuIds:menuIds,anticache:Math.floor(Math.random()*1000)},true),
		dataType : 'json',
		type : 'post',
		success : function(data){
			if(data.status =="success"){
				$.messager.alert('成功','分配权限成功','info');
				$('#grantPermissionDg').dialog('close');
			}else{
				$.messager.alert('错误',data.msg,'error');
				$('#grantPermissionDg').dialog('close');
			}
		}
	});
}


