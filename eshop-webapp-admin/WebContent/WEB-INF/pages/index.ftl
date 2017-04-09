<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>虾找虾后台管理系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<#include "/common/taglib.ftl" />
	</head>

	<body class="skin-1">
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<img alt="project icon" height="30" src="images/xiazz-logo.png">
						<small>
							虾找虾 - 后台管理系统
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									${userName?if_exists}
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<#--
								<li>
									<a href="#">
										<i class="icon-cog"></i>
										设置
									</a>
								</li>
								
								<li>
									<a href="sys/enterModifyPasswordPage.do">
										<i class="icon-user"></i>
										修改密码
									</a>
								</li>
								-->

								<li class="divider"></li>

								<li>
									<a href="logout.do">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<ul class="nav nav-list">
						<!--首页链接，菜单头部-->
						<li class="active">
							<a href="">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 控制台 </span>
							</a>
						</li>
						<!--/.首页链接，菜单头部-->
					</ul>

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>
							<li class="active">控制台</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">欢迎进入，虾找虾-后台管理系统。
					</div>
				</div>



				
				
				
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

<script type="text/javascript">
	var menus = [{}
	    <#list myMenuList! as m >
	    ,{
	    	id: ${m.id},
	    	parent_id: ${m.parent_id!'undefined'},
	    	ch_name: "${m.ch_name!'(no name)'}",
	    	menu_icon: "${m.description!'undefined'}",
	    	menu_type: ${m.menu_type!'undefined'},
	    	menu_level: ${m.menu_level!'undefined'},
	    	menu_uri: <#if m.menu_uri??>"${m.menu_uri}"<#else>undefined</#if>,
	    	sort_index : ${m.sort_index!1000000000}
	    }
	    </#list>
	];
	menus.splice(0, 1);
	
	/* 填充右侧二级菜单 */
	$(function(){
		for(var i=0; i < menus.length; i++) {
			if(menus[i].menu_type == 1){
				switch(menus[i].menu_level) {
				case 1:	
					$(".nav-list").append('<li id="'+menus[i].id+'"><a href="#"><i class="'+menus[i].menu_icon+'"></i><span class="menu-text"> '+menus[i].ch_name+' </span><b></b></a><ul id="'+menus[i].id+'_sub" class="submenu"></ul></li>'); 
					break;
				case 2:
					//如果存在子菜单，在上级菜单添加箭头标记
					$('#' + menus[i].parent_id + "").find('a').addClass('dropdown-toggle').find('b').addClass('arrow icon-angle-down');
					$('#' + menus[i].parent_id + "_sub").append(
						$('<li></li>').attr("id", menus[i].id).append(
							'<a href="'+menus[i].menuUri+'"><i class="icon-double-angle-right"></i>'+menus[i].ch_name+'<b></b></a>'
						).append(
							$('<ul class="submenu"/>').attr("id", menus[i].id + "_sub")
						)
					);
					break;
				case 3:
					$('#' + menus[i].parent_id + "").find('a').addClass('dropdown-toggle').find('b').addClass('arrow icon-angle-down');
					$('#' + menus[i].parent_id + "_sub").append(
						$('<li />').attr("id", "li_" + menus[i].id).append(
							$('<a leafMenu="true" href="'+menus[i].menu_uri+'" target="mainFrame" />').text(menus[i].ch_name)
						)
					);
					break;
				default:
				}
			}
		}
	});
	
	function pageContentLoad(url){
	   $(".page-content").load(url);
	}
	
	/*菜单点击*/
	$(document).ready(function(){		    
		$(".nav-list a[leafMenu='true']").click(function(e) {
		   var href = $(e.target).attr("href");
		   var menuid = $(e.target).attr("id");
		   if(!href||"#"==href||typeof(href)=="undefined"){
		    return false;
		   }
		   //var breadcrumbHtml=getBreadcrumb(menuid,"");
		   //$("#breadcrumb").html(breadcrumbHtml);
		   
		   //$("#progress-bar").show();
		   //processBar(0);
		   $.ajax({
		      type: "GET",
		      url: href,
		      error: function(data){
		       // $("#progress-bar").hide();
		        var html="<div class=\"alert alert-danger\">功能建设中，请联系系统管理员！</div>";
		        $(".page-content").html(html);
		        
		      },
		      success: function(data){
		       // $("#progress-bar").hide();
		        $(".page-content").html(data);
		      }
		  })
		  $(e.target).dropdown('toggle');
		  return false;
		});
	});	
	
	//bind ajax form submit
		  $(document).on("submit","[ajaxForm='true']",function(e){
		      e.preventDefault(); //important
		     
              var aFTarget='.page-content';
              var aFBeforeSubmit=eval($(this).attr("beforeSubmit"));
              var aFSuccess=eval($(this).attr("afterSubmit"));
              if(eval($(this).attr("target"))){
                  aFTarget=eval($(this).attr("target"));
                  //alert(aFTarget);
              }
              var ajaxFormOptions = { 
		        target:        aFTarget,   // target element(s) to be updated with server response 
		        beforeSubmit:  aFBeforeSubmit,  // pre-submit callback 
		        success:       aFSuccess  // post-submit callback 
		 
		        // other available options: 
		        //url:       url         // override for form's 'action' attribute 
		        //type:      type        // 'get' or 'post', override for form's 'method' attribute 
		        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
		        //clearForm: true        // clear all form fields after successful submit 
		        //resetForm: true        // reset the form after successful submit 
		        // $.ajax options can be used here too, for example: 
		        //timeout:   3000 
		    }; 
			  $(this).ajaxSubmit(ajaxFormOptions);
			  return false; 
		  }); 
</script>
</body>
</html>

