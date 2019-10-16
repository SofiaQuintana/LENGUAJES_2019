<%-- 
    Document   : subscriber-home
    Created on : Sep 24, 2019, 12:06:09 AM
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
        
        <%@include file="scripts.html"%> 
        
        <c:if test="${Type == 1}">
            <%@include file="subscriptor-header.html"%>               
        </c:if>
        <c:if test="${Type == 2}">
            <%@include file="editor-header-form.html"%>
        </c:if>        
       
        <script type="text/javascript">
            var user = "<%="Signed in as: "+ session.getAttribute("User")%>";
                $(document).ready(function () {
                    $('#dropdownMenu2').text(user);
                });
            </script>
                    
            <div class="card border-dark mb-3" id="card">
            <div class="card-body">
                <div> 
                    <h1 class="title">Available Magazines</h1>
                </div>
                <div></div>   
                    
                        <c:forEach var="magazine" items="${sessionScope.magazineList}">
                            <form action="SubscribeServlet?r=${magazine.magazineId}" method="POST"> 
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
                                                <h5></h5>
                                                <h5 class="card-title">Precio: $${magazine.price}</h5>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer bg-dark mb-3">
                                        <input id="button" type="submit" class="btn btn-outline-light" value="Suscribir">
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
            </div>
        </div>
    </body>
</html>

