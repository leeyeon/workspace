<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

<!-- CDN(Content Delivery Network) ȣ��Ʈ ��� -->
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">

	//==> jQuery ���� �߰��� �κ�
	 $(function() {
		
		$(".Depth03").css("cursor","pointer");
		
		var loc = "";
		 
		//==> ����������ȸ Event ����ó���κ�
		//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	 	$(".Depth03:contains('����������ȸ')" ).bind("click" , function() {
	 		loc = $(".Depth03").index(this);
			$(window.parent.frames["rightFrame"].document.location).attr("href","/user/getUser?userId=${user.userId}");
		});
		
	 	$(".Depth03:contains('ȸ��������ȸ')" ).bind("click" , function() {
	 		loc = $(".Depth03").index(this);
	 		$(window.parent.frames["rightFrame"].document.location).attr("href","/user/listUser");
		});

		$(".Depth03:contains('�ǸŻ�ǰ���')").bind("click", function() {
			loc = $(".Depth03").index(this);
			$(window.parent.frames["rightFrame"].document.location).attr("href","/product/addProduct");
		});
		
		$(".Depth03:contains('�ǸŻ�ǰ����')").bind("click", function() {
			loc = $(".Depth03").index(this);
			$(window.parent.frames["rightFrame"].document.location).attr("href","/product/listProduct?menu=manage");
		});

		$(".Depth03:contains('��ǰ��۰���')").bind("click", function() {
			loc = $(".Depth03").index(this);
			$(window.parent.frames["rightFrame"].document.location).attr("href","/purchase/listSale?menu=sale");
		});
		
		$(".Depth03:contains('��ǰ�˻�')").bind("click", function() {
			//$(this).css("background-color","#BEAEAD");
			//console.log("��ǰ�˻�"+$(".Depth03").index(this));
			loc = $(".Depth03").index(this);
			$(window.parent.frames["rightFrame"].document.location).attr("href","/product/listProduct?menu=search");
		});
		
		$(".Depth03:contains('�����̷���ȸ')").bind("click", function() {
			loc = $(".Depth03").index(this);
			$(window.parent.frames["rightFrame"].document.location).attr("href","/purchase/listPurchase?menu=purchase");
		});

		$(".Depth03:contains('��ٱ�����ȸ')").bind("click", function() {
			loc = $(".Depth03").index(this);
			$(window.parent.frames["rightFrame"].document.location).attr("href","/cart/listCart?userId=${user.userId}");
		});
		
		// history
	 	$( ".Depth03:contains('�ֱ� �� ��ǰ')" ).on("click" , function() {
	 		loc = $(".Depth03").index(this);
	 		popWin = window.open("/product/history",
					"popWin",
					"left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
		});
		
		// file upload
		$(".Depth03:contains('MultiFileUpload')").bind("click", function() {
			loc = $(".Depth03").index(this);
			$(window.parent.frames["rightFrame"].document.location).attr("href","/test/multiFileUpload");
		});
		
		$(".Depth03").bind("click", function() { 
			$(".Depth03").css("background-color","");
			//console.log("��� ���...");
			$($(".Depth03").get(loc)).css("background-color","#BEAEAD");
		});
	});	
	 
</script>
</head>

<body background="/images/left/imgLeftBg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >

<table width="159" border="0" cellspacing="0" cellpadding="0">

<!--menu 01 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159" >	
			<tr>
				<c:if test="${ !empty user }">
					<tr>
						<td class="Depth03">
							<!-- ////////////////// jQuery Event ó���� ����� ///////////////////////// 
							<a href="/user/getUser?userId=${user.userId}" target="rightFrame">����������ȸ</a>	
							////////////////////////////////////////////////////////////////////////////////////////////////// -->
							����������ȸ
						</td>
					</tr>
				</c:if>
			
				<c:if test="${user.role == 'admin'}">
					<tr>
						<td class="Depth03" >
							<!-- ////////////////// jQuery Event ó���� ����� ///////////////////////// 
							<a href="/user/listUser" target="rightFrame">ȸ��������ȸ</a>	
							////////////////////////////////////////////////////////////////////////////////////////////////// -->
							ȸ��������ȸ
						</td>
					</tr>
				</c:if>
			
				<tr>
					<td class="DepthEnd">&nbsp;</td>
				</tr>
		</table>
	</td>
</tr>

<!--menu 02 line-->
<c:if test="${user.role == 'admin'}">
	<tr>
		<td valign="top"> 
			<table  border="0" cellspacing="0" cellpadding="0" width="159">
				<tr>
					<td class="Depth03">
						<!-- <a href="../product/addProductView.jsp;" target="rightFrame">�ǸŻ�ǰ���</a>  -->
						�ǸŻ�ǰ���
					</td>
				</tr>
				<tr>
					<td class="Depth03">
						<!-- <a href="/listProduct.do?menu=manage"  target="rightFrame">�ǸŻ�ǰ����</a> -->
						�ǸŻ�ǰ����
					</td>
				</tr>
				<tr>
					<td class="Depth03">
							��ǰ��۰���
					</td>
				</tr>
				<tr>
					<td class="DepthEnd">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</c:if>

<!--menu 03 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159">
			<tr>
				<td class="Depth03">
					��ǰ�˻�
				</td>
			</tr>
			
			<c:if test="${ !empty user && user.role == 'user'}">
			<tr>
				<td class="Depth03">
					�����̷���ȸ
				</td>
			</tr>

			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			<tr>
				<td class="Depth03">
					��ٱ�����ȸ
				</td>
			</tr>
			</c:if>	
				
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			<tr>
				<td class="Depth03">
					�ֱ� �� ��ǰ
				</td>
			</tr>
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			<tr>
				<td class="Depth03">
					MultiFileUpload
				</td>
			</tr>
		</table>
	</td>
</tr>

</table>

</body>
</html>
