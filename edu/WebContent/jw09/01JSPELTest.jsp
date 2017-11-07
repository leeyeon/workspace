<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false" %>

문자: ${"홍길동"} <br>
숫자: ${1234} <br><br>


${1+2} <br>
${"1"+2} <br>
<%--  ${"홍"3} --%>  <br>
${"1"1} <br>

${10>100? "10이 100보다 크다 true" : "10이 100보다 작다 false"} <br><br>

${empty null} <br>
${empty " "} <br>
${empty ""}