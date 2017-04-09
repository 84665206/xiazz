var regRegistePhone = /^(1[3-9]{1}[0-9]{9})$/;
$(document).ready(function(){
	$("#refresh_regist_authcode").click(function(c) {
		c.preventDefault();
		var a = $(this).attr("data-url"),
		b = new Date().getTime();
		$("#refresh_regist_authcode").attr("src", a + "?t=" + b);
	});
	
	$("#login_btn").click(function(f) {
        $("#login_btn").val("正在登录...");
        $.ajax({
    		type : 'post',
    		url : '/passport/tologin.do',
    		data : {
    			"customer_name" : $("#customer_name").val(),
    			"password" : $("#password").val(),
    			"t" : new Date().getTime() 
    		},
    		dataType : 'json',
    		success : function(data) {
    			if (data.code == "200") {
    				window.location = '/passport/returnurl.do';
    				return true;
    			} else {
    				$("#password_tips").html(data.msg).show().addClass("err").removeClass("suc");  
    				$("#login_btn").val("登录");
    				return false;
    			}
    		}
    	});
	});
	
	 $("#regist_btn").click(function(f) {
 	    $(".tops").hide();
 	    $(".tips").html("");
 	    $(".err").removeClass("err");
         if ($.trim($("#J_customer_name").val()) == "") {
         	$("#customer_name_tips").html("请输入手机号").show().addClass("err").removeClass("suc"); 
             return false;
         }
     	if(!$.trim($("#J_customer_name").val()).match(regRegistePhone)){
     		$("#customer_name_tips").html("请输入有效的手机号码").show().addClass("err").removeClass("suc"); 
             return false;
     	}
         if ($.trim($("#J_password").val()) == "") {
         	$("#password_tips").html("请输入密码").show().addClass("err").removeClass("suc"); 
             return false;
         }
         if($.trim($("#J_regist_auth_code").val()) == ""){
         	$("#regist_auth_code_tips").html("请输入验证码").show().addClass("err").removeClass("suc");
         	return false;
         }
         
         $.ajax({
     		type : 'post',
     		url : '/passport/toregist.do',
     		data : {
     			"customer_name" : $("#J_customer_name").val(),
     			"customer_pwd" : $("#J_password").val(),
     			"regist_auth_code" : $("#J_regist_auth_code").val(),
     			"customer_true_name" : $("#J_customer_true_name").val(),
     			"shop_name" : $("#J_shop_name").val(),
     			"shop_address" : $("#J_shop_address").val(),
     			"recruit_code" : $("#J_recruit_code").val(),
     			"t" : new Date().getTime() 
     		},
     		dataType : 'json',
     		success : function(data) {
     			if (data.code == "200") {
     				window.location = '/passport/returnurl.do';
    				return true;
     			} else {
     				$("#refresh_regist_authcode").click();
     				$("#regist_auth_code_tips").html(data.msg).show().addClass("err"); 
     				return false;
     			}
     		}
     	});
	 });
})