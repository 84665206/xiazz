$(function(){

    //树形展现
    $('#menusTree').tree({
        url: ctx+'/sys/listAllMenus.action',
        animate: true,
        checkbox: false,
        dnd: true,
        onClick: function(node){
            //查询出菜单数据，进行编辑
            //$(this).tree('toggle', node.target);
            
        },
        onDblClick: function(node){
            //切换节点展开/折叠状态
            $(this).tree('toggle', node.target);
        },
        onContextMenu: function(e, node){
            e.preventDefault();
            $('#menusTree').tree('select', node.target);
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        }
    });
    
    //绑定右键菜单 点击事件
    $('#mm').menu({
        onClick:function(item){
            //alert(item.id);
        	if(item.id == "reloadMenuCk"){
        		menumgt.reloadNode();
        	}else if(item.id == "addMenuCk"){
        		menumgt.newMenu();
        	}else if(item.id == "editMenuCk"){
        		menumgt.editMenu();
        	}else if(item.id == "delMenuCk"){
        		menumgt.deleteMenu();
        	}else if(item.id == "expandMenuCk"){
        		menumgt.expandAllNode();
        	}else if(item.id == "collapseMenuCk"){
        		menumgt.collapseAllNode();
        	}else{
        		$.messager.alert("Info", "未找到调用方法", "error");
        	}
        }
    });
    
    //绑定全选点击事件
    $("#roleAllCk").click(function(){
    	var flag = $("#roleAllCk").attr("checked");
    	//alert(flag);
    	if(flag == "checked"){
    		$('#roleUl').find('input[name="roleCk"]').attr("checked","true");
    	}else{
    		$('#roleUl').find('input[name="roleCk"]').removeAttr("checked");
    	}
    });
    
    //显示角色多选框列表
    menumgt.showAllRole();
    
});

var menumgt={
		
	//全部展开菜单树
	expandAllNode: function(){
        var node = $('#menusTree').tree('getSelected');
        if (node) {
            $('#menusTree').tree('expandAll', node.target);
        }
        else {
            $('#menusTree').tree('expandAll');
        }
    },
    
	//全部收起菜单树
	collapseAllNode: function(){
		var node = $('#menusTree').tree('getSelected');
        if (node) {
            $('#menusTree').tree('collapseAll', node.target);
        }
        else {
            $('#menusTree').tree('collapseAll');
        }
	},

	//重新加载菜单树
	reloadNode: function(){
        var node = $('#menusTree').tree('getSelected');
        if (node) {
        	$('#menusTree').tree('reload', node.target);
        }
        else {
            $('#menusTree').tree('reload');
        }
    },

	//保存数据后，重新加载菜单树
	reloadNodeAfterSave: function(){
        var node = $('#menusTree').tree('getSelected');
        if (node) {
    		//获取上级菜单信息
    		var pMenu = $('#menusTree').tree('getParent', node.target);
        	if(pMenu){
        		//刷新上级
        		$('#menusTree').tree('reload', pMenu.target);
        	}else{
        		//已经是顶级，全部刷新
        		$('#menusTree').tree('reload');
        		
        	}
        	$('#menusTree').tree('select', node.target);
        }
        else {
            $('#menusTree').tree('reload');
        }
    },
    
    clearMenu: function(){
    	$('#editMenuInfoForm').form('clear');
    },
    
	newMenu: function(){
		var menu = $('#menusTree').tree('getSelected');
		if(!menu){
			alert("请选择所属上级菜单!");
			return false;
		}
		
		var pid = "";
		var pname = "";
		if(menu){
			pid = menu.id;
			pname = menu.text;
		}
		$('#editMenuInfoForm').form('clear');
		$('#editMenuInfoForm').attr('action',ctx+"/sys/addNewMenu.action");
		$('#editMenuInfoForm').find('input[name="upMenuId"]').val(pid);
		$('#editMenuInfoForm').find('input[name="upMenuName"]').val(pname);
	},

	editMenu:function(){
		var menu = $('#menusTree').tree('getSelected');
		if(!menu || menu.id==0){
			alert("请选择需要编辑的菜单!");
			return false;
		}
		
		//获取上级菜单信息
		var pMenu = $('#menusTree').tree('getParent', menu.target);
		$('#editMenuInfoForm').find('input[name="upMenuName"]').val(pMenu.text);
		
		$.ajax({
			url:ctx+"/sys/searchMenu.action",
		 	type:"post",
			dataType:"json",
			data:$.param({id:menu.id,anticache:Math.floor(Math.random()*1000)},true),
			success:function(result){
				if(result.status == "success"){
					var rslData = result.data;
					//alert(rslData);
					$('#editMenuInfoForm').attr('action',ctx+"/sys/editMenu.action");
					$('#editMenuInfoForm').find('input[name="upMenuId"]').val(rslData.parentId);
					
					$('#editMenuInfoForm').find('input[name="id"]').val(rslData.id);
					$('#editMenuInfoForm').find('input[name="menuName"]').val(rslData.chName);
					$('#editMenuInfoForm').find('input[name="vistPath"]').val(rslData.menuUri);
					
					$('#editMenuInfoForm').find('input[name="menuType"][value="' + rslData.status + '"]').attr("checked",true);
					$('#editMenuInfoForm').find('select[name="level"]').val(rslData.level);
					$('#editMenuInfoForm').find('input[name="seqencing"]').val(rslData.sortIndex);
					
					//填充 所属角色 多选框
					var listData = result.roleMenuList;
					// 先清空
					$('#roleUl').find('input[name="roleCk"]').removeAttr("checked");
					// 再匹配
					for(var i=0; i<listData.length; i++){
						$('#editMenuInfoForm').find('input[name="roleCk"][value="' + listData[i].roleId + '"]').attr("checked",true);
					}
				}else{
					$.messager.alert(result.status, result.msg, "error");
				}
			}
		 });
	},
	
	saveMenu:function(){
		var url = $('#editMenuInfoForm').attr('action');
		$('#editMenuInfoForm').form('submit',{
			url:url,
			//do some check
			onSubmit:function(){
				var menuName = $('input[name="menuName"]').val();
				var menuType =  $("input[@type=radio][name=menuType][checked]").val();
				var menuLevel = $('select[name="level"]').val();
				var seqencing = $('input[name="seqencing"]').val();
				if(!menuName){
					alert("请填写菜单名称");
					return false;
				}
				if(!menuType){
					alert("请选择菜单类型");
					return false;
				}
				if(menuLevel==-1){
					alert("请选择菜单类型等级(下拉框)");
					return false;
				}
				if(seqencing==""){
					alert("请输入菜单的排序");
					return false;
				}
			},
			success:function(result){
				var res = eval('('+result+')');
				if(res.status == 'success'){
					$.messager.show({title: res.status,msg: res.msg});
					menumgt.reloadNodeAfterSave(); //reload data
				}else{
					$.messager.alert(res.status, res.msg, "error");
				}
			}
		});
	},
	
	deleteMenu:function(){
		var menu = $('#menusTree').tree('getSelected');
		if(!menu){
			alert("请选择要删除的菜单！");
			return false;
		} else if(menu.id==0){
			alert("不能一次性删除所有的菜单！");
			return false;
		} else{
			$.messager.confirm('Confirm','确定要删除这些数据吗？',function(r){
				if(r){
					var ids = menu.id;
					//找出所有的 孩子 节点
					var childrenMenus = $('#menusTree').tree('getChildren', menu.target);
					if(childrenMenus.length>0){
						for(var i=0; i<childrenMenus.length; i++){
							ids = ids + "," + childrenMenus[i].id;
						}
					}
					
					$.ajax({
						url:ctx+"/sys/deleteMenu.action",
					 	type:"post",
						dataType:"json",
						data:$.param({ids:ids,anticache:Math.floor(Math.random()*1000)},true),
						success:function(result){
							if(result.status == "success"){
								$.messager.show({title: result.status,msg: result.msg});
								//获取上级菜单信息
								var pMenu = $('#menusTree').tree('getParent', menu.target);
								$('#menusTree').tree('select', pMenu.target);
								menumgt.reloadNode(); //reload data
							}else{
								$.messager.alert(result.status, result.msg, "error");
							}
						}
					});
				}
			});
		}
	},
	
	exportAllMenu: function(){
		parent.$.messager.confirm('友情提示','数据导出需要耗费时间较长，是否继续？',function(b){
			if(b){
				window.location=ctx+"/sys/exportAllMenu.action?t="+ (new Date().getTime());
			}
		});
	},
	
	showAllRole: function(){
		$.ajax({
			url:ctx+"/sys/searchAllRoles.action",
		 	type:"post",
			dataType:"json",
			data:$.param({anticache:Math.floor(Math.random()*1000)},true),
			success:function(result){
				if(result.status == "success"){
					var allRoleList = result.data;
					var checkboxHtml = "";
					for(var i=0; i<allRoleList.length; i++){
						checkboxHtml += '<li><input name="roleCk" type="checkbox" value="'+allRoleList[i].id+'" /><span title="'+allRoleList[i].roleName+'">'+allRoleList[i].roleName+'</span></li>';
					}
					$('#roleUl').html(checkboxHtml);
				}
			}
		});
	},
	
	chooseSelect:function(type){
		if(type==1){
			$("select[name='level']").val(-1);
		}
		if(type==2){
			$("select[name='level']").val(4);
		}
	}
};