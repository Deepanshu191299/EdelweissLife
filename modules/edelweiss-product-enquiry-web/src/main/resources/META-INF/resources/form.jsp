				<div class="row">
					<div class="col-sm-12">
						<div class="secure-life-form-wrapper">
							<c:forEach items="${formView.fields }" var="form">

								<c:set var="curFieldValue"
									value="${lmsLeadDataMap[form.fieldReference]}" />
								<c:if test="${form.type == 'text' && form.fieldReference != 'buttonText'}">
								    <div class="form-group">
								        <input type="${form.type}" value="${curFieldValue}"
								            <c:if test="${form.hidden == 'true'}"> hidden="true" </c:if>
								            <c:if test="${form.required == 'true'}"> data-required="${form.required}" data-errormsg="${form.errorMessage}" </c:if>
								            class="form-control etl"
								            name='<portlet:namespace/>${form.fieldReference}'
								            id='${form.fieldReference}' placeholder="${form.placeHolder}"
								            <c:choose>
								                <c:when test="${form.fieldReference == 'fullName'}">
								                    oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');"
								                </c:when>
								                <c:when test="${form.fieldReference == 'email'}">
								                    oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');"
								                </c:when>
								            </c:choose> />
								    </div>
								</c:if>
								
								
								<c:if test="${form.type == 'numeric'}">
									<div class="form-group">
										<input type="tel" maxlength=10 value="${curFieldValue}"
											<c:if test="${form.hidden == 'true'}"> hidden="true" </c:if>
											<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>
											class="form-control etl"
											name='<portlet:namespace/>${form.fieldReference}'
											id='${form.fieldReference}' placeholder="${form.placeHolder}"
											oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" />
									</div>
								</c:if>
								<c:if test="${form.type == 'select'}">
									<div class="form-group">
										<select class="form-control etl"
											name='<portlet:namespace/>${form.fieldReference}'
											id='${form.fieldReference}'
											<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>>
											<option value="" disabled="disabled" selected hidden>${form.placeHolder}</option>
											<c:choose>
												<c:when test="${ form.fieldReference == 'annualIncome' }">
													<c:forEach items="${form.optionValues}" var="options">
												      <option value="${options.key}"
													    ${curFieldValue == options.key ? 'selected' : ''}>${options.value}</option>
											        </c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach items="${form.optionValues}" var="options">
												        <option value="${options.value}"
													${curFieldValue == options.value ? 'selected' : ''}>${options.value}</option>
											</c:forEach>
												</c:otherwise>
											</c:choose>
											
										</select>
									</div>
								</c:if>
							</c:forEach>
							<div class="form-group">
										<button type="submit" class="edto-btn-primary" id="submitbtn">
											<liferay-ui:message key="${buttonLabel}" />
										</button>
							</div>
						</div>
					</div>



						  <c:if test="${showDisclaimer}">
							 <div class="col-sm-12">
								<div class="mt-3">
									<div class="form-check p-0 check-input-fixd-cir">
										<div class=" mt-3">
										<ul class="edto-bullet1">
                                          <li>
											<input class="form-check-input" type="checkbox"
												checked="checked" style="display: none;"
												name='<portlet:namespace/>disclaimer'
												readonly="readonly" value="Consent" onclick="return false" /><label
												class="form-check-label">${disclaimer}</label>
												</li>
												</ul>
										</div>
									</div>
								</div>
							 </div>
						</c:if>
				</div>
				<!-- Partner and UTM jsp -->
				<%@ include file="utm_element.jsp"%>