<%-- 
    Document   : index
    Created on : Oct 22, 2008, 10:18:36 AM
    Author     : rCuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>    
    <c:set var="foo">
        <c:forEach var="i" items="raul,ramirez,alvarado" varStatus="stat">
            <h1><c:out value="${stat.index}"/> = <c:out value="${i}"/></h1>
        </c:forEach>
        <c:forEach var="i" begin="100" end="200" step="5" varStatus="status">
            <c:if test="${status.first}">
                begin:<c:out value="${status.begin}">begin</c:out>
                end:<c:out value="${status.end}">end</c:out>
                step:<c:out value="${status.step}">step</c:out><br>
                sequence:
            </c:if> ${i} 
        </c:forEach>
        <br>
        <c:forTokens var="tok" items="msdkfgdsmxsmdkfmdskxfdgdgx" delims="x">    
        ${tok}
        </c:forTokens>
        
        <c:out value="${omg}" escapeXml="false">
            <omg>
        </c:out>
        
        <h4><c:out value="<c:out>" escapeXml="true"/> with Reader object </h4>
        <%
            java.io.Reader reader1 = new java.io.StringReader("<foo>Text for a Reader!</foo>");
            pageContext.setAttribute("myReader1", reader1);
            java.io.Reader reader2 = new java.io.StringReader("<foo>Text for a Reader !</foo>");
            pageContext.setAttribute("myReader2", reader2);
        %>
        Reader1 (escapeXml=true) : <c:out value="${myReader1}"/><br>
        Reader2 (escapeXml=false): <c:out value="${myReader2}" escapeXml="false"/><br>
        
    </c:set>
    ${foo}    
    
    <c:remove var="foo"/>
    ${foo}    
</html>
