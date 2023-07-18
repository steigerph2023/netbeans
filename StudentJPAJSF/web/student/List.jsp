<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Student Items</title>
            <link rel="stylesheet" type="text/css" href="/StudentJPAJSF/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Student Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Student Items Found)<br />" rendered="#{student.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{student.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{student.pagingInfo.firstItem + 1}..#{student.pagingInfo.lastItem} of #{student.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{student.prev}" value="Previous #{student.pagingInfo.batchSize}" rendered="#{student.pagingInfo.firstItem >= student.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{student.next}" value="Next #{student.pagingInfo.batchSize}" rendered="#{student.pagingInfo.lastItem + student.pagingInfo.batchSize <= student.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{student.next}" value="Remaining #{student.pagingInfo.itemCount - student.pagingInfo.lastItem}"
                                   rendered="#{student.pagingInfo.lastItem < student.pagingInfo.itemCount && student.pagingInfo.lastItem + student.pagingInfo.batchSize > student.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{student.studentItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="StudNo"/>
                            </f:facet>
                            <h:outputText value="#{item.studNo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Last Name"/>
                            </f:facet>
                            <h:outputText value="#{item.lastname}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="First Name"/>
                            </f:facet>
                            <h:outputText value="#{item.firstname}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Age"/>
                            </f:facet>
                            <h:outputText value="#{item.age}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Gender"/>
                            </f:facet>
                            <h:outputText value="#{item.gender}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Grade Level"/>
                            </f:facet>
                            <h:outputText value="#{item.gradelevel}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{student.detailSetup}">
                                <f:param name="jsfcrud.currentStudent" value="#{jsfcrud_class['controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][student.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{student.editSetup}">
                                <f:param name="jsfcrud.currentStudent" value="#{jsfcrud_class['controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][student.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{student.remove}">
                                <f:param name="jsfcrud.currentStudent" value="#{jsfcrud_class['controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][student.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{student.createSetup}" value="New Student"/>
                <br />


            </h:form>
        </body>
    </html>
</f:view>
