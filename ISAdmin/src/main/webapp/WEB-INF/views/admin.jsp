<%@ page language="java"
	import="java.util.*,java.net.*,javax.servlet.*,java.io.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IntellaSphere - Admin</title>
<link rel="stylesheet" href="<c:url value="/reports/css/style.css"/>" />
<link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css?family=Lato:400,400italic,700,700italic"/>" type='text/css' />
<link rel="shortcut icon" href="<c:url value="/reports/images/ES-Favicon.ico"/>" type="image/x-icon" />
</head>
<body>
<script type="text/javascript">
localStorage.setItem("adminSessionTimeout",new Date().getTime());
var adminDetails = {
    baseurl : "${baseUrl}",
    securityKey : "${secureKey}",
    logoutUrl : "/logout",
    userInputName : "${_csrf.parameterName}",
    userInputValue : "${_csrf.token}",
    userName : "${pageContext.request.userPrincipal.name}"
}


console.log(adminDetails);
</script>

	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
   <div role="main">
                <div ng-include="'reports/modules/views/header.html'"></div>
                <section id="uiview" ui-view=""></section>
        </div>
        <script type="text/javascript" src="<c:url value="/reports/scripts/vendor.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/reports/scripts/reports.js"/>"></script>
</body>
</html>

