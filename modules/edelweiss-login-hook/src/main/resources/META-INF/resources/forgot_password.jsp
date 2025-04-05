<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
User user2 = (User)request.getAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_USER);

if (Validator.isNull(authType)) {
	authType = company.getAuthType();
}

String login = (String)portletSession.getAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS);

Integer reminderAttempts = (Integer)portletSession.getAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_ATTEMPTS);

if (reminderAttempts == null) {
	reminderAttempts = 0;
}

renderResponse.setTitle(LanguageUtil.get(request, "forgot-password"));
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/eli_login.css?t=<%= new Date().getTime() %>" />

<portlet:actionURL name="/login/forgot_password" var="forgotPasswordURL">
	<portlet:param name="mvcRenderCommandName" value="/login/forgot_password" />
</portlet:actionURL>

<div class="login-card-container eli-custom-login pt-5">
	<div class="card login-card">
		<div class="card-body">
			<h4 class="card-title text-center">Forgot Password</h4>
			<aui:form action="<%= forgotPasswordURL %>" method="post" name="fm">
				<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
		
				<liferay-ui:error exception="<%= CaptchaConfigurationException.class %>" message="a-captcha-error-occurred-please-contact-an-administrator" />
				<liferay-ui:error exception="<%= CaptchaException.class %>" message="captcha-verification-failed" />
				<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="text-verification-failed" />
				<liferay-ui:error exception="<%= NoSuchUserException.class %>" message='<%= "the-" + TextFormatter.format(HtmlUtil.escape(authType), TextFormatter.K) + "-you-requested-is-not-registered-in-our-database" %>' />
				<liferay-ui:error exception="<%= RequiredReminderQueryException.class %>" message="you-have-not-configured-a-reminder-query" />
				<liferay-ui:error exception="<%= SendPasswordException.MustBeEnabled.class %>" message="password-recovery-is-disabled" />
				<liferay-ui:error exception="<%= UserActiveException.class %>" message="your-account-is-not-active" />
				<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeNull.class %>" message="please-enter-an-email-address" />
				<liferay-ui:error exception="<%= UserEmailAddressException.MustValidate.class %>" message="please-enter-a-valid-email-address" />
				<liferay-ui:error exception="<%= UserLockoutException.LDAPLockout.class %>" message="this-account-is-locked" />
				<liferay-ui:error exception="<%= UserReminderQueryException.class %>" message="your-answer-does-not-match-what-is-in-our-database" />
		
				<aui:fieldset>
					<c:choose>
						<c:when test="<%= user2 == null %>">
		
							<%
							String loginLabel = null;
		
							if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
								loginLabel = "email-address";
							}
							else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
								loginLabel = "screen-name";
							}
							else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
								loginLabel = "id";
							}
							%>
		
							<aui:input name="step" type="hidden" value="1" />
		
							<c:if test="<%= !PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_ENABLED, PropsValues.USERS_REMINDER_QUERIES_ENABLED) %>">
								<portlet:renderURL var="redirectURL">
									<portlet:param name="mvcRenderCommandName" value="/login/login" />
								</portlet:renderURL>
		
								<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />
							</c:if>
		
							<aui:input label="<%= loginLabel %>" cssClass="eli-input" name="login" required="<%= true %>" size="30" type="text" value="<%= login %>" />
		
							<c:if test="<%= captchaConfiguration.sendPasswordCaptchaEnabled() %>">
								<liferay-captcha:captcha />
							</c:if>
		
							<div class="d-flex justify-content-between" style="align-items: baseline;">
								<aui:button cssClass="edto-btn-primary btn-secondary" type="submit" value='<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_ENABLED, PropsValues.USERS_REMINDER_QUERIES_ENABLED) ? "next" : "send-new-password" %>' />
								<%@ include file="/navigation.jspf" %>
							</div>
						</c:when>
						<c:when test="<%= user2 != null %>">
							<aui:input name="step" type="hidden" value="2" />
		
							<portlet:renderURL var="redirectURL">
								<portlet:param name="mvcRenderCommandName" value="/login/login" />
							</portlet:renderURL>
		
							<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />
		
							<c:if test="<%= Validator.isNotNull(user2.getReminderQueryQuestion()) && Validator.isNotNull(user2.getReminderQueryAnswer()) %>">
								<div class="alert alert-info">
									<liferay-ui:message arguments="<%= HtmlUtil.escape(login) %>" key="an-email-will-be-sent-to-x-if-you-can-correctly-answer-the-following-question" translateArguments="<%= false %>" />
								</div>
		
								<aui:input label="<%= HtmlUtil.escape(LanguageUtil.get(request, user2.getReminderQueryQuestion())) %>" cssClass="eli-input" name="answer" type='<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_DISPLAY_IN_PLAIN_TEXT, PropsValues.USERS_REMINDER_QUERIES_DISPLAY_IN_PLAIN_TEXT) ? "text" : "password" %>' />
							</c:if>
		
							<c:choose>
								<c:when test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_REQUIRED, PropsValues.USERS_REMINDER_QUERIES_REQUIRED) && !user2.hasReminderQuery() %>">
									<div class="alert alert-info">
										<liferay-ui:message key="the-password-cannot-be-reset-because-you-have-not-configured-a-reminder-query" />
									</div>
								</c:when>
								<c:otherwise>
									<c:if test="<%= reminderAttempts >= 3 %>">
										<liferay-captcha:captcha />
									</c:if>
		
									<div class="d-flex justify-content-between" style="align-items: baseline;">
										<aui:button style="font-size: 0.7rem;" cssClass="edto-btn-primary btn-secondary" type="submit" value='<%= company.isSendPasswordResetLink() ? "send-password-reset-link" : "send-new-password" %>' />
										<%@ include file="/navigation.jspf" %>
									</div>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<div class="alert alert-warning">
								<liferay-ui:message key="the-system-cannot-send-you-a-new-password-because-you-have-not-provided-an-email-address" />
							</div>
						</c:otherwise>
					</c:choose>
				</aui:fieldset>
			</aui:form>
		</div>
	</div>
</div>