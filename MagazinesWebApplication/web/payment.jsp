<%-- 
    Document   : payment
    Created on : Oct 6, 2019, 2:02:45 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Payment</title>
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
                    <h1 class="title">Pago de suscripcion</h1>
                </div>
                <div></div>   
                <form action="PaymentServlet" method="POST"> 
                        <c:forEach var="post" items="${sessionScope.selectedVersion}">
                            <c:forEach var="magazine" items="${sessionScope.selectedMagazine}">
                                <h4 class="card-title">${magazine.name} tomo: ${post.version}</h4>
                                <div class="form-group">
                                    <label for="payment">Cantidad de pago: </label>
                                    <div class="input-group mb-3">
                                        <div class="input-group-append">
                                            <span class="input-group-text" id="basic-addon1">$</span>
                                        </div>
                                        <input class="form-control" name="payment" value="${magazine.price}" aria-label="payment" aria-describedby="basic-addon1" disabled>                
                                    </div>
                                </div>
                                <input name="magazineId" type="hidden" value="${magazine.magazineId}">
                            </c:forEach>
                            <div class="form-group">
                                <label for="date">Subscription Date: *</label>
                                <div class="input-group mb-3">
                                    <input class="form-control" name="year" placeholder="year" aria-label="year" aria-describedby="basic-addon1">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon1">/</span>
                                    </div>
                                    <input class="form-control" name="month" placeholder="month" aria-label="month" aria-describedby="basic-addon1">
                                    <div class="input-group-append">
                                        <span class="input-group-text" id="basic-addon1">/</span>
                                    </div>
                                    <input class="form-control" name="day" placeholder="day" aria-label="day" aria-describedby="basic-addon1"> 
                                </div>
                                <small class="form-text text-muted">Example: 2016/02/25</small>
                            </div>
                            <input type="submit" class="btn btn-dark" value="Pay">
                        </c:forEach>
                </form>               
            </div>
        </div>
            
            <c:if test="${requestScope['exception'] != null}">
            <script type="text/javascript">
                $(document).ready(function () {
                    $('#ErrorModal').modal('show');
                });
            </script>
            </c:if>
            
            <div class="modal fade" id="ErrorModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Error</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Ingrese la fecha correctamente.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
