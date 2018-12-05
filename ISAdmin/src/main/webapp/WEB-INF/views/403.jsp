<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IntellaSphere - Admin</title>
<link rel="stylesheet" href="<c:url value="/reports/css/style.css"/>" />
<link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css?family=Lato:400,400italic,700,700italic"/>" type='text/css' />
<link rel="shortcut icon" href="<c:url value="/reports/images/ES-Favicon.ico"/>" type="image/x-icon" />
</head>
<body>
<div class="deniedUserBlock">	
<img class="" src="reports/images/admin_Logo.png" alt="intellasphere" />
<h2>Access is denied</h2>
		  <h3>You do not have permission to access this page!</h3>

	<a class="submit-btn login-link" href="<c:url value="/login"/>">login</a>
</div>
</body>
</html>