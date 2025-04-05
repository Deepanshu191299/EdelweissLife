<%@page import="in.edelweiss.guest.rating.web.constants.EdelweissGuestRatingWebPortletKeys"%>
<%@page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@ include file="init.jsp"%>

<script src="${renderRequest.getContextPath()}/js/star-ratings.js"></script>
<link href="${renderRequest.getContextPath()}/css/star-ratings.css" rel="stylesheet">

<style>
#star-rating{
line-height:4;
padding-top: 5px;
}
/* .etli-ratings-main-div{
    border-radius: 0.5rem;
    box-shadow: 1px 4px 16px 0px rgb(22 23 26 / 12%);
    height: 180px;
    border: 1px solid #0b5fff17;
}
.etli-ratings-sub-div{
	position: relative;
    height: 180px;
}
.etli-ratings{
	margin: 0;
    position: absolute;
    top: 50%;
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
} */

.ratings-stars-txt-bl h3 {
   
    color: #124093;
}
.ratings-stars-txt-wh h3{
	color: white;
}

#guestRatings .edto-ideal-box{
    width: inherit;
    border: 3px solid #f26836;
    border-radius: 15px;
    background: #0c57a5;
    padding: 10px;
    text-align: center;
}
#guestRatings .ratingsDiv{
 background: white;
 border: 2px solid #a7a9bc;
 }
 #guestRatings .para1 p{
    font-size: 1.2rem;
    color: white;
    line-height: 23px;
}
#customersCount{
	color: #f26836;
    font-weight: 800;
    font-size: 2rem;
    line-height: 1.5;
}
#avgScore{
	color: #f26836;
    top: 1.5rem;
    position: absolute;
    left: 5.1rem;
}
#submitRatingButton{
    background: #0c57a5;
    border: 0.5px solid #8197af;
    color: white;
    padding: 5px 10px 5px 10px;
    border-radius: 5px;
}
#message {
	height:30px;
}
.jq-star{
	width: 65px !important;
    height: 55px !important;
}
</style>

<liferay-portlet:resourceURL
	id="/guest_page_ratings_entry" var="saveGuestRatingsRsourceURL" copyCurrentRenderParameters="false" />
<%

long classNameId = ClassNameLocalServiceUtil.getClassNameId(Layout.class);
long classPK = themeDisplay.getLayout().getPlid();
String portalURL = themeDisplay.getPortalURL();
String guestRatingsEndPoint = EdelweissGuestRatingWebPortletKeys.GUEST_RATINGS_FILTER;

%>
<!-- <div class="container-fluid etli-ratings-main-div">
  <div class="row">
    <div class="col-sm-6  etli-ratings-sub-div">
    	<div class="etli-ratings" style="width:95%">
			<div style="text-align: center;font-size: 30px;line-height:2;"><span id="avgScore"></span></div>
			<div class=""><span>Rated by <span id="customersCount"></span> customers</span></div>
		</div>
    </div>
    <div class="col-sm-6 etli-ratings-sub-div">
		<div class="etli-ratings">
			<div id="star-rating"></div>
			<div id="message"></div>
		</div>
	</div>
  </div>
</div> -->

<div id="guestRatings" class="edto-login-box row mt-2"
	style="gap: 0; justify-content: center;">
	<div class="col-md-4 my-3">
		<div class="edto-ideal-box shadow-none rc-mobile-card"
			style="background: #0c57a5;">
			<div class="ratings-stars-txt-wh">
				<h3>Share Your Valuable Feedback</h3>
			</div>
			<div class="">
				<div class="row">
					<div class="col-md-6 my-3">
						<div style="text-align: center; font-size: 30px; line-height: 2;">
							<img src="<%=renderRequest.getContextPath()%>/images/white-star-icon.png" width="120" height="95" />
							<p id="avgScore"></p>
						</div>
					</div>
					<div class="col-md-6 my-3"
						style="border-left: 1px solid white; height: 96px;">
						<div class="para1">
							<div class="">
								<p>Rated by</p>
								<p>
									<span id="customersCount"></span>
								</p>
								<p>customers</p>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="col-md-4 my-3">
		<div class="edto-ideal-box shadow-none rc-mobile-card ratingsDiv" id="ratings-div">
			<div class="ratings-stars-txt-bl">
				<h3>Was the Information Helpful?</h3>
			</div>
			<div class="">
				<div id="star-rating"></div>
				<div id="message"></div>
				<div><button type="button" id="submitRatingButton" onclick="submitRating();">Submit Rating</button></div>
			</div>
		</div>
	</div>
</div>



<script>
$(document).ready(function() {
   setPageRating(); 
   getRatings();
});

function setPageRating(){
	 $('#star-rating').starRating({
	   initialRating: parseInt(localStorage.getItem('rated')),
	   totalStars: 5,
	   starSize: 40,
	   callback: function(currentRating, $el) {
	     // Save the rating to localStorage
	     localStorage.setItem('rated', currentRating);
	   }
	 });
}

function displayRatedMessage() {
  // Display a message to the user (you can customize this message)
  $('#message').html('Thank you for your feedback : ' + localStorage.getItem('rated') + ' stars');
}

function submitRating(){
	var _score = localStorage.getItem('rated');
	console.log("_score : "+_score);
	
	if(_score){
		
		var saveGuestRatingsRsourceURL = '<%=saveGuestRatingsRsourceURL%>';
		var classNameId ='<%=classNameId%>';
		var classPK = '<%=classPK%>';
		
		console.log("classNameId : "+classNameId);
		console.log("classPK : "+classPK);
		console.log("_score : "+_score);
		
		var ratingsFormData = new FormData();
		ratingsFormData.append("<portlet:namespace/>classNameId", classNameId);
		ratingsFormData.append("<portlet:namespace/>classPK", classPK);
		ratingsFormData.append("<portlet:namespace/>contentTitle", "");
		ratingsFormData.append("<portlet:namespace/>score", _score);

		
		Liferay.Util.fetch(saveGuestRatingsRsourceURL, {
			body: ratingsFormData,
			method: 'POST'
		})
		.then((response) => {
				return response.json();
		})
		.then((response) => {
			console.log("Ratings response : "+response);
			getRatings();
	        displayRatedMessage();
			
		})
		.catch((e) => {
			console.log(e);
		})
		
	} else {
		alert("Please give your rating and submit");
	}

}

function getRatings(){
	
	var getGuestRatingsURL = '<%=portalURL+guestRatingsEndPoint+"s_"+classPK+classNameId%>'
	console.log("getGuestRatingsURL : "+getGuestRatingsURL);
	
	Liferay.Util.fetch(getGuestRatingsURL, {
		headers : {
			"Content-Type": "application/json"
		},
		method : "GET"
	})

	.then((response) => {
		return response.json();
	})
	.then((validationResponse) => {
		console.log(validationResponse);
	
		console.log("Total Entries : "+validationResponse.totalEntries);
		console.log("Avg Score  : "+validationResponse.averageScore)
		
		if (typeof validationResponse.totalEntries !== 'undefined' && typeof validationResponse.averageScore !== 'undefined'){
			document.getElementById("customersCount").innerHTML=validationResponse.totalEntries;
			document.getElementById("avgScore").innerHTML=validationResponse.averageScore;
		} else {
			document.getElementById("customersCount").innerHTML="0";
			document.getElementById("avgScore").innerHTML="";
		}
	
		
		
	})
	.catch(function(error) {
		console.error(error);
		
	});

}


</script>