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

		아이디 : <input type="text" name="userId"><br /><br /> 
		이 름 : <input type="text" name="userName"><br /> <br /> 
		<input type="submit" value="전송" />
		<input type="reset" value="취소"/>

	</form>
	<br />
	<br /> 아이디 : ${user.userId}
	<br /> 이 름 : ${user.userName}
	<br />

</body>
</html>