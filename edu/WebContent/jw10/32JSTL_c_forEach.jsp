<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("EUC_KR"); %>

<c:forEach var="i" items="${paramValues.sw}">
	������ SW�� :: ${i} <br/>
</c:forEach>

<c:forEach var="i" items="${param}">
	${i.key} :: ${i.value} <br/>
</c:forEach>

<c:forEach var="i" items="${param}">
	<c:if test="${i.key == 'name' }">
		${param.name}���� ���̴� ${param.age}�Դϴ�.
	</c:if>
</c:forEach>