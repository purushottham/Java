<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<title>Intellasphere - Admin</title>
<link rel="stylesheet" href="<c:url value="/reports/css/style.css"/>" />
<link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css?family=Lato:400,400italic,700,700italic"/>" type='text/css' />
<link rel="shortcut icon" href="<c:url value="/reports/images/ES-Favicon.ico"/>" type="image/x-icon" />
</head>
<body>


	<div id="login-box">
        <div class="loginContainer">
<div class="adminLContainer"><img class="adminLogo" src="reports/images/admin_Logo.png" alt="intellasphere" /></div>
		<h3 class="loginTitle">Admin Login</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm' action="<c:url value='/login' />" method='POST'>

				<div class="form-group">
					<input type='text' name='username' class="login-input" value='' placeholder='USERNAME'/>
				</div>
				<div class="form-group">
					<input type='password' name='password' class="login-input" placeholder='PASSWORD' />
				</div>
				<div>
					<input name="submit" class="submit-btn" type="submit" value="submit" />
				</div>
			
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
</div>
	</div>
        <script type="text/javascript" src="<c:url value="/reports/scripts/vendor.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/reports/scripts/reports.js"/>"></script>
</body>
</html>