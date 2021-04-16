<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%@ page import = "java.util.HashMap,java.util.Map,com.virtualclass.database.StudentQuery,com.virtualclass.database.ClassQuery,com.virtualclass.model.Classes,java.util.ArrayList,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<title>Virtual Academy | Home Page</title>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
	.logo_icon{
		width:90px;
		height:90px;
	}
	
	.class_card{
		margin:5px;
		border:1px solid lightblue;
		border-radius:15px;
		padding:5px 0px;
	}

	.newsTitle{
		font-weight: bolder;
		font-size: large;
	}
	
	.newsContent{
		font-size:small;
	}

	.classImgdiv{
		padding-top:15px;
		width:100%;
		padding-left:55px;
		padding-right:55px;
	}

	.container{
		padding:20px;
		border:dotted 1px;
		white-space:nowrap;
		overflow-x:auto;
	}

	.box{
		width:200px;
		height:180px;
		border:1px skyblue solid;
		background-color: white;
		border-radius:15px;
		margin:10px;
		display:inline-block
	}

	.newsBody{
		height:400px;
		overflow-y:scroll;
		overflow-x:none;
	}

	.newsCard{
		margin:2px;
		height:100px;
		border:1px solid orange;
		padding: 2px 5px;
		border-radius:15px;
	}
</style>
</head>
<body>
<div class="container-fluid">
            <!-- A fluid container that uses the full witdh -->

			<%
							
				Map<String,String> studentData = new HashMap<>();
				StudentQuery sq = new StudentQuery();
				ClassQuery cq = new ClassQuery();
				String uid = session.getAttribute("VCUID").toString();
				studentData.putAll(sq.getStudentdetails(uid));
			
			%>

            <nav class="navbar navbar-expand-lg navbar-light bg-light mt-1">
                
                <a class="navbar-brand" href="#"><img style="max-width: 60px;max-height: 60px;" class="img-thumbnail" src="./images/icon.jpg" alt="VirtualAcademy Icon">VirtualAcademy</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
              
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact Us</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About Us</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="./logout">Logout</a>
                    </li>
                  </ul>
                </div>
            </nav>
           <!-- <h1>Hello ${usernmae} </h1> -->
			<div class="row" style="height:400px;">
			  <div class="col-8">
				<h3>Classes</h3>
				<hr />
			  	<div class="container">
			  		
			  		<%		  				  		
			  			List<Classes> classes = new ArrayList<Classes>();
			  			classes.addAll(cq.particularStudent(uid));
			  			
			  			%>
			  			
			  			<%
			  				for(Classes c:classes){
			  			%>
			  				<div class="box"><a href="<%= "./studentClass.jsp?cid="+c.getCid()+"&&cname="+c.getCname() %>">
				  				<div class="classImgdiv">
				  					<img class="rounded-circle logo_icon" src="https://image.freepik.com/free-vector/isometric-classroom-concept_52683-41860.jpg" alt="Circle image">
								</div>
								<div class="mt-2 text-center">
									<div> <span><%= c.getCname() %></span> </div>
									<div> <span><%= c.getCid() %></span> </div>
								</div>
							</a></div>	

					<% } %>

				</div>
				<div class="row mt-2" style="height:200px;">
					<div class="col d-flex justify-content-center" style="padding:25px 0px;">
						<% 
						
							int utype = Integer.parseInt(session.getAttribute("VCTYP").toString());
							String imgType = "";
							
							if(utype==0){
								imgType = "https://image.flaticon.com/icons/png/512/194/194931.png";	
							}else if(utype==1){
								imgType = "https://poly.ac.mu/wp-content/uploads/2020/04/teaching-vector-png-.png";
							}
						
						%>
						<img style="width:50%;height:150px;" class="rounded-circle" src="<% out.print(imgType); %>" alt="Circle image">
					</div>
					<div class="col py-2" style="height:200px;overflow-y: scroll;">
						<table>
							<tr>
								<td>UID : </td>
								<td><%= studentData.get("UID") %></td>
							</tr>
							<tr>
								<td>Name : </td>
								<td><%= studentData.get("FNAME") %></td>
							</tr>
							<tr>
								<td>Age : </td>
								<td><%= studentData.get("UAGE") %></td>
							</tr>
							<tr>
								<td>Address : </td>
								<td><%= studentData.get("UADDR") %></td>
							</tr>
							<tr>
								<td>City : </td>
								<td><%= studentData.get("UCITY") %></td>
							</tr>
							<tr>
								<td>State : </td>
								<td><%= studentData.get("USTATE") %></td>
							</tr>
							<tr>
								<td>Country : </td>
								<td><%= studentData.get("UCOUNTRY") %></td>
							</tr>
							<tr>
								<td>Pincode : </td>
								<td><%= studentData.get("UPINCODE") %></td>
							</tr>
							<tr>
								<td>Email : </td>
								<td><%= studentData.get("UEMAIL") %></td>
							</tr>
						</table>
					</div>
				</div>
			  </div>
			  <div class="col-4">
			  	<nav aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item active" aria-current="page">News</li>
				  </ol>
				</nav>
				<div class="newsBody">
				  	<div class="media">
  						<img class="d-flex mr-3" data-src="holder.js/64x64?theme=sky" alt="64x64" src="./images/news_icon.jpg" data-holder-rendered="true" style="width: 64px; height: 64px;">
  						<div class="media-body">
    						<h5 class="mt-0">Media heading</h5>
    						Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
  						</div>
  					</div>				  	
				</div>
			  </div>
			</div>
            <!-- Footer -->
            <!-- <footer class="bg-light text-center text-lg-start">
                <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.1)">
                  © 2021 Copyright:
                  <a class="text-dark" href="./index.html">FoodCart.com</a>
                </div>
            </footer> -->
            <!-- Footer -->
        </div>
</body>
</html>