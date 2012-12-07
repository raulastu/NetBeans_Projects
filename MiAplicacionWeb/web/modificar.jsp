<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Modificar!</h1>
        <form action="ModificarServ">
            <input type="hidden" name="id" value="<%= request.getParameter("id") %>" />
            Nombres: <input type="text" name="nom" value="<%= request.getParameter("nom") %>" />
            Apellidos: <input type="text" name="ape" value="<%= request.getParameter("ape") %>" />
            Ciclo: <input type="text" name="ciclo" value="<%= request.getParameter("ciclo") %>" />
            <input type="submit" value="Modificar" />
        </form>
    </body>
</html>
