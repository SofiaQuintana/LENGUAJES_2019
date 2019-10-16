<%-- 
    Document   : manager-home
    Created on : Oct 5, 2019, 1:05:04 AM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Home</title>
    </head>
    <body background="./background.jpg">
        <%@include file="manager-header-form.html"%>                      
        <%@include file="scripts.html"%> 

        <script type="text/javascript">
            var user = "<%="Signed in as: "+ session.getAttribute("User")%>";
                $(document).ready(function () {
                    $('#dropdownMenu2').text(user);
                });
        </script>
        
        <div class="card border-dark mb-3" id="card">
            <div class="card-body">
                <div> 
                    <h1 class="title">Revistas pendientes de asignacion de costos</h1>
                </div>
                <div></div>   
                
                    
                        <c:forEach var="magazine" items="${sessionScope.magazineList}">
                            <form action="ChargesServlet?charge=${magazine.magazineId}" method="POST"> 
                                <div class="card text-white bg-dark mb-3" id="magazine-card">
                                    <div class="row no-gutters">
                                        <div class="col-md-4">
                                            <img src="./pdf.png" class="card-img" alt="PDF">
                                        </div>
                                        <div class="col-md-8">
                                            <div class="card-body">
                                                <h4 class="card-title">${magazine.name}</h4>
                                                <p class="card-text">${magazine.description}</p>
                                                <a href="#" class="card-title">${magazine.autor}</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer bg-dark mb-3">
                                        <input id="button" type="submit" class="btn btn-outline-light" value="Edit Charges">
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
            </div>
        </div>
    </body>
</html>
