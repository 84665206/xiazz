<header>
		<div class="m_common_new_top">
				
				<!-- 新版商详头部开始 -->
				<div id="m_common_header">
						<header class="header" >
								<div class="header-new-bar">
										<div id="m_common_header_goback" class="header-icon-back"><a href="javascript:history.go(-1)"><span></span></a></div>
										<div class="header-title" id="headerTitle"></div>
										<div report-eventid="MCommonHead_NavigateButton" report-pageparam="868725" id="m_common_header_jdkey" class="header-icon-shortcut L_ping"><a class="click" id="click_common_header_shortcut" onclick="headerShortcut(1);"><span></span></a></div>
										<div class="header-bar-border"></div>
								</div>
								<ul id="m_common_header_shortcut" class="header-shortcut" style="display:none">
										<li id="m_common_header_shortcut_m_index">
												<a class="L_ping" report-eventid="MCommonHead_home" report-pageparam="868725" href="/">
												<span class="shortcut-home"></span>
												<strong>首页</strong> 
												</a>
										</li>
										<#--<li class="L_ping" report-eventid="MCommonHead_CategorySearch" report-pageparam="868725" id="m_common_header_shortcut_category_search">
												<a href="/listCategory.php">
												<span class="shortcut-categories"></span>
												<strong>分类</strong>
												</a>
										</li>
										<li class="L_ping" report-eventid="MCommonHead_Cart" report-pageparam="868725" id="">
												<a href="/cart/view.php">
												<span class="shortcut-cart"></span>
												<strong>购物车</strong>
												</a>
										</li>-->
										<li id="m_common_header_shortcut_h_home" class=" current">
												<a class="L_ping" report-eventid="MCommonHead_MYJD" report-pageparam="868725" href="/customer/index.do">
												<span class="shortcut-my-account"></span>
												<strong>个人中心</strong>
												</a>
										</li>
								</ul>
					
						</header>
				</div>
				<!-- 新版商详头部结束 -->
		</div>
</header>
<script>
	var title = document.title;
	if(title.length>12){
		title = title.substring(0,12)+"...";
	}
	document.getElementById("headerTitle").innerHTML=title;
	function headerShortcut(type){
		if(type==1){
			$("#m_common_header_shortcut").show();
			$("#click_common_header_shortcut").attr("onclick","headerShortcut(2);");
		}
		
		if(type==2){
			$("#m_common_header_shortcut").hide();
			$("#click_common_header_shortcut").attr("onclick","headerShortcut(1);");
		}
	}
	
	//判断是否是html5
	var ua = window.navigator.userAgent;
	if(ua.indexOf("LePlus")>-1){
		document.getElementById("m_common_header").style.display="none";
		document.getElementById("floor-bottom-bar-pannel-id").style.display="none";
	}
	
</script>