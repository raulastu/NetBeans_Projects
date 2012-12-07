<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : error
    Created on : 15/06/2008, 01:11:13 AM
    Author     : Administrado
-->
<jsp:root version="2.1" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page binding="#{error.page1}" id="page1">
            <webuijsf:html binding="#{error.html1}" id="html1">
                <webuijsf:head binding="#{error.head1}" id="head1">
                    <webuijsf:link binding="#{error.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{error.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{error.form1}" id="form1">
                        <webuijsf:label binding="#{error.label1}" id="label1" style="position: absolute; left: 72px; top: 48px; width: 144px; height: 24px" text="lo sentimos"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
