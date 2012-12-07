<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : principal
    Created on : 15/06/2008, 01:10:44 AM
    Author     : Administrado
-->
<jsp:root version="2.1" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page binding="#{principal.page1}" id="page1">
            <webuijsf:html binding="#{principal.html1}" id="html1">
                <webuijsf:head binding="#{principal.head1}" id="head1">
                    <webuijsf:link binding="#{principal.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{principal.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{principal.form1}" id="form1">
                        <webuijsf:label binding="#{principal.label1}" id="label1" style="height: 24px; left: 24px; top: 48px; position: absolute; width: 144px" text="Bienvenidos"/>
                        <webuijsf:table augmentTitle="false" binding="#{principal.table1}" id="table1"
                            style="height: 120px; left: 24px; top: 72px; position: absolute; width: 408px" title="Table" width="78">
                            <webuijsf:tableRowGroup binding="#{principal.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                sourceData="#{principal.t_paisDataProvider}" sourceVar="currentRow">
                                <webuijsf:tableColumn binding="#{principal.tableColumn4}" headerText="IdPais" id="tableColumn4" sort="IdPais">
                                    <webuijsf:staticText binding="#{principal.staticText4}" id="staticText4" text="#{currentRow.value['IdPais']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn5}" headerText="Nombre" id="tableColumn5" sort="Nombre">
                                    <webuijsf:staticText binding="#{principal.staticText5}" id="staticText5" text="#{currentRow.value['Nombre']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn6}" headerText="Continente" id="tableColumn6" sort="Continente">
                                    <webuijsf:staticText binding="#{principal.staticText6}" id="staticText6" text="#{currentRow.value['Continente']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn7}" headerText="Region" id="tableColumn7" sort="Region">
                                    <webuijsf:staticText binding="#{principal.staticText7}" id="staticText7" text="#{currentRow.value['Region']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn8}" headerText="SurfaceArea" id="tableColumn8" sort="SurfaceArea">
                                    <webuijsf:staticText binding="#{principal.staticText8}" id="staticText8" text="#{currentRow.value['SurfaceArea']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn9}" headerText="IndepYear" id="tableColumn9" sort="IndepYear">
                                    <webuijsf:staticText binding="#{principal.staticText9}" id="staticText9" text="#{currentRow.value['IndepYear']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn10}" headerText="Poblacion" id="tableColumn10" sort="Poblacion">
                                    <webuijsf:staticText binding="#{principal.staticText10}" id="staticText10" text="#{currentRow.value['Poblacion']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn11}" headerText="EsperanzaVida" id="tableColumn11" sort="EsperanzaVida">
                                    <webuijsf:staticText binding="#{principal.staticText11}" id="staticText11" text="#{currentRow.value['EsperanzaVida']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn12}" headerText="PBI" id="tableColumn12" sort="PBI">
                                    <webuijsf:staticText binding="#{principal.staticText12}" id="staticText12" text="#{currentRow.value['PBI']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn13}" headerText="PBIanterior" id="tableColumn13" sort="PBIanterior">
                                    <webuijsf:staticText binding="#{principal.staticText13}" id="staticText13" text="#{currentRow.value['PBIanterior']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn14}" headerText="LocalName" id="tableColumn14" sort="LocalName">
                                    <webuijsf:staticText binding="#{principal.staticText14}" id="staticText14" text="#{currentRow.value['LocalName']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn15}" headerText="FormaGobierno" id="tableColumn15" sort="FormaGobierno">
                                    <webuijsf:staticText binding="#{principal.staticText15}" id="staticText15" text="#{currentRow.value['FormaGobierno']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn16}" headerText="Presidente" id="tableColumn16" sort="Presidente">
                                    <webuijsf:staticText binding="#{principal.staticText16}" id="staticText16" text="#{currentRow.value['Presidente']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn17}" headerText="Capital" id="tableColumn17" sort="Capital">
                                    <webuijsf:staticText binding="#{principal.staticText17}" id="staticText17" text="#{currentRow.value['Capital']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{principal.tableColumn18}" headerText="IdPais2" id="tableColumn18" sort="IdPais2">
                                    <webuijsf:staticText binding="#{principal.staticText18}" id="staticText18" text="#{currentRow.value['IdPais2']}"/>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
