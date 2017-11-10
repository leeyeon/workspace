<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
			
	</c:if>
	<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
			<a href="javascript:fncGetList('${ resultPage.currentPage-1}')">�� ����</a>
	</c:if>
	
	<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
		<a href="javascript:fncGetList('${search.searchOrderbyPrice}','${ i }');" id="pageTemp">${ i }</a>
	</c:forEach>
	
	<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
			
	</c:if>
	<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
			<a href="javascript:fncGetList('${resultPage.endUnitPage+1}')">���� ��</a>
	</c:if>
	

<script type="text/javascript">
<!--
// �˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScrpt �̿� 
function fncGetList(priceOrderbyCode, currentPage) {
	document.getElementById("priceOrderbyCode").value = priceOrderbyCode;
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}

window.onload = function change() {
    document.getElementById("pageTemp").style.fontWeight = "bold";
}
//-->
</script>
