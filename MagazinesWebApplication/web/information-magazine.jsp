<%-- 
    Document   : information-magazine
    Created on : Sep 30, 2019, 12:40:39 AM
    Author     : zofia
--%>

<%@page import="magazineswebapplication.dummyclasses.Rate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Share Magazine</title>
    </head>
    <body background="./background.jpg">
        <%@include file="editor-header-form.html" %>
        <%@include file="scripts.html"%>
        
        
        <script type="text/javascript">
            var user = "<%="Signed in as: "+ session.getAttribute("User")%>";
                $(document).ready(function () {
                    $('#dropdownMenu2').text(user);
                });
            </script>
            
            <form action="MagazinePostServlet" method="POST">
                <div class="card border-dark mb-3" id="card" >
                    <div class="card-body">
                        <div> 
                            <h1 class="title">Magazine</h1>
                        </div>
                        <div></div>
                        <div class="form-group">
                            <label for="MagazineName">Name: *</label>
                            <input class="form-control" name="MagazineName" placeholder="Ingrese Nombre">
                        </div>    
                        <div class="form-group">
                            <label for="version">Version: *</label>
                            <input class="form-control" name="version" placeholder="Ingrese Version">
                        </div>  
                        <div class="form-group">
                            <label for="autor">Autor: *</label>
                            <input class="form-control" name="autor" placeholder="Ingrese Autor">
                        </div>  
                        <div class="form-group">
                            <label for="date">Publication Date: *</label>
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
                        <div class="form-group">
                            <label for="description">Description: *</label>
                            <textarea name="description" class="form-control" rows="5"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="price">Price: </label>
                            <div class="input-group mb-3">
                                <div class="input-group-append">
                                    <span class="input-group-text" id="basic-addon1">$</span>
                                </div>
                                <input class="form-control" name="price" placeholder="Ingrese Precio" aria-label="price" aria-describedby="basic-addon1">                
                            </div>
                            <small class="form-text text-muted">Nota: Para que se tome como suscripcion gratuita coloque 0.</small>
                        </div>
                        <div class="form-group">
                            <label for="category">Category: </label>
                            <textarea name="category" class="form-control" rows="3"></textarea>
                            <small class="form-text text-muted">Example: Programming, Data base</small>
                        </div>
                        <div class="form-group">
                            <label for="tags">Tags: </label>
                            <textarea name="tags" class="form-control" rows="3"></textarea>
                            <small class="form-text text-muted">Example: Java, Jsp, HTML, MYSQL</small>
                        </div>       
                        <div> 
                            <h1 class="title">Seleccionar Permisos</h1>
                        </div>
                        <div></div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" name="likes" value="true" class="custom-control-input" id="customCheck1">
                            <label class="custom-control-label" for="customCheck1">Seleccione para bloquear 'Me gusta'.</label>
                        </div>
                        <h1 class="title"></h1>
                        <div class="custom-control custom-switch">
                            <input type="checkbox" name="commentaries" class="custom-control-input" id="customSwitch1">
                            <label class="custom-control-label" for="customSwitch1">Seleccione para bloquear 'Comentarios'.</label>
                        </div>
                        <h1 class="title"></h1>
                        <button type="submit" class="btn btn-dark">Next: Select PDF</button>
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
                        Ingrese todos los campos marcados con *
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
