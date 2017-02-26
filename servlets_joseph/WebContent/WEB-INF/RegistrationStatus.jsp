<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
  String validationData= null;
  String admin=null;
  String location=null;
  if(request.getAttribute("validationInfo")!=null){
	  validationData =  request.getAttribute("validationInfo").toString();
  }
  if(request.getAttribute("CITY")!=null){
	  location =  request.getAttribute("CITY").toString();
  }
  if(request.getAttribute("ADMIN-DETAILS")!=null){
	  admin =  request.getAttribute("ADMIN-DETAILS").toString();
  }
  
  out.println(validationData);
  out.println(location);
  out.println(admin);

%>

<a href="/bacth4-servlets-jdbc-program/login.jsp">Login</a><br/>


</body>
</html>