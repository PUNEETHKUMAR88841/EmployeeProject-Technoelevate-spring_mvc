<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
<%
String msg = (String) request.getAttribute("msg");
String errMsg = (String) request.getAttribute("errMsg");
int id = (int) request.getAttribute("id");
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
		<form action="./update" method="post">
			<table>
				<tr>
					<td>Id</td>
					<td>: <%=id%></td>
					<td><input hidden="true" type="number" name="id"
						value="<%=id%>" required="required">
				</tr>
				<tr>
					<td>name</td>
					<td>:</td>
					<td><input type="text" name="name">
				</tr>
				<tr>
					<td>dob</td>
					<td>:</td>
					<td><input type="date" name="dob">
				</tr>
				<tr>
					<td>password</td>
					<td>:</td>
					<td><input type="password" name="password">
				</tr>
				<tr>
					<td><input type="submit" value="update"></td>
				</tr>
			</table>
		</form>
	</fieldset>

</body>
</html>