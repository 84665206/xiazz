<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<#include "/WEB-INF/pages/common/taglib.ftl" />
		<script type="text/javascript" src="${staticHost}/js/sys/modifypassword.js"></script>
		<title>修改密码</title>
		<style>
			.fitem label{ width:55px; text-align:right;}
		</style>
	</head>
<body>
	<div class="main">
		<div id="stockinDirectTaskDlg" style="width:400px;height:250px;padding:10px 20px;left:10px;top:10px;" closed="false" closable="false" class="easyui-dialog" buttons="#dlg-buttons" title="修改密码">
			<form id="modifypassword" method="post" novalidate>
				<div class="fitem">
				    <label>
				        原密码
				    </label>
				    <input name="oldPassword" id="oldPassword" type="password">
				</div>
				<div class="fitem" style="margin-top:20px;">
				    <label>
				        新密码
				    </label>
				    <input name="newPassword" id="newPassword" type="password" onKeyUp="modifypassword.authPasswd();">(数字和字母组合)
				</div>
				<div>
					 <p id="weakWordError" style="float:left;color:red;font-weight:bold;text-align:center;width:230px;" class="hide"></p>
					 <p id="weakWord" style="float:left;margin-left:8px;" class="hide">密码强度：</p>
					 <p id="weakSmall" class="hide" style="width:100px;float:left;color: white; text-align: center; background-color: rgb(221, 0, 0);">弱</p>
					 <p id="weakMiddle" class="hide" style="width:130px;float:left;color: white; text-align: center; background-color: rgb(255, 204, 51);">中</p>
					 <p id="weakBig" class="hide" style="width:150px;float:left;color: white; text-align: center; background-color: rgb(0, 153, 0);">强</p>
			    </div>
				<br/>	  
				<div class="fitem" style="margin-top:3px;">
				    <label>
				        重复密码
				    </label>
				    <input name="rePassword" id="rePassword" type="password">
				</div>
				<p id ="errormessage" style="visibility:visible;font-size:15px;height:20px;font-weight:bold;color:red;width:330px;text-align:center;"></p>
				<div class="fitem" style="margin-top:5px;margin-left:100px;">
				    <a href="#" class="easyui-linkbutton" iconcls="icon-ok" id="save">保存</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
