<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<sql:setDataSource var="dataRC"
                   driver="com.microsoft.jdbc.sqlserver.SQLServerDriver"
                   url="jdbc:microsoft:sqlserver://localhost:1433;databaseName=TANTA_FACT;user=sa;password=sa" />    


<sql:query dataSource="${dataRC}" var="omg">
    SELECT * FROM t_Producto
</sql:query>
<c:forEach var="row" begin="0" items="${omg.rowByIndex}">    
    <jsp:useBean id="bookRow" type="java.lang.Object[]"/>
    ${bookRow[3]}
</c:forEach>

