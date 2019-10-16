<%-- 
    Document   : create-manager
    Created on : Oct 15, 2019, 8:43:46 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Sign up - Manager</title>
    </head>
    <body background="./background.jpg">
        <%@include file="manager-header-form.html" %>
        <%@include file="scripts.html"%>
        
        <script type="text/javascript">
            var user = "<%="Signed in as: "+ session.getAttribute("User")%>";
                $(document).ready(function () {
                    $('#dropdownMenu2').text(user);
                });
        </script>
        
        <form action="UserRegisterController" method="GET">
            <div class="card border-dark mb-3" id="card" >
                <div class="card-body">
                    <div> 
                        <h1 class="title">Sign up</h1>
                    </div>
                    <div></div>
                    <div class="form-group">
                        <label for="username">Username: *</label>
                        <input class="form-control" name="username" placeholder="Ingrese Username">
                    </div>    
                    <div class="form-group">
                        <label for="password">Password: *</label>
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div> 
                        <button type="submit" class="btn btn-dark">Crear</button>
                </div>  
            </div>  
        </form>  
        
        <c:if test="${requestScope['error'] != null}">
            <script type="text/javascript">
                $(document).ready(function () {
                    $('#InfoModal').modal('show');
                });
            </script>
        </c:if>
        <div class="modal fade" id="InfoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Error</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Este usuario ya existe, debe elegir uno nuevo.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
            
    </body>
</html>
