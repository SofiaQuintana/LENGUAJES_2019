<%-- 
    Document   : upload-pdf
    Created on : Sep 30, 2019, 10:16:44 PM
    Author     : zofia
--%>

<%@page import="magazineswebapplication.dummyclasses.Rate"%>
<%@page import="magazineswebapplication.dbmanagers.UserDBManager"%>
<%@page import="magazineswebapplication.dbmanagers.DataBaseController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Upload Profile Photo</title>
    </head>
    <body background="./background.jpg">
        <%@include file="editor-header-form.html"%>
        <%@include file="pdf-form.html"%>
        <%@include file="scripts.html"%> 
        
        
        <script type="text/javascript">
            var user = "<%="Signed in as: "+ session.getAttribute("User")%>";
                $(document).ready(function () {
                    $('#dropdownMenu2').text(user);
                });
            </script>
    </body>
</html>
