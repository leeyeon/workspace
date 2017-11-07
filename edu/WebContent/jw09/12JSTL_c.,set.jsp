<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!--  JSTL ��� :  taglib ������ ����-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- :: JSTL c:set : Bean ���� ObjectScope���� -->
<c:set var="client" value="<%= new jw09.Client() %>" scope="session" />



<h3># EL �̿� ObjectScope ��  ����� Bean ���� </h3>
	1.  session ObjectScope ����� client ��ü�� name : ${ sessionScope.client.name }<br/>
	2.  session ObjectScope ����� client ��ü�� addr : ${ client.addr }<br/>
	3.  session ObjectScope ����� client ��ü�� age  : ${ client.age }<br/>
	4.  session ObjectScope ����� client ��ü�� info �迭�� empty : ${ empty client.info }<br/>
	5.  session ObjectScope ����� client ��ü�� info �迭 ������ value<br/>
	5.1 info �迭�� index 0 value : ${ client.info[0] }<br/>
	5.2 info �迭�� index 1 value : ${ client.info[1] }<br/><hr/>


<h3># ��ũ��Ʈ�� �̿� ObjectScope �� ����� Bean ����</h3>
<%
	jw09.Client c = (jw09.Client)session.getAttribute("client");
	out.println("�̸�: "+c.getName()+"<br/>");
	out.println("�ּ�: "+c.getAddr());
%>
<br/><hr/>


 
<!-- :: JSTL : session ObjectScope ����� client �� test �� �ٽ� ����(?)  -->      			
<c:set var="test" value="${client}" scope="session" />


<h3># EL �̿� ObjectScope ��  ����� Bean ���� </h3>
	1.  session ObjectScope ����� client ��ü�� name : ${ client.name }<br/>
	1.  session ObjectScope ����� test ��ü�� name : ${ test.name }<br/><hr/>
	
	
<h3> :: ObjectScope ����� test Bean setter Method ȣ��(ȫ�浿�� �̼������� ����)<br/>
          :: Call By Reference �����ϸ�....</h3>
<c:set target="${test}" property="name" value="�̼���"/>
	2.  session ObjectScope ����� client ��ü�� name : ${ client.name } <br/>
	2.  session ObjectScope ����� test ��ü�� name : ${ test.name }<br/><hr/>


<h3> :: JSTL c:remove ObjectScope ����� test remove </h3>	
	remove�� client��ü�� empty : ${ empty client } <br/>
	Session�� ����� client ��ü remove <br/>
	<c:remove var="client" scope="session"/>
	remove�� client ��ü�� empty : ${ empty client } <br/><hr/>
	remove ���� ����  test ��ü�� empty : ${ empty test } <br/><hr/>
	
	
	
	