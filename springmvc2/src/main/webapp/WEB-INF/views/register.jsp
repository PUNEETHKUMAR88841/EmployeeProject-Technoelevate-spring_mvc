<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ include file="header.jsp" %>
	<%
String msg = (String) request.getAttribute("msg");
	String errMsg = (String) request.getAttribute("errMsg");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	if (msg != null && !msg.isEmpty()) {
	%>
	<h1><%=msg%></h1>
	<%
	}
	%>
	<%
	if (errMsg != null && !errMsg.isEmpty()) {
	%>
	<h1><%=errMsg%></h1>
	<%
	}
	%>

	<fieldset>
		<form action="./add" method="post">
			<table>
				<tr>
					<td>Employee Id</td>
					<td>:</td>
					<td><input type="number" name="id" required="required">
				</tr>
				<tr>
					<td>Name</td>
					<td>:</td>
					<td><input type="text" name="name" required="required">
				</tr>
				<tr>
					<td>DOB</td>
					<td>:</td>
					<td><input type="date" name="dob" required="required">
				</tr>
				<tr>
					<td>Password</td>
					<td>:</td>
					<td><input type="password" name="password" required="required">
				</tr>
				<tr>
					<td><input type="submit" value="register"></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>