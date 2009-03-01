<%@ include file="/struts/common/taglibs.jsp"%>

<body id="login"/>

<form method="post" id="loginForm" action="<c:url value='/struts/j_security_check'/>"
    onsubmit="saveUsername(this);return validateForm(this)">
<fieldset style="padding-bottom: 0">
<ul>
    <li>
       <label for="j_username">
            <fmt:message key="label.username"/>
        </label>
        <input type="text" class="text medium" name="j_username" id="j_username" tabindex="1" />
    </li>

    <li>
        <label for="j_password">
            <fmt:message key="label.password"/>
        </label>
        <input type="password" class="text medium" name="j_password" id="j_password" tabindex="2" />
    </li>

    <li>
        <input type="submit" class="button" name="login" value="<fmt:message key='button.login'/>" tabindex="4" />
    </li>
<c:if test="${param.error != null}">
    <li>
        <img src="${ctx}/images/iconWarning.gif" alt="<fmt:message key='icon.warning'/>"/>
        <fmt:message key="errors.password.mismatch"/>
    </li>
</c:if>
</ul>
</fieldset>
</form>


