$.ajaxSetup({
	type : "POST",
	dataType : "json",
//	scriptCharset : "utf-8",
	beforeSend : function(XMLHttpRequest) {

	},
	complete : function(XMLHttpRequest, textStatus) {
		checkAjaxStatus(XMLHttpRequest, textStatus);
	},
	success : function(data, textStatus, jqXHR) {

	},
	error : function(XMLHttpRequest, textStatus, errorThrown) {

	}
});

function checkAjaxStatus(XMLHttpRequest, textStatus) {
	if (textStatus == "error") {
		showErrorInfo(XMLHttpRequest);
	}
}

function showErrorInfo(XMLHttpRequest) {
	if (window.parent && window.parent != window
			&& typeof window.parent.showErrorInfo == 'function') {
		window.parent.showErrorInfo.apply(window.parent, arguments);
		return;
	}

	var _errorMsgWindow = $("#_errorMsgWindow");
	if (_errorMsgWindow.length == 0) {
		_errorMsgWindow = $("<div id=\"_errorMsgWindow\" style=\"display:none;\"></div>");
		$("body").append(_errorMsgWindow);
	}

	_errorMsgWindow.html(XMLHttpRequest.responseText);

	var msg = "<font color=\"red\">操作失败!</font>&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"showErrorMsgWindow()\">查看详细</a>";
	$.messager.show({
		title : "提示",
		msg : msg,
		timeout : 5000,
		showType : "slide"
	});
}

function showErrorMsgWindow() {
	$("#_errorMsgWindow").show();
	$("#_errorMsgWindow").window({
		title : "详细信息",
		width : "80%",
		height : "80%",
		modal : true,
		collapsible : false,
		minimizable : false
	});
}

/**
 * 通用异步ajax请求方法
 * 
 * @param url
 *            请求url
 * @param data
 *            请求参数
 * @param callBack
 *            回调函数
 * @param modal
 *            请求过程中是否显示遮罩层, 可选true或false
 */
function ajaxRequest(url, type, data, callBack, modal) {
	$.ajax({
		url : url,
		data : data,
		type : type,
		modal : modal,
		success : function(data) {
			try {
				if (callBack && typeof callBack == "function") {
					callBack(true, data);
				}
			} catch (e) {
				console.error(e);
			}
		},
		error : function(request, textStatus, errorThrown) {
			try {
				if (callBack && typeof callBack == "function") {
					callBack(false, request, textStatus, errorThrown);
				}
			} catch (e) {
				console.error(e);
			}
		}
	});
}