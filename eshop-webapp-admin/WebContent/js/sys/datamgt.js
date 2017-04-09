$(function(){
	common.getBrandInfoList("brandInfoDlg");
	common.getBrandInfoList("brandInfo");
	datamgt.getBrandData();
	common.getBrandInfoList("brandInfoAdd");
	
});

var datamgt={
	
	//查询用户
	searchUserInfoToDatamgt:function(){
		var userName = $("#userNameSearch").val().replace(/[ ]/g,"");
		var fullName =  $("#fullNameSearch").val().replace(/[ ]/g,"");
		var userType =  $("#userTypeSearch").val();;
		var queryParams={};
		if(userName!=""){
			queryParams.userName= userName;
		}

		if(fullName!=""){
			queryParams.fullName= fullName;
		}
		if(userType!=""){
			queryParams.userType= userType;
		}
		
		$("#tableUserInfo").datagrid({
			url : ctx + "/sys/listUserInfo.action",
			queryParams : queryParams,
			pageNumber : 1,
			pageSize : 20
		});
	},
	
	//分配地区
	allotAreaInfo:function(userId){
		//清空地区信息
		$(".adress").html("");
		$("#provinceFieldset").hide();
		$("#cityFieldset").hide();
		$("#districtFieldset").hide();
		$("#brandInfoDlg").val(-1);
		$("#changArea").html("");
		
		$("#userIdHidden").val(userId);
		$("#datamgtDlg").dialog('open').dialog('setTitle','分配地区');
	},
	
	//显示地区操作btn
	areaInfoOperateBtn:function(value, row, index){
		
		return "<a href='#' onclick='datamgt.allotAreaInfo("+row.id+");'>分配地区</a>";
	},
	
	//显示地区信息
	showAreaInfo:function(areaType,parentId){
		if(areaType==1){
			datamgt.getAreaInfo(areaType,parentId);
			$("#provinceFieldset").show();			
			return false;
		}
		if(areaType==2){
			datamgt.getAreaInfo(areaType,parentId);
			$("#cityFieldset").show();
			$(".adress.district").html("");
			$("#districtFieldset").hide();
			return false;
		}
		if(areaType==3){
			datamgt.getAreaInfo(areaType,parentId);
			$("#districtFieldset").show();
			return false;
		}
	},
	
	//获取地区信息
	getAreaInfo:function(areaType,parentId){
		var brandId=$("#brandInfoDlg").val();
		if(brandId==-1){
			return false;
		}
		var param="?t=" +(new Date().getTime());
		if(areaType!=null) param+="&areaType="+areaType;
		if(parentId!=null) param+="&parentId="+parentId;
		if(brandId!=null) param+="&brandId="+brandId;
		var userId=$("#userIdHidden").val();
		if(userId=="") return false;
		param+="&userId="+userId;
		
		$.ajax({
			url:ctx +"/area/getAreaInfoWithSysdataByUser.action",
			async:false,
			type:"post",
			data:param,
			dataType:"json",
			success:function(data){
				if(data){
					var areaInfos=data.areaInfos;
					var html="";
					if(areaInfos.length==0){
						$("#areaInfoP_"+areaType).html(html);
						return false;
					}
					for(var i=0;i<areaInfos.length;i++){
						var redfont="";
						if(areaInfos[i].isSelect==1){
							redfont="class='red'";
						}
						if(areaInfos[i].areaType==1){
							html+="<a "+redfont+"><input type='radio' name='provinceRadio' value='"+areaInfos[i].id+"' onclick='datamgt.showAreaInfo("+(areaInfos[i].areaType+1)+","+areaInfos[i].id+");'>"+areaInfos[i].areaName+"</a>";
						}
						if(areaInfos[i].areaType==2){
							html+="<a "+redfont+"><input type='radio' name='cityRadio' value='"+areaInfos[i].id+"' onclick='datamgt.showAreaInfo("+(areaInfos[i].areaType+1)+","+areaInfos[i].id+");'>"+areaInfos[i].areaName+"</a>";
						}
						if(areaInfos[i].areaType==3){
							var districtBoxHtml="";
							if(areaInfos[i].isSelect==0){
								districtBoxHtml="<input type='checkBox' name='districtBox' value='"+areaInfos[i].id+"'>";
							}
							html+="<a "+redfont+">"+districtBoxHtml+areaInfos[i].areaName+"</a>";
						}
						
					}
					$("#areaInfoP_"+areaType).html(html);
				}
			}	
		});		
	},
	
	//选择对应的全部checkbox
	selectAllCheckBox:function(boxName,type){
		if(type==1){
			$("input[name='"+boxName+"']").attr("checked",true); 
		}
		if(type==2){
			$("input[name='"+boxName+"']").attr("checked",false); 
		}
	},
	
	//分配地区
	createUserData:function(){
		var param="?t=" +(new Date().getTime());
		var userIdHidden=$("#userIdHidden").val().replace(/[ ]/g,"");
		if(userIdHidden==""){
			alert("没有选择分配的用户");
			return false;
		}
		var brandInfo=$("#brandInfoDlg").val();
		if(brandInfo==-1){
			alert("没有选择品牌");
			return false;
		}
		var provinceRadio=$("input[name='provinceRadio']:checked").val();
		if(provinceRadio==""||$("input[name='provinceRadio']:checked").length==0){
			alert("没有选择添加地区的省份");
			return false;
		}
		var cityRadio=$("input[name='cityRadio']:checked").val();
		if(cityRadio==""||$("input[name='cityRadio']:checked").length==0){
			alert("没有选择添加地区的城市");
			return false;
		}
		var districtBoxs=$("input[name='districtBox']:checked").val([]);
		if(districtBoxs.length==0){
			alert("没有选择添加地区的区县");
			return false;
		}
		param+="&cityId="+cityRadio;
		param+="&userId="+userIdHidden;
		param+="&brandId="+brandInfo;
		for(var i=0;i<districtBoxs.length;i++){
			param+="&datas["+i+"].provinceId="+provinceRadio;
			param+="&datas["+i+"].cityId="+cityRadio;
			param+="&datas["+i+"].districtId="+districtBoxs[i].value;
			param+="&datas["+i+"].userId="+userIdHidden;
			param+="&datas["+i+"].brandId="+brandInfo;
		}
		
		datamgt.addUserData(param);
		datamgt.getAllDataInfoList();
	},
	
	//添加数据权限
	addUserData:function(param){
		$.ajax({
			url:ctx +"/sys/addDataByDatas.action",
			async:false,
			type:"post",
			data:param,
			dataType:"json",
			success:function(data){
				if(data){
					if(data.status=="SUCCESS"){
						alert(data.message);
						datamgt.getAreaInfo(3,$("input[name='cityRadio']:checked").val());
						return false;
					}
					if(data.status=="FAIL"){
						alert(data.message);
						return false;
					}
				}
			}
		});	
	},
	
	//删除数据权限
	deleteUserData:function(){
		var selectDistrictBox=$("input[name='selectDistrictBox']:checked").val([]);
		if(selectDistrictBox.length==0){
			alert("没有选择需要移除的地区");
			return false;
		}
		var userIdHidden=$("#userIdHidden").val().replace(/[ ]/g,"");
		if(userIdHidden==""){
			alert("没有选择分配的用户");
			return false;
		}
		var brandInfo=$("#brandInfoDlg").val();
		if(brandInfo==-1){
			alert("没有选择品牌");
			return false;
		}
		
		if(!confirm("是否需要移除"+selectDistrictBox.length+"条数据？？？")){
			return false;
		}
		
		var param="?t=" +(new Date().getTime());
		param+="&brandId="+brandInfo;
		param+="&userId="+userIdHidden;
		for(var i=0;i<selectDistrictBox.length;i++){
			param+="&districtIds["+i+"]="+selectDistrictBox[i].value;
		}
		$.ajax({
			url:ctx +"/sys/deleteDataByDistrictIds.action",
			async:false,
			type:"post",
			data:param,
			dataType:"json",
			success:function(data){
				if(data){
					if(data.status=="SUCCESS"){
						alert(data.message);
						datamgt.getAllDataInfoList();
						datamgt.getAreaInfo(3,$("input[name='cityRadio']:checked").val());
						return false;
					}
					if(data.status=="FAIL"){
						alert(data.message);						
						return false;
					}
				}
			}	
		});	
	},
	
	//查询已有地区
	getAllDataInfoList:function(){
		var userIdHidden=$("#userIdHidden").val().replace(/[ ]/g,"");
		if(userIdHidden==""){
			alert("没有选择分配的用户");
			return false;
		}
		var brandId=$("#brandInfoDlg").val();
		if(brandId==-1){
			alert("没有选择品牌");
			return false;
		}
		$.ajax({
			url:ctx +"/sys/getAllDataInfoList.action",
			async:false,
			type:"post",
			data:{"userId":userIdHidden,"brandId":brandId},
			dataType:"json",
			success:function(data){
				if(data){
					var html="";
					if(data.status=="SUCCESS"){
						var provinceList=data.datas;
						if(provinceList.length==0) return false;
						for(var i=0;i<provinceList.length;i++){
							html+="<div>";
							html+="<p class='provinceP'>"+provinceList[i].areaName+"</p>";
							var cityList=provinceList[i].datas;
							for(var j=0;j<cityList.length;j++){
								html+="<a class='selectCity'>"+cityList[j].areaName+"：</a>";
								html+="<p class='selectOver'>";
								var districtList=cityList[j].datas;
								for(var k=0;k<districtList.length;k++){
									var checked="";
									if(districtList[k].isSelect==1) checked="checked";

									html+="<a><input type='checkBox' name='selectDistrictBox' value='"+districtList[k].districtId+"' "+checked+" />"+districtList[k].areaName+"</a>";
								}
								html+="</p>";
							}
							html+="</div><br/>";
						}
						$("#changArea").html(html);
					}
					if(data.status=="FAIL"){
						alert(data.message);
						return false;
					}
				}
			}
		});	
	},
	
	//显示用户地区
	showUserAreaInfoByBrandId:function(){
		//清空已选地区
		$("#changArea").html("");
		//清空地区信息
		$(".adress").html("");
		$("#provinceFieldset").hide();
		$("#cityFieldset").hide();
		$("#districtFieldset").hide();
		
		var brandId=$("#brandInfoDlg").val();
		if(brandId==-1) return false;
		
		//显示地区
		datamgt.showAreaInfo(1,null);
		//显示已存在的权限
		datamgt.getAllDataInfoList();
	},
	
	//查询用户结算区域
	searchDataPage:function(){
		var brandInfo=$("#brandInfo").val().replace(/[ ]/g,"");
		if(brandInfo==""||brandInfo==-1){
			return false;
		}
		$("#tableUsersettlarea").datagrid({
			url : ctx + "/sys/searchDataPage.action",
			queryParams : {
				"brandId":$("#brandInfo").val().replace(/[ ]/g,"")
			},
			pageNumber : 1,
			pageSize : 10
		});
	},
	
	//获取品牌数据权限
	getBrandData:function(){
		var brandData=$("#brandInfo option");
		var brandDataP="";
		if(brandData.length>0){
			for(var i=0;i<brandData.length;i++){
				if(i>0){
					brandDataP+=brandData[i].text+"&nbsp;&nbsp;&nbsp;";
				}
				
			}
			$("#brandInfoP").html(brandDataP);
		}
	},
	
	//导入数据权限--弹出框
	openImportDataDlg:function(){
		$("#message").html("");
		$("#userTypeAdd").val(0);
		$("#brandInfoAdd").val(0);
		$("#Filedata").val("");
		$("#importdatadlg").dialog('open').dialog('setTitle','导入数据权限');
	},
	
	//导入数据
	importDataInfo : function(){
		$('#importForm').form('submit', {   
		    url:ctx+'/sys/importDataInfo.action',   
		    onSubmit: function(){   
		    	parent.$.messager.progress({
				    title:'友情提示',   
				    msg:'数据处理中,请等待……',   
				    interval:60,
				    text:""
				});
		    },   
		    success:function(data){
		    	parent.$.messager.progress('close');
		    	var result = eval('('+data+')');
		    	if(result.status =="success"){
					//$.messager.alert('成功',result.msg,'info');
		    		var msg=common.replaceAll(result.msg, "，", "<br>");
		    		$("#message").html(msg);
		    		$("#userTypeAdd").val(0);
					$("#brandInfoAdd").val(0);
					$("#Filedata").val("");
				}else{
					var msg=common.replaceAll(result.msg, "，", "<br>");
					//$.messager.alert('错误',result.msg,'error');
					$("#message").html(msg);
					
				}
		    }
		});	
	},
	
};