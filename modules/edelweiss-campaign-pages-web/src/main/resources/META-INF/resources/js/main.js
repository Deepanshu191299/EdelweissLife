jQuery(document).ready(function() {

	if(ctaAlign==="LeftAlign"){
		$(".cta-btn").addClass("cta-left-align");
	}else if(ctaAlign==="CenterAlign"){
		$(".cta-btn").addClass("cta-center-align");
	}else if(ctaAlign==="RightAlign"){
		$(".cta-btn").addClass("cta-right-align");
	}
	
	var callGooglePageViewFunc = new Function(googlePageViewScript + "\nreturn callGooglePageView;");
	var googlePageViewResult = callGooglePageViewFunc();
	googlePageViewResult();
	
	var callFacebookPageViewFunc = new Function(facebookPageViewScript + "\nreturn callFacebookPageView;");
	var facebookPageViewResult = callFacebookPageViewFunc();
	facebookPageViewResult();

    $('#campaignPageForm, #'+namespace+'campaignPageForm').validate({
        errorClass: "error form-feedback-item",
        errorElement: "div",
        onfocusout: function(element) {
            this.element(element);
        }
    });
    var date = document.getElementById('dateOfBirth');
    $(date).inputmask({
        alias: "datetime",
        inputFormat: "dd/mm/yyyy",
        prefillYear: false
    });

    var dateOfBirth = document.getElementById(namespace+'dateOfBirth');
    $(dateOfBirth).inputmask({
        alias: "datetime",
        inputFormat: "dd/mm/yyyy",
        prefillYear: false
    });

    jQuery.validator.addMethod("customEmail", function(value, element) {
        return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            .test(value);
    }, 'Please enter valid E-mail id');

    jQuery.validator.addMethod("fullName", function(value, element) {
        return /^[a-zA-Z]+ [a-zA-Z ]+[a-zA-Z ]*$/.test(value);
    }, 'Please enter your Full Name');

    jQuery.validator.addMethod("mobileNumber", function(value, element) {

        return /^(?!.*(.)\1{5,})[6-9]\d{9}$/.test(value);

    }, 'Please Enter Valid Mobile Number');

    jQuery.validator.addMethod("validDateFormat", function(value, element) {

        return /^\d{2}\/\d{2}\/\d{4}$/.test(value);

    }, 'Please Enter a valid date');

    $.validator.addMethod("minAge", function(value, element, min) {

    	if(!element.required && !value) { return true; }

        var today = moment(new Date(), 'DD/MM/YYYY').toDate();

        var birthDate = moment(value, 'DD/MM/YYYY').toDate();

        var age = today.getFullYear() - birthDate.getFullYear();
        if (age > min + 1) {
            return true;
        }

        var m = today.getMonth() - birthDate.getMonth();

        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }

        return age >= min;
    }, "The age should be between 18 to 65");

    $.validator.addMethod("maxAge", function(value, element, max) {

    	if(!element.required && !value) { return true; }

        var maxAllowedDob = new Date();
        maxAllowedDob.setHours(0);
        maxAllowedDob.setMinutes(0);
        maxAllowedDob.setSeconds(0);
        maxAllowedDob.setFullYear(maxAllowedDob.getFullYear() - (Number(max) + 1));

        var birthDate = moment(value, 'DD/MM/YYYY').toDate();

        return maxAllowedDob < birthDate;

    }, "The age should be between 18 to 65");

    jQuery('.etl').each(function() {
        if (jQuery(this).attr('id') == "email" || jQuery(this).attr('id') == namespace+"email") {
            if (jQuery(this).data('required') == true) {

                jQuery(this).rules("add", {
                    required: true,
                    customEmail: true,
                    messages: {
                        required: function() {
                            return Liferay.Language.get("email-field-required");
                        }
                    }
                })
            } else {
                jQuery(this).rules("add", {
                    customEmail: true,
                    messages: {
                        customEmail: function() {
                            return Liferay.Language.get("valid-mail-id");
                        }
                    }
                })
            }
        } else if (jQuery(this).attr('id') == "fullName" || jQuery(this).attr('id') == namespace+"fullName") {
            if (jQuery(this).data('required') == true) {
                jQuery(this).rules("add", {
                    required: true,
                    fullName: true,
                    messages: {
                        required: function() {
                            return Liferay.Language.get("fullname-field-required");
                        },
                        fullName: function() {
                            return Liferay.Language.get("please-enter-full-name");
                        }
                    },

                })
            } else {
                jQuery(this).rules("add", {
                    fullName: true,
                    messages: {
                        fullName: function() {
                            return Liferay.Language.get("please-enter-full-name");
                        }
                    }
                })
            }
        } else if (jQuery(this).attr('id') == "mobileNumber" || jQuery(this).attr('id') == namespace+"mobileNumber") {
            if (jQuery(this).data('required') == true) {

                jQuery(this).rules("add", {
                    required: true,
                    mobileNumber: true,
                    messages: {
                        required: function() {
                            return Liferay.Language.get("mobno-field-required");
                        },
                        mobileNumber: function() {
                            return Liferay.Language.get("please-enter-a-valid-10-digit-mobile-number");
                        }
                    }
                })
            } else {
                jQuery(this).rules("add", {

                    mobileNumber: true,
                    messages: {
                        mobileNumber: function() {
                            return Liferay.Language.get("please-enter-a-valid-10-digit-mobile-number");
                        }
                    }
                })
            }
            
            /*
             * Allow only Digits
             */
            $(this).on("input", function(){
            	this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
            });
            
            /*
             * Allow max 10 digits
             */
            $(this).attr("maxlength","10");
        } else if (jQuery(this).attr('id') == "dateOfBirth" || jQuery(this).attr('id') == namespace+"dateOfBirth") {
            if (jQuery(this).data('required') == true) {

                jQuery(this).rules("add", {
                    required: true,
                    validDateFormat: true,
                    minAge: minAge,
                    maxAge: maxAge,
                    messages: {
                        required: jQuery(this).data('errormsg'),
                        minAge: function() {
                            return Liferay.Language.get("age-should-be-between") + ' ' + minAge + ' and ' + maxAge;
                        },
                        maxAge: function() {
                            return Liferay.Language.get("age-should-be-between") + ' ' + minAge + ' and ' + maxAge;
                        }
                    }
                })
            } else {
                jQuery(this).rules("add", {
                    minAge: minAge,
                    maxAge: maxAge,
                    messages: {
                        minAge: function() {
                            return Liferay.Language.get("age-should-be-between") + ' ' + minAge + ' and ' + maxAge;
                        },
                        maxAge: function() {
                            return Liferay.Language.get("age-should-be-between") + ' ' + minAge + ' and ' + maxAge;
                        }
                    }
                })
            }
        } else if (jQuery(this).attr('id') == "gender" || jQuery(this).attr('id') == namespace+"gender") {
            if (jQuery(this).data('required') == true) {

                jQuery(this).rules("add", {
                    required: true,
                    messages: {
                        required: function() {
                            return Liferay.Language.get("field-required");
                        }
                    }
                })
            }
        } else if (jQuery(this).attr('id') == "incomeRange" || jQuery(this).attr('id') == namespace+"incomeRange") {
            if (jQuery(this).data('required') == true) {

                jQuery(this).rules("add", {
                    required: true,
                    messages: {
                        required: function() {
                            return Liferay.Language.get("field-required");
                        }
                    }
                })
            }
        }
    });

    jQuery('#campaignPageForm, #'+namespace+'campaignPageForm').on('submit', function(event) {

        event.preventDefault();

        if ($('#campaignPageForm, #'+namespace+'campaignPageForm').validate().form()) {
            let loader = document.getElementById('lms-loader');
            loader.style.display = 'block';
            var form = document.campaignPageForm;

            Liferay.Util.fetch(resuourceURL, {
                    body: new FormData(form),
                    method: 'POST',
                })
                .then(function(response) {
                    loader.style.display = 'none';
                    if (response.ok) {
                        return response.json();
                    } else {
                        showFailureModal();
                    }
                })
                .then(function(response) {
                    if (response?.status == 200) {
                        if (response?.showThankYouMessage) {
                            showSuccessModal();
                        } else if (response?.redirectURL) {
                            Liferay.Util.navigate(response.redirectURL);
                        } else {
                            showSuccessModal();
                        }
                        gtag_report_conversion();
                        callFacebookLeadConversion();
                    } else {
                        showFailureModal(response.message);
                    }
                })
                .catch(function(e) {
                    loader.style.display = 'none';
                    console.log("error...", e);
                    showFailureModal();
                })

        } else {
            console.log("does not validate");
        }
    })

    function showSuccessModal() {
        $('.success-blk').removeClass('hide');
        $('.error-blk').addClass('hide');
        $('.align-items-center').removeClass('hide');

        $('#lmsModal').modal('show');
    }

    function showFailureModal(msg) {
        if (msg) {
            $('.error-msg').text(msg);
        }
        $('.error-blk').removeClass('hide');
        $('.success-blk').addClass('hide');
        $('.align-items-center').addClass('hide');
        $('#lmsModal').modal('show');
    }
    
    // code for social media sharing  toggle slide-up
    $('.social-open').slideUp();
    
    // code for rating toggle slide-up
    $('.review-open').slideUp();
    
    // code for rating modal pop up 
    $('.child-rating li.star').click(function() {
        $("#thankyouRatingModal").modal('show');
    });

    // code for highlight stars on hover
    $(".child-rating li.star").hover(function() {
        $(this).prevAll().addBack().toggleClass("hovered-star");
    });

    // code for rating star's events   
    $('.child-rating li.star').on("click", function() {
        updateStarRating($(this).data('rate'));
        var jqueryEvent = event.type;

        if (jqueryEvent == 'mouseout') {
            if ($(this).index() == 0) {
                $('.review-star-blank').css("fill", "");
            }
        }
    });
    var cookieValue = getCookie("ratings");
    if (cookieValue) {
        updateStarRating(Number(cookieValue));
    }

});

// code for rating 
function updateStarRating(currRating) {

    var parentRating = $('.demo svg.review-star-blank');
    var childRating = $('.child-rating svg.review-star-blank');


    if (currRating) {
        parentRating.each(function(ind) {
            ind++;
            if (ind <= currRating) {
                $(this).css("fill", "orange");
            } else {
                $(this).css("fill", "");
            }
        });

        childRating.each(function(index) {
            if (index <= currRating) {
                $(this).css("fill", "orange");
            } else {

                $(this).css("fill", "");
            }
        });
        var starValues = setCookie(currRating, "", "");
    }
    return currRating;
}

// setting values of star's feedback in cookie
function setCookie(ratings, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    let expires = "expires=" + d.toUTCString();
    document.cookie = "ratings" + " = " + ratings;
}

// getting values of star's feedback from cookie
function getCookie(ratings) {
    let name = ratings + "=";
    let decodedCookie = decodeURIComponent(document.cookie);

    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}