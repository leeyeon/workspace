<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model2.mvc.common.SearchVO"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	HashMap<String, Object> map = (HashMap<String, Object>) request.getAttribute("map");
	SearchVO searchVO = (SearchVO)request.getAttribute("searchVO");
	
	int total = 0;
	ArrayList<ProductVO> list = null;
	if(map != null) {
		total = ((Integer)map.get("count")).intValue();
		list = (ArrayList<ProductVO>)map.get("list");
	}
	
	int currentPage = searchVO.getPage();
	
	int totalPage = 0;
	if(total > 0) {
		totalPage = total / searchVO.getPageUnit();
		if (total%searchVO.getPageUnit() > 0) {
			totalPage += 1;
		}
	}
	
	int startPage = 1;
			//((searchVO.getPage() * searchVO.getPageUnit()) - searchVO.getPageUnit()+1)%3;
			
	int tempPage = 4;
	// tempPage = endPage;
	if(request.getAttribute("tempPage") != null) {
		tempPage = (Integer)request.getAttribute("tempPage");
		startPage = tempPage - 3;
		if(tempPage > totalPage) {
			tempPage = totalPage;
		}
	}
	
	String menu = (String)request.getParameter("menu");
	
	System.out.println("startPage :: "+startPage);
	System.out.println("tempPage :: "+tempPage);
	System.out.println("endPage :: "+totalPage);
	
	//System.out.println("searchVO.getSearchKeyword()"+searchVO.getSearchKeyword());
	//System.out.println("searchVO.getSearchCondition()"+searchVO.getSearchCondition());
	
	//Debug
	/*
	if("search".equals(menu)) {
		out.println("User ����Ʈ: "+ menu);
	} else {
		out.println("Manage ����Ʈ: "+ menu);
	}
	*/

%>

<html>
<head>
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetProductList(){
	document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=<%=menu%>" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
							<% if("search".equals(menu)) { %>
							��ǰ �����ȸ
							<% } else { %>
							��ǰ ����
							<% } %>
					
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
	<%
		if(searchVO.getSearchCondition() != null) {
	%>
		
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
		<%
			if(searchVO.getSearchCondition().equals("0")) {
		%>
				<option value="0" selected>��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
		<%
			} else if(searchVO.getSearchCondition().equals("1")) {
		%>
				<option value="0" >��ǰ��ȣ</option>
				<option value="1" selected>��ǰ��</option>
				<option value="2">��ǰ����</option>
		<%
			} else if(searchVO.getSearchCondition().equals("2")) {
		%>
				<option value="0" >��ǰ��ȣ</option>
				<option value="1" >��ǰ��</option>
				<option value="2" selected>��ǰ����</option>
		<%
			} else {
		%>
				<option value="0" selected>��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>
		<%
			}
		%>	
			</select>
			<input type="text" name="searchKeyword" value=""
					 class="ct_input_g" style="width:200px; height:19px" />
		</td>
	<% 
		} else {
	%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" selected>��ǰ��ȣ</option>
				<option value="1">��ǰ��</option>
				<option value="2">��ǰ����</option>			
			</select>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
	<%
		}
	%>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">�˻�</a>
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
		<td colspan="11" >��ü <%= total %> �Ǽ�, ���� <%= currentPage %> ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%
		int no = list.size();
		for (ProductVO vo : list) {
	%>
	<tr class="ct_list_pop">
		<td align="center"><%= no-- %></td>
		<td></td>
		<td align="left">
			<a href="/getProduct.do?prodNo=<%=vo.getProdNo()%>&menu=<%=menu%>"><%= vo.getProdName() %></a>
		</td>
		<td></td>
		<td align="left"><%= vo.getPrice() %></td>
		<td></td>
		<td align="left"><%= vo.getRegDate() %></td>
		<td></td>
		<td align="left">
		
		<%= vo.getProTranCode() %>
		
		<% if("manage".equals(menu) && "1".equals(vo.getProTranCode())) { %>
			<a href="/updateTranCodeByProd.do?prodNo=<%=vo.getProdNo()%>&tranCode=2">����ϱ�</a>
		<% } %>
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<%
		}
	%>	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<%
			for(int i=startPage; i < tempPage; i++) {
				if((i!=1) && (i==startPage)) {
		%>
				<a href="/listProduct.do?page=<%=i%>&menu=<%=menu%>
				&searchCondition=<%=searchVO.getSearchCondition()%>
				&searchKeyword=<%=searchVO.getSearchKeyword()%>">����</a>
		<%
				}
		%>
			<a href="/listProduct.do?page=<%=i%>&menu=<%=menu%>
				&searchCondition=<%=searchVO.getSearchCondition()%>
				&searchKeyword=<%=searchVO.getSearchKeyword()%>"><%= i %></a>
		<%
				if((i!=(totalPage-1))&&(i==tempPage-1)) {
		%>
				<a href="/listProduct.do?page=<%=i%>&menu=<%=menu%>
				&searchCondition=<%=searchVO.getSearchCondition()%>
				&searchKeyword=<%=searchVO.getSearchKeyword()%>
				&tempPage=<%=i+1%>">����</a>
		<%
				}
			}
		%>
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>
