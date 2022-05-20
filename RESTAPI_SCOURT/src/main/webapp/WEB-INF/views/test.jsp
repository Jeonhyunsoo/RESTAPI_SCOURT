<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" />
<script>
function fn_submit(data){
	var params = {};
	params.url = $("#url").val();
	params.uid = $("#uid").val();
	params.pwd = $("#pwd").val();
	 
	$.ajax({
		type: "POST",
		url: "/test",
		dataType: "json",
		async: false,
		data: params,
		success: function(result){
		    if(result != null){
		    	$("#resultUrl").html(result);
		    }else{
		    }
		}
	}	 
</script>

<body>
<div>
<button type="submit" name="action" value="encode">암호화</button>
</div>
<div>
	<span id="resultUrl"></span>
	<span id="resultUid"></span>
	<span id="resultPwd"></span>
</div>
</body>
</html>