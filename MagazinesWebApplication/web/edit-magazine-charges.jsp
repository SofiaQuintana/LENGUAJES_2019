<%-- 
    Document   : edit-magazine-charges
    Created on : Oct 6, 2019, 11:56:12 AM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Edit Charges</title>
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
                    <h1 class="title">Asignacion de costos</h1>
                </div>
                <div></div>   
                
                        <c:forEach var="magazine" items="${sessionScope.selectedMagazine}">
                            <form action="UpdateChargesServlet?charge=${magazine.magazineId}" method="POST"> 
                                <h4 class="card-title">${magazine.name}</h4>
                                <div class="form-group">
                                    <label for="charge">Cobro por suscripcion: </label>
                                    <div class="input-group mb-3">
                                        <div class="input-group-append">
                                            <span class="input-group-text" id="basic-addon1">$</span>
                                        </div>
                                    <input class="form-control" name="cost" placeholder="Ingrese Cargo" aria-label="charge" aria-describedby="basic-addon1">                
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="chargeByDay">Costo por dia: </label>
                                    <div class="input-group mb-3">
                                        <div class="input-group-append">
                                            <span class="input-group-text" id="basic-addon1">$</span>
                                        </div>
                                    <input class="form-control" name="costoDia" placeholder="Ingrese Costo" aria-label="costoDia" aria-describedby="basic-addon1">                
                                    </div>
                                </div>
                                <div> 
                                    <h1 class="title">Otros: </h1>
                                </div>
                                <div></div>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" name="global-charge" value="true" class="custom-control-input" id="customCheck1">
                                    <label class="custom-control-label" for="customCheck1">Seleccione para asignar cargo global.</label>
                                </div>
                                <h1 class="title"></h1>
                                <div class="custom-control custom-switch">
                                    <input type="checkbox" name="global-cost" class="custom-control-input" id="customSwitch1">
                                    <label class="custom-control-label" for="customSwitch1">Seleccione para asignar costo por dia global.</label>
                                </div>
                                <h1 class="title"></h1>
                                <input name="magazineId" type="hidden" value="${magazine.magazineId}">
                                <input type="submit" class="btn btn-dark" value="Submit">
                            </form>              
                        </c:forEach>
                
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
