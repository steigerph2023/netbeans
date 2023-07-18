<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Student JPA</title>
            <link rel="stylesheet" type="text/css" href="/StudentJPAJSF/faces/jsfcrud.css" />
        </head>
        <body>
            <h1><h:outputText value="Student Handling"/></h1>
            <h:form>
                <h:commandLink action="student_detail" value="Show All"/><br/>
                <h:commandLink action="student_create" value="Create"/><br/>
                <h:commandLink action="student_list" value="List"/><br/>
                <h:commandLink action="student_edit" value="Edit"/><br/>
            </h:form>
        </body>
    </html>
</f:view>
