<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GATEWAY</title>
<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!--  material design bootstrap stylesheet -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.5/css/mdb.min.css" />

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- font awesome -->
<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<!-- Bootstrap 4.0 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<c:set	value="${pageContext.request.contextPath}/" 
		var="root" scope="request"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.js"></script>

<c:set value="${pageContext.request.contextPath}/" 
	var="root" scope="request"/>
<script src="${root}resources/js/monitoring.js"></script>

<script>
$(function(){
	$('#monitoring').monitoring();
});
</script>
</head>

<body>
	<div class="container">
		<h1>Gateway</h1>
		<hr>
		
		<div id="monitoring" class="row">
		</div>
	</div>
</body>

</html>
<!-- material design bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.5/js/mdb.min.js"></script>

