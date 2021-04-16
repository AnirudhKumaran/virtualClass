<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.text.ParseException,java.util.HashMap,java.util.Map,com.virtualclass.database.StudentQuery" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Virtual Class | Form</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container-fluid">
	
		<nav class="navbar navbar-expand-lg navbar-light bg-light mt-1">
              
			<a class="navbar-brand" href="#"><img style="max-width: 60px;max-height: 60px;" class="img-thumbnail" src="./images/icon.jpg" alt="VirtualAcademy Icon">VirtualAcademy</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav ml-auto">
				<li class="nav-item">
		          <a class="nav-link" href="./adminboard.jsp">DashBoard</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="./logout">Logout</a>
		      </li>
		    </ul>
		  </div>
		</nav>
	
		<div>
			<%
			
				
				Map<String,String> studentData = new HashMap<>();
				StudentQuery sq = new StudentQuery();
				String uid = "";
				
				if (request.getParameterMap().containsKey("uid")) {
				
					uid = request.getParameter("uid").toString();
					studentData.putAll(sq.getStudentdetails(uid));	
				}
				
				int etype = -1,utype=-1;
				try {
					etype = Integer.parseInt(request.getParameter("etype"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				utype = Integer.parseInt(request.getParameter("utype"));
				
				String action = "";
				
				if(etype==1)
					action = "./formInput?rnum=6&&uid="+uid;
				else
					action = "./formInput?rnum=5";
					
			%>
			
			<form action="<%= action %>" method="post">
				<input name="utype" value="<%= utype %>" hidden />
				<input name="fname" value="<%= studentData.get("FNAME")!=null?studentData.get("FNAME"):"" %>" class="form-control my-2" type="text" placeholder="Name">
				<input name="uname" value="<%= studentData.get("LNAME")!=null?studentData.get("LNAME"):"" %>" class="form-control my-2" type="text" placeholder="User Name">
				<input name="uage" value="<%= studentData.get("UAGE")!=null?studentData.get("UAGE"):"" %>" class="form-control my-2" type="number" placeholder="Age">
				<textarea class="form-control" name="uaddr" rows="3" style="resize:none;" placeholder="Address"><%= studentData.get("UADDR")!=null?studentData.get("UADDR"):"" %></textarea>
				<input name="ucity" value="<%= studentData.get("UCITY")!=null?studentData.get("UCITY"):"" %>" class="form-control my-2" type="text" placeholder="City">
				<input name="ustate" value="<%= studentData.get("USTATE")!=null?studentData.get("USTATE"):"" %>" class="form-control my-2" type="text" placeholder="State">
				<input name="ucountry" value="<%= studentData.get("UCOUNTRY")!=null?studentData.get("UCOUNTRY"):"" %>" class="form-control my-2" type="text" placeholder="Country">
				<input name="upincode" value="<%= studentData.get("UPINCODE")!=null?studentData.get("UPINCODE"):"" %>" class="form-control my-2" type="text" placeholder="Pincode">
				<input name="umail" value="<%= studentData.get("UEMAIL")!=null?studentData.get("UEMAIL"):"" %>" class="form-control my-2" type="mail" placeholder="Mail">
				<input type="submit" value="Submit" />
			</form>
		</div>
	
	</div>
</body>
</html>