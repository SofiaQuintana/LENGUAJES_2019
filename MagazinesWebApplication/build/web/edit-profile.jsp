<%-- 
    Document   : edit-profile
    Created on : Oct 5, 2019, 12:53:02 AM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Edit Profile</title>
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
        
        <c:forEach var="user" items="${sessionScope.ProfileInformation}">
            <form action="ProfileController" method="POST">
                <div class="card border-dark mb-3" id="card" >
                    <div class="card-body">
                        <div> 
                            <h1 class="title">Edit Profile</h1>
                        </div>
                        <div></div>
                        <div class="form-group">
                            <label for="name">First name: *</label>
                            <input class="form-control" id="name" name="name" value="${user.name}">
                        </div>
                        <div class="form-group">
                            <label for="lastname">Last name: *</label>
                            <input class="form-control" id="lastname" name="lastName" value="${user.lastName}">
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <textarea name="description" class="form-control" rows="5">${user.description}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="preferences">Preferences: </label>
                            <textarea name="preferences" class="form-control" rows="3">${user.preferences}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="hobbies">Hobbies: </label>
                            <textarea name="hobbies" class="form-control" rows="3">${user.hobbies}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="topics">Favorite topics: </label>
                            <textarea name="topics" class="form-control" rows="3">${user.favoriteTopics}</textarea>
                        </div>
                        <button type="submit" class="btn btn-dark">Submit</button>
                    </div>
                </div>  
            </form>
        </c:forEach>
        
    </body>
</html>
