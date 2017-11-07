<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>상품 목록 조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
function fncGetList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}

function fncSetPriceList(priceOrderbyCode,currentPage) {
	document.getElementById("priceOrderbyCode").value = priceOrderbyCode;
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=${menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					<c:choose>
						<c:when test="${!empty menu && menu eq search}">
						 	상품 목록조회
						</c:when>
						<c:otherwise>
							상품 관리
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<!-- <option value="0" ${(!empty search && search.searchCondition eq '0')? "selected" : ""}>상품번호</option>  -->		
				<option value="1" ${(!empty search && search.searchCondition eq '1')? "selected" : ""}>상품명</option>
				<option value="2" ${(!empty search && search.searchCondition eq '2')? "selected" : ""}>상품가격</option>
			</select>
			<input type="text" name="searchKeyword" value="${search.searchKeyword}"
					 class="ct_input_g" style="width:200px; height:19px" />
		</td>		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetList('1')">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >
		전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="50">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="300">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="50">재고</td>
		<td class="ct_line02"></td>
		
		<td class="ct_list_b" width="200">가격
			<c:choose>
				<c:when test="${search.searchOrderbyPrice eq 0}">
					<a href="javascript:fncSetPriceList('1',${resultPage.currentPage});">▲</a>
				</c:when>
				<c:when test="${search.searchOrderbyPrice eq 1}">
					<a href="javascript:fncSetPriceList('0',${resultPage.currentPage});">▼</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:fncSetPriceList('1',${resultPage.currentPage});">▲</a>
				</c:otherwise>
			</c:choose>
		</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:forEach var="product" items="${list}">
		<tr class="ct_list_pop">
			<td align="center">${list.indexOf(product) + 1}</td>
			<td></td>
			<td align="left">
				<c:choose>
					<c:when test="${empty product.proTranCode || (!empty user && user.role eq 'admin' )}">
						<a href="/getProduct.do?prodNo=${product.prodNo}&menu=${menu}">${product.prodName}</a>
					</c:when>
					<c:otherwise>
						${product.prodName}
					</c:otherwise>
				</c:choose>
			</td>
			<td></td>
			<td align="left">개수</td>
			<td></td>
			<td align="left"><fmt:formatNumber value="${product.price}" pattern="#,###" /></td>
			<td></td>
			<td align="left">
			
			<c:if test="${empty product.proTranCode }">
				판매중
			</c:if>
			<c:if test="${!empty product.proTranCode }">
			<c:choose>
				<c:when test="${!empty user && user.role.trim() eq 'admin'}">
					<c:choose>
					<c:when test="${product.proTranCode.trim() eq '1'}">구매완료</c:when>
					<c:when test="${product.proTranCode.trim() eq '2'}">배송중</c:when>
					<c:otherwise>배송완료</c:otherwise>
					
					</c:choose>
					<c:if test="${product.proTranCode.trim() eq '1' && menu eq 'manage' }">
						<a href="/updateTranCodeByProd.do?prodNo=${product.prodNo}&tranCode=2"">배송하기</a>
					</c:if>
				</c:when>
				<c:otherwise>
					재고 없음
				</c:otherwise>
			</c:choose>
			
			</c:if>
			</td>	
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<input type="hidden" id="priceOrderbyCode" name="priceOrderbyCode" value=""/>
			
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
