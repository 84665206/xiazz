//初始化用户信息表格数据
$(function(){	
	 // 移到右边
    $('.addOne',"#sel-operate").click(function(){
        // 获取选中的选项，删除并追加给对方
        var p = $(this).parent().parent().parent();
        p.find('.allItems option:selected').appendTo(p.find('.selectItems'));
    });
    // 移到左边
    $('.removeOne',"#sel-operate").click(function(){
        var p = $(this).parent().parent().parent();
        p.find('.selectItems option:selected').appendTo(p.find('.allItems'));
    });
    // 全部移到右边
    $('.addAll',"#sel-operate").click(function(){
        // 获取全部的选项,删除并追加给对方
        var p = $(this).parent().parent().parent();
        p.find('.allItems option').appendTo(p.find('.selectItems'));
    });
    // 全部移到左边
    $('.removeAll',"#sel-operate").click(function(){
        var p = $(this).parent().parent().parent();
        p.find('.selectItems option').appendTo(p.find('.allItems'));
    });
    // 双击选项
    $('.allItems',"#grantRoleDlg").dblclick(function(){ // 绑定双击事件
        // 获取全部的选项,删除并追加给对方
        var p = $(this).parent().parent();
        $("option:selected", this).appendTo(p.find('.selectItems')); // 追加给对方
    });
    // 双击选项
    $('.selectItems',"#grantRoleDlg").dblclick(function(){
        var p = $(this).parent().parent();
        $("option:selected", this).appendTo(p.find('.allItems'));
    });
	//对于分页的操作处理
	var p = $('#tableUserInfo').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			//alert('before refresh');
		},
		//自定义分页参数发送到后台(可以不用自己写）
		onSelectPage: function(pageNum, pageSize){
			 var queryParams = $('#tableUserInfo').datagrid('options').queryParams;
		        queryParams.pageSize = pageSize;
		        queryParams.pageNo = pageNum;
		        //重新加载datagrid的数据
		        $("#tableUserInfo").datagrid('reload');
		}

	});
	
    //绑定用户查询事件（注意事件增加了命名空间)
    $("#searchUserInfoByCondition").unbind(".userInfoSearch").bind("click.userInfoSearch", function(){
        userInfo.searchUserInfoByCondition();
    });

});

var userInfoUrl;

//每个页面定义一个全局变量，避免变量污染
var userInfo={
		
	//查询指定用户
	searchUserInfoByCondition:function(){
		this.clearSelections();
		//获取查询参数
		var searchParent$ = $("div#searchParent");
		var searchUserNameVal = $.trim(searchParent$.find("#searchUserName").val());
		var searchFullNameVal =  $.trim(searchParent$.find("#searchFullName").val());
		var searchPhoneVal = $.trim(searchParent$.find("#searchPhone").val());

		var queryParams={};
		if(searchUserNameVal!=''){
			queryParams.userName= searchUserNameVal;
		}

		if(searchFullNameVal!=''){
			queryParams.fullName= searchFullNameVal;
		}

		if(searchPhoneVal!=''){
			queryParams.phone= searchPhoneVal;
		}
		$("#tableUserInfo").datagrid({
			url : ctx + "/sys/listUserInfo.action",
			queryParams : queryParams,
			pageNumber : 1,
		    pageSize : 20
		});
	},
	
	//状态
	isUserValid:function(value,row,index){
   		if(value == 0){
			return "无效";
		}else{
			return "有效";
		}
	},
	
	//编辑用户
	editUser: function(){
		var selectUserInfo = $("#tableUserInfo").datagrid("getSelections");
		if(selectUserInfo.length==0){
			$.messager.alert("info", "请选择需要编辑的用户", "info");
			return false;
		}
		if(selectUserInfo.length>1){
			$.messager.alert("info", "只能选择一位用户", "info");
			return false;
		}
		$("#userName").val(selectUserInfo[0].userName);
		$("#userType").val(selectUserInfo[0].userType);
		$("#userType").attr("disabled",true);
		$("#fullName").val(selectUserInfo[0].fullName);		
		$("#phone").val(selectUserInfo[0].phone);
		$("#mail").val(selectUserInfo[0].mail);
		$("input[name='isValid'][value="+selectUserInfo[0].isValid+"]").attr("checked",true);
		$("#idHidden").val(selectUserInfo[0].id);
		$("#userName").attr("disabled",true);
		$("#shopId").attr("disabled",true);
		
		if(selectUserInfo[0].userType>=25) {
			$("#shopIdP").show();			
			userInfo.getCurrentUserShopList("shopId");
			userInfo.getShopUserInfo(selectUserInfo[0].id);
		}
		
		userInfoUrl = ctx+'/sys/updateUserInfo.action';
	    $('#dlg').dialog('open').dialog('setTitle','编辑用户');
	},
	
	//获取用户所属店铺
	getShopUserInfo:function(userId){
		$.ajax({
			type:"post",
			async:false,
			dataType:"json",
			url:ctx+'/shop/getShopUserInfo.action',
			data:{"userId":userId},
			success:function(data){
				if(data&&data.shopIds){
					$("#shopId").val(data.shopIds[0]);
				}
			}
		});
	},
	
	//获取当前用户拥有的店铺
	getCurrentUserShopList:function(htmlid){
		var html="<option value='-1'>请选择...</option>";
		$.ajax({
			type:"post",
			async:false,
			dataType:"json",
			url:ctx+'/shop/getCurrentUserShopList.action',
			data:{},
			success:function(data){
				if(data){
					$("#shopId").show();
					var shopInfos=data.shopInfos;
					if(shopInfos.length==0){
						$("#shopId").html(html);
						return false;
					}
					
					for(var i=0;i<shopInfos.length;i++){
						html+="<option value='"+shopInfos[i].id+"'>"+shopInfos[i].shopName+"</option>";
					}
					$("#"+htmlid).html(html);
					return false;
				}
			}
		});	
	},
	
	//检查用户信息是否规范
	checkUserInfo:function(){
		var userName=$("#userName").val().replace(/[ ]/g,"");
		if(userName==""){
			alert("请输入用户名");
			$("#userName").select();
			$("#userName").focus();
			return false;
		}
		var userType=$("#userType").val();
		if(userType==-1){
			alert("请选择用户类型");
			return false;
		}
		
		var fullName=$("#fullName").val().replace(/[ ]/g,"");
		if(fullName==""){
			alert("请输入真实姓名");
			$("#fullName").select();
			$("#fullName").focus();
			return false;
		}
		var phone=$("#phone").val().replace(/[ ]/g,"");
		if(phone==""){
			alert("请输入手机号");
			$("#phone").select();
			$("#phone").focus();
			return false;
		}
		
		return true;
	},
	
	//选择所属店铺
	changeUserShop:function(){
		var userType=$("#userType").val();
		if(userType==-1) return false;
		if(userType>=25){
			userInfo.getCurrentUserShopList("shopId");
			$("#shopIdP").show();
		}else{
			$("#shopIdP").hide();
		}
	},
	
	//创建用户dlg
	createUser:function(){
		$("#userName").val("");
		$("#userType").val(-1);
		$("#fullName").val("");
		$("#phone").val("");
		$("#mail").val("");
		$("#shopId").hide();
		$("#idHidden").val("");
		$("#shopId").val(-1);
		$("#userName").attr("disabled",false);
		$("#shopId").attr("disabled",false);
		$("#userType").attr("disabled",false);
		userInfoUrl = ctx+'/sys/insertUserInfo.action';
		$('#dlg').dialog('open').dialog('setTitle','新增用户');
	},
	
	//保存用户
	saveUser: function(){
		if(!userInfo.checkUserInfo()) return false;
		
        //初始化表单提交动作信息
	    $('#userForm').form('submit',{
	        url:userInfoUrl,
	        onSubmit:function(){
		        return $(this).form('validate');
	        },
	        success:function(data){
	            alert(data);
	            if(data.indexOf("成功")>0){            	
	            	userInfo.searchUserInfoByCondition();
	            	$('#dlg').dialog('close');
	            }
	        }
	    });

	},

    //删除用户
	deleteUser:function(){
		var selectUserInfo = $("#tableUserInfo").datagrid("getSelections");
		if(selectUserInfo.length==0){
			$.messager.alert("info", "请选择需要删除的用户", "info");
			return false;
		}
		if(selectUserInfo.length>1){
			$.messager.alert("info", "只能选择一位用户", "info");
			return false;
		}
		
		userInfoUrl= ctx+'/sys/removeUserInfo.action';
		if(!confirm("是否确认删除？？？")){
			return false;
		}
		
		$.ajax({
			type:"post",
			dataType:"json",
			url:userInfoUrl,
			data:{"id":selectUserInfo[0].id,"userName":selectUserInfo[0].userName},
			success:function(data){
			    $.messager.alert('Info', data, 'info');
				//右下角弹出提示框
				//$.messager.show({showSpeed:100,showType:"show",msg:data,title:"提示",timeout:2000});
				$('#tableUserInfo').datagrid("reload");
			}
		});
	},
    //批量删除
	deleteUsers:function(){
		$.messager.confirm('Confirm','确定要删除这些数据吗？',function(r){
			if(r){
				
				userInfoUrl= ctx+'/sys/removeUserInfo.action';
				var selected = userInfo.getSelections();
				if(selected.length>0){
					$.ajax({
						type:"post",
						dataType:"json",
						url:userInfoUrl,
						data:{"ids":selected.join(",")},
						success:function(data){
			           		$.messager.alert('Info', data, 'info');
							//右下角弹出提示框
							//$.messager.show({showSpeed:100,showType:"show",msg:data,title:"提示",timeout:2000});
							$('#tableUserInfo').datagrid("reload");
						}
					});
				}
				
			}
		});
	},
	
	grantRole : function(){
		var rows = $('#tableUserInfo').datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert('错误','请选择用户！','error');
			return false;
		}
		if(rows.length > 1){
			$.messager.alert('错误','最多选择一条用户信息！','error');
			return false;
		}
		var userId = rows[0].id;
		$('#grantRoleDlg').dialog('open').dialog('setTitle','分配角色');
		this.listLeftRole(userId);
		this.listRightRole(userId);
	},
	listLeftRole : function(userId){
		$.ajax({
			url:ctx+'/sys/listLeftRole.action',
			data:{userId:userId},
			dataType:'json',
			type:'post',
			success : function(data){
				if(data){
					var theOption = "";
					$(data).each(function(){
						theOption = theOption + "<option value='"+this.id+"'>"+this.roleName+"</option>";
						}
					);
					$("#allItems","#grantRoleDlg").html(theOption);
				}
			}
		});
	},
	listRightRole : function(userId){
		$.ajax({
			url:ctx+'/sys/listRightRole.action',
			data:{userId:userId},
			dataType:'json',
			type:'post',
			success : function(data){
				if(data){
					var theOption = "";
					$(data).each(function(){
						theOption = theOption + "<option value='"+this.id+"'>"+this.roleName+"</option>";
						}
					);
					$("#selectItems","#grantRoleDlg").html(theOption);
				}
			}
		});
	},
	//保存给用户分配的角色
	saveRole : function(){
		var roleId$ = $("#selectItems","#grantRoleDlg").find("option");
		var rows = $('#tableUserInfo').datagrid('getSelections');
		var userId = rows[0].id;
		var roleValues = roleId$.map(function(){
			return $(this).val();
		}).get().join(",");
		if(roleValues.length == 0){
			$.messager.alert('错误','没有选择你瞎保存个啥！','error');
			return false;
		}
		$.ajax({
			url : ctx + '/sys/saveGrantInfo.action',
			dataType : 'json',
			data : {roleIdStr:roleValues,userId:userId},
			type : 'post',
			success : function(data){
				var temp = data;
				//if(data.status="success"){
					$.messager.alert('Info', temp, 'info');
					$('#tableUserInfo').dialog('close');
				//}else{
					//$.messager.alert('错误', temp, 'error');
				//}
			}
		});
	},
	
	//取消所有选 择
	clearSelections: function(){
		$('#tableUserInfo').datagrid('clearSelections');
	},

	getSelected: function(){
		var selected = $('#tableUserInfo').datagrid('getSelected');
		if (selected){
			alert(selected.id+":"+selected.userName+":"+selected.fullName);
		}
		$('#w').window('open');
		$('#tableUserInfo').datagrid("reload");

	},

	getSelections: function(){
		var ids = [];
		var rows = $('#tableUserInfo').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].id);
		}
		return ids;
		//alert(ids.join(':'));
	}
};
