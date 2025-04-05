<%@ include file="/init.jsp"%>

<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />

<div class="fund-equity250-header mt-4 product-feature">
	<div class="container">
		<div id="<portlet:namespace/>productFeatures">
			<div class="fund-equity250-inner">
				<div class="fund-equity250-heading-box mb-3">
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

					<div class="fund-equity250-customize-wrapper d-flex pb-md-4 pt-md-3"
						style="justify-content: end;">
						<a href="javascript:;"
							id="<portlet:namespace/>showProductRidersBtn"><liferay-ui:message
								key="label-choose-riders" /></a>
					</div>

				</div>
				<div class="edelweiss-tokio-life-tab-group">
					<div class="row">
						<div class="col-md-5">
							<div class="edelweiss-tokio-life-tab">
								<ul class="nav nav-tabs flex-column">
									<c:if test="${not empty customizeFeaturesCard.title}">
										<li class="nav-item"><a class="nav-link active"
											data-toggle="tab" href="#tokio-tab-1">
												<div class="tokio-tab-heading">
													<div class="tokio-dots-left">
														<span class="tokio-dots"></span>
													</div>
													<div class="tokio-tab-txt">
														<c:if test="${not empty customizeFeaturesCard.title}">
															<h5>${customizeFeaturesCard.title}</h5>
														</c:if>
														<c:if test="${not empty customizeFeaturesCard.content}">
															<p>${customizeFeaturesCard.content}</p>
														</c:if>
													</div>
													<div class="tokio-dots-right">
														<svg xmlns="http://www.w3.org/2000/svg" width="28"
															height="28" viewBox="0 0 28 28">
	                                          <g fill="none"
																fill-rule="evenodd" transform="translate(1 .274)">
	                                              <circle cx="13"
																cy="13.726" r="13" stroke="#124093" stroke-width="1.3"></circle>
	                                              <path fill="#124093"
																fill-rule="nonzero" stroke="#124093" stroke-width=".65"
																d="M14.615 9.135a.452.452 0 0 0-.645 0 .455.455 0 0 0 0 .639l3.269 3.269H6.45a.45.45 0 0 0-.451.451c0 .252.2.458.451.458H17.24l-3.27 3.263a.463.463 0 0 0 0 .645c.181.18.472.18.646 0l4.043-4.043a.444.444 0 0 0 0-.638l-4.043-4.044z"></path>
	                                          </g>
	                                      </svg>
													</div>
												</div>
										</a></li>
									</c:if>
									<c:forEach var="cfcNestedCard"
										items="${customizeFeaturesCard.cfcNestedData}">
										<li class="nav-item"><a class="nav-link"
											data-toggle="tab" href="#${cfcNestedCard.enterTabId}">
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
														<svg xmlns="http://www.w3.org/2000/svg" width="28"
															height="28" viewBox="0 0 28 28">
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
										</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="col-md-7">
							<div class="edelweiss-tokio-life-tab-content">
								<div class="tab-content">
									<div class="tab-pane active" id="tokio-tab-1">
										<div class="" id="page-1">
											<div class="fund-tabs">
												<div id="fund-performance-graph"
													class="graph-performance-cont c-maxheight310">
													<div class="fund-list" id="graphone">
														<div class="graph-container">
															<div class="table-view d-none d-block">
																<div class="ng-star-inserted">
																	<div class="table-responsive pd-x-15">
																		<table id="planIncomeBenefitsBreakupTable"
																			class="table table-head scrl-tbl-w scrl-tbl-600 table-border mb-0">
																			<thead>
																				<tr>
																					<th>Policy Year</th>
																					<th>Income Benefit Pay-out</th>
																					<th>Maturity Benefit</th>
																				</tr>
																			</thead>
																			<tbody id="planIncomeBenefitsBreakupTableBody"
																				class="scrl-tbl-hgt m-cust-scroll maturity-table-view">


																			</tbody>
																		</table>

																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<c:forEach var="cfcNestedCard"
										items="${customizeFeaturesCard.cfcNestedData}">
										<div class="tab-pane" id="${cfcNestedCard.enterTabId}">
											<div class="tab-pane-img">
												<c:if test="${not empty cfcNestedCard.featureImage.url}">
													<img class="img-fluid"
														alt="${cfcNestedCard.featureImage.alt}"
														data-fileentryid="${cfcNestedCard.featureImage.fileEntryId}"
														src="${cfcNestedCard.featureImage.url}" />
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

<style>
.white-space-p {
	white-space: initial !important;
}
</style>