<!DOCTYPE HTML>
<html>
<head>
	<title>个人中心</title>
	<meta name="keywords" content="个人中心"/> 
	<meta name="description" content="个人中心"/>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
<style>
.main {
	font-size: 0.8em;
}

.mb-user {
	background-color: #f00;
	background-size: cover;
	border-bottom: 1px dashed #eee;
	padding: 0px;
	line-height: 18px;
	overflow: hidden;
}

.mb-user .touxiang {
	margin: 10px auto;
	border-radius: 50px;
	width: 100px;
	height: 100px;
	display: -webkit-box
}

.mb-user .dengji {
	margin: auto;
	margin-bottom: 10px;
	width: 100px;
	display: -webkit-box
}

.mb-user p {
	width: 100%;
	text-align: center;
	overflow: hidden;
	color: #fff
}

.mb-user .icon {
	width: 24px;
}

.mb-user dt span {
	background: url(/lovo/m/images/touxiang.png) no-repeat;
	background-size: contain;
	width: 68px;
	height: 26px;
	position: absolute;
	left: 5px;
	top: 4px;
	padding-top: 64px;
	font-size: 10px;
	color: #fff;
}

.mb-os {
	height: 50px;
	margin-top: 0px;
	background: rgba( 255,255,255,0.1);
	margin: auto;
	width: 100%;
	overflow: hidden;
}

.mb-os ul {
	display: -webkit-box;
}

.mb-os ul li {
	-webkit-box-flex: 1;
	text-align: center;
	position: relative;
	font-size: 10px;
}

.mb-os ul li:after {
	content: ' ';
	position: absolute;
	top: 20px;
	right: -1px;
	height: 14px;
	border-right: 1px #fff solid;
}

.mb-os ul li a {
	display: block;
	padding-top: 6px;
	height: 40px;
	color: #fff;
}

.mb-list {
	width: 100%;
	padding: 0;
	text-align: left;
	overflow: hidden;
	margin: 0 auto;
	margin-top: 10px;
}

.mb-list a {
	width: 100%;
	height: 46px;
	line-height: 46px;
	position: relative;
	text-align: left;
	float: left;
	font-size: 12px;
	text-indent: 46px;
}

.mb-list a span {
	margin: auto;
	color: #999
}

.mb-list a:before {
	content: ' ';
	position: absolute;
	width: 30px;
	height: 30px;
	left: 8px;
	top: 8px;
	background-size: cover
}

.mb-icon1:before {
	background-image: url(http://pic2.lowol.cn/upfiles/activity/lovo/M/dsh.png);
}

.mb-icon2:before {
	background-image: url(http://pic2.lowol.cn/upfiles/activity/lovo/M/shdz.png);
	background-size: 35px 35px;
}

.mb-icon3:before {
	background-image: url(http://pic2.lowol.cn/upfiles/activity/lovo/M/kftx.png);
	background-size: 42px 42px;
}

.mb-icon4:before {
	background-image: url(http://pic2.lowol.cn/upfiles/activity/lovo/M/gz.png);
	background-size: 42px 42px;
}

.mb-icon5:before {
	background-image: url(http://pic2.lowol.cn/upfiles/activity/lovo/M/sz.png);
	background-size: 35px 35px;
}

.mb-list .arrow {
	float: right;
	padding-right: 26px;
	text-align: right !important
}

.mb-list .arrow:after {
	content: '';
	display: inline-block;
	width: 8px;
	height: 8px;
	position: absolute;
	right: 8px;
	top: 40%;
	border-right: 1px solid #bfbfbf;
	border-top: 1px solid #bfbfbf;
	-webkit-transform: rotate(45deg)
}
</style>
</head>
<body>
<#include "/global/global_back.ftl" />
<div style="display: block;" id="J_wrap" class="landscape">
  <div class="main">
    <div class="mb-user">
      <img src="/images/customer/no-img.jpg" class="touxiang" />
      <p><#if customer_true_name??>${customer_true_name?if_exists}<#else>${customer_name?if_exists}</#if></p>
      <#--<a href="https://m.lovo.cn/activity/vipIndex.htm">
      	<img src="//pic2.lowol.cn/upfiles/activity/lovo/M/L1.png" class="dengji" />
      </a>-->
      <#--<div class="mb-os">
        <ul>
          <li>
            <a class="fragment" data-fragment="" data-status=""><img src="//pic2.lowol.cn/upfiles/activity/lovo/M/yhq.png" class="icon" /><br>
            11张优惠券</a>
          </li>
          <li>
            <a class="fragment" data-fragment="" data-status=""><img src="//pic2.lowol.cn/upfiles/activity/lovo/M/lblg.png" class="icon"/><br>
            162乐币</a>
          </li>
          <li>
            <a class="fragment" data-fragment="" data-status=""><img src="//pic2.lowol.cn/upfiles/activity/lovo/M/lw.png" class="icon"/><br>
            礼品领取</a>
          </li>
        </ul>
      </div>-->
    </div>
    <div class="mb-list">
      <a class="mb-icon1 fragment" href="/customer/order/list.do">我的订单<span class="arrow">查看</span></a>
      <a class="mb-icon2 fragment" href="/customer/delivery.do">收货地址<span class="arrow">修改</span></a>
      <a class="mb-icon3 fragment" ">客服中心（021-57677138）<span class="arrow"></span></a>
      <#--<a class="mb-icon4 fragment" href="javascript:alert('敬请期待')">我的关注<span class="arrow"></span></a>-->
      <a class="mb-icon5 fragment" href="/customer/info.do">我的资料<span class="arrow">设置</span></a>
      <input type="button" value="退出登录" id="wanshan" onclick="javascript:location.href='/passport/logout.do';" style="width: 90%;margin: 30% 5% 0; line-height: 30px; background: #f00; border-radius: 5px; color: #fff;	">
    </div>
  </div>
</div>

<script type="text/javascript" src="/js/common/zepto.min.js"></script>
</body>
</html>