<%@ page contentType="text/html;charset=euc-kr" %>

<!-- 
	1. �α��� ����Ȯ�� :: Work Flow Control
		�� ��α��� �ΰ��  : �α��� ȭ�� display 
	    �� �α����� ��� : �̹� �α��� �� ȸ������ display
	2. �α��� Ȯ����...
		�� �α����� ȸ���� session ObjectScope�� User��ü�� ����, active �� true
		�� User��ü�� ���� �� User�� active ���� true / false ������ �Ǵ�
====================================================
==> logon.jsp�� view  ���� ��  Work Flow Control (����� �ڵ�)�� ����
====================================================	
-->

<%
	//String userId = "���̵��Է�";
	//String password = "�н������Է�";
	//User user = (User)session.getAttribute("user");
%>

<html>
	<head><title>Logon Page</title></head>
	<body>
	[info] :: ${requestScope.message}
	<% //if(user == null ||  user.isActive() != true ) {%>
		<form  method="post" action="logonAction">

			��  ��  �� : <input type="text" name="userId" value="${(empty user.userId)? '���̵� �Է�':user.userId}"><br/><br/>
			�н����� : <input type="text" name="password" value="�н����� �Է�"><br/><br/>
			<input type="submit" name="submit" value="Enter"/>

		</form>
	 <%//}else{ %>
		<%--	<%= user.getUserId() %>���� �̹� �α��� �ϼ̽��ϴ�.  --%>
	<% //} %>

	</body>
</html>