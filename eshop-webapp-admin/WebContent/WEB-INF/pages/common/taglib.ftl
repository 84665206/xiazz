<#--JS-->
<!-- basic scripts -->
	<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='js/jquery/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>
	<!-- <![endif]-->
	
	<!--[if IE]>
		<script type="text/javascript">
 			window.jQuery || document.write("<script src='js/jquery/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>
	<![endif]-->

	<script type="text/javascript">
		if("ontouchend" in document) document.write("<script src='js/jquery/jquery.mobile.custom.min.js'>"+"<"+"script>");
	</script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<script src="js/typeahead-bs2.min.js"></script>
	<!-- page specific plugin scripts -->
	<script src="js/jquery/jquery-ui-1.10.3.full.min.js"></script>
	<script src="js/jquery/jquery.bootstrap-duallistbox.min.js"></script>
	<script src="js/jquery/chosen.jquery.min.js"></script>
	<script src="js/bootstrap/bootstrap-multiselect.min.js"></script>
	<script src="js/select2.min.js"></script>
	<script src="js/bootstrap/date-time/bootstrap-datetimepicker.js"></script>
	<script src="js/bootstrap/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
	<!--<script src="js/bootstrap/date-time/bootstrap-datepicker.min.js"></script>-->
	<script src="js/bootstrap/date-time/daterangepicker.min.js"></script>
	<script src="js/bootstrap/date-time/moment.min.js"></script>
	<script src="js/jquery/jquery.form.js"></script>
	<!--<script src="js/jquery/jquery.jqGrid.min.js"></script>-->
	<script src="js/jquery/jquery.dataTables.min.js"></script>
	<script src="js/jquery/jquery.dataTables.bootstrap.js"></script>
	<script src="js/jquery/i18n/grid.locale-cn.js"></script>
	<script src="js/bootstrap/bootbox.min.js"></script>
	<script src="js/ztree/jquery.ztree.all.min.js"></script>
	
	
	<!-- ace scripts -->
	<script src="js/ace/ace-elements.min.js"></script>
	<script src="js/ace/ace.min.js"></script>
	
<#--CSS-->
<!-- basic styles -->
	<link href="css/jquery.dataTables.min.css" rel="stylesheet" />
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="css/font-awesome.min.css" />
	<!--[if IE 7]>
		<link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
	<![endif]-->
	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="css/jquery-ui-1.10.3.full.min.css" />
	<link rel="stylesheet" href="css/chosen.css" />
	
	<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" />
	<!--<link rel="stylesheet" href="css/datepicker.css" />-->
	<!--<link rel="stylesheet" href="css/ui.jqgrid.css" />-->
	<link rel="stylesheet" href="css/zTreeStyle.css" />
	<link rel="stylesheet" href="css/daterangepicker.css" />
	<link rel="stylesheet" href="css/bootstrap-duallistbox.min.css" />
	<link rel="stylesheet" href="css/bootstrap-multiselect.min.css" />
	<link rel="stylesheet" href="css/select2.css" />
	
	<!-- ace styles -->
	<link rel="stylesheet" href="css/ace.min.css" />
	<link rel="stylesheet" href="css/ace-rtl.min.css" />
	<link rel="stylesheet" href="css/ace-skins.min.css" />
	<!--[if lte IE 8]>
		<link rel="stylesheet" href="css/ace-ie.min.css" />
	<![endif]-->
	<!-- inline styles related to this page -->
	<!-- ace settings handler -->
	<script src="js/ace/ace-extra.min.js"></script>
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />

<script type="text/javascript">
	/*汉化datatable*/
	(function(){
	    var oLanguage={
	        "oAria": {
	            "sSortAscending": ": 升序排列",
	            "sSortDescending": ": 降序排列"
	        },
	        "oPaginate": {
	            "sFirst": "首页",
	            "sLast": "末页",
	            "sNext": "下页",
	            "sPrevious": "上页"
	        },
	        "sEmptyTable": "没有相关记录",
	        "sInfo": "第 _START_ 到 _END_ 条记录，共 _TOTAL_ 条",
	        "sInfoEmpty": "第 0 到 0 条记录，共 0 条",
	        "sInfoFiltered": "(从 _MAX_ 条记录中检索)",
	        "sInfoPostFix": "",
	        "sDecimal": "",
	        "sThousands": ",",
	        "sLengthMenu": "每页显示条数: _MENU_",
	        "sLoadingRecords": "正在载入...",
	        "sProcessing": "正在载入...",
	        "sSearch": "搜索:",
	        "sSearchPlaceholder": "",
	        "sUrl": "",
	        "sZeroRecords": "没有相关记录"
	    }
	    $.fn.dataTable.defaults.oLanguage=oLanguage;
	    //$.extend($.fn.dataTable.defaults.oLanguage,oLanguage)
	})();
</script>