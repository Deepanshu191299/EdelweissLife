<%@ include file="/init.jsp"%>
<div class="modal wealth-modal" tabindex="-1" role="dialog" id="errorMessageModal" style="display: none;">
	<div class="modal-dialog modal-dialog-centered popup-container modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header m-1 p-1">
					<h2 class="fs22 fontbold  w-100">
						<liferay-ui:message key="error" />
					</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">
						<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">
    <path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>
</svg>
						</span>
						
					</button>
				</div>
				<div id="errorMessageContainer">
					<liferay-ui:message key="service-unavailable-error-msg" />
				</div>
				<div class="modal-footer p-1" style="border: unset;">
					<button type="button" class="edto-btn-primary" data-dismiss="modal">
						<liferay-ui:message key="ok" />
					</button>
				</div>
			</div>
		</div>
	</div>
</div>