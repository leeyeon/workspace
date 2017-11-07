<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!--  JSTL 사용 :  taglib 지시자 설정-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- :: JSTL c:set : Bean 생성 ObjectScope저장 -->
<c:set var="client" value="<%= new jw09.Client() %>" scope="session" />



<h3># EL 이용 ObjectScope 에  저장된 Bean 접근 </h3>
	1.  session ObjectScope 저장된 client 객체의 name : ${ sessionScope.client.name }<br/>
	2.  session ObjectScope 저장된 client 객체의 addr : ${ client.addr }<br/>
	3.  session ObjectScope 저장된 client 객체의 age  : ${ client.age }<br/>
	4.  session ObjectScope 저장된 client 객체의 info 배열은 empty : ${ empty client.info }<br/>
	5.  session ObjectScope 저장된 client 객체의 info 배열 각각의 value<br/>
	5.1 info 배열의 index 0 value : ${ client.info[0] }<br/>
	5.2 info 배열의 index 1 value : ${ client.info[1] }<br/><hr/>


<h3># 스크립트렛 이용 ObjectScope 에 저장된 Bean 접근</h3>
<%
	jw09.Client c = (jw09.Client)session.getAttribute("client");
	out.println("이름: "+c.getName()+"<br/>");
	out.println("주소: "+c.getAddr());
%>
<br/><hr/>


 
<!-- :: JSTL : session ObjectScope 저장된 client 을 test 로 다시 저장(?)  -->      			
<c:set var="test" value="${client}" scope="session" />


<h3># EL 이용 ObjectScope 에  저장된 Bean 접근 </h3>
	1.  session ObjectScope 저장된 client 객체의 name : ${ client.name }<br/>
	1.  session ObjectScope 저장된 test 객체의 name : ${ test.name }<br/><hr/>
	
	
<h3> :: ObjectScope 저장된 test Bean setter Method 호출(홍길동을 이순신으로 변경)<br/>
          :: Call By Reference 고려하면....</h3>
<c:set target="${test}" property="name" value="이순신"/>
	2.  session ObjectScope 저장된 client 객체의 name : ${ client.name } <br/>
	2.  session ObjectScope 저장된 test 객체의 name : ${ test.name }<br/><hr/>


<h3> :: JSTL c:remove ObjectScope 저장된 test remove </h3>	
	remove전 client객체는 empty : ${ empty client } <br/>
	Session에 저장된 client 객체 remove <br/>
	<c:remove var="client" scope="session"/>
	remove후 client 객체는 empty : ${ empty client } <br/><hr/>
	remove 하지 않은  test 객체는 empty : ${ empty test } <br/><hr/>
	
	
	
	