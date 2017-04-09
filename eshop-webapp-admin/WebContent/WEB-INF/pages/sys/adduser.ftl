<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<#include "/WEB-INF/pages/common/taglib.ftl" />
		<script type="text/javascript" src="${staticHost}/js/sys/userInfo.js"></script>
		<title>新增用户</title>
	</head>
<body>
	<div class="main">
		
		<fieldset name="areaInfo" style="width:500px; height:300px; margin-top:10px; padding-left:10px; padding-right:10px; padding-bottom:10px;">
			<legend>新增用户 </legend>
			<form id="userForm" method="post" novalidate>
				<p class="widthm" style="margin:10px 0px 0px 0px;">
					用&nbsp;&nbsp;户&nbsp;&nbsp;名：
					<input type="text" name="user.userName" maxlength="20" id="userName" style="width:200px;" /><span style="color:red;">*</span>(6-20字符(小写字母和数字组成))
				</p>
				<p class="widthm" style="margin:10px 0px 0px 0px;">
					用户类型：
					<@s.select id="userType" name="user.userType" onchange="userInfo.changeUserShop();" list="allUserType" listKey="keyData" listValue="valueData" theme="simple" style="width:200px;"/>
					<span style="color:red;">*</span>
				</p>
				<p class="widthm" id="shopIdP" style="margin:10px 0px 0px 0px;display:none;">
					所属店铺：
					<select id="shopId" name="shopId" style="width:200px;"/></select>
					<span style="color:red;">*</span>
				</p>
				<#--<p class="widthm" style="margin:10px 0px 0px 0px;display:none;" id="customerCodeP">
					客户编号：
					<input type="text" id="customerCode" name="user.customerCode" style="width:200px;"/><span style="color:red;">*</span>
				</p>-->
				<p class="widthm" style="margin:10px 0px 0px 0px;">
					真实姓名：
					<input type="text" id="fullName" name="user.fullName" style="width:200px;"/><span style="color:red;">*</span>
				</p>
				<p class="widthm" style="margin:10px 0px 0px 0px;">
					手机号码：
					<input type="text" id="phone" name="user.phone" style="width:200px;"/><span style="color:red;">*</span>
				</p>
				<p class="widthm" style="margin:10px 0px 0px 0px;">
					邮箱地址：
					<input type="text" id="mail" name="user.mail" style="width:200px;"/>
				</p>
				<p class="widthm" style="margin:10px 0px 0px 0px;">
					是否立即生效 ：
	                <input id ="validRadio" name ="user.isValid" type="radio" value="1" >是
	                <input id ="invalidRadio" name ="user.isValid" type="radio" value="0" class="width13" checked="checked">否
	            </p>
            </form>
            <a href="#" style="margin-top:50px;margin-left:100px;" class="easyui-linkbutton" iconCls="icon-ok" onclick="userInfo.saveUser()">保存</a>
		</fieldset>
 	</div>
</body>
</html>

