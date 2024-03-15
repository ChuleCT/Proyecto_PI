<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<head>
<meta charset="utf-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Registro.css" />
 <title>Check Order</title>

</head>

<body>
	<header>
		<h1> ${requestScope.CheckType} Order </h1>
	</header>
	<div class="centeredcontainer sizereduced">
		<ul>
			<li>Customer name: ${order.name}</li>
			<li>Customer email: ${order.email}</li>
			<li>Customer phone: ${order.tel}</li>
			<li>Size: ${order.size}</li>
			<li>Type: ${order.type}</li>
			<li>Delivery time: ${order.delivery}</li>
			<li>Comments: ${order.comments}</li>		
		</ul>
		<form method="POST" action="?">
			<div class="divbutton">
				<input id="button" class="buttonstyle" type="submit" value="${requestScope.CheckType} Order"/>
			</div>		
		</form>
	</div>	
</body>
</html>


