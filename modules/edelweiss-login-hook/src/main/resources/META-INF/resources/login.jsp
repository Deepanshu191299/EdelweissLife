<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/eli_login.css?t=<%= new Date().getTime() %>" />

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">

		<%
		String signedInAs = HtmlUtil.escape(user.getFullName());

		if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
			String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());

			signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">" + signedInAs + "</a>";
		}
		%>

		<liferay-ui:message arguments="<%= signedInAs %>" key="you-are-signed-in-as-x" translateArguments="<%= false %>" />
	</c:when>
	<c:otherwise>

		<%
		String formName = "loginForm";

		if (windowState.equals(LiferayWindowState.EXCLUSIVE)) {
			formName += "Modal";
		}

		String redirect = ParamUtil.getString(request, "redirect");

		String login = (String)SessionErrors.get(renderRequest, "login");

		if (Validator.isNull(login)) {
			login = LoginUtil.getLogin(request, "login", company);
		}

		String password = StringPool.BLANK;
		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

		if (Validator.isNull(authType)) {
			authType = company.getAuthType();
		}
		%>

		<div class="login-card-container eli-custom-login pt-5">
			<div class="card login-card">
				<div class="card-body">
	            	<h4 class="card-title text-center">Login</h4>
	            	<portlet:actionURL name="/login/login" secure="<%= request.isSecure() %>" var="loginURL">
						<portlet:param name="mvcRenderCommandName" value="/login/login" />
					</portlet:actionURL>
		
					<aui:form action="<%= loginURL %>" autocomplete='<%= PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off" %>' cssClass="sign-in-form" method="post" name="<%= formName %>" onSubmit="event.preventDefault();" validateOnBlur="<%= false %>">
						<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
						<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
						<aui:input name="doActionAfterLogin" type="hidden" value="<%= portletName.equals(PortletKeys.FAST_LOGIN) ? true : false %>" />
		
						<div class="inline-alert-container lfr-alert-container"></div>
		
						<liferay-util:dynamic-include key="com.liferay.login.web#/login.jsp#alertPre" />
		
						<c:choose>
							<c:when test='<%= SessionMessages.contains(request, "forgotPasswordSent") %>'>
								<div class="alert alert-success">
									<liferay-ui:message key="your-request-completed-successfully" />
								</div>
							</c:when>
							<c:when test='<%= SessionMessages.contains(request, "userAdded") %>'>
		
								<%
								String userEmailAddress = (String)SessionMessages.get(request, "userAdded");
								%>
		
								<div class="alert alert-success">
									<liferay-ui:message key="thank-you-for-creating-an-account" />
		
									<c:if test="<%= company.isStrangersVerify() %>">
										<liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="your-email-verification-code-was-sent-to-x" translateArguments="<%= false %>" />
									</c:if>
		
									<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
										<c:choose>
											<c:when test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.LOGIN_CREATE_ACCOUNT_ALLOW_CUSTOM_PASSWORD, PropsValues.LOGIN_CREATE_ACCOUNT_ALLOW_CUSTOM_PASSWORD) %>">
												<liferay-ui:message key="use-your-password-to-login" />
											</c:when>
											<c:otherwise>
												<liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="you-can-set-your-password-following-instructions-sent-to-x" translateArguments="<%= false %>" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</div>
							</c:when>
							<c:when test='<%= SessionMessages.contains(request, "userPending") %>'>
		
								<%
								String userEmailAddress = (String)SessionMessages.get(request, "userPending");
								%>
		
								<div class="alert alert-success">
									<liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved" translateArguments="<%= false %>" />
								</div>
							</c:when>
						</c:choose>
		
						<c:if test="<%= PropsValues.SESSION_ENABLE_PERSISTENT_COOKIES && PropsValues.SESSION_TEST_COOKIE_SUPPORT %>">
							<div class="alert alert-danger" id="<portlet:namespace />cookieDisabled" style="display: none;">
								<liferay-ui:message key="authentication-failed-please-enable-browser-cookies" />
							</div>
						</c:if>
		
						<c:choose>
							<c:when test="<%= company.isSendPasswordResetLink() %>">
								<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed-due-to-incorrect-credentials-or-account-lockout" />
							</c:when>
							<c:otherwise>
								<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed" />
							</c:otherwise>
						</c:choose>
		
						<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-log-in-because-the-maximum-number-of-users-has-been-reached" />
						<liferay-ui:error exception="<%= CookieNotSupportedException.class %>" message="authentication-failed-please-enable-browser-cookies" />
						<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="authentication-failed" />
						<liferay-ui:error exception="<%= PasswordExpiredException.class %>" message="your-password-has-expired" />
						<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeNull.class %>" message="please-enter-an-email-address" />
						<liferay-ui:error exception="<%= UserLockoutException.LDAPLockout.class %>" message="this-account-is-locked" />
		
						<c:choose>
							<c:when test="<%= company.isSendPasswordResetLink() %>">
								<liferay-ui:error exception="<%= UserLockoutException.PasswordPolicyLockout.class %>" message="authentication-failed-due-to-incorrect-credentials-or-account-lockout" />
							</c:when>
							<c:otherwise>
								<liferay-ui:error exception="<%= UserLockoutException.PasswordPolicyLockout.class %>" message="authentication-failed" />
							</c:otherwise>
						</c:choose>
		
						<liferay-ui:error exception="<%= UserPasswordException.class %>" message="authentication-failed" />
						<liferay-ui:error exception="<%= UserScreenNameException.MustNotBeNull.class %>" message="the-screen-name-cannot-be-blank" />
		
						<liferay-util:dynamic-include key="com.liferay.login.web#/login.jsp#alertPost" />
		
						<aui:fieldset>
		
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
		
							<aui:input cssClass="eli-input clearable" label="<%= loginLabel %>" name="login" required="<%= true %>" showRequiredLabel="<%= false %>" type="text" value="<%= login %>">
								<c:if test="<%= authType.equals(CompanyConstants.AUTH_TYPE_EA) %>">
									<aui:validator name="email" />
								</c:if>
							</aui:input>
		
							<aui:input cssClass="eli-input" name="password" required="<%= true %>" showRequiredLabel="<%= false %>" type="password" value="<%= password %>" />
		
							<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>
		
							<div class="d-flex">
								<aui:input checked="<%= rememberMe %>" name="showHidePass" label="Show Password" type="checkbox" />
								<c:if test="<%= company.isAutoLogin() %>">
									<aui:input checked="<%= rememberMe %>" name="rememberMe" type="checkbox" cssClass="ml-3"/>
								</c:if>
							</div>
						</aui:fieldset>
		
						<div class="d-flex justify-content-between" style="align-items: baseline;">
							<aui:button cssClass="edto-btn-primary btn-secondary" type="submit" value="sign-in" />
							<div class="eli-login-dynamin-navigation">
								<%@ include file="/navigation.jspf" %>
							</div>
						</div>
					</aui:form>
	           	</div>
			</div>
			
		</div>

		<aui:script sandbox="<%= true %>">
		
			$('#<portlet:namespace />showHidePass').change(function() {
		        if ($(this).is(':checked')) {
		            $('#<portlet:namespace />password').attr('type', 'text');
		        } else {
		        	$('#<portlet:namespace />password').attr('type', 'password');
		        }
		    });
		    
			var form = document.getElementById('<portlet:namespace /><%= formName %>');

			if (form) {
				form.addEventListener('submit', (event) => {
					<c:if test="<%= PropsValues.SESSION_ENABLE_PERSISTENT_COOKIES && PropsValues.SESSION_TEST_COOKIE_SUPPORT %>">
						if (!navigator.cookieEnabled) {
							document.getElementById(
								'<portlet:namespace />cookieDisabled'
							).style.display = '';

							return;
						}
					</c:if>

					<c:if test="<%= Validator.isNotNull(redirect) %>">
						var redirect = form.querySelector('#<portlet:namespace />redirect');

						if (redirect) {
							var redirectVal = redirect.getAttribute('value');

							redirect.setAttribute('value', redirectVal + window.location.hash);
						}
					</c:if>

					submitForm(form);
				});

				var password = form.querySelector('#<portlet:namespace />password');

				if (password) {
					password.addEventListener('keypress', (event) => {
						Liferay.Util.showCapsLock(
							event,
							'<portlet:namespace />passwordCapsLockSpan'
						);
					});
				}
			}
		</aui:script>
	</c:otherwise>
</c:choose>