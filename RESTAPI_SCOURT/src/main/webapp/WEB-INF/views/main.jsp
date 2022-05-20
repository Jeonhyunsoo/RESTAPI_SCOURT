<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OLAP 암/복호화</title>
</head>
<body>
<div>
	<form action="/main" method="post">
		<p>OLAP JDBC 암/복호화 툴</p>
		SEED : <input type="text" name="seed" size="50" value="${result.seed}"><br>
		JDBC URL : <input type="text" name="url" size="50" value="${result.url}"><br>
		JDBC UID : <input type="text" name="uid" size="50" value="${result.uid}"><br>
		JDBC PWD : <input type="text" name="pwd" size="50" value="${result.pwd}"><br>
		<br>
		<button type="submit" name="action" value="encode">암호화</button>&nbsp;
		<button type="submit" name="action" value="decode">복호화</button>
	</form>
</div>
<br>
<div>
	<h2>${result.msg}</h2>
	<c:choose>
		<c:when test="${!empty result.error}">
			<c:if test="${!empty result.error}"><p>에러 : ${result.error}</p></c:if>
		</c:when>
		<c:otherwise>
			<c:if test="${!empty result.rstUrl}"><p>URL : ${result.rstUrl}</p></c:if>
			<c:if test="${!empty result.rstUid}"><p>UID : ${result.rstUid}</p></c:if>
			<c:if test="${!empty result.rstPwd}"><p>PWD : ${result.rstPwd}</p></c:if>	
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>