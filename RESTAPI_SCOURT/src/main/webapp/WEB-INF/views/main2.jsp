

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OLAP 암/복호화</title>
</head>

<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
function fn_submit(){
	var params = {};
	params.url = document.getElementsByName("url")[0].value;
	params.uid = document.getElementsByName("uid")[0].value;
	params.pwd = document.getElementsByName("pwd")[0].value;
	 
	
	$.ajax({
		url: "/test.do",
		type: "post",
		data: {"url" : $("#url").val()},
		contentType : "application/json;charset=UTF-8",
		success: function(result){
		    if(result != null){
		    	$("#resultUrl").html(result);
		    }else{
		    }
		}
	});
}
</script>
<form action="/index.do" method="post">
	<p>OLAP 암/복호화</p>
	JDBC URL : <input type="text" name="url" size="50"><br>
	JDBC UID : <input type="text" name="uid" size="50"><br>
	JDBC PWD : <input type="text" name="pwd" size="50"><br>
	<br>
	<button type="submit" name="action" value="encode">암호화</button>
	<button type="submit" name="action" value="decode">복호화</button>
	
</form>
<a onclick="fn_submit();">테스트</a>
<h2>결과출력</h2>
<%-- <c:if test="${not empty param.name}">
 이름: ${param.name} <br>
  나이: ${param.age }<br>
</c:if> --%>


</body>
</html>