<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content="<fmt:message key='mainMenu.heading'/>"/>
    <meta name="menu" content="MainMenu"/>
</head>
<h1>Welcome</h1>

<p><fmt:message key="mainMenu.message"/></p>

<div class="separator"></div>

<ul class="glassList">
    <li>
        <a href="<c:url value='/admin/users.html'/>"><fmt:message key="menu.admin.users"/></a>
    </li>
    <li>
    	<a href="<c:url value='/logout.jsp'/>"><fmt:message key="user.logout"/></a>
    </li>
</ul>
