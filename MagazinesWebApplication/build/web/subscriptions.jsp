<%-- 
    Document   : subscriptions
    Created on : Oct 6, 2019, 8:37:06 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Subscriptions</title>
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
            
           <form action="SubscriberServlet" method="GET">
                <div class="card text-white bg-dark mb-3">
                    <div class="row no-gutters">
                        <input id="input" type="submit" class="btn btn-outline-light" value="Ver Revistas">                
                    </div>                                   
                </div>
            </form>
            
            <div class="card border-dark mb-3" id="card">
            <div class="card-body">
                <div> 
                    <h1 class="title">Subscriptions</h1>
                </div>
                <div></div>   
                    
                <c:forEach var="subscription" items="${requestScope.subscriptions}">
                            <form action="SubscriberServlet?r=${subscription.magazineId}" method="POST"> 
                                <div class="card text-white bg-dark mb-3" id="magazine-card">
                                    <div class="row no-gutters">
                                        <div class="col-md-4">
                                            <img src="./pdf.png" class="card-img" alt="PDF">
                                        </div>
                                        <div class="col-md-8">
                                            <div class="card-body">
                                                <h4 class="card-title">${subscription.name}</h4>
                                                <p class="card-text">${subscription.description}</p>
                                                <a href="#" class="card-title">${subscription.autor}</a>
                                                <h5></h5>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer bg-dark mb-3">
                                        <input id="button" type="submit" class="btn btn-outline-light" value="Ver Revista">
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
            </div>
        </div>
    </body>
</html>
