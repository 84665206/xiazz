<!DOCTYPE HTML>
<html>
<head>
	<title>登录</title>
	<meta name="keywords" content="登录"/> 
	<meta name="description" content="登录"/>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
	<style>
		#yanzhengma1{
		   display:none;
		}
		.field {
		margin-top: 1.2em;
		}
		.innercontent .login-form {
		width: 90%;
		margin: auto;
		}
		
		.innercontent {
		margin: 20px 0;
		}
		.innercontent .login-form fieldset {
		border: none;
		}
		.c-form-search button {
		top: 0.6em;
		right:0.6em;
		width: 1em;
		height: 1em;
		position: absolute;
		border: 0;
		background-color: transparent;
		text-align: center;
		}
		.c-form-search button span:after {
		width: 1em;
		height: 1em;
		background-image: url();
		background-position: center center;
		background-repeat: no-repeat;
		display: block;
		content: "";
		}
		
		.c-btn-oran-big {
		border: 0;
		text-align: center;
		text-decoration: none;
		margin-top: 10px;
		color: #fff;
		font-size: 1em;
		font-weight: normal;
		-webkit-border-radius: 0px;
		background: #f00;
		min-width: 100%;
		height: 2.5em;
		line-height: 2em;
		}
		
		.c-form-txt-normal, .c-form-search input[type="text"],.c-form-search input[type="email"], .c-form-search input[type="search"], .c-form-search input[type="number"], .c-form-search input[type="tel"], .c-form-search input[type="url"] {
		height: 2em;
		width:80%;
		border: solid 1px #d6d6d6;
		border-top-color: none;
		padding-left: 9px;
		color: #042148;
		font-size: 1em;
		-webkit-border-radius: 0;
		-webkit-appearance: none;
		background: -webkit-gradient(linear, 0 0, 0 100%,);
		}
		
		.c-form-search {
		position: relative;
		}
		
		.innercontent .err .tips {
		color: red;
		font-size: 12px;
		margin: 5px 0 5px 0;
		}
		.innercontent .err input {
		border: 2px solid #B92231;
		}
		
		.weibo{width:20%; margin:15px; height:4em; display:none; background: url(//pic2.lowol.cn/upfiles/activity/lovo/appimg/wxlg.png) no-repeat; background-size: contain}
		.qqlogin{width: 20%; margin:15px; height:4em; display: -webkit-inline-box;background: url(//pic2.lowol.cn/upfiles/activity/lovo/appimg/qqtx.png) no-repeat; background-size: contain}
		.weibo img,.qqlogin img{ width:100%}
		.tips{font-size:14px;}
		.tips.err{color:#c40001;}
		.tips.suc{color:#47A505}
		p a{ color:#999 !important;font-size:1.4em}
	</style>
</head>

<body>
<#include "/global/global_back.ftl" />
<div id="h5v0">
          <section class="innercontent"> 
            <div id="message" style="display: none; color: red; margin-top: 10px; margin-bottom: 10px; font-size: 14px;"></div>
            <form method="post" class="c-form login-form" action="" id="J_Login">
                
                <fieldset>
                    <div class="field username">
                        <div class="c-form-search">
                        	  账 号：<input type="text" name="customer_name" id="customer_name" placeholder="请输入手机号" value="${username!}">
                          	<button type="button" style="display: none; "><span></span></button>
                           	<div id="username_tips" class="tips"></div>
                        </div>
                    </div>
                    <div class="field pwd">
                                                                                      密 码：<input type="password" name="password" id="password" placeholder="请输入密码" class="c-form-txt-normal">
                         <div id="password_tips" class="tips"></div>
                    </div>
                    
                    
                    <div class="field submit-btn">
                        <input type="button" id="login_btn" class="c-btn-oran-big" value="登 录">
                    </div>
                    
                    <div style="color: #666; margin-top:20px;font-size: 12px;text-align:center">
                    	<p><a href="/passport/regist.do" style="float:right; ">快速注册</a></p>
                        <p></p>
						
                    </div>
                </fieldset>
   
            </form>
       </section>
          
</div>

<script type="text/javascript" src="/js/common/zepto.min.js"></script>
<script type="text/javascript" src="/js/passport/login.js"></script>
</body>
<html>