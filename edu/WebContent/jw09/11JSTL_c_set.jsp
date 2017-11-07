<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- JSTL ��� :  taglib ������ ����-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>�� taglib�� c:set, c:remove,c:out�� ���</h3>

<c:set var="num1" value="100" scope="page"/>
<c:set var="num2"/> <!-- Default ObjectScope : page -->

<%
	String abc = (String)pageContext.getAttribute("num2");
	System.out.println("_"+abc+"_");
%>

1. num1�� empty : ${empty pageScope.num1} num1= ${pageScope.num1} <br/>
2. num2�� empty : ${empty num2 }  num2 = _${num2}_ <br/>
3. num1+num2 : ${num1+pageScope.num2 }<br/>
<!-- EL�� JSTL���� ���� ���Ǿ���,  JSP2.0 ����-->
4. c:out�� ����� num1+num2 : <c:out value="${num1+num2 }"/> <br/>
<hr/>

<c:set var="num1" value="${num1+100 }"/>
5. num1 : ${num1 } <br/><hr/>

<c:remove var="num1" scope="page"/>
6. num1�� empty : ${empty num1 }  num1 = _${num1 }_ <br/>
7. num2�� empty : ${empty num2 }  num2 = _${pageScope.num2 }_

<%
	String def = (String)pageContext.getAttribute("num1");
	System.out.println("_"+def+"_");
%>