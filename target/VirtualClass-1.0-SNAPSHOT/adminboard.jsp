<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.virtualclass.database.StudentQuery,com.virtualclass.database.VideoQuery,com.virtualclass.model.Student,com.virtualclass.model.Video,java.util.List,java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Virtual Class Admin Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
	.icon{
		width:30px;
		height:30px;
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
		          <a class="nav-link" onclick="onstudent()">Student</a>
		      </li>
		      <li class="nav-item">
		          <a class="nav-link" onclick="onteacher()">Teachers</a>
		      </li>
		      <li class="nav-item">
		          <a class="nav-link" onclick="onVideo()">Videos</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="./logout">Logout</a>
		      </li>
		    </ul>
		  </div>
		</nav>
	
	<div class="w-100 p-2">
		<%
		
		StudentQuery sq = new StudentQuery();
		VideoQuery vq = new VideoQuery();
		List<Student> students = new ArrayList<Student>();
		List<Student> teachers = new ArrayList<Student>();
		
		List<Video> videos = new ArrayList<Video>();
		
		students.addAll(sq.getAllStudent(0));
		teachers.addAll(sq.getAllStudent(1));
		videos.addAll(vq.getAllVideos());
		
		String studentsHtml = "";
		String teachersHtml = "";
		String videosHtml = "";
		String subString = "";
		
		for(Video video:videos){
			
			String checked = "";
			
			if(video.getVstat()==1){
				checked = "<input type='checkbox' class='custom-control-input' id='"+video.getVid()+"' checked>";
			}else
				checked = "<input type='checkbox' class='custom-control-input' id='"+video.getVid()+"'>";			
			
			subString = "<div class='media shadow p-3 mb-5 bg-light rounded'>"
					+ "<img class='d-flex mr-3 icon' src='./images/video_icon.png' >"
					+ "<div class='media-body'>"
					+ "<h5 class='mt-0'>"+video.getVtitle()+"</h5>"
					+ "<p>"+video.getVcont()+"</p>"
					+ "<div class='d-flex justify-content-center'>"
					+ "<embed src='./videos/"+video.getVpath()+"' width='480px' height='360px' />"
					+ "</div>"
					+ "<p>Uploaded By : "+video.getUid()+"</p>"
					+ "<p>Upladed on : "+video.getpdt()+"</p>"
					+ "<div class='custom-control custom-switch'>"
			  		+ checked
			  		+ "<label class='custom-control-label' for='"+video.getVid()+"'>Approve Video</label>"
					+ "</div>"
			  		+ "</div>"
			  		+ "</div>";	
			videosHtml+=subString;
			  				
		}
		
		for(Student student:students){
			subString = "<tr>"
		      				+ "<th>"+student.getUid()+"</th>"
		      				+ "<td>"+student.getFname()+"</td>"
		      				+ "<td>"+student.getUemail()+"</td>"
		      				+ "<td>"+student.getUcity()+"</td>"
		      				+ "<td> <a href='./Form.jsp?etype=1&&utype=0&&uid="+student.getUid()+"'>EDIT</a>&nbsp;&nbsp;&nbsp;<a href='./selectquery?rnum=7&&uid="+student.getUid()+"'>DELETE</a> </td>"
		    			+ "</tr>";
		    studentsHtml+=subString;
		}
		
		for(Student teacher:teachers){
			subString = "<tr>"
		      				+ "<th>"+teacher.getUid()+"</th>"
		      				+ "<td>"+teacher.getFname()+"</td>"
		      				+ "<td>"+teacher.getUemail()+"</td>"
		      				+ "<td>"+teacher.getUcity()+"</td>"
		      				+ "<td> <a href='./Form.jsp?etype=1&&utype=1&&uid="+teacher.getUid()+"'>EDIT</a>&nbsp;&nbsp;&nbsp;<a href='./selectquery?rnum=7&&uid="+teacher.getUid()+"'>DELETE</a> </td>"
		    			+ "</tr>";
		    teachersHtml+=subString;
		}
		
		%>
		<div id="studentContent" style="display:block;">
			<button type="button" onclick="onAddbtn()" id="addBtn" class="btn btn-primary my-2">Add Student</button>
			<table class="table">
			  <thead>
			    <tr>
			      <th>UID</th>
			      <th>Name</th>
			      <th>Email</th>
			      <th>City</th>
			      <th>Operation</th>
			    </tr>
			  </thead>
			  <tbody id="tableBody">
			    
			  </tbody>
			</table>
		</div>
		
		<div id="videoContent" style="display:none;">
			<div> <h3 class="my-2">Videos Approval</h3></div>
			<hr/>
			<div id="videoList" class="px-5" style="height:400px;overflow-y:scroll;">
				
			</div>
		</div>
	</div></div>
	<script>
		var teacherHtml = "<%= teachersHtml %>";
		var studentsHtml = "<%= studentsHtml %>";
		var videosHtml = "<%= videosHtml %>";
		
		var studentView = document.getElementById("studentContent");
		var tableBody = document.getElementById("tableBody");
		var addBtn =  document.getElementById("addBtn");
		var videoContent = document.getElementById("videoContent");
		var videoList = document.getElementById("videoList");
		
		var screenChange = false;
		var teacherMode = false;
		var studentMode = true;
		
		//videoList.innerHTML = videosHtml;
		tableBody.innerHTML = "";
		tableBody.innerHTML = studentsHtml;
		addBtn.innerHTML = "Add Student";
		
		function onAddbtn(){
			
			if(teacherMode)
				location.replace("./Form.jsp?utype=1")
				
			if(studentMode)
				location.replace("./Form.jsp?utype=0")
		}
		
		function onteacher(){
			if(screenChange){
				screenChange = false;
				videoContent.style.display = "none";
				studentView.style.display = "block";	
			}
			teacherMode=true;
			studentMode=false;
			tableBody.innerHTML = "";
			tableBody.innerHTML = teacherHtml;
			addBtn.innerHTML = "Add Teacher";
		}
		
		function onstudent(){
			if(screenChange){
				screenChange = false;
				videoContent.style.display = "none";
				studentView.style.display = "block";	
			}
			studentMode=false;
			teacherMode=true;
			tableBody.innerHTML = "";
			tableBody.innerHTML = studentsHtml;
			addBtn.innerHTML = "Add Student";
		}
		
		function onVideo(){
			screenChange = true;
			studentView.style.display = "none";
			videoContent.style.display = "block";
			videoList.innerHTML = "";
			videoList.innerHTML = videosHtml;
		}
		
		$(document).on('change', '.custom-control', function (e) {
		    let test = e.target.checked;
			let value = e.target.id;
		    console.log(test,value);
			//updateItem(value,(test?1:0))
			$.post("./formInput?rnum=4",
			  {
			    vid: value,
			    approve: test
			  },
			  function(data, status){
			    console.log("Data: " + data + "\nStatus: " + status);
			  })
		});
		
		function enableMarkers(pid){
			document.getElementById(pid).checked = true;
		}
		
	</script>
</body>
</html>