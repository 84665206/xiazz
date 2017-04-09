<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <#include "/WEB-INF/pages/common/taglib.ftl" />
    <title> 罗莱家纺电子商务新版仓库管理系统 </title>
    <link href="${staticHost}/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<fieldset name="shippingInfo" style="width:600px;height:100px; margin-top:10px; padding-left:10px; padding-right:10px; padding-bottom:10px;">
				<legend>初始化用户密码 </legend>
			<div style="margin-top:10px;">用户名：<input type="text" id="userName" value="" /></div>
			<p style="color:red;">(提示：初始化密码后，被初始化的用户密码为:用户名+123456)</p>
			<div style="margin-top:10px;"><a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="initUserPwd()">确定</a></div>
		</fieldset>
	</div>
	<script type="text/javascript">
		function initUserPwd(){
			var userName=$("#userName").val().replace(/[ ]/g,"");
			if(userName==""){
				alert("用户名不能为空");
				$("#userName").select();
				$("#userName").focus();
				return false;
			}
			$.post(ctx+'/sys/initUserPassword.action',{"userName":userName},
				function(data){
				if(data){
					if(data.status=="FAIL"){
						alert(data.message);
						$("#userName").select();
						$("#userName").focus();
						return false;
					}
					if(data.status=="SUCCESS"){
						alert(data.message);
						$("#userName").val("");
						$("#userName").focus();
						return true;
					}
				}
			});	
		}
	</script>
</body>
</html>

