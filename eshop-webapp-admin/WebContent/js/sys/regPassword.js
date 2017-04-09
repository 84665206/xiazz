// 如果顶层页面不是登录页面，也就是说登录页面在框架里
if(window.parent.location != window.location) {
	 window.parent.location = window.location;
}

$(function(){
	$("#login").click(function(event){
		var newPassword = $.trim($('#newPassword').val());
		var rePassword = $.trim($('#rePassword').val());
		var oldPassword = $.trim($('#oldPassword').val());
					
		if(!oldPassword){
			$("#oldPassword").select();
			$("#oldPassword").focus();
			regPassword.showMessage("请输入原始密码");
			return false;
		}
		if(!newPassword){
			$("#newPassword").select();
			$("#newPassword").focus();
			regPassword.showMessage("请输入新密码");
			return false;
		}
		if(!rePassword){
			$("#rePassword").select();
			$("#rePassword").focus();
			regPassword.showMessage("请输入确认密码");
			return false;
		}
		if(newPassword != rePassword){
			regPassword.showMessage("输入的两次新密码不一致").css({visibility: 'visible'});
			return false;
		}
		if(newPassword.length<6){
			$("#newPassword").val("");
			$("#newPassword").focus();
			$("#rePassword").val("");
			regPassword.showMessage("设置的密码长度必须大于6");
			return false;
		}
		
		if(!(/[a-zA-Z]+/.test(newPassword) && /[0-9]+/.test(newPassword))){
			$("#newPassword").val("");
			$("#newPassword").focus();
			$("#rePassword").val("");
			regPassword.showMessage("设置的密码必须包含数字和字母");
			return false;
		}
		
		$('#loginform').submit();
	});
});


var regPassword={
		
	//检验密码的长度
	authPasswd:function(){  
		var string=$("#newPassword").val();
		var containSpecial=RegExp(/[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)(\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\,)(\.)(\/)(\<)(\>)(\?)(\)]+/);
		if(string.length>=6&&/[a-zA-Z]+/.test(string) && /[0-9]+/.test(string)) {
			if(/[a-zA-Z]+/.test(string) && /[0-9]+/.test(string)&&containSpecial.test(string)) {  
				regPassword.noticeAssign(1);  
			}else if(/[a-zA-Z]+/.test(string) || /[0-9]+/.test(string) || /\W+\D+/.test(string)) {  
				if(/[a-zA-Z]+/.test(string) && /[0-9]+/.test(string)) {  
					regPassword.noticeAssign(0); 
				}else if(/\[a-zA-Z]+/.test(string)&&containSpecial.test(string)) {  
					regPassword.noticeAssign(0);
				}else if(/\[a-zA-Z]+/.test(string)) {  
					regPassword.noticeAssign(-1);
				}else if(/[0-9]+/.test(string)&&containSpecial.test(string)) {  
					regPassword.noticeAssign(0);	
				}else if(/[0-9]+/.test(string)) {
					regPassword.noticeAssign(-1);
				}
			}  
		}else if(string.length>=6&&(/[a-zA-Z]+/.test(string) || /[0-9]+/.test(string))){  
			regPassword.noticeAssign(-2);   
		}else{
			regPassword.noticeAssign(-3);   
		}
	},

	//显示密码条强度
	 noticeAssign:function(num){
		 $("#divWeek").show();
		if(num == 1) {  
			$("#weakWordError").hide();
			$("#weakWord").show();
			$("#weakBig").show();
			$("#weakSmall").hide();
			$("#weakMiddle").hide();
		}else if(num == -1){  
			$("#weakWordError").hide();
			$("#weakWord").show();
			$("#weakSmall").show();
			$("#weakBig").hide();
			$("#weakMiddle").hide();
		}else if(num ==0) {
			$("#weakWordError").hide();
			$("#weakWord").show();
			$("#weakMiddle").show();
			$("#weakBig").hide();
			$("#weakSmall").hide();
		}else if(num ==-2){
			$("#weakWordError").show();
			$("#weakWordError").html("设置的密码必须包含数字和字母");
			$("#weakWord").hide();
			$("#weakMiddle").hide();
			$("#weakBig").hide();
			$("#weakSmall").hide();
		}else if(num ==-3){
			$("#weakWordError").show();
			$("#weakWordError").html("设置的密码长度必须大于6位");
			$("#weakWord").hide();
			$("#weakMiddle").hide();
			$("#weakBig").hide();
			$("#weakSmall").hide();
		}
	},
	
	//显示message
	showMessage:function(message){
		$("#errormessage").html(message);	
		window.setTimeout("regPassword.clearMessage()",10000);
	},
	//清空message(跳转)
	clearMessage:function(){
		$("#errormessage").html("");
	},
};