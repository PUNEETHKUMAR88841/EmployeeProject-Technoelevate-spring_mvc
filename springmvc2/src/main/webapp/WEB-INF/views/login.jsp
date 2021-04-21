<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
String msg = (String) request.getAttribute("errMsg");
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
	<h1>Welcome to Login</h1>
	<fieldset>
		<legend>Login Page</legend>
		<form action="./empLogin" method="post">

			<table>
				<tr>
					<td>userid</td>
					<td>:</td>
					<td><input type="number" name="id" required="required">
				</tr>
				<tr>
					<td>password</td>
					<td>:</td>
					<td><input type="password" name="password" required="required"></td>
				</tr>

				<tr>
					<td><input type="submit" value="login"></td>
				</tr>
			</table>

		</form>
	</fieldset>
</body>
</html>