<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page isELIgnored="false" %>

<h3> EL ���� ��ü</h3>

requestURI : ${ pageContext.request.requestURI } <br>
session Id : ${ pageContext.session.id } <br><br>

�̸� : ${ param.name } <br>
�ּ� : ${ param.addr } <br><br>

������ ����Ʈ����<br>
<% String[] sw = request.getParameterValues("sw"); %>
1. ${paramValues.sw[0]}<br>
2. ${paramValues.sw[1]}<br>
3. ${paramValues.sw[2]}<br><br>

��Ű JSESSIONID name : ${ cookie.JSESSIONID.name } <br>
��Ű JSESSIONID value : ${ cookie.JSESSIONID.value }