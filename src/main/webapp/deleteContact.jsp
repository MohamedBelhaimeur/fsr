<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete</title>
</head>
<form method="post" action="DeleteContactServlet">
		<table>
			<tr>
				<th><h2>Voici un premier exemple de composant web avec un servlet</h2></th>
				<tr>
					<td><i>id: <input type="text" name="id" size="25"></i></td>
				</tr>
				
				<tr>
					<td><input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset"></td>
				</tr>
			
		</table>
	</form>
</html>