<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

1. foreach 5�� ��� <br/>
<c:forEach var="i" begin="1" end = "10" step="1">
	5*${i} = ${5*i}<br/>
</c:forEach><hr/>

<%
	java.util.List list = new java.util.Vector();
	list.add("A");
	list.add("B");
	list.add("C");
	list.add("D");
	
	System.out.println("==================");
	for(int i = 0; i<list.size(); i++) {
		System.out.println((String)list.get(i));
	}
	System.out.println("==================");
	for(Object obj:list) {
		System.out.println((String)obj);
	}
	System.out.println("==================");
%>

<c:forEach var="i" items="<%= list %>" begin="0" step="1" end="10">
	:: list ���� : ${i} <br/>
</c:forEach><hr/>

<c:forEach var="i" items="<%= list %>">
	:: list ���� : ${i} <br/>
</c:forEach><hr/>

<%
	java.util.Map map = new java.util.HashMap();
	map.put("a", "A");
	map.put("b", "B");
	map.put("c", "C");
	map.put("d", "D");
%>

<c:forEach var="i" items="<%=map%>">
	map�� ����� ���� :: ${i.key} = ${i.value} <br/>
</c:forEach><hr/>


<c:set var="aaa" value="<%=map%>"/>
<c:forEach var="i" items="${aaa}">
	<c:if test="${i.key == 'a' }">
		key a �� value : ${i.value}
	</c:if>
</c:forEach>