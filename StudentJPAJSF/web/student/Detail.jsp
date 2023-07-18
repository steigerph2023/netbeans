<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Student Detail</title>
            <link rel="stylesheet" type="text/css" href="/StudentJPAJSF/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Student Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="StudNo:"/>
                    <h:outputText value="#{student.student.studNo}" title="StudNo" />
                    <h:outputText value="Lastname:"/>
                    <h:outputText value="#{student.student.lastname}" title="Lastname" />
                    <h:outputText value="Firstname:"/>
                    <h:outputText value="#{student.student.firstname}" title="Firstname" />
                    <h:outputText value="Age:"/>
                    <h:outputText value="#{student.student.age}" title="Age" />
                    <h:outputText value="Gender:"/>
                    <h:outputText value="#{student.student.gender}" title="Gender" />
                    <h:outputText value="Gradelevel:"/>
                    <h:outputText value="#{student.student.gradelevel}" title="Gradelevel" />


                </h:panelGrid>
                <br />
                <h:commandLink action="#{student.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentStudent" value="#{jsfcrud_class['controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][student.student][student.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{student.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentStudent" value="#{jsfcrud_class['controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][student.student][student.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{student.createSetup}" value="New Student" />
                <br />
                <h:commandLink action="#{student.listSetup}" value="Show All Student Items"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
