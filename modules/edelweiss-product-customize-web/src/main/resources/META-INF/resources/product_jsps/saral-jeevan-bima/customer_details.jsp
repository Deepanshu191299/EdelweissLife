<%@ include file="/init.jsp" %>


<div class="edelweissTokioDetails p-2">
	<div class="container">
		<div class="edelweissTokioDetailsInner d-flex align-items-center">
			<div class="edelweissTokioHeading">
				<h5 class="fontbold"><liferay-ui:message key="label-your-details"/></h5>
			     <h5 class="fontbold d-block d-md-none d-lg-none basic-details"><liferay-ui:message key="label-basic-details"/></h5>
			</div>
			 <div class="edelweissTokioFormDetails d-none d-md-block">
				<ul class="nav" id="personal-details">
					<li class="nav-item">${basicDetailsMap['fullName']}</li>
					<li class="nav-item">${basicDetailsMap['genderValue']}</li>
					<li class="nav-item">${basicDetailsMap['dateOfBirth']}</li>
					<li class="nav-item">${basicDetailsMap['smokerDetails']}</li>
					<li class="nav-item">${basicDetailsMap['mobileNumber']}</li>
					<li class="nav-item">${basicDetailsMap['email']}</li>
					<li class="nav-item amount-with-rupee" data-amount="${basicDetailsMap['annualIncome']}">${basicDetailsMap['annualIncome']}</li>
					<li class="nav-item">${basicDetailsMap['age']} Years</li>
				</ul>
			</div> 
			<div class="editModel ml-md-auto">
				<span name="editDetails" id="editDetails"> 
					<svg xmlns="http://www.w3.org/2000/svg" width="42" height="22" viewBox="0 0 22 22">
						<path fill="#999" fill-rule="nonzero" stroke="#999" stroke-width=".3"
							d="M19.705 2.295a4.41 4.41 0 0 0-6.243 0L2.016 13.738a.573.573 0 0 0-.162.328l-.848 6.282a.57.57 0 0 0 .162.482c.106.106.255.17.404.17.026 0 .051 0 .077-.004l6.225-.825c.315-.042.474-.332.432-.647a.576.576 0 0 0-.648-.495l-5.412.722.592-4.381 4.61 4.611c.107.107.194.17.343.17.15 0 .36-.059.467-.17L19.705 8.538A4.388 4.388 0 0 0 21 5.414a4.37 4.37 0 0 0-1.295-3.12zm-6.147 1.22l1.922 1.921L5.035 15.882 3.113 13.96 13.558 3.514zm-5.825 15.06l-1.88-1.879L16.298 6.251l1.88 1.879L7.732 18.576zM18.979 7.308L14.38 2.71a3.255 3.255 0 0 1 2.08-.746c.874 0 1.692.341 2.31.955.618.613.954 1.436.954 2.31 0 .771-.264 1.496-.745 2.08z"></path> 
					</svg>
				</span>
			</div>
			<div id="<portlet:namespace/>planBrochureContainer" class="brochure-link d-block d-md-none d-lg-none ml-auto pl-2" style="white-space: nowrap;">
				<a href="${productCustomizeSummaryData['productBrochure'].link.href}" 
					id="<portlet:namespace/>planBrochure" download="${productCustomizeSummaryData['productBrochure'].link.label}">
						<liferay-ui:message key="label-download-brochure"/>
				</a>
			</div>
		 </div>
	</div>
</div>