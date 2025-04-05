<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>
<%@
page import="java.util.Date" %>

<%
long mfaEmailOTPFailedAttemptsRetryTimeout = GetterUtil.getLong(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_FAILED_ATTEMPTS_RETRY_TIMEOUT));
%>
<div class="login-card-container eli-custom-login">
	<div class="card login-card">
		<div class="card-body">
			<h4 class="card-title text-center">Multi-factor Authentication</h4>
			
			<c:if test="<%= mfaEmailOTPFailedAttemptsRetryTimeout > 0 %>">
				<div class="alert alert-danger">
					<liferay-ui:message arguments="<%= mfaEmailOTPFailedAttemptsRetryTimeout %>" key="maximum-allowed-attempts-error" translateArguments="<%= false %>" />
				</div>
			</c:if>
			
			<div id="<portlet:namespace />phaseOne">
				<div class="portlet-msg-info">
					<liferay-ui:message arguments="<%= GetterUtil.getString(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SEND_TO_ADDRESS_OBFUSCATED)) %>" key="press-the-button-below-to-obtain-your-one-time-password-it-will-be-sent-to-x" translateArguments="<%= false %>" />
				</div>
			</div>
			
			<div id="<portlet:namespace />messageContainer"></div>
			
			<div id="<portlet:namespace />phaseTwo">
				<aui:input cssClass="eli-input" autocomplete="off" label="enter-the-otp-from-the-email" name="otp" showRequiredLabel="yes" />
			</div>
			
			<div class="d-flex justify-content-between" style="align-items: baseline;">
				<aui:button cssClass="edto-btn-primary btn-secondary" id="submitEmailButton" type="submit" value="submit" />
				<aui:button cssClass="edto-btn-primary btn-secondary" id="sendEmailButton" value="send" />
			</div>
		</div>
	</div>
</div>

<aui:script use="aui-base,aui-io-request">
	<liferay-portlet:resourceURL id="/mfa_email_otp_verify/send_mfa_email_otp" portletName="<%= MFAEmailOTPPortletKeys.MFA_EMAIL_OTP_VERIFY %>" var="sendEmailOTPURL" />

	var configuredResendDuration = <%= mfaEmailOTPConfiguration.resendEmailTimeout() %>;

	var failedAttemptsRetryTimeout = <%= mfaEmailOTPFailedAttemptsRetryTimeout %>;

	var countdown;

	var messageContainer = A.one('#<portlet:namespace />messageContainer');

	var sendEmailButton = A.one('#<portlet:namespace />sendEmailButton');

	var submitEmailButton = A.one('#<portlet:namespace />submitEmailButton');

	var originalButtonText = sendEmailButton.text();

	var previousSetTime = <%= GetterUtil.getLong(request.getAttribute(MFAEmailOTPWebKeys.MFA_EMAIL_OTP_SET_AT_TIME)) %>;

	var elapsedTime = Math.floor((Date.now() - previousSetTime) / 1000);

	function <portlet:namespace />createCountdown(f, countdown, interval) {
		return setInterval(() => {
			--countdown;
			f(countdown);
		}, interval);
	}

	function <portlet:namespace />setResendCountdown(resendDuration) {
		if (resendDuration < 1) {
			sendEmailButton.text(originalButtonText);

			sendEmailButton.removeAttribute('disabled');

			clearInterval(countdown);

			messageContainer.html(
				'<span class="alert alert-success"><liferay-ui:message key="your-otp-has-been-sent-by-email" /></span>'
			);
		}
		else {
			sendEmailButton.text(resendDuration);
		}
	}

	if (
		elapsedTime > 0 &&
		elapsedTime < configuredResendDuration &&
		previousSetTime > 0
	) {
		sendEmailButton.setAttribute('disabled', 'disabled');

		var resendDuration = configuredResendDuration - elapsedTime;

		countdown = <portlet:namespace />createCountdown(
			<portlet:namespace />setResendCountdown,
			resendDuration,
			1000
		);
	}

	if (failedAttemptsRetryTimeout > 0) {
		sendEmailButton.setAttribute('disabled', 'disabled');
		submitEmailButton.setAttribute('disabled', 'disabled');

		var originalSubmitButtonText = submitEmailButton.text();

		setInterval(() => {
			--failedAttemptsRetryTimeout;
			{
				if (failedAttemptsRetryTimeout < 1) {
					sendEmailButton.removeAttribute('disabled');

					submitEmailButton.text(originalSubmitButtonText);

					submitEmailButton.removeAttribute('disabled');

					clearInterval(failedAttemptsRetryTimeout);
				}
				else {
					submitEmailButton.text(failedAttemptsRetryTimeout);
				}
			}
		}, 1000);
	}

	A.one('#<portlet:namespace />sendEmailButton').on('click', (event) => {
		sendEmailButton.setAttribute('disabled', 'disabled');

		var resendDuration = <%= mfaEmailOTPConfiguration.resendEmailTimeout() %>;

		countdown = <portlet:namespace />createCountdown(
			<portlet:namespace />setResendCountdown,
			resendDuration,
			1000
		);

		var data = {
			p_auth: Liferay.authToken,
		};

		var setupEmail = A.one('#<portlet:namespace />setupEmail');

		if (setupEmail) {
			data['email'] = setupEmail.val();
		}

		var sendEmailOTPURL = '<%= HtmlUtil.escapeJS(sendEmailOTPURL) %>';

		A.io.request(sendEmailOTPURL, {
			dataType: 'JSON',
			data: data,
			method: 'POST',
			on: {
				failure: function (event, id, obj) {
					var messageContainer = A.one(
						'#<portlet:namespace />messageContainer'
					);

					messageContainer.html(
						'<span class="alert alert-danger"><liferay-ui:message key="failed-to-send-email" /></span>'
					);

					sendEmailButton.text(buttonText);
					sendEmailButton.removeAttribute('disabled');

					clearInterval(interval);
				},
				success: function (event, id, obj) {
					messageContainer.html(
						'<span class="alert alert-success"><liferay-ui:message key="your-otp-has-been-sent-by-email" /> <liferay-ui:message key="please-wait-before-requesting-a-new-otp" /></span>'
					);

					var phaseTwo = A.one('#<portlet:namespace />phaseTwo');
					phaseTwo.disabled = false;
				},
			},
		});
	});
</aui:script>

<style>
.login-card-container {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f8f9fa;
}
.eli-custom-login .login-card {
    width: 100%;
    max-width: 550px;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.eli-custom-login .eli-input {
    height: 35px;
    font-size: 12px;
}

.eli-custom-login label {
    font-size:14px;
}

.eli-custom-login .panel-body{
	padding-left: unset;
    padding-right: unset;
}

.eli-custom-login .eli-login-dynamin-navigation{
	display: inline;
}

form.form{
margin-top:60px;
}
</style>