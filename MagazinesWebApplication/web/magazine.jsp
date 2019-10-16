<%-- 
    Document   : magazine
    Created on : Oct 6, 2019, 10:04:45 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Magazine</title>
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
           
        <c:forEach var="magazines" items="${requestScope.magazine}">
            <c:forEach var="versions" items="${requestScope.version}">
                
                <div class="card text-white bg-dark mb-3" id="profile">
                    <img src="./pdf.png" class="card-img" alt="Magazine Pdf" id="magazine-image">
                    <div class="card-body" id="magazine-body">
                        <h3 class="card-title">${magazines.name} Tomo ${versions.version}</h3>
                        <p class="card-text">Autor: ${magazines.autor}</p>
                        <p class="card-text">Descripcion: ${magazines.description}</p>      
                        <a href="http://localhost:8080/static/PDF/${versions.username}/${versions.pdfUrl}" download>Download: ${magazines.name}</a>
                    </div>             
                    <div class="card-footer bg-transparent ">
                        <h5 class="card-title">Comentarios de otros usuarios:</h5>
                        <c:forEach var="comment" items="${requestScope.commentaries}">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"></span>
                                </div>
                                        <textarea class="form-control" aria-label="With textarea" disabled>
${comment.commentaryDate} || ${comment.commentaryId} dice:
${comment.commentary}</textarea>
                            </div>
                            <br>
                        </c:forEach>
                        <c:if test="${not magazines.blockCommentary}">
                            <form action="CommentServlet?m=${versions.idPost}" method="POST">
                                <div class="input-group-prepend">                                                   
                                    <h5 class="card-title">Comentar:</h5>
                                </div>
                                <textarea class="form-control" aria-label="With textarea" name="commentary"></textarea>
                                <br>
                                <div class="form-group">
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
                                    <small class="form-text text-muted">Example: 1998/02/28</small>
                                </div>
                                <br>
                                <button type="submit" class="btn btn-outline-light">Aceptar</button>
                        </form>
                        </c:if>                       
                    </div>
                </div>
            </c:forEach>
            
        </c:forEach>
        
        <c:if test="${requestScope['Error'] != null}">
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
                        Ingrese correctamente la fecha.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
