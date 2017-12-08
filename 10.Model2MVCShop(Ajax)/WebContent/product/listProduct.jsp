<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>��ǰ ��� ��ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<style>
.bubble {
    transform: scale(1);
    width: 120%;
    height: 120%;
    transition: all 2s;
}
</style>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

function fncGetList(searchOrderbyPrice, currentPage) {
	$("#currentPage").val(currentPage);
	$("#searchOrderbyPrice").val(searchOrderbyPrice);
	$("form").attr("method" , "POST").attr("action" , "/product/listProduct?menu=${menu}").submit();
}

	$(function() {
		
		// �˻����� �� tooltip		
		$(":text[name='searchKeyword']").on("keydown", function(e) {
			
			if(e.keyCode == 13) {
				fncGetList('','1');
			} else {
				
				$(this).autocomplete({
					source: function(request, response) {
					$.ajax({
						url : "/product/json/listProductName",
						method : "POST",
						data : {
							prodName : request.term
						},
						dataType : "json",
						success : function(serverData) {
							response(serverData);
						}
					});
					},
					minLength : 2
				});
			}
		});

		// ���� Scroll
		$(window).on("scroll", function() {
			if($(window).scrollTop() == ($(document).height() - $(window).height())) {
				dataLoading();
			}
		})
		
		var pageInfo = 1;
		function dataLoading() {
			pageInfo ++;
			//alert("pageInfo :: "+pageInfo);
			$.ajax({
				url : "/product/json/listProduct",
				method : "POST",
				contentType : "application/json; charset=UTF-8",
				data : JSON.stringify({
					currentPage : pageInfo
				}),
				dataType : "json",
				success : function(serverData) {
					//alert("ajax dataLoading Success...");

					var idx = 4*(pageInfo-1);
					
					$(serverData.list).each(function(index,data) {
						console.log(data.price);
						var html = '<tr class="ct_list_pop"><td align="center">'+(++idx)
						+'</td><td></td><c:if test="${!empty user && user.role.trim() eq \'admin\'}"><td align="center">'+data.prodNo+'</td><td></td></c:if><td align="left"><input type="hidden" value="'+data.prodNo+'" name="prodNo"/><img src = "/images/uploadFiles/'+data.fileName+'" onerror="this.src=\'/images/no_image.jpg\'" height="90px" width="100px" border="0" align="absmiddle" style="padding: 5px"/>&nbsp;'+data.prodName+'</td><td></td><td align="right">'+data.price+' ��</td><td></td><td align="center">'
						+((data.amount==0)? "ǰ��": data.amount+'&nbsp;��')+'</td><td></td><td align="center">'+data.regDate+'</td><td></td><tr><td colspan="11" bgcolor="D6D7D6" height="1"></td></tr>'
						
						$("#list").append(html);
					})
					
				},
				error:function(request,status,error){
				    alert("code:"+request.status+"\n"+
				    		"message:"+request.responseText+
				    		 "\n"+"error:"+error);
				   }
			});
		}

		/////////////////////////////////////////////////////////////////////////////////////
		
		$("td.ct_btn01:contains('�˻�')").bind("click", function() {
			fncGetList('','1');
		});
		
		var columnIndex = $("#list td:contains('��ǰ����')").index()+1;
		
		//ǰ�����ο� ���� ��ũ ����
		var arr = $("tr.ct_list_pop").index($("tr.ct_list_pop:contains('ǰ��')"));
		
		$("tr.ct_list_pop td:nth-child("+columnIndex+")").on("click", function() {
			
			//console.log(columnIndex);
			var index = $("tr.ct_list_pop td:nth-child("+columnIndex+")").index(this);
			console.log("Ŭ�� Index :: "+index);
			var prodNo = $($("input[name=prodNo]")[index]).val();
			console.log("��ǰ��ȣ :: "+prodNo);

			//�� �������� ǰ���� ���� ��
			if(arr<0) {
				self.location = "/product/getProduct?prodNo="+prodNo+"&menu=${menu}";
			} else {
				var position = true;
				$("tr.ct_list_pop:contains('ǰ��')").each(function(idx){
					//console.log("idx :: "+idx+"index :: "+(index-1));
					//console.log("�̰���?"+$("tr.ct_list_pop").index($($("tr.ct_list_pop:contains('ǰ��')")[idx])));
					if($("tr.ct_list_pop").index($($("tr.ct_list_pop:contains('ǰ��')")[idx])) == index) {
						position = false;
						//console.log("position :: "+position);
					}
				})
				if(position == true) {
					console.log("prodNo:: "+prodNo+" / menu = ${menu}");
					self.location = "/product/getProduct?prodNo="+prodNo+"&menu=${menu}";
				}
			}

		});
		
		$("tr.ct_list_pop img").hover(function() {
			$(this).addClass('bubble');
		}, function() {
			$(this).removeClass('bubble');
		});
		
	});

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm">

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
						<c:when test="${!empty menu && menu eq 'search'}">
						 	��ǰ ���
						</c:when>
						<c:otherwise>
							�Ǹ� ��ǰ ����
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
				<!-- <option value="0" ${(!empty search && search.searchCondition eq '0')? "selected" : ""}>��ǰ��ȣ</option>  -->		
				<option value="1" ${(!empty search && search.searchCondition eq '1')? "selected" : ""}>��ǰ��</option>
				<option value="2" ${(!empty search && search.searchCondition eq '2')? "selected" : ""}>��ǰ����</option>
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
						�˻�
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;"
	id="list">
	<tr>
		<td colspan="11" >
		��ü ${resultPage.totalCount} �Ǽ�, �߰� ��ǰ�� ��ũ�ѽ�ũ��~ </td>
	</tr>
	<tr>
		<td colspan="11" align="right">
		<a href="">��ǰ���</a>&nbsp;&nbsp;
		<a href="">����</a>&nbsp;&nbsp;
		<a href="">��ǰ����ϼ�</a>
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		
		<c:if test="${!empty user && user.role.trim() eq 'admin'}">
		<td class="ct_list_b" width="120">��ǰ��ȣ</td>
		<td class="ct_line02"></td>
		</c:if>
		
		<td class="ct_list_b" width="450">��ǰ����</td>
		<td class="ct_line02"></td>		
		<td class="ct_list_b" width="120">����
			<c:choose>
				<c:when test="${search.searchOrderbyPrice eq '0'}">
					<a href="javascript:fncGetList('1','1');">��</a>
				</c:when>
				<c:when test="${search.searchOrderbyPrice eq '1'}">
					<a href="javascript:fncGetList('0','1');">��</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:fncGetList('1',${resultPage.currentPage});">��</a>
				</c:otherwise>
			</c:choose>
		</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">��ǰ����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ǰ�����</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:forEach var="product" items="${list}">
		
		<tr class="ct_list_pop">
			<td align="center">${list.indexOf(product) + 1}</td>
			<td></td>
			<c:if test="${!empty user && user.role.trim() eq 'admin'}">
			<td align="center">${product.prodNo}</td>
			<td></td>
			</c:if>
			<td align="left">
			<input type="hidden" value="${product.prodNo}" name="prodNo"/>
				<img src = "/images/uploadFiles/${product.fileName}" onerror="this.src='/images/no_image.jpg'"
				height="90px" width="100px" border="0" align="absmiddle"
				style="padding: 5px"/>&nbsp;
				${product.prodName}
			</td>
			<td></td>
			<td align="right"><fmt:formatNumber value="${product.price}" pattern="#,###"/> ��</td>
			<td></td>
			<td align="center">
				<c:if test="${product.amount == 0}">
					ǰ��
				</c:if>	
				<c:if test="${product.amount != 0}">
					${product.amount}&nbsp;��
				</c:if>
			</td>
			<td></td>
			<td align="center">${product.regDate}</td>
			<td></td>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
</table>
<input type="hidden" id="currentPage" name="currentPage" value=""/>
</form>

</div>
</body>
</html>
