<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<#include "/WEB-INF/pages/common/taglib.ftl" />
		<script type="text/javascript" src="${staticHost}/js/sys/datamgt.js"></script>
		<script type="text/javascript" src="${staticHost}/js/sys/userInfo.js"></script>
		<title>数据权限管理</title>
		<style>
			.selectAll{TEXT-DECORATION:none}
			.selectAll:hover{TEXT-DECORATION:underline}
			.areaSizeCheck{font-size:16px;font-weight:bold;color:red;margin-right:10px;}
			.areaSize{font-size:16px;font-weight:bold;margin-right:10px;}
		    .adress a{ white-space:nowrap; display:inline-block;TEXT-DECORATION:none; color:#017AEA;}
		    .adress a.red{ color:#ff0000; font-weight:bold;}
		    .redfont{color:red;}
		    .provinceP{font-size:16px; font-weight:bold;}
		    .selectDiv{width:370px;}
		    .selectCity{TEXT-DECORATION:none;font-size:14px;float:left}
		    .selectOver a{ white-space:nowrap; display:inline-block;TEXT-DECORATION:none; color:#404040;}
		    .selectOver a.red{font-weight:bold;}
		</style>
	</head>
<body>
	<div class="main">
		<div class="hights" style="width:1000px;">
			<p style="margin-top:10px;">
				<span class="widthm">用户名：</span><input class="inputtxt" id="userNameSearch" type="text" style="width:150px;">
				<span class="widthm">真实姓名：</span><input class="inputtxt" id="fullNameSearch" type="text" style="width:150px;">
				<span class="widthm">用户类型：</span><@s.select id="userTypeSearch" name="userTypeSearch" list="allUserType" listKey="keyData" listValue="valueData" theme="simple" style="width:150px;"/>
				
			</p>
			<p style="margin-top:10px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="false" onclick="datamgt.searchUserInfoToDatamgt();">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-excel" plain="false" onclick="datamgt.openImportDataDlg();">导入</a>
			</p>
		</div>
		<fieldset name="allotArea" style="width:1000px;margin-top:10px;padding-top:10px;padding-left:10px; padding-right:10px; padding-bottom:10px;">
			<legend>分配用户信息 </legend>
			<#--<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="userInfo.createUser();">创建用户</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="userInfo.editUser();">编辑用户</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="userInfo.deleteUser();">删除用户</a>
				
			</div> -->
			<table id="tableUserInfo" class="easyui-datagrid" style="width:1000px;height:560px;"
			rownumbers="true" fitColumns="true" singleSelect="true" striped="true" pagination="true" pageSize="20">
				<thead>
					<tr>
						<th field="ck" checkbox="true"></th>
						<th field="id" hidden="true"></th>
						<th field="userName" width="100">用户名</th>
						<th field="fullName" width="100">真实姓名</th>
						<th field="userCode" width="80">用户编号</th>
						<th field="userTypeName" width="80">用户类别</th>
						<th field="phone" width="100">手机</th>
						<th field="mail" width="100">邮箱</th>
						<th field="isValid" width="100" formatter="userInfo.isUserValid">用户状态</th>
						<th field="operateBtn" width="100" formatter="datamgt.areaInfoOperateBtn">操作</th>
					</tr>
				</thead>
			</table>
		</fieldset>
		<div id="datamgtDlg" class="easyui-dialog" style="width:920px;height:550px;padding:0px 0px;" closed="true">
	    	<input type="hidden" id="userIdHidden"/>
	    	<table>
				<tr>
					<td>
						<div id="allAreaInfo">
							<span>待分配地区</span>
							<div style="width:400px;height:435px;padding:5px;border:solid 1px #99BBE8;overflow-x:hidden;overflow-y:auto;">
								<p>请选择——>&nbsp;品&nbsp;&nbsp;&nbsp;牌&nbsp;：<select id="brandInfoDlg" onchange="datamgt.showUserAreaInfoByBrandId();" style="width:150px;"><option value='-1'>请选择...</option></select></p>
								<fieldset id="provinceFieldset" class="hide" style="width:400px;">
									<legend>请选择——>省份 </legend>
									<p class="adress province" id="areaInfoP_1"></p>
								</fieldset>
								<fieldset id="cityFieldset" class="hide" style="width:400px;margin-top:5px;">
									<legend>
										请选择——>城市
									</legend>
									<p class="adress city" id="areaInfoP_2"></p>
								</fieldset>
								<fieldset id="districtFieldset" class="hide" style="width:400px;margin-top:5px;">
									<legend>
										请选择——>区/县 &nbsp;
										<a class="selectAll" onclick="datamgt.selectAllCheckBox('districtBox',1);" style="font-size:16px;color:#07A0E7;font-weight:bold;">全选</a>
										&nbsp;|&nbsp;
										<a class="selectAll" onclick="datamgt.selectAllCheckBox('districtBox',2);" style="font-size:16px;color:#07A0E7;font-weight:bold;">取消</a>
									</legend>
									<p class="adress district" id="areaInfoP_3"></p>
								</fieldset>
							</div>
						</div>
					</td>
					<td style="width:10px;">
						<div style="height:135px;margin-top:200px;">
							<a onclick="datamgt.createUserData();"><img src="../images/btn-R.gif"/></a><br/>
							<a onclick="datamgt.deleteUserData();"><img src="../images/btn-L.gif" style="margin-top:20px;"/></a>
						</div>
					</td>
					<td>
					    <div id="areaGroupDetail">
							<span>已分配地区</span>
							<div style="width:400px;height:435px;padding:5px;border:solid 1px #99BBE8;overflow-x:hidden;overflow-y:auto;">
								<div id="changArea" style="margin-top:5px;"></div>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<div id="datamgtDlg-buttons">
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#datamgtDlg').dialog('close')">关闭</a>
			</div>
		</div>
		<#--导入权限 对话框-->
		<div id="importdatadlg" class="easyui-dialog" style="width:490px;height:350px;padding:10px 20px;"
			 closed="true" buttons="#dlg-buttons" modal="true">
			<div>
				<form id="importForm" method="post" action="${ctx}/sys/importDataInfo.action" enctype="multipart/form-data">
					<p>
						<span class="widthm">用户类型：</span><@s.select id="userTypeAdd" name="userType" list="allUserType" listKey="keyData" listValue="valueData" theme="simple" style="width:150px;"/>
					</p>
					<p style="margin-top:10px;margin-bottom:20px;">
						<span class="widthm">&nbsp;&nbsp;&nbsp;&nbsp;品&nbsp;&nbsp;&nbsp;&nbsp;牌：</span><select id="brandInfoAdd" name="brandId" style="width:150px;"><option value='-1'>请选择...</option></select>
					</p>
					<input type="file" name="Filedata" id="Filedata" class="easyui-linkbutton width13"></input>
				</form>
			</div>
			<div style="margin-top:20px;">
				<a href="#" onclick="datamgt.importDataInfo()" class="easyui-linkbutton width13" iconCls="icon-excel">导入</a>
				<a href="#" onclick="common.exportTemplate('DATA_INFO_IMPORT')" class="easyui-linkbutton width13" iconCls="icon-excel">模板下载</a>
			</div>
			<div style="margin-top:5px;color:red;width:430px;height:130px;overflow-y:scroll;font-weight:bold;" id="message">
				
			</div>
		</div>
    </div>	
</body>
</html>

