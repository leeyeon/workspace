<%@ page contentType="text/html;charset=euc-kr"%>

<html>
<head>
<title>Test Page</title>
</head>
<body>

	<a href="/Spring14/user/getUser/user01">/Spring14/user/getUser/user01</a>
	<br />
	<br />

	<form method="Post" action="/Spring14/user/getUser/user01">

		���̵� : <input type="text" name="userId"><br /><br /> 
		�� �� : <input type="text" name="userName"><br /> <br /> 
		<input type="submit" value="����" />
		<input type="reset" value="���"/>

	</form>
	<br />
	<br /> ���̵� : ${user.userId}
	<br /> �� �� : ${user.userName}
	<br />

</body>
</html>