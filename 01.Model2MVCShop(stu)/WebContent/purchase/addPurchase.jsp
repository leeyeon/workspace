<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@page import="com.model2.mvc.service.user.vo.UserVO"%>
<%@page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	PurchaseVO purchaseVO = (PurchaseVO)request.getAttribute("purchaseVO");
	UserVO buyer = purchaseVO.getBuyer();
	ProductVO product = purchaseVO.getPurchaseProd();
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
		<td><%= product.getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td><%= buyer.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		
		<% if("1".equals(purchaseVO.getPaymentOption())) { %>
			���ݱ���
		<% } else { %>
			�ſ뱸��
		<% } %>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%= purchaseVO.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><%= purchaseVO.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><%= purchaseVO.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td><%= purchaseVO.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><%= purchaseVO.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>