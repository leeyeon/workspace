<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false" %>

����: ${"ȫ�浿"} <br>
����: ${1234} <br><br>


${1+2} <br>
${"1"+2} <br>
<%--  ${"ȫ"3} --%>  <br>
${"1"1} <br>

${10>100? "10�� 100���� ũ�� true" : "10�� 100���� �۴� false"} <br><br>

${empty null} <br>
${empty " "} <br>
${empty ""}