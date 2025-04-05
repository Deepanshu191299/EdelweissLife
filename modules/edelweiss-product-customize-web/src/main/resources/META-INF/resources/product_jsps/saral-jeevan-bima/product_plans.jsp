

<div class="productPlansContainer"  id="<portlet:namespace/>productPlans">
	<h2 class="productPlansHeading fs28">We Have Shortlisted Some of the options For You</h2>
	<form method = "post" action="${saveInvestmentDetails}" name="fm">
		<div class="main-container" >
		<!-- Card for Regular Pay -->
			<div class="container1 sjbCard  productPlan" id="productForRegular" onclick="handleProductSelection('productForRegular', 'forRegular', 'titleForRegular'); handleProductBreakup('premiumRegular','gstRegular','totalRegular', 'pptRegular');">
				<div class="recommend-tag"><liferay-ui:message key="label-recommended"/></div>
				<div class="card-body p-0">
					<div class="offer-top text-center">
						<div class="icon-box">
							<img class="img-fluid" src="" alt="" id="ImageForRegular">
						</div>
						<div class="offer-heading">
							<h2 id="titleForRegular"></h2>
							<p id="descriptionForRegular"></p>
				          </div>
					</div>
					<div class="offer-amt bg-yellow text-center">
						<h2 class="h2">
							<span class="rupee" id="rupee"> &#8377; </span>
							<span id="forRegular"></span>
						</h2>
						<p class="small-text"><liferay-ui:message key="label-incl-gst"/></p>
					</div>
					<div class="offer-details">
						<ul class="bullet-list">
						</ul>
					</div>
					<span id="premiumRegular" hidden></span>
					<span id="gstRegular" hidden></span>
					<span id="totalRegular" hidden></span>
					<span id="pptRegular" hidden></span>
				</div>
				<div class="button-wrap d-flex justify-content-center sjbProceed">
					<div class="button-wrap d-flex justify-content-center">
						<button type="button" id="proceedBtnForRegular" class="planProceedBtn" color="primary">
							<liferay-ui:message key="label-proceed"/>
						</button>
					</div>
				</div>
			</div>
			<!-- Card for Limitted pay 10 Years -->
			<div class="container1 sjbCard  productPlan" id="productFor10" onclick="handleProductSelection('productFor10', 'for10', 'titleFor10'); handleProductBreakup('premium10','gst10','total10', 'ppt10');">
				<div class="recommend-tag"><liferay-ui:message key="label-recommended"/></div>
				<div class="card-body p-0">
					<div class="offer-top text-center">
						<div class="icon-box">
							<img class="img-fluid" src="" alt="" id="imageFor10">
						</div>
						<div class="offer-heading">
							<h2 id="titleFor10"></h2>
							<p id="descriptionFor10"></p>
				          </div>
					</div>
					<div class="offer-amt bg-yellow text-center">
						<h2 class="h2">
							<span class="rupee" id="rupee"> &#8377; </span>
							<span id="for10"></span>
						</h2>
						<p class="small-text"><liferay-ui:message key="label-incl-gst"/></p>
					</div>
					<div class="offer-details">
						<ul class="bullet-list">
						</ul>
					</div>
					<span id="premium10" hidden></span>
					<span id="gst10" hidden></span>
					<span id="total10" hidden></span>
					<span id="ppt10" hidden></span>
				</div>
				<div class="button-wrap d-flex justify-content-center sjbProceed">
					<div class="button-wrap d-flex justify-content-center">
						<button type="button" id="proceedBtnFor10" class="planProceedBtn" color="primary">
							<liferay-ui:message key="label-proceed"/>
						</button>
					</div>
				</div>
			</div>
			<!-- card for Limitted Pay 5 Years -->
			<div class="container1 sjbCard productPlan" id="productFor5" onclick="handleProductSelection('productFor5', 'for5', 'titleFor5'); handleProductBreakup('premium5','gst5','total5', 'ppt5');">
				<div class="recommend-tag"><liferay-ui:message key="label-recommended"/></div>
				<div class="card-body p-0">
					<div class="offer-top text-center">
						<div class="icon-box">
							<img class="img-fluid" src="" alt="" id="imageFor5">
						</div>
						<div class="offer-heading">
							<h2 id="titleFor5"></h2>
							<p id="descriptionFor5"></p>
				          </div>
					</div>
					<div class="offer-amt bg-yellow text-center">
						<h2 class="h2">
							<span class="rupee" id="rupee"> &#8377; </span>
							<span id="for5"></span>
						</h2>
						<p class="small-text"><liferay-ui:message key="label-incl-gst"/></p>
					</div>
					<div class="offer-details">
						<ul class="bullet-list">
						</ul>
					</div>
					<span id="premium5" hidden></span>
					<span id="gst5" hidden></span>
					<span id="total5" hidden></span>
					<span id="ppt5" hidden></span>
				</div>
				<div class="button-wrap d-flex justify-content-center sjbProceed">
					<div class="button-wrap d-flex justify-content-center">
						<button type="button" id="proceedBtnFor5" class="planProceedBtn" color="primary">
							<liferay-ui:message key="label-proceed"/>
						</button>
					</div>
				</div>
			</div>
			
			<!-- Card for Single Pay -->
			<div class="container1 sjbCard  productPlan" id="productForSingle" onclick="handleProductSelection('productForSingle', 'forSingle', 'titleForSingle'); handleProductBreakup('premiumSingle','gstSingle','totalSingle', 'pptSingle');">
				<div class="recommend-tag"><liferay-ui:message key="label-recommended"/></div>
				<div class="card-body p-0">
					<div class="offer-top text-center">
						<div class="icon-box">
							<img class="img-fluid" src="" alt="" id="imageForSingle">
						</div>
						<div class="offer-heading">
							<h2 id="titleForSingle"></h2>
							<p id="descriptionForSingle"></p>
				          </div>
					</div>
					<div class="offer-amt bg-yellow text-center">
						<h2 class="h2">
							<span class="rupee" id="rupee"> &#8377; </span>
							<span id="forSingle"></span>
						</h2>
						<p class="small-text"><liferay-ui:message key="label-incl-gst"/></p>
					</div>
					<div class="offer-details">
						<ul class="bullet-list">
						</ul>
					</div>
					<span id="premiumSingle" hidden></span>
					<span id="gstSingle" hidden></span>
					<span id="totalSingle" hidden></span>
					<span id="pptSingle" hidden></span>
				</div>
				<div class="button-wrap d-flex justify-content-center sjbProceed">
					<div class="button-wrap d-flex justify-content-center">
						<button type="button" id="proceedBtnForSingle" class="planProceedBtn" color="primary">
							<liferay-ui:message key="label-proceed"/>
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<style>
.sjbCard .button-wrap button {
    background: transparent;
    border: 1px solid #e5e5e5;
    color: #868686;
}

.sjbCard.recomend-card .button-wrap button {
    background: #ff4c00;
    border: 0;
    color: #fff;
}
</style>