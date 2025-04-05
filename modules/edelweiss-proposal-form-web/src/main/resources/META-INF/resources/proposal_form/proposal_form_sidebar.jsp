<%@ include file="/init.jsp"%>

<div class="app-sidebar-main-wrapper tabs-proposal-wrapper">
   <div class="filter-main-wrapper">
      <p><liferay-ui:message key="label-pr-policy-no" /><span>${commonDetails.policyNo }</span></p>
      <!-- <div id="bar-1" class="bar-main-container">
         <div class="wrap">
            <div class="bar-container">
               <div class="bar" style="width:50%;"></div>
            </div>
            <div class="bar-percentage" data-percentage="50">50%</div>
         </div>
         <p>Your current progress has been saved</p>
      </div>-->
   </div>
   <div class="sidebar-tabs-wrapper form-poposal-tabs">
      <button class="btn btn-link text-left tab-btn active step--save--btn--1" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
         <p><liferay-ui:message key="label-personal-details" /> <!-- <span>Complete</span>--></p>
         <!-- <span><img src="/o/edelweisstokio-theme/images/praposal/tick.png" alt="icon"></span> -->
      </button>
      <button class="btn btn-link text-left tab-btn collapsed step--save--btn--2" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
         <p><liferay-ui:message key="label-health-details" /><!-- <span>Complete</span> --></p>
         <!-- <span><img src="/o/edelweisstokio-theme/images/praposal/tick.png" alt="icon"></span> -->
      </button>
      <c:choose>         
         <c:when test = "${isWSP}">
            <c:if test="${isLaPrSame || isSpouseExist || isChildBenefit}">
               <button class="btn btn-link text-left tab-btn collapsed step--save--btn--3" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                  <p><liferay-ui:message key="label-nominee-details" /></p>
                  <!-- <span><img src="/o/edelweisstokio-theme/images/praposal/pending.png" alt="icon"></span> -->
               </button>
            </c:if>
         </c:when>
         
         <c:when test = "${isZP}">
            <c:if test="${isLaPrSame || isSpouseExist || isChildBenefit}">
               <button class="btn btn-link text-left tab-btn collapsed step--save--btn--3" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                  <p><liferay-ui:message key="label-nominee-details" /></p>
                  <!-- <span><img src="/o/edelweisstokio-theme/images/praposal/pending.png" alt="icon"></span> -->
               </button>
            </c:if>
         </c:when>
         
         <c:otherwise>
            <c:if test="${isLaPrSame}">
               <button class="btn btn-link text-left tab-btn collapsed step--save--btn--3" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                  <p><liferay-ui:message key="label-nominee-details" /></p>
                  <!-- <span><img src="/o/edelweisstokio-theme/images/praposal/pending.png" alt="icon"></span> -->
               </button>
            </c:if>
         </c:otherwise>
      </c:choose>
      <button class="btn btn-link text-left tab-btn collapsed step--save--btn--4" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
         <p><liferay-ui:message key="label-other-details" /></p>
         <!-- <span><img src="/o/edelweisstokio-theme/images/praposal/pending.png" alt="icon"></span> -->
      </button>
      <c:if test="${commonDetails.showCovidFormYn == 'Y'}">
	      <button class="btn btn-link text-left tab-btn collapsed step--save--btn--5" type="button" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
	         <p><liferay-ui:message key="label-covid-questionnaire" /></p>
	         <!-- <span><img src="/o/edelweisstokio-theme/images/praposal/default.png" alt="icon"></span> -->
	      </button>
       </c:if>
      <c:if test="${commonDetails.paymentStatus == 'N'}">
	      <button class="btn btn-link text-left tab-btn collapsed" type="button" data-toggle="collapse" data-target="#pfpaymentModal" aria-expanded="false" aria-controls="pfpaymentModal" id="pfpaymentModalBtn">
	         <p><liferay-ui:message key="label-payment" /></p>
	         <!-- <span><img src="/o/edelweisstokio-theme/images/praposal/default.png" alt="icon"></span> -->
	      </button>
      </c:if>
   </div>
</div>