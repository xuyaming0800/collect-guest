$(function() {
	if(system_type)
		$.post(content+"/myitems/report/queryProjectInfo.html",{
			system_type:system_type
		},function(data){
			if(data.success){
				$("#count_allNum").text(data.info&&
						data.info.sall?data.info.sall:0);
				$("#count_successNum").text(data.info&&
						data.info.auditSuccess?data.info.auditSuccess:0);
				$("#count_fallNum").text(data.info&&
						data.info.auditFall?data.info.auditFall:0);
				$("#count_auditingNum").text(data.info&&
						data.info.auditing?data.info.auditing:0);
			}
		},"json");
});