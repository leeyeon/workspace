<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("EUC_KR"); %>

<c:if test="true">
	if�� ����1 <br/>
</c:if>

<c:if test="false">
	if�� ����2 <br/>
</c:if>

1.�̸�: <%= request.getParameter("name") %> <br/>
2.�̸�: ${param.name};

<c:if test="${param.name=='ȫ�浿'}" >
	3. ȫ�浿�� ȯ���մϴ�. <br/>
</c:if>

<c:if test="${!empty param.name}">
	4. ${param.name}�� ȯ���մϴ�. <br/>
</c:if>


<c:set var="level" value="����" scope="session"/>
<c:if test="${param.age < 19}">
	<c:set var="level" value="û�ҳ�"/>
</c:if>

5. ${param.name}���� ���� ${param.age}�� ${level}�Դϴ�. <br/>
Ȯ��1 : ${sessionScope.level} <br/>
Ȯ��2 : ${pageScope.level}<br/>

<c:if test="${!empty paramValues.sw[0]}">
	6. ������ SW : ${paramValues.sw[0] } <br/>
</c:if>

<c:if test="${!empty paramValues.sw[1]}">
	6. ������ SW : ${paramValues.sw[1] } <br/>
</c:if>

<c:choose>
	<c:when test="${param.age > 19}">
		7. ${param.name}���� �������� ����:${param.age} <br/>
	</c:when>
	<c:when test="${param.age < 19}">
		7. ${param.name}���� û�ҳ����� ����:${param.age} <br/>
	</c:when>
	<c:otherwise>
		7. ${param.name}���� ����? û�ҳ�? ����:${param.age} <br/>
	</c:otherwise>
</c:choose>

