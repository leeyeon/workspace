<%@page import="com.model2.mvc.service.domain.Product"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Purchase purchase = (Purchase)request.getAttribute("purchase");
%>
<html>
<head>
<title>���� Ȯ��</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td><%= purchase.getPurchaseProd().getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td><%= purchase.getBuyer().getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		
		<% if("1".equals(purchase.getPaymentOption())) { %>
			���ݱ���
		<% } else { %>
			�ſ뱸��
		<% } %>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%= purchase.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><%= purchase.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><%= purchase.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td><%= purchase.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><%= purchase.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>