<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : Page2
    Created on : 15/06/2008, 01:17:16 AM
    Author     : Administrado
-->
<jsp:root version="2.1" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page binding="#{Page2.page1}" id="page1">
            <webuijsf:html binding="#{Page2.html1}" id="html1">
                <webuijsf:head binding="#{Page2.head1}" id="head1">
                    <webuijsf:link binding="#{Page2.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{Page2.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{Page2.form1}" id="form1">
                        <webuijsf:textField binding="#{Page2.textField1}" id="textField1" required="true" style="position: absolute; left: 144px; top: 72px; width: 168px; height: 24px"/>
                        <webuijsf:passwordField binding="#{Page2.passwordField1}" id="passwordField1" required="true" style="height: 24px; left: 144px; top: 120px; position: absolute; width: 168px"/>
                        <webuijsf:label binding="#{Page2.label1}" for="passwordField1" id="label1"
                            style="position: absolute; left: 24px; top: 120px; width: 96px; height: 24px" text="Label"/>
                        <webuijsf:label binding="#{Page2.label2}" for="textField1" id="label2"
                            style="position: absolute; left: 24px; top: 72px; width: 96px; height: 24px" text="Label"/>
                        <webuijsf:button actionExpression="#{Page2.button1_action}" binding="#{Page2.button1}" id="button1"
                            style="position: absolute; left: 192px; top: 192px; width: 120px; height: 24px" text="Button"/>
                        <webuijsf:message binding="#{Page2.message1}" for="textField1" id="message1" showDetail="false" showSummary="true" style="position: absolute; left: 360px; top: 72px; width: 96px; height: 24px"/>
                        <webuijsf:message binding="#{Page2.message2}" for="passwordField1" id="message2" showDetail="false" showSummary="true" style="position: absolute; left: 360px; top: 120px; width: 120px; height: 24px"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
