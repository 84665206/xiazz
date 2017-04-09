/**
 * liuyi
 * 2012-5-28
 */
var userLog = {

	formatDate:function(value,row,index){
		var date = new Date(value);
		return common.formatDate.call(date,'yyyy-MM-dd hh:mm:ss');
	},

    formatUserLogState: function(value, row, index){
		if(value=="1"){
			return"成功";
		}else{
			return "失败";
		}
    },
    searchUserLogByCondition: function(){
        var userName = $.trim($("#userName").val());
        var beginDate = $.trim($("input[name='beginDate']").val());
        var endDate = $.trim($("input[name='endDate']").val());
        var queryParams = {};
        if (userName != "") {
            queryParams.userName = userName;
        }
        if (beginDate != "") {
            queryParams.beginDate = beginDate;
        }

        if (endDate != "") {
            queryParams.endDate = endDate;
        }

        $("#userLogDlg").datagrid("options").queryParams = queryParams;
        $("#userLogDlg").datagrid("load");
    }

};

$(function(){
	$("#parentUserLogDlg").show();
});

