<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"  %>
<fmt:requestEncoding value = "EUC-KR" />

<html>
<head>
<title>��� ��ǰ ����</title>
</head>

<body>
	<center>< ��� ��ǰ ��� ></center><br><br>

<c:if test="${fn:length(history) > 1}">
	<c:forEach var="i" items="${history}">
		<a href="/product/getProduct?prodNo=${i}&menu=search" target="rightFrame">${i}</a><br>
	</c:forEach>
</c:if>
<c:if test="${fn:length(history) <= 1}">
	���� �ѷ����� ��ǰ�� �����.
</c:if>

</body>
</html>