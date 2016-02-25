<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	</head>
	<body>
		
			<div class="container">
				<div class="col-lg-1 col-offset-6 centered">
						<h1>Signup</h1><br>
						<form action="newSignup" method="POST">
							<div class="form-group">
								<label>Mail<input type="text" name="mail" class="form-row form-control" placeholder="example@gamil.com" required autofocus ></label>
							</div>
							<div class="form-group">
								<label>Password<input type="password" class="form-row form-control" name="pass" placeholder="Password" required></label>
							</div>
							<div class="form-group">
							<button type="submit" name=signUp class="login-btn btn btn-primary">SIGNUP</button>	
						</form>
						<br><br>
						<a href="login" class="signup-label">LOGIN</a>
				</div>
				<%
					String result = (String) request.getAttribute("RESULT");
					if(result != null) {
						out.println("<h5 style='color:#e75853'>" + result + "</h5>");	
					}
				%>
			</div><!-- end container -->
	</body>
</html>