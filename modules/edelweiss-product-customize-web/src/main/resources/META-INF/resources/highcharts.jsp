 <div class="edelweiss-tokio-life-tab-group">
      <div class="row">
          <div class="col-md-5">
              <div class="edelweiss-tokio-life-tab">
                  <ul class="nav nav-tabs flex-column">
                  	  <c:if test="${not empty customizeFeaturesCard.title}">
                  	      <li class="nav-item">
	                          <a class="nav-link active" data-toggle="tab" href="#tokio-tab-1">
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
	                                      <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" viewBox="0 0 28 28">
	                                          <g fill="none" fill-rule="evenodd" transform="translate(1 .274)">
	                                              <circle cx="13" cy="13.726" r="13" stroke="#124093" stroke-width="1.3"></circle>
	                                              <path fill="#124093" fill-rule="nonzero" stroke="#124093" stroke-width=".65" d="M14.615 9.135a.452.452 0 0 0-.645 0 .455.455 0 0 0 0 .639l3.269 3.269H6.45a.45.45 0 0 0-.451.451c0 .252.2.458.451.458H17.24l-3.27 3.263a.463.463 0 0 0 0 .645c.181.18.472.18.646 0l4.043-4.043a.444.444 0 0 0 0-.638l-4.043-4.044z"></path>
	                                          </g>
	                                      </svg>
	                                  </div>
	                              </div>
	                          </a>
	                      </li>
                  	  </c:if>
                      <c:forEach var="cfcNestedCard" items="${customizeFeaturesCard.cfcNestedData}">
							<li class="nav-item">
								<a class="nav-link" data-toggle="tab" href="#${cfcNestedCard.enterTabId}">
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
                      <div class="tab-pane active" id="tokio-tab-1">
                          <div class="tokio-life-tab-pane">
                              <div class="tokio-tab-pane">
                                  <ul class="nav nav-tabs mb-4">
                                      <li class="nav-item">
                                          <a class="nav-link active" data-toggle="tab" href="javascript:void(0);" id="<portlet:namespace />chartLink">
                                              <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 14 14">
                                                  <g fill="#124093" fill-rule="nonzero" stroke="#124093" stroke-width=".4">
                                                      <path d="M12.752 13.078h-.542V4.86c0-.383-.299-.694-.666-.694h-1.213c-.367 0-.666.311-.666.694v8.218H8.287V8.583c0-.382-.299-.694-.666-.694H6.408c-.367 0-.665.312-.665.694v4.495H4.364V10.75c0-.382-.299-.693-.665-.693H2.485c-.367 0-.665.31-.665.693v2.328h-.572a.253.253 0 0 0-.248.258c0 .143.11.258.248.258h11.504a.253.253 0 0 0 .248-.258.253.253 0 0 0-.248-.258zm-8.883 0H2.315V10.75c0-.098.076-.177.17-.177H3.7c.093 0 .17.08.17.177v2.328zm3.923 0H6.238V8.583c0-.098.076-.177.17-.177H7.62c.094 0 .17.08.17.177v4.495zm3.923 0H10.16V4.86c0-.098.077-.178.17-.178h1.214c.094 0 .17.08.17.178v8.218zM12.142.996a.24.24 0 0 0-.074-.187.262.262 0 0 0-.195-.071H10.307a.252.252 0 0 0-.257.246c0 .136.115.246.257.246h.959L8.727 3.665l-.957-.919a.263.263 0 0 0-.364 0L2.79 7.174a.24.24 0 0 0 0 .348c.05.048.115.072.181.072a.262.262 0 0 0 .182-.072L7.588 3.27l.957.918c.1.096.263.096.364 0l2.72-2.609v.833c0 .136.115.246.257.246.142 0 .257-.11.257-.246V1.008.996z"></path>
                                                  </g>
                                              </svg>
                                              <!-- <liferay-ui:message key="link-chart" /> -->
                                          </a>
                                      </li>
                                      <li class="nav-item">
                                          <a class="nav-link"  data-toggle="tab" href="javascript:void(0);" id="<portlet:namespace />fundTableLink">
                                              <svg xmlns="http://www.w3.org/2000/svg" width="12" height="10" viewBox="0 0 12 10">
                                                  <g fill="#124093" fill-rule="nonzero">
                                                      <path d="M11.4 9.257H3.343c-.332 0-.601-.307-.601-.686 0-.378.269-.685.6-.685H11.4c.332 0 .601.307.601.685 0 .38-.269.686-.6.686zM11.4 5.486H3.343c-.332 0-.601-.307-.601-.686 0-.379.269-.686.6-.686H11.4c.332 0 .601.307.601.686 0 .379-.269.686-.6.686zM11.4 1.371H3.343c-.332 0-.601-.307-.601-.685 0-.379.269-.686.6-.686H11.4c.332 0 .601.307.601.686 0 .378-.269.685-.6.685z"></path>
                                                      <circle cx=".857" cy=".857" r="1"></circle>
                                                      <circle cx=".857" cy="4.629" r="1"></circle>
                                                      <circle cx=".857" cy="8.4" r="1"></circle>
                                                  </g>
                                              </svg>
                                              <!-- <liferay-ui:message key="link-fund-table" /> -->
                                          </a>
                                      </li>
                                  </ul>
                              </div>
                              <!-- Tab panes -->
                              <div class="tab-content">
                                  <div class="active" id="tokio-grap">
                                      <div class="tokio-bar-grap">
                                          <figure class="highcharts-figure">
                                              <div id="highchart-container"></div>
                                          </figure>
                                      </div>
                                  </div>
                                  <div class="" id="fundDataTable" style="display: none; opacity: 1;">
                                      <div class="tokio-tbl-detail">
                                          <div class="table-responsive">
                                              <table class="table">
                                                  <thead>
                                                      <tr>
                                                          <th><liferay-ui:message key="policy-year" /></th>
                                                          <th><liferay-ui:message key="total-premium" /></th>
                                                          <th><liferay-ui:message key="additional-allocation" /></th>
                                                          <th><liferay-ui:message key="booster-premium" /></th>
                                                          <th><liferay-ui:message key="fund-value" /></th>
                                                      </tr>
                                                  </thead>
                                                  <tbody class="scrl-tbl-hgt m-cust-scroll">
                                                  </tbody>
                                              </table>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                    <c:forEach var="cfcNestedCard" items="${customizeFeaturesCard.cfcNestedData}">
                    	<div class="tab-pane" id="${cfcNestedCard.enterTabId}">
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
  <style>
  .white-space-p{
  	white-space: initial !important;
  }
  </style>
  <script src="<%=request.getContextPath()%>/js/highcharts.js"></script>