<html>
    <!--
  Copyright (c) 1999 The Apache Software Foundation.  All rights 
  reserved.
-->
    
    <% 
    double freeMem = Runtime.getRuntime().freeMemory();
    double totlMem = Runtime.getRuntime().totalMemory();
    double percent = freeMem/totlMem;
    if (percent < 0.5) { 
    %>
    
    <jsp:forward page="/forward/one.jsp"/>
    
    <% } else {
        if (percent < 0.8){ %>
            <jsp:forward page="two.html"/>
        <% }else{ %>
        <jsp:forward page="three.jsp"/>
        <% } %>
    <% } %>    
</html>
