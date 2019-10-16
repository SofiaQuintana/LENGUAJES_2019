<%-- 
    Document   : edit-global-cost
    Created on : Oct 6, 2019, 4:31:54 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Set Costs</title>
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
        
        <div class="card-group" >
            <div class="card text-white bg-dark mb-3" id="first-card">
                <div class="card-body" >
                    <h5 class="card-title">Costos globales actuales</h5>
                    <div class="form-group">
                        <label>Costo Global: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-append">
                                <span class="input-group-text" id="basic-addon1">$</span>
                            </div>
                            <input class="form-control" value="${globalCost}" aria-label="payment" aria-describedby="basic-addon1" disabled>                
                        </div>
                    </div>
                    <div class="form-group">
                        <label >Costo global por dia: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-append">
                                <span class="input-group-text" id="basic-addon1">$</span>
                            </div>
                            <input class="form-control" value="${globalDayCost}" aria-label="payment" aria-describedby="basic-addon1" disabled>                
                        </div>
                    </div>                               
                </div>
            </div>
                
            
                <div class="card text-white bg-dark mb-3" id="second-card">
                    <form action="UpdateChargesServlet" method="GET">
                    <div class="card-body">
                         <h5 class="card-title">Actualizar costos</h5>
                         <div class="form-group">
                            <label for="globalCost">Costo Global: </label>
                            <div class="input-group mb-3">
                                <div class="input-group-append">
                                    <span class="input-group-text" id="basic-addon1">$</span>
                                </div>
                                <input class="form-control" name="globalCost" aria-label="payment" aria-describedby="basic-addon1">                
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="costoDia">Costo global por dia: </label>
                            <div class="input-group mb-3">
                                <div class="input-group-append">
                                    <span class="input-group-text" id="basic-addon1">$</span>
                                </div>
                                <input class="form-control" name="costoDia" aria-label="payment" aria-describedby="basic-addon1">                
                            </div>
                            <input type="submit" class="btn btn-outline-light" value="Submit">
                        </div>
                    </div>
                </form >
                </div>
        </div>
        
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
                        Ingrese solamente numeros en los campos.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>               
    </body>
</html>
