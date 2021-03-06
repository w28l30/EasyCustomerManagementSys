<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Customer</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/ShowCalendar.js"></script>
    	<script type="text/javascript">
		function makepre(){
			var pres = document.getElementsByName("pre");
			var preference = "";
			for(var i=0;i<pres.length;i++){
				var input = pres[i];
				if(input.checked==true){
					preference = preference + input.value + ",";
				}
			}
			preference = preference.substr(0,preference.length-1);
			
			var form = document.getElementById("form");
			var input = document.createElement("input");
			input.type = "hidden";
			input.name = "preference";
			input.value = preference;
			
			form.appendChild(input);
			return true;
		}
	</script>
</head>
<body style="text-align: center;">
	<form id="form" action="${pageContext.request.contextPath }/AddCustomerServlet"
		method="post" onsubmit="return makepre()">
		<table border="1" width="50%" align="center">
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><c:forEach var="gender" items="${genders}">
						<input name="gender" type="radio" value="${gender }">${gender }
				</c:forEach></td>
			</tr>
			<tr>
				<td>Birthday</td>
				<td><input type="text" name="birthday"
					onClick="showCalendar(this.id)" id="birthday"></td>
			</tr>
			<tr>
				<td>Cellphone</td>
				<td><input type="text" name="cellphone"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Preference</td>
				<td><c:forEach var="preference" items="${preferences}">
						<input name="pre" type="checkbox" value="${preference }">${preference }
				</c:forEach></td>
			</tr>
			<tr>
				<td>Type</td>
				<td><c:forEach var="type" items="${types}">
						<input name="type" type="radio" value="${type }">${type }
				</c:forEach></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><textarea rows="5" cols="60" name="description"></textarea></td>
			</tr>
			<tr>
				<td><input type="reset" value="reset"></td>
				<td><input type="submit" value="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>