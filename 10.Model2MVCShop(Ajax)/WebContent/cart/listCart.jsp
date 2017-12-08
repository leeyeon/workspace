<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>��ٱ���</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/lisCart.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">��ٱ���</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="7">
		��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="300">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">��ǰ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ٱ��� ��¥</td>

	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:forEach var="cart" items="${list}">
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/cart/getCart?cartNo=${cart.cartNo}">
				${list.indexOf(cart) + 1}
			</a>
		</td>
		<td></td>
		<td align="center">
			<a href="/product/getProduct?prodNo=${cart.prodNo}">
			${cart.prodName}</a>
		</td>
		<td></td>
		<td align="left">
			<fmt:formatNumber value="${cart.price}" pattern="#,###"/>��
		</td>
		<td></td>
		<td align="left">
			<fmt:formatDate value="${cart.cartDate}" dateStyle="long"/>
		</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="7" bgcolor="D6D7D6" height="1"></td>
	</tr>
	
	</c:forEach>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
		<jsp:include page="../common/pageNavigator.jsp"/>
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>