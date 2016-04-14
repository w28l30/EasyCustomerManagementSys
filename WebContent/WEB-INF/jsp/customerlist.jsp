<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/myExample" prefix="my"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function gotopage(currentpage) {
	if (currentpage<1 || currentpage!=parseInt(currentpage) || currentpage>${pageBean.totalPage}) {
		alert("Please input an legal number");
		document.getElementById("pagenum").value = '';
	} else {
		var pagesize = document.getElementById("pagesize").value;
		window.location.href='${pageContext.request.contextPath}/CustomerListServlet?currentPage=' + currentpage + '&pageSize=' + pagesize;
	}
}

function changesize(pagesize, oldvalue) {
	if(pagesize<0 || pagesize!=parseInt(pagesize)){
		alert("Please input an positive number");
		document.getElementById("pagesize").value = oldvalue;
	}else{
		window.location.href = '${pageContext.request.contextPath}/CustomerListServlet?pageSize=' + pagesize;
	}
}

function del(id){
	if(window.confirm("Arae you sure to delete this item？？")){
		location.href='${pageContext.request.contextPath}/DeleteServlet?id=' + id;
	}
}
</script>
<title>Customer List</title>
</head>
<body style="text-align: center;">
	<table border="1" width="85%" align="center">
		<tr>
			<td>Name</td>
			<td>Gender</td>
			<td>Birthday</td>
			<td>Cellphone</td>
			<td>Email</td>
			<td>Preference</td>
			<td>Type</td>
			<td>Description</td>
			<td>Operation</td>
			<td>
		</tr>
		<c:forEach items="${requestScope.pageBean.list }" var="customer">
			<tr>
				<td>${customer.name }</td>
				<td>${customer.gender }</td>
				<td>${customer.birthday }</td>
				<td>${customer.cellphone }</td>
				<td>${customer.email }</td>
				<td>${my:sub(customer.preference) }</td>
				<td>${customer.type }</td>
				<td>${customer.description }</td>
				<td><a
					href="${pageContext.request.contextPath }/UpdateServlet?id=${customer.id}">Update</a>
					<a
					href="javascript:void(0)" onclick="del('${customer.id}')">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<br /> Total Records: [${pageBean.totalRecord }],
	<input type="text" id="pagesize" style="width: 30px"
		value="${pageBean.pageSize }"
		onchange="changesize(this.value,${pageBean.pageSize })" maxlength="2">
	records per page, Total Pages: [${pageBean.totalPage }], Current Page:
	[${pageBean.currentPage }] &nbsp;&nbsp;&nbsp;
	<a href="javascript:void(0)"
		onclick="gotopage(${pageBean.previousPage})">Previous</a>
	<c:forEach items="${pageBean.pageBar }" var="pageNum">
		<c:choose>
			<c:when test="${pageNum==pageBean.currentPage}">
				<font color='red'>${pageNum }</font>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0)" onclick="gotopage(${pageNum})">${pageNum }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<a href="javascript:void(0)" onclick="gotopage(${pageBean.nextPage})">Next</a>
	&nbsp;&nbsp;&nbsp;
	<input type="text" id="pagenum" style="width: 30px">
	<input type="button" value="go"
		onclick="gotopage(document.getElementById('pagenum').value)">
</body>
</html>