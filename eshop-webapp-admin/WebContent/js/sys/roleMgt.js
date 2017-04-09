$(function(){
	
	
	
});


var roleMgt = {

	//新增 角色
	newRole : function (){
		$('#roleDialog').dialog('open').dialog('setTitle','添加');
		$('#fm').form('clear');
		$("#saveButn").unbind().click(function(){
			var url = ctx+"/sys/saveRole.action";
			roleMgt.saveRole(url);
		});
	},

	//编辑 角色
	editRole : function (){
		var rows = $('#roleList').datagrid('getSelections');
		if(rows.length !=1){
			$.messager.alert('错误','请选择一条需要编辑的记录','error');
			return;
		}
		$("#fm").form('load',rows[0]);
		$("#saveButn").unbind().click(function(){
			var url = ctx+"/sys/updateRole.action?roleId="+rows[0].id;
			roleMgt.saveRole(url);
		});
		$('#roleDialog').dialog('open').dialog('setTitle','编辑');
	},

	//保存 角色
	saveRole : function (url){
		$("#saveButn").unbind();
		$('#fm').form('submit',{
			url:url ,
			onSubmit: function(){
				return $(this).form('validate');
			},
			success: function(data){
				var result = eval('('+data+')');		
				if (result.status=="success"){
					$.messager.show({title: result.status,msg: result.msg});
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
	},

	//删除 角色
	deleteRoles : function (){
		var rows = $('#roleList').datagrid('getSelections');
		if(rows.length){
			
			$.messager.confirm('Confirm','确定要删除这些数据吗？',function(r){
				if(r){
					
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
								$.messager.show({title: data.status,msg: data.msg});
								$('#roleList').datagrid('clearSelections');
								$('#roleList').datagrid('reload');
							}else{
								$.messager.alert('错误',data.msg,'error');
							}
						}
					});
					
				}
			});
			
		}else{
			$.messager.alert('错误','请选择需要删除的记录','error');
			return;
		}
	},

	//分配权限
	grantPermission : function (roleId){
		var rows = $('#roleList').datagrid('getSelections');
		if(rows.length != 1){
			$.messager.alert("错误","请选择需要分配权限的角色！","error");
			return false;
		}
		var roleId = rows[0].id;
		$('#grantPermissionDg').dialog('open').dialog('setTitle','给角色分配权限');
		
		 //树形展现
	    $('#menusTree').tree({
	        url: ctx+'/sys/listRolsMenu.action?roleId='+roleId,
	        animate: true,
	        checkbox: true,
	        dnd: false,
	        cascadeCheck: false,
	        onCheck: function(node,checked){
	        	if(checked){
	        		var parent=$('#menusTree').tree('getParent',node.target);
	        		$('#menusTree').tree('check',parent.target);
	        	}else {
					
				}
	            
	        },
	        onDblClick: function(node){
	            //切换节点展开/折叠状态
	            $(this).tree('toggle', node.target);
	        }
	    });
		
	},

	//授权保存
	saveGrantMenu : function (){
		var roleRows = $('#roleList').datagrid('getSelections'); //选中角色
		var menuRows = $('#menusTree').tree('getChecked'); //选中菜单
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
					$.messager.show({title: data.status,msg: data.msg});
					$('#grantPermissionDg').dialog('close');
				}else{
					$.messager.alert('错误',data.msg,'error');
					$('#grantPermissionDg').dialog('close');
				}
			}
		});
	},
	
	//查看选中角色的用户
	selectRoleUser:function(){
		var roleRows = $('#roleList').datagrid('getSelections');
		if(roleRows.length==0){
			$.messager.alert('错误','请选择一条数据','error');
			return false;
		}
		if(roleRows.length>1){
			$.messager.alert('错误','只能选择一条数据','error');
			return false;
		}		
		$.ajax({
			url : ctx + '/sys/selectRoleUser.action',
			data : {roleId:roleRows[0].id},
			dataType : 'json',
			type : 'post',
			success : function(data){
				if(data.roleUserTotal>0){
					var html="";
					var roleUser=data.roleUser;
					for(var i=0;i<roleUser.length;i++){
						html+="<tr><td>"+roleUser[i].userName+"</td><td>"+roleUser[i].fullName+"</td></tr>";
					}
					$("#roleUserTotal").html(data.roleUserTotal);
					$("#roleUserInfo").html(html);					
				}else{
					$("#roleUserTotal").html(0);
					$("#roleUserInfo").html("<tr><td style='color:red;' colspan='2'>暂无用户使用此角色</td></tr>");		
				}
				$('#roleUserDlg').dialog('open');
			}
		});
	}

		
};

