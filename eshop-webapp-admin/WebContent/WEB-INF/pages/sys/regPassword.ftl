<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "/WEB-INF/pages/common/taglib.ftl" />
    <title> 罗莱家纺电子商务新版仓库管理系统 </title>
    <link href="${staticHost}/css/login.css" rel="stylesheet" type="text/css" />
    <script src="${staticHost}/js/sys/regPassword.js" type="text/javascript"></script>
    <style>
      body{ background:#dae2ed url(${staticHost}/images/changePassword.jpg) no-repeat 50% 0;}
      label{ display:inline-block; width:60px; text-align:right; color:#005898; font-weight:bold;}
      .btns{ background-image:url(${staticHost}/images/changePasswordbtn_03.jpg);}
      .loginblock{width:400px;}
      .loginblockinner{ padding-top:128px;}
      label{ width:80px;}
      .loginerror{ left:108px; top:100px;}
    </style>
</head>
<body>
	<form id ="loginform" action="${ctx}/sys/registerPassword.action" method="post" target="_top">
		<input type="hidden" name="uid" value="${uid!}">
		<div class="loginblock">
		<div class="loginblockinner">
			  <p id ="errormessage" class="loginerror" style="font-weight:bold;">${messages!} </p>
			  <#if registersuccess??>
				  <p style="margin-top:20px;margin-left:140px;">
				  	<a href="${ctx}/main.action" class="easyui-linkbutton" iconcls="icon-ok" >去登陆</a>
				  </p>
			  <#else>
				  <p>
				  	 <label style="width:90px;"> 原始密码：</label>
				  	 <input type="password" id="oldPassword"  name="oldPassword" class="logininput" style="width:150px"/>
				  	 (原来使用的密码)
				  </p>
				  
				  <p style="margin-top:5px;">
				  	 <label style="width:90px;"> 新密码：</label>
				  	 <input type="password" id="newPassword"  name="newPassword" onKeyUp="regPassword.authPasswd();" class="logininput" style="width:150px"/>
				  	 (6-16个字符)
				  	 <br/>
				  </p>
				  
				  <div id="divWeek" style="margin-left:95px;height:18px;margin-top:2px;display:none;">
				  	<span id="weakWordError" style="float:left;color:red;font-weight:bold;" class="hide"></span>
				  	<span id="weakWord" style="float:left;" class="hide">强度：</span>
				  	<p id="weakSmall" class="hide" style="width:100px;height:20px;float:left;color: white; text-align: center; background-color: rgb(221, 0, 0);">弱</p>
				  	<p id="weakMiddle" class="hide" style="width:130px;height:20px;float:left;color: white; text-align: center; background-color: rgb(255, 204, 51);">中</p>
				  	<p id="weakBig" class="hide" style="width:160px;height:20px;float:left;color: white; text-align: center; background-color: rgb(0, 153, 0);">强</p>
				  </div>
				 
				  <p style="margin-top:5px;">
				  	 <label style="width:90px;">确认新密码：</label>
				  	 <input type="password" id="rePassword"  name="rePassword" maxlength="16"  class="logininput" style="width:150px"/>
				  </p>
		
				  <p style="padding-top:10px;">
				  	 <label> &nbsp; </label>
				  	 <a class="btns" href="#" style="margin-left:50px;" id="login">保存</a>
				  </p>
			  </#if>
		</div>
		</div>
	</form>
</body>
</html>