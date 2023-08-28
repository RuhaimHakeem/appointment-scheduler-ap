<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<title>Admin Login</title>
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body style="background: #E9ECEB;">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<section class="vh-100">
	


    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-md-8 col-lg-6 col-xl-4">
    	<h3 class="text-center mb-4" style="color:#424C49;">Admin Login</h3>
            <p class="text-success">${feebackMessage}</p>
		<br/>
        <form>
          <!-- Username input -->
          <div class="form-outline mb-4">
            <input style="background: #F4F6F5" type="text" name="username" id="username" class="form-control form-control-lg"
              placeholder="Enter the username" />
          </div>

          <!-- Password input -->
          <div class="form-outline mb-3">
            <input style="background: #F4F6F5" type="password" id="password" name="password" class="form-control form-control-lg"
              placeholder="Enter password" />
          </div>

          <div class="text-center mt-4 pt-2">
            <button type="button" class="btn btn-primary btn-lg" style="background: #5C7066; border:none; width: 120px"
              style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
          </div>

        </form>
      </div>
    </div>

</section>
</body>
</html>