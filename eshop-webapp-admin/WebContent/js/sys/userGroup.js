/* 权限左右移动 */
$(function(){

    // 移到右边
    $('.addOne',"#userGroupDlg-operate").click(function(){
        // 获取选中的选项，删除并追加给对方
        var p = $(this).parent().parent().parent();
        p.find('.allItems option:selected').appendTo(p.find('.selectItems'));
    });
    // 移到左边
    $('.removeOne',"#userGroupDlg-operate").click(function(){
        var p = $(this).parent().parent().parent();
        p.find('.selectItems option:selected').appendTo(p.find('.allItems'));
    });
    // 全部移到右边
    $('.addAll',"#userGroupDlg-operate").click(function(){
        // 获取全部的选项,删除并追加给对方
        var p = $(this).parent().parent().parent();
        p.find('.allItems option').appendTo(p.find('.selectItems'));
    });
    // 全部移到左边
    $('.removeAll',"#userGroupDlg-operate").click(function(){
        var p = $(this).parent().parent().parent();
        p.find('.selectItems option').appendTo(p.find('.allItems'));
    });
    // 双击选项
    $('.allItems',"#userGroup-fm").dblclick(function(){ // 绑定双击事件
        // 获取全部的选项,删除并追加给对方
        var p = $(this).parent().parent();
        $("option:selected", this).appendTo(p.find('.selectItems')); // 追加给对方
    });
    // 双击选项
    $('.selectItems',"#userGroup-fm").dblclick(function(){
        var p = $(this).parent().parent();
        $("option:selected", this).appendTo(p.find('.allItems'));
    });

//    //展开折叠效果(没有动态添加元素时此种可以使用)
//	$("#userGroup_baseTable").find("div[name^='header_']").each(function(){
//		var id = $(this).attr("name").replace(/header/,"table");
//		//alert($(this).attr("id"));
//		$(this).toggle(
//			function(){
//				$("#"+id).slideUp("fast");
//				$(this).removeClass("datagrid-row-collapse").addClass("datagrid-row-expand");
//			},
//			function(){
//				$("#"+id).slideDown("fast");
//				$(this).removeClass("datagrid-row-expand").addClass("datagrid-row-collapse");
//			}
//		)
//
//	})

	//将展开效果绑定到父类中（因为动态添加的元素也是需要事件）
	$("#tbody").live("click",function(event){
		var this$= $(event.target);
		var target$= this$.closest('td').find("div");
		//alert(this$.closest('td').html());
		if ($(target$).attr("name") && $(target$).attr("name").match(/header/)) {
			var id = $(this$).attr("name").replace(/header/, "table");
			//alert($(this).attr("id"));
			$(this$).toggle(function(){
				$("#" + id).slideUp("fast");
				$(this$).removeClass("datagrid-row-collapse").addClass("datagrid-row-expand");
			}, function(){
				$("#" + id).slideDown("fast");
				$(this$).removeClass("datagrid-row-expand").addClass("datagrid-row-collapse");
			}).trigger('click');
		}

	});

	//$("#tbody").trigger("click");

    //js完成加载后，初始化组件
	$("#userGroupToolbar").show();
	$("#userGroup_table_wrapper").show();
	$("#userGroup_parent_userGroupDlg").show();


});


var userGroup={

    //新增用户组
	addUserGroup: function(){
		$('#userGroup-fm').form('clear');
		//清空上次选 择的用户成员信息
		$("#selectItems","#userGroup-fm").empty();
		//清空左侧上次初始化的用户成员信息
		$("#allItems","#userGroup-fm").empty();

	    //动态获取用户成员信息
		$.post(ctx+'/sys/getAllUndeleteUsers.action',function(data){
			if(data){
				var userOptions ="";
				$(data).each(function(){
						userOptions=userOptions+"<option value='"+this.id+"'>"+this.fullName+"</option>";
					}
				);
				//alert(userOptions);
				$("#allItems","#userGroup-fm").html(userOptions);
			}
		},"json");

		$('#userGroupDlg').dialog('open').dialog('setTitle','添加');
		//保存url信息
		$('#userGroupDlg').removeData("url").data("url",ctx+'/sys/addUserGroups.action');
	},

    //动态添加用户组
	addDynamicUserGroup: function(groupId,groupName){
		var  subGroup =
		'<tr id="subGroup'+groupId+'" name="'+groupName+'">'+
            '<td align="center">'+
                '<div name="header_'+groupId+'"style="cursor: pointer; height: 14px;" row-index="0" class="datagrid-row-expander datagrid-row-collapse"></div>'+
            '</td>'+
            '<td style="text-align:left; padding-left:3px;"><input type="checkbox" id ="checkbox_'+groupId+'" value="" /><label for="checkbox_'+groupId+'" style="padding-left:3px;">'+groupName+'</label></td>'+
        '</tr>'+
        '<tr id="table_'+groupId+'">'+
            '<td></td>'+
            '<td style="padding-left:46px;">'+
                '<table id="data_grid_'+groupId+'" style="width:430px;" class="easyui-datagrid" url ="'+ctx+'/sys/listUsersByUserGroupId.action?groupId='+groupId+'" fit="false" border="false" fitColumns="true" striped="true">'+
                    '<thead>'+
                        '<tr>'+
                            '<th field="userName" width="128">登陆名</th>'+
                            '<th field="fullName" width="128">真实姓名</th>'+
                            '<th field="phone" width="128">手机</th>'+
                        '</tr>'+
                    '</thead>'+
                '</table>'+
            '</td>'+
        '</tr>';
		//alert(subGroup);

		$("#tbody").append(subGroup);
		//重新解析，刷新效果
//		$.parser.parse("#tbody");
		$.parser.parse("#table_"+groupId);
	},



	saveUserGroup:function(){
		var url = $('#userGroupDlg').data("url");
		$('#userGroup-fm').form('submit',{
				url: url,
				onSubmit: function(){
					//easyui是按form来提交的，因此对于需要传的参数，可以动态构建表单隐藏域来传值，在返回时将该隐藏域清除
					var optionsValue = $("#selectItems","#userGroup-fm").find("option").map(function(){
						return $(this).val();
					}).get().join(",");
					if(optionsValue != ''){
						$('<input type="hidden" id="selectUserIds" name ="selectUserIds">').val(optionsValue).appendTo($('#userGroup-fm'));

					}
					return $(this).form('validate');
				},
				success: function(data){
					//因为easyUI返回的是json格式的html需要自己来解析
					var result = eval('('+data+')');
					if (result.type=="add"){
						if(result.status == "success"){
							//清除动态构造的隐藏域
							$("#selectUserIds","#userGroup-fm").remove();
							$("#selectGroupId","#userGroup-fm").remove();
							$('#userGroupDlg').dialog('close');
							//将新组添加至用户组列表中
							var groupId= result.groupId;
							var groupName = result.groupName;
				            userGroup.addDynamicUserGroup(groupId, groupName);
							$('#userGroupDlg').dialog('close');
							$.messager.alert("info",result.msg);
						}else{
							//清除动态构造的隐藏域
							$("#selectUserIds","#userGroup-fm").remove();
							$("#selectGroupId","#userGroup-fm").remove();
							$('#userGroupDlg').dialog('close');
							$.messager.alert("info",result.msg);
						}

				   } else if(result.type=="edit"){
				   		if(result.status =="success"){
							$('#data_grid_'+result.groupId).datagrid('reload');
							//清除动态构造的隐藏域
							$("#selectGroupId","#userGroup-fm").remove();
							$("#selectUserIds","#userGroup-fm").remove();
							$('#userGroupDlg').dialog('close');
							$.messager.alert("info",result.msg);
						}else{
							//清除动态构造的隐藏域
							$("#selectGroupId","#userGroup-fm").remove();
							$("#selectUserIds","#userGroup-fm").remove();
							$('#userGroupDlg').dialog('close');
							$.messager.alert("info",result.msg);

						}

				   }

				}
			});
	},
    //选 择单个用户组(存在选 择多个时清除选 择)
	selectSingleUserGroup:function(){
		var selectCheckBox$= $("#userGroup_baseTable #tbody").find(":checkbox:checked");
		if(selectCheckBox$.length==0){
			$.messager.alert("info","请选择用户组!");
			return null;
		}

		if(selectCheckBox$.length>1){
			$.messager.alert("info","只能选择单个用户组!");
			selectCheckBox$.each(function(){
				 $(this).attr('checked', false);
			});
			return null;
		}else{
			var selectUserGroupId = selectCheckBox$.attr("id");
			var groupId = selectUserGroupId.substr(selectUserGroupId.indexOf("checkbox_")+9);
			//alert(groupId);
			return groupId;

		}

	},

	editUserGroup:function(){
		    var selectGroupId = userGroup.selectSingleUserGroup();
			//编辑前先展开，否则折叠编辑当前展示结果为空
			$("#userGroup_baseTable").find("div[name='header_"+selectGroupId+"']").removeClass("datagrid-row-expand").addClass("datagrid-row-collapse");
			$("#table_" + selectGroupId).slideDown();

			if(selectGroupId ==null){
				return false;
			}

			var selectGroupName = $("#userGroup_baseTable #tbody").find("#subGroup"+selectGroupId).attr("name");
			//alert(selectGroupName);

			$('#userGroup-fm').form('clear');
			//清空上次选 择的用户成员信息
			$("#selectItems","#userGroup-fm").empty();
			//清空左侧上次初始化的用户成员信息
			$("#allItems","#userGroup-fm").empty();

		    //动态获取用户成员信息
			$.post(ctx+'/sys/getEditGroupUsers.action',{"groupId":selectGroupId},function(data){
				if(data){
					//待选择用户列表
					var leftSelectUsers = data.leftSelectUsers;
					var leftOptions = "";
					if(leftSelectUsers.length >0){
						$(leftSelectUsers).each(function(){
							leftOptions= leftOptions+"<option value='"+this.id+"'>"+this.fullName+"</option>";

						});

						$("#allItems","#userGroup-fm").html(leftOptions);
					}

					//已关联用户列表
					var rightSelectedUsers = data.rightSelectedUsers;
					var rightOptions ="";
					if(rightSelectedUsers.length>0){
						$(rightSelectedUsers).each(function(){
							rightOptions= rightOptions+"<option value='"+this.id+"'>"+this.fullName+"</option>";
						});
						$("#selectItems","#userGroup-fm").html(rightOptions);
					}
				}
			},"json");

			//赋予原组名（组名可以编辑)
			$('#userGroup-fm').find("input[name='groupName']").val(selectGroupName);

			//记录用户组id,保存时再清除此隐藏字段
			$('<input type="hidden" id="selectGroupId" name ="groupId">').val(selectGroupId).appendTo($('#userGroup-fm'));


			$('#userGroupDlg').dialog('open').dialog('setTitle','编辑');
			//保存url信息
			$('#userGroupDlg').removeData("url").data("url",ctx+'/sys/editUserGroups.action');





	},
	//删除用户组
	removeUserGroup:function(){
		var groupId = userGroup.selectSingleUserGroup();
		if(groupId){
				var userLength = $("#table_"+groupId).find(".datagrid-row").length;
				if(userLength>0){
					$.messager.alert("info","已关联用户的用户组不能删除，请先编辑!");
				}else{

					$.post(ctx+'/sys/removeUserGroup.action',{"groupId":groupId},function(data){
					if (data.status == "success"){
						//清除所选用户组记录
						$("#subGroup"+groupId).remove();
						$("#table_"+groupId).remove();
						$.messager.alert("info",data.msg);

					} else {
						$.messager.info("error",data.msg);

					}
				},'json');
			}
		}
	   }
};
