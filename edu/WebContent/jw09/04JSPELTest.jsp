<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	session.setAttribute("client", new jw09.Client());

	jw09.Client client = (jw09.Client) session.getAttribute("client");

%>

name: ${ sessionScope.client.name } <br>
addr: ${ client.addr } <br>
age: ${ client.age } <br><br>

empty ? : ${ empty client.info } <br><br>

index 0 value : ${ client.info[0] }<br>
index 1 value : ${ client.info[1] }<br><br>

