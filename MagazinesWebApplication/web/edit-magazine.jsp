<%-- 
    Document   : edit-magazine
    Created on : Oct 6, 2019, 10:47:04 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Editar Permisos</title>
    </head>
    <body background="./background.jpg">
        <%@include file="editor-header-form.html"%>
        <%@include file="scripts.html"%> 
        
        <script type="text/javascript">
            var user = "<%="Signed in as: "+ session.getAttribute("User")%>";
                $(document).ready(function () {
                    $('#dropdownMenu2').text(user);
                });
        </script>
       
        <c:forEach var="magazine" items="${requestScope.editMagazine}">
            
            <form action="EditMagazineServlet" method="GET">
                <div class="card border-dark mb-3" id="card" >
                    <div class="card-body">
                        <div> 
                            <h1 class="title">Editar Permisos</h1>
                        </div>
                        <div></div>
                        <div class="form-group">
                            <label for="MagazineName">Name: *</label>
                            <input class="form-control" name="MagazineName" value="${magazine.name}" disabled>
                        </div>    
                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                            <div class="input-group-text">
                                <input type="checkbox" name="commentaries" aria-label="Checkbox for following text input">
                            </div>
                          </div>
                            <input type="text" class="form-control" aria-label="Text input with checkbox" value="Seleccione para bloquear 'Comentarios'." disabled>
                        </div>
                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                            <div class="input-group-text">
                                <input type="checkbox" name="likes" aria-label="Checkbox for following text input">
                            </div>
                          </div>
                            <input type="text" class="form-control" aria-label="Text input with checkbox" value="Seleccione para bloquear 'Me gusta'." disabled>
                        </div>
                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                            <div class="input-group-text">
                                <input type="checkbox" name="subscriptions" aria-label="Checkbox for following text input">
                            </div>
                          </div>
                            <input type="text" class="form-control" aria-label="Text input with checkbox" value="Seleccione para bloquear 'Suscripciones'." disabled>
                        </div>                       
                        <h1 class="title"></h1>
                        <button type="submit" class="btn btn-dark">Aceptar</button>
                    </div>
                </div>   
            </form>
        </c:forEach>
    </body>
</html>
