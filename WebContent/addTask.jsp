<%@ page language="java" 
	import="java.util.* , il.ac.shenkar.javaeeproject.model.*"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Add Task</h1>

<form action="../src/il.ac.shenkar.javaeeproject.controller/*" method="post">

TITLE: <input type="text" name="title">
<br />
BODY: <input type="text" name="taskBody" />
<br />
<input type="submit" value="Submit" />

</form>

</body>
</html>