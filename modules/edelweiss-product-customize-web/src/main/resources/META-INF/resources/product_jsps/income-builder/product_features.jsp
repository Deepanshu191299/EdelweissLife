<%@ include file="/init.jsp"%>

<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />

<div id="<portlet:namespace/>productFeatures">
	<section class="fund-equity250-header mt-4 product-feature">
		<div class="container">
		<div class="fund-equity250-inner">
			<div class="fund-equity250-header flex-wrap">
				<div class="fund-equity250-heading-box">
					<div class="fund-equity250-content">
						<c:if test="${not empty customizeFeaturesCard.sectionTagLine}">
					    	<p class="d-cstm-block">${customizeFeaturesCard.sectionTagLine}</p>
					  	</c:if>
						<h5 class="recomendedFundsStrongTag fs24 fontsemi">${customizeFeaturesCard.sectionTitle}</h5>
						<p class="d-cstm-block">${customizeFeaturesCard.sectionSubTitle}</p>
						<c:if test="${not empty customizeFeaturesCard.shortNote}">
					    	<p class="cstm-block-note">${customizeFeaturesCard.shortNote}</p>
					  	</c:if>
					</div>
				</div>

				<div class="fund-equity250-customize-wrapper d-flex pb-md-4 pt-md-3" style="justify-content: end;">
					<a href="javascript:;" id="<portlet:namespace/>showProductRidersBtn"><liferay-ui:message key="label-choose-riders" /></a>
				</div>
			</div>
			
			<div id="<portlet:namespace/>productFeaturesContainer" class="plan-features row">
				<div class="bg-white equity-card" id="equity" style="">
					<div class="col-12">
						<div class="edelweiss-tokio-life-tab-group" id="<portlet:namespace/>productFeaturesContainer">
					      <div class="row">
					          <div class="col-md-5">
					              <div class="edelweiss-tokio-life-tab">
					                  <ul class="nav nav-tabs flex-column">
					                      <c:forEach var="cfcNestedCard" items="${customizeFeaturesCard.cfcNestedData}" varStatus="loop">
												<li class="nav-item">
													<a class="nav-link ${loop.index == 0 ? 'active' : ''}" data-toggle="tab" href="#${cfcNestedCard.enterTabId}">
														<div class="tokio-tab-heading">
															<div class="tokio-dots-left">
																<span class="tokio-dots"></span>
															</div>
															<div class="tokio-tab-txt">
																<c:if test="${not empty cfcNestedCard.cfcTitle}">
																    <h5>${cfcNestedCard.cfcTitle}</h5>
																</c:if>
																<c:if test="${not empty cfcNestedCard.cfcContent}">
																    <p>${cfcNestedCard.cfcContent}</p>
																</c:if>
															</div>
															<div class="tokio-dots-right">
																<svg xmlns="http://www.w3.org/2000/svg" width="28" height="28"
																	viewBox="0 0 28 28">
						                                          <g fill="none"
																		fill-rule="evenodd" transform="translate(1 .274)">
						                                              <circle cx="13"
																		cy="13.726" r="13" stroke="#124093" stroke-width="1.3" />
						                                              <path fill="#124093"
																		fill-rule="nonzero" stroke="#124093" stroke-width=".65"
																		d="M14.615 9.135a.452.452 0 0 0-.645 0 .455.455 0 0 0 0 .639l3.269 3.269H6.45a.45.45 0 0 0-.451.451c0 .252.2.458.451.458H17.24l-3.27 3.263a.463.463 0 0 0 0 .645c.181.18.472.18.646 0l4.043-4.043a.444.444 0 0 0 0-.638l-4.043-4.044z" />
						                                          </g>
						                                      </svg>
															</div>
														</div>
													</a>
												</li>
										</c:forEach>
					                  </ul>
					              </div>
					          </div>
					          <div class="col-md-7">
					              <div class="edelweiss-tokio-life-tab-content">
					                  <div class="tab-content">
					                    <c:forEach var="cfcNestedCard" items="${customizeFeaturesCard.cfcNestedData}" varStatus="imgLoop">
					                    	<div class="tab-pane ${imgLoop.index == 0 ? 'active' : ''}" id="${cfcNestedCard.enterTabId}">
											 	<div class="tab-pane-img">
											 		<c:if test="${not empty cfcNestedCard.featureImage.url}">
													    <img class="img-fluid" alt="${cfcNestedCard.featureImage.alt}" data-fileentryid="${cfcNestedCard.featureImage.fileEntryId}" src="${cfcNestedCard.featureImage.url}" />
													</c:if>
												</div>
											</div>
										</c:forEach>
					                  </div>
					              </div>
					          </div>
					      </div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	</section>
</div>