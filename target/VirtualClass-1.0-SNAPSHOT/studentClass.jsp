<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.virtualclass.database.ClassQuery"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Virtual Academy | Student Class</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="./scripts/studentClass.js"></script>
<style type="text/css">
	.border{
		border: 1px solid blue;
		border-radius: 15px;
	}
	
		/* Fixed/sticky icon bar (vertically aligned 50% from the top of the screen) */
	.icon-bar {
	  position: fixed;
	  top: 50%;
	  -webkit-transform: translateY(-50%);
	  -ms-transform: translateY(-50%);
	  transform: translateY(-50%);
	}
	
	/* Style the icon bar links */
	.icon-bar a,#myBtn {
	  display: block;
	  text-align: center;
	  padding: 5px;
	  transition: all 0.3s ease;
	  color: white;
	  font-size: 20px;
	  pointer-events: auto;  
   		cursor: pointer;
	}
	
	/* Style the social media icons with color, if you want */
	.icon-bar a:hover {
	  background-color: #000;
	}
	
	.facebook {
	  background: #3B5998;
	  color: white;
	}
	
	.twitter {
	  background: #55ACEE;
	  color: white;
	}
	
	.google {
	  background: #dd4b39;
	  color: white;
	}
	
	.linkedin {
	  background: #007bb5;
	  color: white;
	}
	
	.youtube {
	  background: #bb0000;
	  color: white;
	}
	
	.icon{
		width:30px;
		height:30px;
	}
	
		.commentBox{
		width:auto;
		background-color:lightblue;
		padding: 2px 2px;
		margin: 2px 0px;
	}
	
	.commentBox #uname{
		font-size:16px;
		height:20px;
		font-weight:bold;
		margin: 2px 0px;
	}
	
	.commentBox #reply,#datime{
		font-size:12px;
		margin: 2px 0px;
	}
	
	#comments{
		height:200px;
		overflow-y:scroll;
		display:block;
	}
</style>
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
                      <a class="nav-link" href="./Home.jsp">Classes</a>
                  </li>
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
	
			<div class="row" style="height:500px;">
				<div class="col-8" style="height:500px;overflow-y:scroll;">
					<div class="icon-bar">
						<a class="facebook" onclick="loadAll()"><img class="icon" alt="" src="https://static.thenounproject.com/png/65965-200.png" /></a>
						<a class="google" onclick="loadDoubts()"><img class="icon" alt="" src="./images/doubts_icon.png" /></a>
						<a class="twitter" onclick="loadPost()"><img class="icon" alt="" src="./images/post_icon.png" /></a>
						<%
							String modalType = "";
							String uid = "";
							
							uid = session.getAttribute("VCUID").toString();
							int utype = Integer.parseInt(session.getAttribute("VCTYP").toString());
							if(utype==0){
								modalType = "#exampleModal3";	
							}else if(utype==1){
								modalType = "#exampleModal4";
							}
						
						%>
						<button id="myBtn" type="button" class="youtube" style="outline:none;border:none;" data-toggle="modal" data-target="<%= modalType %>">
				  			<img class="icon" alt="" src="https://img.icons8.com/cotton/2x/plus--v2.png" />
						</button>
						<a class="facebook" onclick="loadVideos()"><img class="icon" alt="" src="./images/video_icon.png" /></a>
					</div>
					<div class="justify-content-center px-5 py-2" id="allContents">
						<!-- All the posts gets loaded inside this div -->
					</div>
				
				</div>
				<div class="col-4">
					<%
						String cid = request.getParameter("cid");
						String cname = request.getParameter("cname");
						ClassQuery cq = new ClassQuery();
						String cdesc = cq.getClassDescription(cid);
					%>
					<div class="p-3">
						<h6>Class Title</h6>
						<p><%= cid %></p>
						<h6>CID : </h6><span><%= cname %></span>
						<br />
						<h6>Description : </h6>
						<p>
							<%= cdesc %>
						</p>
						<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal3">
						  Launch demo modal
						</button> -->
					</div>
				</div>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="exampleModal3" tabindex="-1" role="dialog" aria-labelledby="exampleModal3Label" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModal3Label">Ask Doubt ?</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <form action="./formInput?rnum=0&&cid=<%= cid+"&&uid="+uid+"&&cname="+cname %>" method="post">
			      <div class="modal-body">
			      	
			      		<textarea placeholder= "question" class="form-control mt-1" name="doubtQuestion" rows="3" style="resize:none;" required></textarea>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <button type="reset" class="btn btn-primary">Reset</button>
			        <button type="submit" class="btn btn-primary">Post</button>
			      </div>
			      </form>
			    </div>
			  </div>
			</div>
			
			<!-- Modal for teachers -->
			<div class="modal fade" id="exampleModal4" tabindex="-1" role="dialog" aria-labelledby="exampleModal4Label" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModal4Label">Add Post / Video </h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <div>
			        	<button style="float:left;disply:inline;margin-left:5px;" onclick="changeView(0)" type="button" class="btn btn-primary btn-sm"> + Post </button>
			        	<button style="float:left;disply:inline;margin-left:5px;" onclick="changeView(1)" type="button" class="btn btn-primary btn-sm"> + Video </button>
			        </div>
			        <br><br>
			        <div id="postForm" style="clear:both;">
			        	<form action="./formInput?rnum=2&&cid=<%= cid+"&&uid="+uid+"&&cname="+cname %>" method="post" style="display:block;">
			        		<input placeholder="Title" class="form-control" type="text" name="pTitle" required />
			        		<textarea placeholder="Content" class="form-control mt-2" name="pContent" rows="3" style="resize:none;" required></textarea>
			        		<button type="submit" class="btn btn-success mt-2">Upload Post</button>
			        	</form>
			        </div>
			        <div id="videoForm" style="display:none;">
			        	<form action="./formInput?rnum=3&&cid=<%= cid+"&&uid="+uid+"&&cname="+cname %>" method="post" enctype="multipart/form-data">
				        	<input placeholder="Title" class="form-control" type="text" name="vtitle" required />
				        	<textarea placeholder="Content" class="form-control mt-2" name="vcontent" rows="3" style="resize:none; required"></textarea>
				        	<input type="file" class="form-control-file mt-2" name="videoFile" required />
				        	<button type="submit" class="btn btn-success mt-2">Upload Video</button>
			        	</form>
			        </div>
			        <script>
			        	//Teacher add post form
			        	function changeView(viewType){
			        		if(viewType==0){
			        			document.getElementById("postForm").style.display = "block";
			        			document.getElementById("videoForm").style.display = "none";
			        		}
			        		if(viewType==1){
			        			document.getElementById("postForm").style.display = "none";
			        			document.getElementById("videoForm").style.display = "block";
			        		}
			        	}
			        </script>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
	</div>
	<script>
		var cid = '<%= cid %>';
		var uid = '<%= session.getAttribute("VCUID")%>';
		var utype = <%= utype %>
		var cname = '<%= cname %>';
		//var replyBox = "";

		//console.log("CID : ",cid," UID : ",uid);
		var contentHolder = document.getElementById("allContents");
		//renderUsers();
		//renderPosts();
		//renderVideos();
		getMethod('./selectquery?rnum=2&&cid=CID-000001',2);
		getMethod('./selectquery?rnum=3&&cid=CID-000001',3);
		getMethod('./selectquery?rnum=4&&cid=CID-000001',4);
		dataConfirmation();
	</script>
</body>
</html>