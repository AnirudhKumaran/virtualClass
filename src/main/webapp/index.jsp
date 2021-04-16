<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Virtual Class | Login Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link href="./styles/signin.css" rel="stylesheet">
<link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/sign-in/">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body>
<div class="container-fluid">
            <!-- A fluid container that uses the full witdh -->

            <nav class="navbar navbar-expand-lg navbar-light bg-light mt-1">
                
                <a class="navbar-brand" href="#"><img style="max-width: 60px;max-height: 60px;" class="img-thumbnail" src="./images/icon.jpg" alt="Food Cart Icon">VirtualAcademy</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
              
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About Us</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#">Contact Us</a>
                    </li>
                  </ul>
                </div>
            </nav>
            <form action="UserLoginServlet" class="form-signin" style="height: 100%;" method="POST">
            	<br>
            	<div class="btn-group btn-group-toggle d-flex" data-toggle="buttons">
				  <label class="btn btn-secondary active">
				    <input type="radio" name="userType" id="option1" autocomplete="off" value="0" checked> Student
				  </label>
				  <label class="btn btn-secondary">
				    <input type="radio" name="userType" id="option2" value="1" autocomplete="off"> Staff
				  </label>
				  <label class="btn btn-secondary">
				    <input type="radio" name="userType" id="option3" value="2" autocomplete="off"> Admin
				  </label>
				</div> 
				<br>
                <center><img class="mb-4" src="./images/icon.jpg" alt="" width="196" height="196"></center>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input name="uid" type="text" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input name = "upass" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                <!---<div class="checkbox mb-3">
                  <label>
                    <input type="checkbox" value="remember-me"> Remember me
                  </label>
                </div>--->
                <button name="submit" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
			${message}
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