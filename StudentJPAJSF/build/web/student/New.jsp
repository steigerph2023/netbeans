<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Student</title>
            <link rel="stylesheet" type="text/css" href="/StudentJPAJSF/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Student</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{student.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Last Name:"/>
                    <h:inputText id="lastname" value="#{student.student.lastname}" title="Lastname" required="true" requiredMessage="The lastname field is required." />
                    <h:outputText value="First Name:"/>
                    <h:inputText id="firstname" value="#{student.student.firstname}" title="Firstname" required="true" requiredMessage="The firstname field is required." />
                    <h:outputText value="Age:"/>
                    <h:inputText id="age" value="#{student.student.age}" title="Age" required="true" requiredMessage="The age field is required." />
                    <h:outputText value="Gender:"/>
                    <h:selectOneRadio id="gender" value="#{student.student.gender}" title="Gender" required="true" requiredMessage="The gender field is required.">
                        <f:selectItem itemValue="Male" itemLabel="Male"/>
                        <f:selectItem itemValue="Female" itemLabel="Female"/>
                    </h:selectOneRadio>

                    <h:outputText value="Grade Level:"/>

                    <h:selectOneMenu value="#{student.student.gradelevel}" title="Gradelevel" required="true" requiredMessage="The gradelevel field is required." >
                        <f:selectItem itemValue="Kinder 1"/>
                        <f:selectItem itemValue="Kinder 2"/>
                        <f:selectItem itemValue="Grade 1"/>
                        <f:selectItem itemValue="Grade 2"/>
                        <f:selectItem itemValue="Grade 3"/>
                        <f:selectItem itemValue="Grade 4"/>
                        <f:selectItem itemValue="Grade 5"/>
                        <f:selectItem itemValue="Grade 6"/>
                        <f:selectItem itemValue="Grade 7"/>
                        <f:selectItem itemValue="Grade 8"/>
                        <f:selectItem itemValue="Grade 9"/>
                        <f:selectItem itemValue="Grade 10"/>
                        <f:selectItem itemValue="Grade 11"/>
                        <f:selectItem itemValue="Grade 12"/>
                    </h:selectOneMenu>    
                </h:panelGrid>
                <br />
                <h:commandLink action="#{student.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{student.listSetup}" value="Show All Student Items" immediate="true"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
