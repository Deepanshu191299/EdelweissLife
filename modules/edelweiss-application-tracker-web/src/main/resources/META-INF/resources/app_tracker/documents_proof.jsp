<%@page import="com.liferay.petra.string.StringPool"%>
<%@ include file="/init.jsp"%>

<%
boolean docSubmissionPendingStep = (boolean)request.getAttribute("docSubmissionPending");
String policyNumber1 = (String)request.getAttribute("policyNumber");
%>

<portlet:resourceURL var="getMultipleLoginDataURL" >
	<portlet:param value="multipleLoginData1" name="cmd" ></portlet:param>
</portlet:resourceURL>

<div class="<%= docSubmissionPendingStep ? "active show" : StringPool.BLANK %> tab-pane fade" id="pills-contact" role="tabpanel"
	aria-labelledby="pills-contact-tab">
	<h2>
		<liferay-ui:message key="label-documents-proof" />
	</h2>
	<p>
		<liferay-ui:message key="label-please-upload-documents-only-in-png-jpg-jpeg-pdf-format" />
	</p>
	<form name="<portlet:namespace/>documentSubmissionForm" action=""
		id="<portlet:namespace/>documentSubmissionForm" method="POST">
		<div class="app-file-upload-main-wrapper">
			<div id="age_proof_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-age-proof" />
					</h4>
				</div>
				<div class="file-details">
					<div class="select-container">
						<select id="<portlet:namespace/>age_proof"
							name="<portlet:namespace/>age_proof" tabindex="-1"
							class="select2-hidden-accessible select2-dropClass validate"
							data-placeholder="Document Type"
							aria-hidden="true">
						</select>
					</div>
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>age_proof_file"
									name="<portlet:namespace/>age_proof_file" type="file"
									
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>age_proof_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="age_proof_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="age_proof_file_name_div" class="file__value"
								style='display: none;'>
								<div id="age_proof_file_name" class="file__value--text"></div>
								<div id="age_proof_file_name_size"></div>
								<div id="age_proof_file_name_remove" class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="identity_proof_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-id-proof" />
					</h4>
				</div>
				<div class="file-details">
					<div class="select-container">
						<select id="<portlet:namespace/>identity_proof"
							name="<portlet:namespace/>identity_proof" tabindex="-1"
							class="select2-hidden-accessible select2-dropClass validate"
							data-placeholder="Document Type"
							aria-hidden="true">
						</select>
					</div>
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>identity_proof_file" type="file"
									 name="<portlet:namespace/>identity_proof_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>identity_proof_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="identity_proof_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="identity_proof_file_name_div" class="file__value"
								style='display: none;'>
								<div id="identity_proof_file_name" class="file__value--text"></div>
								<div id="identity_proof_file_name_size"></div>
								<div id="identity_proof_file_name_remove"
									class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="address_proof_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-address-proof" />
					</h4>
				</div>
				<div class="file-details">
					<div class="select-container">
						<select id="<portlet:namespace/>address_proof"
							name="<portlet:namespace/>address_proof" tabindex="-1"
							class="select2-hidden-accessible select2-dropClass validate"
							data-placeholder="Document Type"
							aria-hidden="true">
						</select>
					</div>
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>address_proof_file" type="file"
									 name="<portlet:namespace/>address_proof_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>address_proof_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="address_proof_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="address_proof_file_name_div" class="file__value"
								style='display: none;'>
								<div id="address_proof_file_name" class="file__value--text"></div>
								<div id="address_proof_file_name_size"></div>
								<div id="address_proof_file_name_remove"
									class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- NRI Address Proof-->
			<div id="nri_address_proof_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-nri-address-proof" />
					</h4>
				</div>
				<div class="file-details">
					<div class="select-container">
						<select id="<portlet:namespace/>nri_address_proof"
							name="<portlet:namespace/>nri_address_proof" tabindex="-1"
							class="select2-hidden-accessible select2-dropClass validate"
							data-placeholder="Document Type"
							aria-hidden="true">
						</select>
					</div>
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>nri_address_proof_file" type="file"
									 name="<portlet:namespace/>nri_address_proof_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>nri_address_proof_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="nri_address_proof_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="nri_address_proof_file_name_div" class="file__value"
								style='display: none;'>
								<div id="nri_address_proof_file_name" class="file__value--text"></div>
								<div id="nri_address_proof_file_name_size"></div>
								<div id="nri_address_proof_file_name_remove"
									class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="income_proof_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<span style="font-size: 14px;"><liferay-ui:message key="label-income-proof" /></span>
					
					<span class="image-container1" id="nriSpan" style="display:none;" ><img src="/o/edelweisstokio-theme/images/praposal/info-co.png" alt="icon">
							<div class="tooltip1" >
									<liferay-ui:message key="income-proof-tip" /><br> 
							</div>
					</span>
					
				</div>
				<div class="file-details">
					<div class="select-container">
						<select id="<portlet:namespace/>income_proof"
							name="<portlet:namespace/>income_proof" tabindex="-1"
							class="select2-hidden-accessible select2-dropClass validate"
							data-placeholder="Document Type"
							aria-hidden="true">
						</select>
					</div>
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>income_proof_file" type="file"
									multiple="true" name="<portlet:namespace/>income_proof_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>income_proof_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="income_proof_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="income_proof_file_name_div" style='display: none;'>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="recent_photo_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-recent-photo" />
					</h4>
				</div>
				<div class="file-details">
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>recent_photo_file" type="file"
									 name="<portlet:namespace/>recent_photo_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>recent_photo_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="recent_photo_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="recent_photo_file_name_div" class="file__value"
								style='display: none;'>
								<div id="recent_photo_file_name" class="file__value--text"></div>
								<div id="recent_photo_file_name_size"></div>
								<div id="recent_photo_file_name_remove"
									class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="proposer_age_proof_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-proposer-age-proof" />
					</h4>
				</div>
				<div class="file-details">
					<div class="select-container">
						<select id="<portlet:namespace/>proposer_age_proof"
							name="<portlet:namespace/>proposer_age_proof" tabindex="-1"
							class="select2-hidden-accessible select2-dropClass validate"
							data-placeholder="Document Type"
							aria-hidden="true">
						</select>
					</div>
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>proposer_age_proof_file" type="file"
									name="<portlet:namespace/>proposer_age_proof_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>proposer_age_proof_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="proposer_age_proof_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="proposer_age_proof_file_name_div" class="file__value"
								style='display: none;'>
								<div id="proposer_age_proof_file_name" class="file__value--text"></div>
								<div id="proposer_age_proof_file_name_size"></div>
								<div id="proposer_age_proof_file_name_remove"
									class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="spouse_age_proof_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-spouse-age-proof" />
					</h4>
				</div>
				<div class="file-details">
					<div class="select-container">
						<select id="<portlet:namespace/>spouse_age_proof"
							name="<portlet:namespace/>spouse_age_proof" tabindex="-1"
							class="select2-hidden-accessible select2-dropClass validate"
							data-placeholder="Document Type"
							aria-hidden="true">
						</select>
					</div>
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>spouse_age_proof_file" type="file"
									name="<portlet:namespace/>spouse_age_proof_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>spouse_age_proof_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="spouse_age_proof_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="spouse_age_proof_file_name_div" class="file__value"
								style='display: none;'>
								<div id="spouse_age_proof_file_name" class="file__value--text"></div>
								<div id="spouse_age_proof_file_name_size"></div>
								<div id="spouse_age_proof_file_name_remove"
									class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!--  NRI GST Declaration  -->
			<div id="nri_gst_declaration_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-gst-self-declaration" />
					</h4>
				</div>
				<div class="download-file-details">
					<div class="download-icon" style="padding-top: 44px; padding-left: 44px; ">
						<a href="/documents/d/guest/gst-declaration-form_nri" download="">
							<img src="/o/edelweisstokio-theme/images/download.svg" alt="download" loading="lazy">
							<label class="download-file__input--label" data-text-btn="Download">
						<span style="font-size: 14px; font-family: 'Montserrat-SemiBold'; "><liferay-ui:message key="label-choose-a-file-to-download" /></span>
					</label> </a>
             		</div>
				</div>
								
				<div class="file-details">
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<!-- <div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>nri_gst_declaration_file" type="file"
									 name="<portlet:namespace/>nri_gst_declaration_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>nri_gst_declaration_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div> -->
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>nri_gst_declaration_file" type="file"
									name="<portlet:namespace/>nri_gst_declaration_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>nri_gst_declaration_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="nri_gst_declaration_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="nri_gst_declaration_file_name_div" class="file__value"
								style='display: none;'>
								<div id="nri_gst_declaration_file_name" class="file__value--text"></div>
								<div id="nri_gst_declaration_file_name_size"></div>
								<div id="nri_gst_declaration_file_name_remove"
									class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- GST Certificate --> 
			<div id="gst_certificate_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-gst-certificate" />
					</h4>
				</div>
				<div class="download-file-details">
					<div class="file-details">
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>gst_certificate_file" type="file"
									name="<portlet:namespace/>gst_certificate_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>gst_certificate_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="gst_certificate_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="gst_certificate_file_name_div" class="file__value"
								style='display: none;'>
								<div id="gst_certificate_file_name" class="file__value--text"></div>
								<div id="gst_certificate_file_name_size"></div>
								<div id="gst_certificate_file_name_remove"
									class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
		
		<!-- Passport First & Last Page details for NRI --> 
		<div id="passport_first_last_page_div" class="file-upload-inner-wrapper"
				style='display: none;'>
				<div class="file-title">
					<h4>
						<liferay-ui:message key="label-passport_first_last_page" /> 
					</h4>
				</div>
				<div class="download-file-details">
					<div class="file-details">
					<span> <svg width="41" height="41" viewBox="0 0 41 41"
							fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path
								d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
								fill="#DDDDDD"></path>
                                 <path
								d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
								fill="#DDDDDD"></path>
                              </svg>
					</span>
					<div class="drag-drop-wrapper">
						<div class="file">
							<div class="file__input" id="file__input">
								<input class="file__input--file"
									id="<portlet:namespace/>passport_first_last_page_file" type="file"
									name="<portlet:namespace/>passport_first_last_page_file"
									onChange="saveDocumentDetails(this.files,this.id);"> <label
									class="file__input--label" for="<portlet:namespace/>passport_first_last_page_file"
									data-text-btn="Upload"> <liferay-ui:message
										key="label-drag-and-drop-or" /> <span> <liferay-ui:message
											key="label-choose-a-file-to-upload" />
								</span>
								</label>
								<p>
									<liferay-ui:message key="label-size-limit-3-mb" />
								</p>
							</div>
							<div id="passport_first_last_page_file_name_success" class="uploadSuccess" style='display: none;'>
								<liferay-ui:message key="label-file-uploaded-successfully" />
							</div>
							<div id="passport_first_last_page_file_name_div" class="file__value"
								style='display: none;'>
								<div id="passport_first_last_page_file_name" class="file__value--text"></div>
								<div id="passport_first_last_page_file_name_size"></div>
								<div id="passport_first_last_page_file_name_remove"
									class="file__value--remove"
									onClick="deleteDocumentDetails(this.id);">X</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
			<div class="center-btn">
				<a class="edto-btn-primary"
					onClick="populateDocumentDetails(false);" href="javascript:;">
					<liferay-ui:message key="label-confirm-and-submit" />
				</a>
			</div>
		</div>
	</form>
</div>
<%
String createDateRr = (String) request.getAttribute("uniqueDateObj");
%>

<script>

$(document).ready(function () {
	
	init();
	
	$(".validate").on("change", function(event){
		$("#"+event.target.id + "-error").remove();
	});
	
	$.validator.addMethod('filesize', function (value, element, size) {
		return (element.files[0].size <= size);
	}, 'File size must be less than 3 MB');
	
	$.validator.addMethod('extension', function (value, element, extension) {
	   	var fileName = element.files[0].name
	   	const ext = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length);
	    if(extension.includes(ext)){
	    	return true;
	    }
	    return false;
	}, "Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file");
	
	initDocumentSubmissionFormValidation();
	
	function initDocumentSubmissionFormValidation(){
		
		var summaryFormRuleFields = new Object({}), summaryFormMessageFields = new Object({});
		
		summaryFormRuleFields[portletNamespace+"age_proof"] = {
		    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"age_proof"] = {
				required: 'Please select a value from dropdown'
		};
		
		summaryFormRuleFields[portletNamespace+"identity_proof"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"identity_proof"] = {
				required: 'Please select a value from dropdown'
		};
		
		summaryFormRuleFields[portletNamespace+"address_proof"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"address_proof"] = {
				required: 'Please select a value from dropdown'
		};
		
		summaryFormRuleFields[portletNamespace+"nri_address_proof"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"nri_address_proof"] = {
				required: 'Please select a value from dropdown'
		};
		
		summaryFormRuleFields[portletNamespace+"income_proof"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"income_proof"] = {
				required: 'Please select a value from dropdown'
		};
		
		summaryFormRuleFields[portletNamespace+"proposer_age_proof"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"proposer_age_proof"] = {
				required: 'Please select a value from dropdown'
		};
		
		summaryFormRuleFields[portletNamespace+"spouse_age_proof"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"spouse_age_proof"] = {
			    required: 'Please select a value from dropdown'
		};
		
		summaryFormRuleFields[portletNamespace+"age_proof_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"age_proof_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"recent_photo_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"recent_photo_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"nri_gst_declaration_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"nri_gst_declaration_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"gst_certificate_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"gst_certificate_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"passport_first_last_page_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"passport_first_last_page_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"identity_proof_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"identity_proof_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"address_proof_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"address_proof_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"nri_address_proof_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"nri_address_proof_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"income_proof_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"income_proof_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"proposer_age_proof_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"proposer_age_proof_file"] = {
				 required: 'Please upload a file',
				 extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		summaryFormRuleFields[portletNamespace+"spouse_age_proof_file"] = {
				required: true,
				extension: "png,jpg,jpeg,pdf",
                filesize: 3000000
		};
		
		summaryFormMessageFields[portletNamespace+"spouse_age_proof_file"] = {
			    required: 'Please upload a file',
			    extension:"Please upload only 'png', 'jpg', 'jpeg' & 'pdf' file"
		};
		
		$("#"+portletNamespace+"documentSubmissionForm").validate({
			errorClass: "error",
			errorElement: "div",
			errorPlacement: function( error, element ) {
				error.appendTo(element.parent());
			},
			rules : summaryFormRuleFields,
			messages : summaryFormMessageFields,
		});
	}
	
});
var namespace=$("#portletNamespace").val();
var getMultipleLoginDataURL = "${getMultipleLoginDataURL}";
/**
 * App Tracker Initilazation
 */
init = function(){
	//Populate Address Dropdown Options under Documents Proof
	populateDocumentDetails(true);
	console.log("populateDocumentDetails---------1");
}

function populateDocumentDetails(doPopulateDropdown){
	console.log("inside populateDocumentDetails-------2" + doPopulateDropdown);
	if(doPopulateDropdown != true){
		console.log("inside doPopulateDropdown-------3" + doPopulateDropdown);
		var policyNumber = '<%= policyNumber1 %>';
		console.log("polNumber1 is::" + policyNumber);
		var createDateRr = '<%= createDateRr%>';
		console.log("createDateRr::" + createDateRr);
		var form1 = new FormData();
		form1.append("<portlet:namespace/>" +"policyNumberTest", policyNumber);
		Liferay.Util.fetch(getMultipleLoginDataURL, {
			body: form1,
			method: 'POST',
			async: true
		}).then(function(response) {
			return response.json();
		}).then(function(response) {
			console.log("responsefromMultiple::" + response.loginCreateDate);
			console.log("createDateRr:::"+ createDateRr );
			if((response.loginCreateDate != null) && (createDateRr != response.loginCreateDate)){
				console.log("inside if:::");
				 localStorage.setItem('multipleLoginsDetected', 'true');
				window.location.href='/login';
			}else{
				console.log("inside else::::::");
				showLoader();
			
			const url = documentFetchURL+"?applicationNumber="+applicationNumber;
			Liferay.Util.fetch(url, {
				headers: {
					"Content-Type": "application/json"
				},
				method: "GET",
			})
		
			.then((response) => {
				return response.json();
			})
		
			.then((validationResponse) => {
				
				hideLoader();
				var documentTypes = "";
				var documentTypeCount = [];
				var showSuccessModal = false;
				documentTypes = validationResponse.responseData.documentType;
				console.log("DocumentType from API:::" + documentTypes);
				documentTypes.forEach((docType) => {
					var doc1 = docType;
					doc1 = doc1.replace(/[^a-zA-Z ]/g, "").split(/\s+/).join(' ');
					var doc = doc1.replaceAll(" ","_").toLowerCase();
					$("#" + portletNamespace + doc).select2("val","");
					
				})
				
				var nriCheck = "NRI Address Proof";
				if(documentTypes.indexOf(nriCheck) !== -1){
					 $("#nriSpan").show();
					 }else{
						 $("#nriSpan").hide();
					 }
				
				if(doPopulateDropdown){
					if(documentTypes){
						//Populate Documents Dropdown Options under Documents Proof
						populateDocumentDropdown(documentTypes);				
					}
				}
				
				const uploadedDocuments  = validationResponse.responseData.uploadedDocs;
				console.log("uploaded documents::"+ uploadedDocuments );
				if(uploadedDocuments != null && uploadedDocuments != ""){
					uploadedDocuments.forEach((doc) => {
						if(doc){
							const document_type = doc.document_Type;
							const document_Type_Data_Id = doc.document_Type_DataId;
							const document_Details_Id = doc.document_Details_Id;
							const document_file_name = doc.document_File;
							console.log("documenttype::"+ document_type);
							console.log("documenttype_FileName::"+ document_file_name);
							console.log("documentTypes::"+ documentTypes);
							
							/*  console.log("doc for Passport:"+ doc.toUpperCase() +"::::::::::"+document_type.toUpperCase());*/
							if(doPopulateDropdown || documentTypes.findIndex(item => document_type.toUpperCase() === item.toUpperCase()) !== -1 ){
								if(documentTypeCount.indexOf(document_type) === -1) {
									documentTypeCount.push(document_type);
								}
								
								if(document_type.toUpperCase() == "INCOME PROOF"){
									
									const fieldName = document_type.replaceAll(" ", "_").toLowerCase() + "_file_name";
									var allFilesDiv = document.getElementById(fieldName+"_div");
									
									if(!document.getElementById(fieldName + document_Details_Id + "_details")){
										
										const fileDetailsDiv = document.createElement("div");
										fileDetailsDiv.setAttribute("id", fieldName + document_Details_Id + "_details");
										fileDetailsDiv.className="file__value";
										
										const fileNameDiv = document.createElement("div");
										fileNameDiv.setAttribute("id", fieldName + document_Details_Id);
										fileNameDiv.className="file__value--text";
										fileDetailsDiv.appendChild(fileNameDiv);
										
										const fileNameSizeDiv = document.createElement("div");
										fileNameSizeDiv.setAttribute("id", fieldName + document_Details_Id + "_size");
										fileDetailsDiv.appendChild(fileNameSizeDiv);
										
										const fileNameRemoveDiv = document.createElement("div");
										fileNameRemoveDiv.setAttribute("id", fieldName + document_Details_Id +"_remove");
										fileNameRemoveDiv.className="file__value--remove";
										fileNameRemoveDiv.setAttribute("onClick", "deleteDocumentDetails(this.id);");
										fileNameRemoveDiv.innerHTML = "X";
										fileDetailsDiv.appendChild(fileNameRemoveDiv);
										
										allFilesDiv.appendChild(fileDetailsDiv);
									}
									
									$("#" + fieldName + document_Details_Id).data('documentType',document_type);
									$("#" + fieldName + document_Details_Id).data('documentTypeDataId',document_Type_Data_Id);
									$("#" + fieldName + document_Details_Id).data('documentDetailsId',document_Details_Id);
									$("#" + fieldName + document_Details_Id).html(document_file_name);
									$("#" + fieldName+"_div").show();
									$("#" + fieldName+"_success").hide();
								}else{
									const fieldName = document_type.replaceAll(" ", "_").toLowerCase() + "_file_name";
									$("#" + fieldName).data('documentType',document_type);
									$("#" + fieldName).data('documentTypeDataId',document_Type_Data_Id);
									$("#" + fieldName).data('documentDetailsId',document_Details_Id);
									$("#" + fieldName).html(document_file_name);
									$("#" + fieldName+"_div").show();
									$("#" + fieldName+"_success").hide();
								}
							}else{
								$("#responseMessage").html("Please Upload all Documents.");
								openModal();
								return false;
							}
						}
					})
					showSuccessModal = true;
				}
				
				if(!doPopulateDropdown && (documentTypes.length != documentTypeCount.length)){
					console.log("documenttype lenght::"+documentTypes.length +"doPopulateDropdown::" + doPopulateDropdown);
					console.log("documentTypeCount::"+ documentTypeCount.length);
					$("#responseMessage").html("Please Upload all Documents.");
					openModal();
					return false;
				}else if(!doPopulateDropdown && showSuccessModal){
					$("#responseMessage").html("All Documents Uploaded Sucessfully.");
					openModal();
				}
				
			})

				.catch(function(error) {
					console.error(error);
					$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
					$("#closeBtn").attr("onclick","closeModal(false)");
					hideLoader();
					openModal();
				});
				}
				}).catch(function(exception) {
					console.error("Error in Update API");
					console.error(exception);
				});
}else{
	console.log("inside else during function1------");
	showLoader();
	
	const url = documentFetchURL+"?applicationNumber="+applicationNumber;
	Liferay.Util.fetch(url, {
		headers: {
			"Content-Type": "application/json"
		},
		method: "GET",
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		
		hideLoader();
		var documentTypes = "";
		var documentTypeCount = [];
		var showSuccessModal = false;
		documentTypes = validationResponse.responseData.documentType;
		console.log("DocumentType from API:::" + documentTypes);
		documentTypes.forEach((docType) => {
			var doc1 = docType;
			doc1 = doc1.replace(/[^a-zA-Z ]/g, "").split(/\s+/).join(' ');
			var doc = doc1.replaceAll(" ","_").toLowerCase();
			$("#" + portletNamespace + doc).select2("val","");
			
		})
		
		var nriCheck = "NRI Address Proof";
		if(documentTypes.indexOf(nriCheck) !== -1){
			 $("#nriSpan").show();
			 }else{
				 $("#nriSpan").hide();
			 }
		
		if(doPopulateDropdown){
			if(documentTypes){
				//Populate Documents Dropdown Options under Documents Proof
				populateDocumentDropdown(documentTypes);				
			}
		}
		
		const uploadedDocuments  = validationResponse.responseData.uploadedDocs;
		console.log("uploaded documents::"+ uploadedDocuments );
		if(uploadedDocuments != null && uploadedDocuments != ""){
			uploadedDocuments.forEach((doc) => {
				if(doc){
					const document_type = doc.document_Type;
					const document_Type_Data_Id = doc.document_Type_DataId;
					const document_Details_Id = doc.document_Details_Id;
					const document_file_name = doc.document_File;
					console.log("documenttype::"+ document_type);
					console.log("documenttype_FileName::"+ document_file_name);
					console.log("documentTypes::"+ documentTypes);
					
					/*  console.log("doc for Passport:"+ doc.toUpperCase() +"::::::::::"+document_type.toUpperCase());*/
					if(doPopulateDropdown || documentTypes.findIndex(item => document_type.toUpperCase() === item.toUpperCase()) !== -1 ){
						if(documentTypeCount.indexOf(document_type) === -1) {
							documentTypeCount.push(document_type);
						}
						
						if(document_type.toUpperCase() == "INCOME PROOF"){
							
							const fieldName = document_type.replaceAll(" ", "_").toLowerCase() + "_file_name";
							var allFilesDiv = document.getElementById(fieldName+"_div");
							
							if(!document.getElementById(fieldName + document_Details_Id + "_details")){
								
								const fileDetailsDiv = document.createElement("div");
								fileDetailsDiv.setAttribute("id", fieldName + document_Details_Id + "_details");
								fileDetailsDiv.className="file__value";
								
								const fileNameDiv = document.createElement("div");
								fileNameDiv.setAttribute("id", fieldName + document_Details_Id);
								fileNameDiv.className="file__value--text";
								fileDetailsDiv.appendChild(fileNameDiv);
								
								const fileNameSizeDiv = document.createElement("div");
								fileNameSizeDiv.setAttribute("id", fieldName + document_Details_Id + "_size");
								fileDetailsDiv.appendChild(fileNameSizeDiv);
								
								const fileNameRemoveDiv = document.createElement("div");
								fileNameRemoveDiv.setAttribute("id", fieldName + document_Details_Id +"_remove");
								fileNameRemoveDiv.className="file__value--remove";
								fileNameRemoveDiv.setAttribute("onClick", "deleteDocumentDetails(this.id);");
								fileNameRemoveDiv.innerHTML = "X";
								fileDetailsDiv.appendChild(fileNameRemoveDiv);
								
								allFilesDiv.appendChild(fileDetailsDiv);
							}
							
							$("#" + fieldName + document_Details_Id).data('documentType',document_type);
							$("#" + fieldName + document_Details_Id).data('documentTypeDataId',document_Type_Data_Id);
							$("#" + fieldName + document_Details_Id).data('documentDetailsId',document_Details_Id);
							$("#" + fieldName + document_Details_Id).html(document_file_name);
							$("#" + fieldName+"_div").show();
							$("#" + fieldName+"_success").hide();
						}else{
							const fieldName = document_type.replaceAll(" ", "_").toLowerCase() + "_file_name";
							$("#" + fieldName).data('documentType',document_type);
							$("#" + fieldName).data('documentTypeDataId',document_Type_Data_Id);
							$("#" + fieldName).data('documentDetailsId',document_Details_Id);
							$("#" + fieldName).html(document_file_name);
							$("#" + fieldName+"_div").show();
							$("#" + fieldName+"_success").hide();
						}
					}else{
						$("#responseMessage").html("Please Upload all Documents.");
						openModal();
						return false;
					}
				}
			})
			showSuccessModal = true;
		}
		
		if(!doPopulateDropdown && (documentTypes.length != documentTypeCount.length)){
			console.log("documenttype lenght::"+documentTypes.length +"doPopulateDropdown::" + doPopulateDropdown);
			console.log("documentTypeCount::"+ documentTypeCount.length);
			$("#responseMessage").html("Please Upload all Documents.");
			openModal();
			return false;
		}else if(!doPopulateDropdown && showSuccessModal){
			$("#responseMessage").html("All Documents Uploaded Sucessfully.");
			openModal();
		}
		
	})

	.catch(function(error) {
		console.error(error);
		$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
		$("#closeBtn").attr("onclick","closeModal(false)");
		hideLoader();
		openModal();
	});
	
}
}

function populateDocumentDropdown(documentTypes){
	console.log("insid ethe populateDocumentDropDown::");
	documentTypes.forEach((doc) => {

		var doc1 = doc;
		doc1 = doc1.replace(/[^a-zA-Z ]/g, "").split(/\s+/).join(' '); 
		const fieldName = doc1.replaceAll(" ", "_").toLowerCase();
		if(doc=="NRI Address Proof"){
			doc = "Address Proof";
		}
		
		const url = documentMasterOptionsURL + "?type="+doc;
		
		$("#"+fieldName + "_div").show();
		Liferay.Util.fetch(url, {
			headers: {
				"Content-Type": "application/json"
			},
			method: "POST",
		})

		.then((response) => {
			return response.json();
		})

		.then((validationResponse) => {
			console.log(validationResponse);
			if(validationResponse != null && validationResponse.status){
				const options = validationResponse.responseData.data;
				const dropdownOptions = [];
				dropdownOptions.push(
						"<option value=''>Document Type</option>"
				);
				
                // Default handling for other document types
                options.forEach((option) => {
                    dropdownOptions.push("<option value='" + option.value + "'>" + option.name + "</option>");
                });
                
				const inputField = document.getElementById(portletNamespace + fieldName);
				inputField.innerHTML = inputField.innerHTML + dropdownOptions.join('');
			}
		})

		.catch(function(error) {
			console.error(error);
			$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
			$("#closeBtn").attr("onclick","closeModal(false)");
			hideLoader();
			openModal();
		});
	})
}

function saveDocumentDetails(files, fieldId){
	for(var j = 0; j < files.length; j++){

	const file = files[j];
	const document_type = fieldId.replace("_file", "").replace(portletNamespace, "").replaceAll("_"," ").toUpperCase();
	const document_type_data_id = $("#"+ fieldId.replace("_file", "")).val();
	if(fieldId.includes("recent_photo") || fieldId.includes("nri_gst_declaration") || fieldId.includes("gst_certificate") || fieldId.includes("passport_first_last_page") || $("#"+portletNamespace+"documentSubmissionForm").validate().element(":input[name='"+fieldId.replace("_file", "")+"']")){
		console.log("inside saveDocumentDetails");
		if($("#"+portletNamespace+"documentSubmissionForm").validate().element(":input[name='"+fieldId+"']")) {
			showLoader();
		
			const parameterJSON = JSON.stringify({
				"application_number": applicationNumber, 
				"document_type_data_id": Number(document_type_data_id),
				"document_type": titleCase(document_type)
			});
			
			
			const formData = new FormData();
			formData.append('parameters', parameterJSON);
			formData.append('files', file);
			
			Liferay.Util.fetch(saveDocumentURL, {
				method : "POST",
				body : formData
			})
		
			.then((response) => {
				return response.json();
			})
		
			.then((validationResponse) => {
				if(validationResponse != null && validationResponse.status){
					console.log("True - Successfully Sumitted");
					if(validationResponse.responseData != null & validationResponse.responseData.status){
						$("#" + fieldId.replace(portletNamespace, "")+"_name_success").show();
					}else if(validationResponse.responseData != null & !validationResponse.responseData.status){
						$("#responseMessage").html(validationResponse.responseData.message);
						openModal();
					}
				}else if(validationResponse != null && !validationResponse.status){
					console.debug("false - API Exception");
					$("#responseMessage").html(validationResponse.responseData);
					openModal();
					return false;
				}
				hideLoader();
			})
		
			.catch(function(error) {
				console.error(error);
				$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
				$("#closeBtn").attr("onclick","closeModal(false)");
				hideLoader();
				openModal();
			});
		}
	}else{
		$("#"+fieldId).val("");
	}
	}
}

function deleteDocumentDetails(fieldId){

	showLoader();
	
	const fieldName = fieldId.replace("_remove","");
	const fileInputId = fieldId.split("_name")[0];
	const document_details_id = $("#" + fieldName).data('documentDetailsId');
	const reqJSON = JSON.stringify({
				"application_number": applicationNumber, 
				"document_details_id": Number(document_details_id)
			});
	
	Liferay.Util.fetch(deleteDocumentURL, {
		headers : {
			"Content-Type": "application/json"
		},
		method : "POST",
		body : reqJSON
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.log(validationResponse);
		if(validationResponse != null && validationResponse.status){
			if(validationResponse.responseData.status){
				
				$("#" + fieldName).data('documentType',"");
				$("#" + fieldName).data('documentTypeDataId',"");
				$("#" + fieldName).data('documentDetailsId',"");
				$("#" + portletNamespace + fileInputId).val("");
				$("#" + fieldName).html("");
				$("#" + fieldName + "_details").hide();
				$("#" + fieldName + "_div").hide();
			}
		}
		hideLoader();
	})

	.catch(function(error) {
		console.error(error);
		$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
		$("#closeBtn").attr("onclick","closeModal(false)");
		hideLoader();
		openModal();
	});
}

function titleCase(str) {
	   var splitStr = str.toLowerCase().split(' ');
	   for (var i = 0; i < splitStr.length; i++) {
	       splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1);     
	   }
	   return splitStr.join(' '); 
	}
</script>
<style>
    /* Style for the tooltip */
    .image-container1 {
        position: relative;
        display: inline-block;
    }

    .tooltip1 {
        display: none;
        position: absolute;
        background-color: #333;
        color: #fff;
        padding: 5px;
        border-radius: 5px;
        z-index: 1;
        top: 100%; /* Position it above the icon */
        left: 200%; /* Center it horizontally */
        transform: translateX(-50%);
        width: 240px;
        font-size: 14px;
        
    }

    .image-container1:hover .tooltip1 {
        display: block;
    }
</style>