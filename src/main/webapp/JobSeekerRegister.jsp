<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<title>Job Seeker Signup</title>
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body style="background: #E9ECEB;">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<section class="vh-100">

    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-md-8 col-lg-6 col-xl-4">
    	<h3 class="text-center mb-4" style="color:#424C49;">Job Seeker Signup</h3>
         <c:set var="isError" value="${fn:startsWith(sessionScope.feedbackMessage, 'operation')}" />

    	<c:choose>
        <c:when test="${isError}">
        <div class="alert alert-danger" role="alert">
        		${sessionScope.feedbackMessage}
    	</div>
    		<%
        	session.removeAttribute("feedbackMessage");
    		%>
        </c:when>
         <c:when test="${isError == false && !empty sessionScope.feedbackMessage }">
         <div class="alert alert-success" role="alert">
        		${sessionScope.feedbackMessage}
    	</div>
    		<%
        	session.removeAttribute("feedbackMessage");
    		%>
        </c:when>
    	</c:choose>
		<br/>
        <form action="JobSeekerManager" method="POST">
          <!-- Username input -->
          <div class="form-outline mb-4">
            <input style="background: #F4F6F5" type="text" name="username" id="username" class="form-control form-control-lg"
              placeholder="Enter the username" required />
          </div>

          <!-- Password input -->
          <div class="form-outline mb-3">
            <input style="background: #F4F6F5" type="password" id="password" name="password" class="form-control form-control-lg"
              placeholder="Enter password" required />
          </div>
          
           <div class="form-outline mb-3">
            <input style="background: #F4F6F5" type="text" id="name" name="name" class="form-control form-control-lg"
              placeholder="Enter name" />
          </div>
          
           <div class="form-outline mb-3">
            <input style="background: #F4F6F5" type="email" id="email" name="email" class="form-control form-control-lg"
              placeholder="Enter email" required />
          </div>
          	
          	<input type="hidden" name="action" value="register" />    

          <div class="text-center mt-4 pt-2">
            <button type="submit" class="btn btn-primary btn-lg" style="background: #5C7066; border:none; width: 120px"
              style="padding-left: 2.5rem; padding-right: 2.5rem;">Register</button>
          </div>

        </form>
      </div>
    </div>

</section>
</body>
</html>