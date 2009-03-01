<%@ include file="/struts/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="heading" content="<fmt:message key='userProfile.heading'/>"/>
    <meta name="menu" content="UserMenu"/>
    <script type="text/javascript" src="<c:url value='/struts/scripts/selectbox.js'/>"></script>
</head>
<c:choose>
<c:when test="${param.from == 'list' and not empty user.id}">
    <h1>Edit User</h1>
</c:when>
<c:otherwise>
    <h1>Add User</h1>
</c:otherwise>
</c:choose>

<s:form name="userForm" action="saveUser" method="post" validate="true">
	<input type="hidden" name="user.id" value="<c:out value="${user.id}"/>"/>
	<input type="text" name="user.username" value="<c:out value="${user.username}"/>"/>
        <c:set var="buttons">
            <input type="submit" name="method:save" value="Save" onclick="onFormSubmit(this.form)"/>
            
        	<c:if test="${param.from == 'list' and not empty user.id}">
        		<input type="submit" onclick="return confirmDelete('user')" value="Delete" name="method:delete"/>
        	</c:if>
        
            <input type="submit" value="Cancel" name="method:cancel"/>
        </c:set>
        <c:out value="${buttons}" escapeXml="false"/>
 </s:form>
 
<script type="text/javascript">
    Form.focusFirstElement(document.forms["userForm"]);
    
<!-- This is here so we can exclude the selectAll call when roles is hidden -->
function onFormSubmit(theForm) {
<c:if test="${param.from == 'list'}">
    selectAll('userRoles');
</c:if>
}
</script>
