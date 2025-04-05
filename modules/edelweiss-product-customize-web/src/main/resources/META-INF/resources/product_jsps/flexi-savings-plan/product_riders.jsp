<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@ include file="/init.jsp"%>

<portlet:resourceURL id="/edelweiss/guaranteed-savings-star/generate-bi" var="guaranteedSavingsStarGenerateBIURL" />

<%
	
	JSONObject liferayProductNestedData = null;
	if(Validator.isNotNull(renderRequest.getAttribute("liferayProductNestedData")))
	{
		liferayProductNestedData = (JSONObject)renderRequest.getAttribute("liferayProductNestedData");
	}
	JSONArray productRidersList = liferayProductNestedData.getJSONArray("riders");
	
	JSONObject riderValidations = null;
	if(Validator.isNotNull(renderRequest.getAttribute("riderValidations")))
	{
		riderValidations = (JSONObject)renderRequest.getAttribute("riderValidations");
	}
	
	
%>
<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />
<c:if test="<%=Validator.isNotNull(productRidersList) && productRidersList.length()>0%>">
	<div id="<portlet:namespace/>custome-plan" style="display: none" class="">
	<section class="equity-250 bg-gray rider-section">
	<div class="container">
		<div class="custom-link d-flex pb-2" style="justify-content: end;">
			<a href="javascript:;" class="pointer customize-btn"  onclick="showProductFeatures()" id=""><liferay-ui:message key="label-show-features"/></a>
		</div>
		<h2 class="fw700 fs28 text-center  pb-4">Add Riders to your Plan</h2>
		<div class="container customizeplan custmplan gcap-cst-plan" id="custome-plan">
		<div id="<portlet:namespace/>productRidersContainer" class="custome-plan row">
			<div id="<portlet:namespace/>productRiders" class="col-md-8 col-sm-12">
			<div class="custom-card-wrapper benefit-card row">
		
				<%for(int i=0; i<productRidersList.length();i++){ 
					JSONObject productRider=productRidersList.getJSONObject(i);
					String riderId=productRider.getString("riderId").replace(StringPool.SPACE, StringPool.BLANK);
					if(!productRider.getBoolean("isActive"))
					{
						continue;
					}
				%>
					<div id="<portlet:namespace/>rider_<%=riderId %>" class="col-12 col-md-6 productRider p-0 m-0">
					<div class="m-2 custom-card">
						<div class="d-flex justify-content-between" style="column-gap: 10px;align-items: flex-start;">
							<h2><%=productRider.getString("title", StringPool.BLANK) %></h2>
							  <div class="edto-check-content p-0 mx-0 mt-1" style=" width: auto; ">
							  	<div class="form-group">
									<input name="<portlet:namespace/>selectedRiders" type="checkbox" value="<%=riderId %>" id="<portlet:namespace/><%="selectedRider_"+riderId %>" 
										data-rider-price="" data-rider-name="<%=productRider.getString("title", StringPool.BLANK) %>" 
										data-rider-key="<%=productRider.getString("key", StringPool.BLANK) %>" class="riderSelectionInput"/>
									<label class="form-check-label" for="<portlet:namespace/><%="selectedRider_"+riderId %>"></label>
								</div>
							</div>
						</div>
						<div id="<portlet:namespace/>riderBrochure_<%=riderId %>" class="">
							<c:if test="<%=Validator.isNotNull(productRider.getJSONObject("brochure")) && 
								Validator.isNotNull(productRider.getJSONObject("brochure").getJSONObject("link"))%>" >
								<a href="<%=productRider.getJSONObject("brochure").getJSONObject("link").getString("href","#") %>" 
									download="<%=productRider.getJSONObject("brochure").getJSONObject("link").getString("href",StringPool.BLANK)  %>">
										<liferay-ui:message key="label-download-brochure"/>
								</a>
							</c:if>
						</div>
						<div id="<portlet:namespace/>riderDescription<%=riderId %>">
							<%=productRider.getString("description", StringPool.BLANK) %>
						</div>
						<div id="<portlet:namespace/>riderPremiumDetails_<%=riderId %>" class="row mt-2 price-tile">
							<div id="<portlet:namespace/>riderPriceDetails_<%=riderId %>" class="col-6 custom-price">
							<p>	<span>@</span><span id="<portlet:namespace/>riderPriceAmount_<%=riderId %>"></span></p>
							</div>
							
							<div>
							</div>
							<c:if test="<%=Validator.isNotNull(riderValidations.getJSONObject(riderId)) && 
									Validator.isNotNull(riderValidations.getJSONObject(riderId).getJSONArray("investmentOptions")) &&
									riderValidations.getJSONObject(riderId).getJSONArray("investmentOptions").length()>0 %>" >
								<div id="<portlet:namespace/>riderPremium_<%=riderId %>" class="col-6">
									<aui:select name="<%="riderPremiumAmount_"+riderId %>" label="" cssClass="riderPremiumInput onlyBottomBorder" data-rider-id="<%=riderId %>" title="">
										<%
											for(int count=0;count<riderValidations.getJSONObject(riderId).getJSONArray("investmentOptions").length();count++){
										%>
												<aui:option value="<%=riderValidations.getJSONObject(riderId).getJSONArray("investmentOptions").getJSONObject(count).getString("key") %>"
												data-amount="<%=riderValidations.getJSONObject(riderId).getJSONArray("investmentOptions").getJSONObject(count).getString("key") %>" 
												cssClass="productAmounts">
													<%=riderValidations.getJSONObject(riderId).getJSONArray("investmentOptions").getJSONObject(count).getString("name") %>
												</aui:option>
										<%
											}
										%>
										
									</aui:select>
								</div>
							</c:if>
							<c:if test="<%=productRider.getString("key", StringPool.BLANK).equalsIgnoreCase("PayorWaiverBenefitRider") %>" >
								<div id="<portlet:namespace/>PayorWaiverBenefitPremium_<%=riderId %>" class="col-6">
									<aui:select name="<%="payorWaiverBenefitOption" %>" label="" cssClass="riderPremiumInput onlyBottomBorder" data-rider-id="<%=riderId %>">
										<aui:option value="" >
											<liferay-ui:message key="label-select-payor-waiver-benefit-option"/>
										</aui:option>
										<c:forEach var="payorWaiverBenefitOption" items="${payorWaiverBenefitOptions}">
											<aui:option value="${payorWaiverBenefitOption.key}" >
												${payorWaiverBenefitOption.value}
											</aui:option>
										</c:forEach>
									</aui:select>
								</div>
							</c:if>
						</div>
						
						<div class="family-income-benefit-error overlay-card card-body my-family-body" id="<portlet:namespace/>familyIncomeBenefitError_<%=riderId %>" style="display:none">
 							<p class="overlay-card-text"><liferay-ui:message key="label-not-applicable-with-family-income-benefit-opt" arguments="<%= productRider.getString("title", StringPool.BLANK)  %>"/></p>
						</div>
						
						<div class="available-for-myself overlay-card card-body my-family-body" id="<portlet:namespace/>forMySelf_<%=riderId %>" style="display:none">
							<p class="overlay-card-text">	<liferay-ui:message key="label-only-for-myself-investmentes" arguments="<%= productRider.getString("title", StringPool.BLANK)  %>"/>	</p>					
						</div>
						<div class="available-for-family overlay-card card-body my-family-body" id="<portlet:namespace/>forFamily_<%=riderId %>" style="display:none">
							<p class="overlay-card-text">	<liferay-ui:message key="label-only-for-family-investmentes" arguments="<%= productRider.getString("title", StringPool.BLANK)  %>"/>	</p>				
						</div>
						<div class="available-for-family overlay-card card-body my-family-body" id="<portlet:namespace/>criticalIllnessRiderError_<%=riderId %>" style="display:none">
                            <p class="overlay-card-text">        <liferay-ui:message key="label-for-critical-illness-rider-condition" arguments="<%= productRider.getString("title", StringPool.BLANK)  %>"/>        </p>                                
                    	</div>
                    	<div class="family-income-benefit-error overlay-card card-body my-family-body" id="<portlet:namespace/>WOPAgeError_<%=riderId %>" style="display:none">
 							<p class="overlay-card-text"><liferay-ui:message key="label-not-applicable-with-WOP-age" arguments="<%= productRider.getString("title", StringPool.BLANK)  %>"/></p>
						</div>
						
					</div>
					</div>
				<%} %>
				</div>
			</div>
			<div id="<portlet:namespace/>selectedPlanDetails" class="col-12 col-md-4 col-sm-12">
			<div class="mt-2">
			<div class="blue-bg">
				<h3>
					<liferay-ui:message key="lable-your-plan"/>
				</h3>
				</div>
				<div class="pre-benefits">
				<div class="pre-benefits_row base-plan d-flex p-3" id="<portlet:namespace/>basePlanPrice" data-base-plan-price="" >
					<liferay-ui:message key="label-base-plan"/>
					<span id="<portlet:namespace/>basePlanPriceAmount"></span>
				</div>
				<div class="pre-benefits benefit-brkup_row d-flex p-3 flex-column" id="<portlet:namespace/>selectedRidersList">
					<strong><liferay-ui:message key="label-additional-riders" /></strong>
					<div id="<portlet:namespace/>selectedRidersListContainer">
						
					</div>
				</div>
				</div>
				<div class="checkoutPage" id="<portlet:namespace/>premiumPayDetails" data-total-price="">
					<liferay-ui:message key="label-pay-premium"/>
					<span id="<portlet:namespace/>totalPayPremiumAmount"><liferay-ui:message key="symbol-rupee"/>0</span>
					<liferay-ui:message key="label-incl-gst"/>
				</div>
			</div>
			</div>
		</div>
	</div>
	</div>
		</section>
	</div>

</c:if>

<script>
	var customizePageDetails = new Object({});
	customizePageDetails.generateBiUrl="${guaranteedSavingsStarGenerateBIURL}";
	const riderValidations=new Map(Object.entries(parseJSONString('${riderValidations}')));
	var prodRidersList = <%=productRidersList%>;
</script>