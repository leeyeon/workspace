<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page isELIgnored="false" %>

<h3> EL 내장 객체</h3>

requestURI : ${ pageContext.request.requestURI } <br>
session Id : ${ pageContext.session.id } <br><br>

이름 : ${ param.name } <br>
주소 : ${ param.addr } <br><br>

선택한 소프트웨어<br>
<% String[] sw = request.getParameterValues("sw"); %>
1. ${paramValues.sw[0]}<br>
2. ${paramValues.sw[1]}<br>
3. ${paramValues.sw[2]}<br><br>

쿠키 JSESSIONID name : ${ cookie.JSESSIONID.name } <br>
쿠키 JSESSIONID value : ${ cookie.JSESSIONID.value }