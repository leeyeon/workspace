<%@ page contentType="text/html;charset=euc-kr" %>

<%@ page import="java.util.*" %>

<h3>4. 색인(. , [] ) 연산자 사용</h3><p/>

<%-- 아래내용은 Servlet Controller 가  화면구성 위해 ObjectScope 에 저장 한 것 으로 생각 --%>
<%
	// 1. page ObjectScope 저장
	pageContext.setAttribute("abc",new String("page ObjectScope 저장된 문자열"));
	
	// 2. request ObjectScope 저장
	request.setAttribute("abc",new String("request ObjectScope 저장된 문자열"));
	
	//3.1 session ObjectScope  (size() ==0인)  ArrayList 저장
	session.setAttribute("abc",new ArrayList());
	
	// 3.2 session ObjectScope 값을 갖는 ArrayList 저장
	ArrayList arrayList =new ArrayList();
	arrayList.add( new String("ArrayList  저장된 문자열") );
	session.setAttribute("def",arrayList);
	
	// 4. Application ObjectScope 값을 갖는 HashMap 저장
	HashMap hashMap = new HashMap();
	hashMap.put("zzz",new String("HashMap 저장된 문자열"));
	application.setAttribute("abc",hashMap);
%>

<h5>1. page ObjectScope</h5>
${ pageScope.abc } = ${ pageScope["abc"] }


<h5>2. request ObjectScope,</h5>
${ request.abe } = { requestScopt["abc"] }

<h5>3. session ObjectScope : 저장된 ArrayList empty 유무 </h5>
${ empty sessionScope.abc } = ${ empty sessionScope["abc"] }

<h5>4.1 session n ObjectScope </h5>
${ empty sessionScope.def } = ${ empty sessionScope["def"] }

<h5>4.2 session ObjectScope </h5>
${ sessionScope.def[0] } = ${ sessionScope["def"][0] }

<h5>5.1 application ObjectScope : 저장된 HashMap empty 유무</h5>
${ empty applicationScope.abc } = ${ empty applicationScope["abc"] }

<h5>5.2 appliaction OjectSope</h5>
${ applicationScope.abc.zzz } = ${ applicationScope["abc"].zzz }

<br><br>

abc : ${ abc }