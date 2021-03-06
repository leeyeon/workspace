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
<title>구매 확인</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%= product.getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%= buyer.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		
		<% if("1".equals(purchaseVO.getPaymentOption())) { %>
			현금구매
		<% } else { %>
			신용구매
		<% } %>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%= purchaseVO.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%= purchaseVO.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%= purchaseVO.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%= purchaseVO.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%= purchaseVO.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>