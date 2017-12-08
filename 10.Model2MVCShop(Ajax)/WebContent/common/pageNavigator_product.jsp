<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
			
	</c:if>
	<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
			<a href="javascript:fncGetList('${search.searchOrderbyPrice}','${ resultPage.currentPage-1}')">�� ����</a>
	</c:if>
	
	<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
		<a href="javascript:fncGetList('${search.searchOrderbyPrice}','${ i }');">${ i }</a>
	</c:forEach>
	
	<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
			
	</c:if>
	<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
			<a href="javascript:fncGetList('${search.searchOrderbyPrice}','${resultPage.endUnitPage+1}')">���� ��</a>
	</c:if>
	

<script type="text/javascript">
<!--
// �˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScrpt �̿� 
function fncGetList(searchOrderbyPrice, currentPage) {
	$("#currentPage").val(currentPage);
	$("#searchOrderbyPrice").val(searchOrderbyPrice);
	$("form").attr("method" , "POST").attr("action" , "/product/listProduct?menu=${menu}").submit();
}
//-->
</script>
