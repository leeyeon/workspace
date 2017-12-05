<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/purchase/listPurchase" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">
		��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">�ֹ� ����(����)</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">���ų�¥</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:forEach var="purchase" items="${list}">
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/purchase/getPurchase?tranNo=${purchase.tranNo}">
			${list.indexOf(purchase) + 1}</a>
		</td>
		<td></td>
		<td align="center">
			<a href="/product/getProduct?prodNo=${purchase.purchaseProd.prodNo}&menu=${menu}">
			${purchase.purchaseProd.prodName}
		</a>
		</td>
		<td></td>
		<td align="left"><fmt:formatNumber value="${purchase.price}" pattern="#,###"/>�� ( ${purchase.amount} )</td>
		<td></td>
		<td align="left">
			<fmt:formatDate value="${purchase.orderDate}" dateStyle="long"/> </td>
		<td></td>
		<td align="left">
		
		<c:set var="state" value=""/>
		<c:set var="code" value="${purchase.tranCode.trim()}"/>
		<c:choose>
			<c:when test="${code eq 1}">
				<c:set var="state" value="���� �Ϸ� ����"/>
			</c:when>
			<c:when test="${code eq 2}">
				<c:set var="state" value="��� ��"/>
			</c:when>
			<c:when test="${code eq 3}">
				<c:set var="state" value="��� �Ϸ� ����"/>
			</c:when>
			<c:otherwise>
				<c:set var="state" value="�Ǹ� ��"/>
			</c:otherwise>
		</c:choose>

		���� ${state}�Դϴ�.
		</td>
		<td></td>
		<td align="left">
		
		<c:if test="${code eq 2 }">
			<a href="/purchase/updateTranCode?tranNo=${purchase.tranNo}&tranCode=3">���ǵ���</a>
		</c:if>
		</td>
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
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