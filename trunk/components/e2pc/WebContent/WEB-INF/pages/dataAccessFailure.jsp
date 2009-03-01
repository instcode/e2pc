<%@ include file="/struts/common/taglibs.jsp" %>

<title>Data Access Error</title>

<p>
    <c:out value="${requestScope.exception.message}"/>
</p>

<!--
<% 
Exception ex = (Exception) request.getAttribute("exception");
ex.printStackTrace(new java.io.PrintWriter(out)); 
%>
-->

<a href="struts/mainMenu.html" onclick="history.back();return false">&#171; Back</a>
