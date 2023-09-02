<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en" data-bs-theme="auto">
  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.115.4">
    <title>Admin</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/dashboard/">
    

    

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .btn-bd-primary {
        --bd-violet-bg: #712cf9;
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #6528e0;
        --bs-btn-hover-border-color: #6528e0;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #5a23c8;
        --bs-btn-active-border-color: #5a23c8;
      }
      .bd-mode-toggle {
        z-index: 1500;
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
  </head>
  <body style="background: #E9ECEB;">
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<c:if test="${sessionScope.username != null && sessionScope.role == 'admin'}">
<jsp:include page="shared/navbar.jsp" />

<div class="container-fluid">
  <div class="row">
 
    
	<jsp:include page="shared/AdminSidebar.jsp" />
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2" style="color:#424C49;">Admin Dashboard</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
          <div class="btn-group me-2">
            <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
            <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
          </div>
          <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle d-flex align-items-center gap-1">
            <svg class="bi"><use xlink:href="#calendar3"/></svg>
            This week
          </button>
        </div>
      </div>
      
      <h4 style="color:#424C49;" class="mb-4 text-center">Total Number Of Appointments</h4>
      
      <div class="table-responsive text-center">
        <div style="display: flex; justify-content: center;">
        <table class="table table-striped w-75">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">This Week</th>
		      <th scope="col">This Month</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <td>${totalAppointmentsThisWeek}</td>
		      <td>${totalAppointmentsThisMonth}</td>
		    </tr>
  		</tbody>
	 </table>
	 </div>
      </div>
      
        <h4 style="color:#424C49;" class="mb-4 text-center">Total Number Of Users</h4>
      
      <div class="table-responsive text-center">
        <div style="display: flex; justify-content: center;">
        <table class="table table-striped w-75 justify-self-center">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Consultants</th>
		      <th scope="col">Job Seekers</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <td>${totalConsultants}</td>
		      <td>${totalJobSeekers}</td>
		    </tr>
  		</tbody>
	 </table>
	 </div>
      </div>
     
      
  <c:set var="appointment" value="${appointments}" />
  


      <h4 style="color:#424C49;" class="mb-4 text-center">Appointments</h4>
      <div style="display:flex; justify-content: center; ">
      
       <form action="AdminManager" method="GET">
		<div class="d-flex justify-content-center">
		
		<div class="form-outline mb-3 mr-4">
		    <select class="form-select bg-light mr-4" style="color:#868e93; border-color:#D3D3D3; padding: 12px;" aria-label="Default select example" name="consultantId">
		        <option value="" disabled selected>Select Consultant</option>
		        <c:forEach var="consultant" items="${consultants}">
		            <option value="${consultant.consultantId}">${consultant.name}</option>
		        </c:forEach>
		    </select>
		</div>
         
		<div class="d-flex justify-content-center">
		<div class="form-outline mb-3">
		    <select class="form-select bg-light mr-4" style="color:#868e93; border-color:#D3D3D3; padding: 12px;" aria-label="Default select example" name="jobSeekerId">
		        <option value="" disabled selected>Select Job Seeker</option>
		        <c:forEach var="jobSeeker" items="${jobSeekers}">
		            <option value="${jobSeeker.jobSeekerId}">${jobSeeker.name}</option>
		        </c:forEach>
		    </select>
		</div>
		
		</div>
       </div>
        <div class="text-center mt-4 pt-2">
            <button type="submit" id="submit-btn" class="btn btn-primary btn-lg mb-4" style="background: #5C7066; border:none; width: 120px"
              style="padding-left: 2.5rem; padding-right: 2.5rem;">Search</button>
          </div>
       </form>
      </div>
      
        
            
      <c:choose>
    	<c:when test="${empty appointments}">
         <div class="alert alert-warning" role="alert">
  			No record found!
	 	</div>
    	</c:when>
	</c:choose>
     

	<div class="table-responsive text-center">
        
        <table class="table table-striped">
		  <thead class="thead-dark">
		    <tr>
		     <th scope="col">#</th>
		      <th scope="col">Date</th>
		      <th scope="col">Time</th>
		      <th scope="col">Status</th>
		      <th scope="col">Consultant Name</th>
		      <th scope="col">Job Seeker Name</th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>
		   <tag:forEach var="appointment" items="${appointments}">
		    <tr>
		      <th>${appointment.appointmentId}</th>
		      <td>${appointment.date}</td>
		      <td>${appointment.time}</td>
		      <td>${appointment.status}</td>
		      <td>${appointment.consultantName}</td>
		      <td>${appointment.jobSeekerName}</td>
		    </tr>
		    </tag:forEach>
  		</tbody>
	 </table>
      </div> 
    </main>
  </div>
</div>
</c:if> 
<script>
    
    function submitDeleteForm(formId) {
        document.getElementById("deleteForm-" + formId).submit();
    }
    
    window.onload = function() {
        if (window.location.search) {
            var urlWithoutParams = window.location.href.split("?")[0];
            window.history.replaceState({}, document.title, urlWithoutParams);
        }
    };

</script>
</body>
</html>
