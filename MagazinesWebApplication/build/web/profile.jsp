<%-- 
    Document   : profile
    Created on : Oct 3, 2019, 11:28:10 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Profile</title>
    </head>
    <body background="./background.jpg">
        <%@include file="scripts.html"%> 

        <c:if test="${Type == 1}">
            <%@include file="subscriptor-header.html"%>               
        </c:if>
        <c:if test="${Type == 2}">
            <%@include file="editor-header-form.html"%>
        </c:if>        
        <c:if test="${Type == 3}">
            <%@include file="manager-header-form.html"%>               
        </c:if>
        <script type="text/javascript">
            var user = "<%="Signed in as: "+ session.getAttribute("User")%>";
                $(document).ready(function () {
                    $('#dropdownMenu2').text(user);
                });
        </script>

        <c:forEach var="profile" items="${sessionScope.ProfileInformation}">
            <div class="card text-white bg-dark mb-3" id="profile">
                <img src="http://localhost:8080/static/${profile.username}/${profile.photoUrl}" class="card-img" alt="Profile Photo" id="profile-image">
                <div class="card-body" id="profile-body">
                    <h3 class="card-title">${profile.name} ${profile.lastName}</h3>
                    <p class="card-text">Fecha de nacimiento: ${profile.bornDate}</p>
                    <p class="card-text">Genero:  ${profile.gender}</p>
                    <p class="card-text">Descripcion: ${profile.description}</p>
                    <p class="card-text">Gustos: ${profile.preferences}</p>
                    <p class="card-text">Temas de interes: ${profile.favoriteTopics}</p>
                    <p class="card-text">Hobbies: ${profile.hobbies}</p>                            
                </div>  
                <div class="card-footer bg-transparent ">
                    <a href="edit-profile.jsp" class="btn btn-outline-light">Editar</a>
                </div>
            </div>               
        </c:forEach>

    </body>
</html>
