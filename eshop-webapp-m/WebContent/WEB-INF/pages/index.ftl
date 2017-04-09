<!DOCTYPE HTML>
<html>
<head>
	<title>虾找虾</title>
	<meta name="keywords" content="虾找虾"/> 
	<meta name="description" content="虾找虾"/>
	<#include "/global/global_all.ftl" />
	<link href="/css/common.css" rel="stylesheet" />
	<link href="/css/index.css" rel="stylesheet" />
</head>

<body style="background: #F2EFEF;">
	<div class="fullscreen">
		<div id="content" >
			<#--头部切换大图-->
    		<section data-group="1">
    			<div class="slide imgbox" id="slide3">
					<ul>
						<li>
							<a name="H5focus_2" class="track" href="/about.do" title="虾找网">
								<img class="photo" width="100%" index="0" title="虾找网" l="l" src="images/index/index-banner1.jpg">
							</a> 
						</li>
						<li>
							<a name="H5focus_2" class="track" href="/html/act/act170409.html" title="虾找网">
								<img class="photo" width="100%" index="0" title="虾找网" l="l" src="images/index/index-banner2.jpg">
							</a> 
						</li>
						
					</ul>
					<div class="switch-tab">
						<a href="javascript:void(0);" onclick="return false;" class="current"></a>
						<a href="javascript:void(0);" onclick="return false;"></a>
				    </div>
				</div>
    		</section>
    		
    		<section style="margin-top:10px">
				<div style="position:relative;width:47%;margin:2%; margin-right:0; float:left">
			        <a  href="/goods/view.do?sku_id=2" >
			          	<img src="images/index/index-t1.jpg" width="100%">
			          	<div style="position:absolute;z-index:2;left:0px;top: 87%;width: 100%;text-align: center;font-size:13px;">
						        库存:${today_stock?if_exists}斤
						</div>
			        </a>
				</div>
				<div style="position:relative;width:47%;margin:2%; float:right">
					<a  href="/goods/view.do?sku_id=1" >
			          	<img src="images/index/index-t2.jpg" width="100%">
			          	<div style="position:absolute;z-index:2;left:0px;top: 87%;width: 100%;text-align: center;font-size:13px;">
						        库存:${tomorrow_stock?if_exists}斤
						</div>
			        </a>
				</div>
    		</section>
    		
    		<section>
    			<input type="button" id="my_order" class="c-btn-oran-big" value="我的订单">
    			<input type="button" id="about_me" class="c-btn-oran-big" value="关于我们" style="margin-top:10px;">
    		</section>
    		<div style="margin-bottom:20px;">
    		&nbsp;
    		</div>
    	</div>
	</div>

<script type="text/javascript" src="/js/common/zepto.min.js"></script>
<script type="text/javascript" src="/js/sea/sea.js"></script>
<script filepath="//g.alicdn.com/mtb/??lib-flexible/0.2.3/flexible_css.js,lib-flexible/0.2.3/flexible.js,lib-env/1.5.0/env.js">!function(){var e='@charset "utf-8"; html{overflow-y:scroll}html,body{font-family:sans-serif}.clearfix:before,.clearfix:after{content:" ";display:table}.clearfix:after{clear:both}.fn-hide{display:none}html{color:#000;background:#fff;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}html *{outline:0;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0)}body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td,hr,button,article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{margin:0;padding:0}input,select,textarea{font-size:100%}table{border-collapse:collapse;border-spacing:0}fieldset,img{border:0}abbr,acronym{border:0;font-variant:normal}del{text-decoration:line-through}address,caption,cite,code,dfn,em,th,var{font-style:normal;font-weight:500}ol,ul{list-style:none}caption,th{text-align:left}h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:500}q:before,q:after{content:\'\'}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}sup{top:-.5em}sub{bottom:-.25em}a:hover{text-decoration:underline}ins,a{text-decoration:none}.grid{box-sizing:content-box;padding-left:.3rem;padding-right:.3rem;margin-left:-.2rem}.grid:before,.grid:after{content:" ";display:table}.grid:after{clear:both}.grid [class^=col-]{margin-left:.2rem;float:left}.grid .col-1{width:1.1rem}.grid .col-2{width:2.4rem}.grid .col-3{width:3.7rem}.grid .col-4{width:5rem}.grid .col-5{width:6.3rem}.grid .col-6{width:7.6000000000000005rem}.grid .col-7{width:8.900000000000002rem}.grid .col-8{width:10.200000000000001rem}.grid .col-9{width:11.500000000000002rem}.grid .col-10{width:12.8rem}.grid .col-11{width:14.100000000000001rem}.grid .col-12{width:15.400000000000002rem}.grid-thin{box-sizing:content-box;padding-left:.4rem;padding-right:.4rem;margin-left:-.4rem}.grid-thin:before,.grid-thin:after{content:" ";display:table}.grid-thin:after{clear:both}.grid-thin [class^=col-]{margin-left:.4rem;float:left}.grid-thin .col-1{width:.9rem}.grid-thin .col-2{width:2.2rem}.grid-thin .col-3{width:3.5000000000000004rem}.grid-thin .col-4{width:4.8rem}.grid-thin .col-5{width:6.1rem}.grid-thin .col-6{width:7.4rem}.grid-thin .col-7{width:8.7rem}.grid-thin .col-8{width:10rem}.grid-thin .col-9{width:11.299999999999999rem}.grid-thin .col-10{width:12.6rem}.grid-thin .col-11{width:13.9rem}.grid-thin .col-12{width:15.200000000000001rem}.grid-fat{box-sizing:content-box;padding-left:.2rem;padding-right:.2rem;margin-left:0rem}.grid-fat:before,.grid-fat:after{content:" ";display:table}.grid-fat:after{clear:both}.grid-fat [class^=col-]{margin-left:0rem;float:left}.grid-fat .col-1{width:1.3rem}.grid-fat .col-2{width:2.6rem}.grid-fat .col-3{width:3.9000000000000004rem}.grid-fat .col-4{width:5.2rem}.grid-fat .col-5{width:6.5rem}.grid-fat .col-6{width:7.800000000000001rem}.grid-fat .col-7{width:9.1rem}.grid-fat .col-8{width:10.4rem}.grid-fat .col-9{width:11.700000000000001rem}.grid-fat .col-10{width:13rem}.grid-fat .col-11{width:14.3rem}.grid-fat .col-12{width:15.600000000000001rem}',d=document.createElement("style");if(document.getElementsByTagName("head")[0].appendChild(d),d.styleSheet){d.styleSheet.disabled||(d.styleSheet.cssText=e)}else{try{d.innerHTML=e}catch(f){d.innerText=e}}}();!function(x){function w(){var a=r.getBoundingClientRect().width;a/v>540&&(a=540*v),x.rem=a/16,r.style.fontSize=x.rem+"px"}var v,u,t,s=x.document,r=s.documentElement,q=s.querySelector('meta[name="viewport"]'),p=s.querySelector('meta[name="flexible"]');if(q){console.warn("将根据已有的meta标签来设置缩放比例");var o=q.getAttribute("content").match(/initial\-scale=(["']?)([\d\.]+)\1?/);o&&(u=parseFloat(o[2]),v=parseInt(1/u))}else{if(p){var o=p.getAttribute("content").match(/initial\-dpr=(["']?)([\d\.]+)\1?/);o&&(v=parseFloat(o[2]),u=parseFloat((1/v).toFixed(2)))}}if(!v&&!u){var n=(x.navigator.appVersion.match(/android/gi),x.navigator.appVersion.match(/iphone/gi)),v=x.devicePixelRatio;v=n?v>=3?3:v>=2?2:1:1,u=1/v}if(r.setAttribute("data-dpr",v),!q){if(q=s.createElement("meta"),q.setAttribute("name","viewport"),q.setAttribute("content","initial-scale="+u+", maximum-scale="+u+", minimum-scale="+u+", user-scalable=no"),r.firstElementChild){r.firstElementChild.appendChild(q)}else{var m=s.createElement("div");m.appendChild(q),s.write(m.innerHTML)}}x.dpr=v,x.addEventListener("resize",function(){clearTimeout(t),t=setTimeout(w,300)},!1),x.addEventListener("pageshow",function(b){b.persisted&&(clearTimeout(t),t=setTimeout(w,300))},!1),"complete"===s.readyState?s.body.style.fontSize=12*v+"px":s.addEventListener("DOMContentLoaded",function(){s.body.style.fontSize=12*v+"px"},!1),w()}(window);!function(e,d){function f(b){Object.defineProperty(this,"val",{value:b.toString(),enumerable:!0}),this.gt=function(c){return f.compare(this,c)>0},this.gte=function(c){return f.compare(this,c)>=0},this.lt=function(c){return f.compare(this,c)<0},this.lte=function(c){return f.compare(this,c)<=0},this.eq=function(c){return 0===f.compare(this,c)}}d.env=d.env||{},f.prototype.toString=function(){return this.val},f.prototype.valueOf=function(){for(var h=this.val.split("."),g=[],k=0;k<h.length;k++){var j=parseInt(h[k],10);isNaN(j)&&(j=0);var i=j.toString();i.length<5&&(i=Array(6-i.length).join("0")+i),g.push(i),1===g.length&&g.push(".")}return parseFloat(g.join(""))},f.compare=function(h,g){h=h.toString().split("."),g=g.toString().split(".");for(var k=0;k<h.length||k<g.length;k++){var j=parseInt(h[k],10),i=parseInt(g[k],10);if(window.isNaN(j)&&(j=0),window.isNaN(i)&&(i=0),i>j){return -1}if(j>i){return 1}}return 0},d.version=function(b){return new f(b)}}(window,window.lib||(window.lib={})),function(h,g){g.env=g.env||{};var l=h.location.search.replace(/^\?/,"");if(g.env.params={},l){for(var k=l.split("&"),j=0;j<k.length;j++){k[j]=k[j].split("=");try{g.env.params[k[j][0]]=decodeURIComponent(k[j][1])}catch(i){g.env.params[k[j][0]]=k[j][1]}}}}(window,window.lib||(window.lib={})),function(g,f){f.env=f.env||{};var j,i=g.navigator.userAgent;if(j=i.match(/Windows\sPhone\s(?:OS\s)?([\d\.]+)/)){f.env.os={name:"Windows Phone",isWindowsPhone:!0,version:j[1]}}else{if(i.match(/Safari/)&&(j=i.match(/Android[\s\/]([\d\.]+)/))){f.env.os={version:j[1]},i.match(/Mobile\s+Safari/)?(f.env.os.name="Android",f.env.os.isAndroid=!0):(f.env.os.name="AndroidPad",f.env.os.isAndroidPad=!0)}else{if(j=i.match(/(iPhone|iPad|iPod)/)){var h=j[1];j=i.match(/OS ([\d_\.]+) like Mac OS X/),f.env.os={name:h,isIPhone:"iPhone"===h||"iPod"===h,isIPad:"iPad"===h,isIOS:!0,version:j[1].split("_").join(".")}}else{f.env.os={name:"unknown",version:"0.0.0"}}}}f.version&&(f.env.os.version=f.version(f.env.os.version))}(window,window.lib||(window.lib={})),function(f,e){e.env=e.env||{};var h,g=f.navigator.userAgent;(h=g.match(/(?:UCWEB|UCBrowser\/)([\d\.]+)/))?e.env.browser={name:"UC",isUC:!0,version:h[1]}:(h=g.match(/MQQBrowser\/([\d\.]+)/))?e.env.browser={name:"QQ",isQQ:!0,version:h[1]}:(h=g.match(/Firefox\/([\d\.]+)/))?e.env.browser={name:"Firefox",isFirefox:!0,version:h[1]}:(h=g.match(/MSIE\s([\d\.]+)/))||(h=g.match(/IEMobile\/([\d\.]+)/))?(e.env.browser={version:h[1]},g.match(/IEMobile/)?(e.env.browser.name="IEMobile",e.env.browser.isIEMobile=!0):(e.env.browser.name="IE",e.env.browser.isIE=!0),g.match(/Android|iPhone/)&&(e.env.browser.isIELikeWebkit=!0)):(h=g.match(/(?:Chrome|CriOS)\/([\d\.]+)/))?(e.env.browser={name:"Chrome",isChrome:!0,version:h[1]},g.match(/Version\/[\d+\.]+\s*Chrome/)&&(e.env.browser.name="Chrome Webview",e.env.browser.isWebview=!0)):g.match(/Safari/)&&(h=g.match(/Android[\s\/]([\d\.]+)/))?e.env.browser={name:"Android",isAndroid:!0,version:h[1]}:g.match(/iPhone|iPad|iPod/)?g.match(/Safari/)?(h=g.match(/Version\/([\d\.]+)/),e.env.browser={name:"Safari",isSafari:!0,version:h[1]}):(h=g.match(/OS ([\d_\.]+) like Mac OS X/),e.env.browser={name:"iOS Webview",isWebview:!0,version:h[1].replace(/\_/,".")}):e.env.browser={name:"unknown",version:"0.0.0"},e.version&&(e.env.browser.version=e.version(e.env.browser.version))}(window,window.lib||(window.lib={})),function(e,d){d.env=d.env||{};var f=e.navigator.userAgent;d.env.thirdapp=f.match(/Weibo/i)?{appname:"Weibo",isWeibo:!0}:f.match(/MicroMessenger/i)?{appname:"Weixin",isWeixin:!0}:!1}(window,window.lib||(window.lib={})),function(r,q){q.env=q.env||{};var p,o,n=r.navigator.userAgent;(o=n.match(/WindVane[\/\s]([\d\.\_]+)/))&&(p=o[1]);var m=!1,l="",k="",j="";(o=n.match(/AliApp\(([A-Z\-]+)\/([\d\.]+)\)/))&&(m=!0,l=o[1],j=o[2],k=l.indexOf("-PD")>0?q.env.os.isIOS?"iPad":q.env.os.isAndroid?"AndroidPad":q.env.os.name:q.env.os.name),!l&&n.indexOf("TBIOS")>0&&(l="TB"),q.env.aliapp=m?{windvane:q.version(p||"0.0.0"),appname:l||"unkown",version:q.version(j||"0.0.0"),platform:k||q.env.os.name}:!1,q.env.taobaoApp=q.env.aliapp}(window,window.lib||(window.lib={}));</script> 
<script src="/js/mv2/setting.js" type="text/javascript"></script>
<script src="/js/mv2/mggScrollImg.js" type="text/javascript"></script>


<script type="text/javascript">

$(document).ready(function(){
	//头部图片切换滚动
	var scrollImg = $.mggScrollImg('.imgbox ul',{
        loop : true,//循环切换
        auto : true,//自动切换
        callback : function(ind){//这里传过来的是索引值
            $('#page').text(ind+1);
        }
    });
    
    $("#about_me").click(function(f) {
    	location.href="/about.do";
    });
    
    $("#my_order").click(function(f) {
    	location.href="/customer/index.do";
    });
    
});

seajs.config({ 
  plugins: ['shim'], 
  alias: {
    'zepto': {
      src: 'common/zepto.min.js',
      exports: '$'
    }
  }
}); 
//seajs.use('index/index.js');
</script>
</body>
</html>

