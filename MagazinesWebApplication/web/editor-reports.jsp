<%-- 
    Document   : commentaries-report-editor
    Created on : Oct 10, 2019, 10:44:02 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Reports</title>
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
        
        <div class="card border-dark mb-3" id="card">
            <div class="card-body">
                <div> 
                    <h1 class="title">Reportes</h1>
                </div>
                <form action="EditorServlet" method="GET">
                    
                    <div class="form-row">
                        <div class="form-group col-md-6">
                          <label for="initial-date">Fecha inicial</label>
                          <input type="text" class="form-control" placeholder="Fecha inicial" name="initialDate">
                          <small class="form-text text-muted">Example: 2015-02-28</small>
                        </div>
                        <div class="form-group col-md-6">
                          <label for="final-date">Fecha final</label>
                          <input type="text" class="form-control" placeholder="Fecha final" name="finalDate">
                          <small class="form-text text-muted">Example: 2018-03-15</small>
                        </div>
                      </div>
                    <div class="form-group">
                        <label for="filter">Revista</label>
                        <input type="text" class="form-control" placeholder="Filtrar por revista" name="filter">
                        <small class="form-text text-muted">Opcional</small>
                    </div>
                    <div class="input-group">
                        <select name="report" class="custom-select" id="inputGroupSelect04" aria-label="Example select with button addon">
                        <option selected >Elegir reporte...</option>
                        <option value="1" >Reporte de comentarios</option>
                        <option value="2">Reporte de suscripciones</option>
                        <option value="3">Reporte de ganancias</option>
                      </select>
                      <div class="input-group-append">
                        <button class="btn btn-dark" type="submit">Aceptar</button>
                      </div>
                    </div>
                </form>               
            </div>
            <div class="card-footer bg-transparent ">
                
                <c:if test="${requestScope.flag == 1}">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Usuario</th>
                                <th scope="col">Revista</th>
                                <th scope="col">Comentario</th>
                                <th scope="col">Fecha</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="report" items="${requestScope.First}">     
                                <tr>
                                    <th scope="row">${report.username}</th>
                                    <td>${report.name}</td>
                                    <td>${report.commentary}</td>
                                    <td>${report.commentaryDate}</td>
                                </tr>                                                    
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if> 
            
                <c:if test="${requestScope.flag == 2}">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Usuario</th>
                                <th scope="col">Revista</th>
                                <th scope="col">Tomo</th>
                                <th scope="col">Fecha</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="report" items="${requestScope.Second}">     
                                <tr>
                                    <th scope="row">${report.username}</th>
                                    <td>${report.name}</td>
                                    <td>${report.version}</td>
                                    <td>${report.subscriptionDate}</td>
                                </tr>                                                    
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>   
                <c:if test="${requestScope.flag == 3}">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Usuario</th>
                                <th scope="col">Revista</th>
                                <th scope="col">Tomo</th>
                                <th scope="col">Ganancia</th>
                                <th scope="col">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="report" items="${requestScope.Third}">     
                                <tr>
                                    <th scope="row">${report.username}</th>
                                    <td>${report.name}</td>
                                    <td>${report.version}</td>
                                    <td>${report.profit}</td>
                                    <td>-</td>
                                </tr>                                                    
                            </c:forEach>
                                <tr>
                                    <th scope="row">-</th>
                                    <td>-</td>
                                    <td>-</td>
                                    <td>-</td>
                                    <td>${requestScope.total}</td>
                                </tr>            
                        </tbody>
                    </table>
                </c:if>   
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
                        Seleccione un reporte.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>               
    </body>
</html>
