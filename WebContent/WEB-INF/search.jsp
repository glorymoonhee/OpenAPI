<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<c:set var="ctxpath" value="${pageContext.servletContext.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctxpath}/static/css/bootstrap.css">

<script type="text/javascript" src="${ctxpath}/static/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="${ctxpath}/static/js/bootstrap.js"></script>

<script type="text/javascript">
  $(document).ready(function ( e){
        console.log('잘 나왔다');	    
		 });

</script>

<script type="text/javascript"></script>

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
      <form class="navbar-form navbar-left" role="search" action="${ctxpath}/search" method="post">
        <div class="form-group" >
			 <input type="text" class="form-control" id="q" name="q" placeholder="검색어"/>
		</div>
		<div class="form-group">
		 <select class="form-control" id="" name="display">
		 	<option value ="10">10
		 	<option value ="30">30
		 	<option value ="50">50
		 </select>
		</div>
		 
		 <button type="submit" class="form-control btn btn-default">검색</button>
      </form>
      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div style="padding-top: 70px"></div>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12">
	     <c:if test="${not empty query}">
	     	<c:forEach var="q" items="${query}">
	       	<h3 class="label label-primary btn-lg">${q}</h3>
	     	</c:forEach>
	       	
	     </c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
		  
		 <c:if test="${not empty results}">
			<div class="page-header">
  				<h1><small>검색어</small>${keyword}</h1>
			</div>
			<div class="list-group" id="results">
			      <c:forEach var="item" items="${results.items}">
			      <!--
			      ${results} = request.getAttribute("results").getItems() != null
			                   session.getAttribute("results").getItems()
			                   servletContext.getAttribute(dddldldl).getImtes();   
			       -->
			       	<a class="list-group-item" href="${item.link}">
					    <p class="item-title"><h3>${item.title}</h3></p>
					    <p class="item-desc">${item.description}</p>
				    </a>
                  </c:forEach>  
			
			
			</div>
		 </c:if>
		 <c:if test="${empty results }">
		 <h2>검색어를 입력하세요</h2>
		 </c:if>		

		</div>
	</div>
</div>
</body>
</html>