<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : Page1
    Created on : 10/09/2008, 04:15:22 PM
    Author     : rCuser
-->
<jsp:root version="2.1" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page binding="#{Page1.page1}" id="page1">
            <webuijsf:html binding="#{Page1.html1}" id="html1">
                <webuijsf:head binding="#{Page1.head1}" id="head1">
                    <webuijsf:link binding="#{Page1.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{Page1.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{Page1.form1}" id="form1">
                        <webuijsf:button binding="#{Page1.button1}" id="button1" style="height: 24px; left: 47px; top: 144px; position: absolute; width: 120px" text="Button"/>
                        <webuijsf:button binding="#{Page1.button2}" id="button2" style="height: 24px; left: 239px; top: 48px; position: absolute; width: 120px" text="Button"/>
                        <webuijsf:staticText binding="#{Page1.staticText1}" id="staticText1" style="height: 24px; left: 192px; top: 96px; position: absolute; width: 120px"/>
                        <webuijsf:calendar binding="#{Page1.calendar1}" id="calendar1" style="height: 96px; left: 264px; top: 168px; position: absolute; width: 216px"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
