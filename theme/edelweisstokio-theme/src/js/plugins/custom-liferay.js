let sections = $('.testi-grid');
for (let i = 0; i < $('.testi-grid').length; i++) {
  for (let j = 0; j < $('.testi-grid')[i].children.length; j++) {
    if (j < 6) {
    } else {
      $($('.testi-grid')[i].children[j]).addClass('d-none');
    }
  }
}

$('#viewMore').click(function () {
  for (let i = 0; i < $('.testi-grid').length; i++) {
    for (let j = 0; j < $('.testi-grid')[i].children.length; j++) {
      $($('.testi-grid')[i].children[j]).removeClass('d-none');
    }
  }
  $('#viewMore').addClass('d-none');
});

$(document).ready(function () {
  // Configure/customize these variables.
  var showChar = 50; // How many characters are shown by default
  var ellipsestext = '';
  var moretext = 'Read More';
  var lesstext = 'Read Less';

  $('.more-text').each(function () {
    var content = $(this).html();

    if (content.length > showChar) {
      var c = content.substr(0, showChar);
      var h = content.substr(showChar, content.length - showChar);

      var html =
        c +
        '<span class="moreellipses">' +
        ellipsestext +
        '&nbsp;</span><span class="morecontent"><span>' +
        h +
        '</span>&nbsp;&nbsp;<a href="" class="morelink">' +
        moretext +
        '</a></span>';

      $(this).html(html);
    }
  });

  $('.morelink').click(function () {
    if ($(this).hasClass('less')) {
      $(this).removeClass('less');
      $(this).html(moretext);
    } else {
      $(this).addClass('less');
      $(this).html(lesstext);
    }
    $(this).parent().prev().toggle();
    $(this).prev().toggle();
    return false;
  });

    //Go through each carousel on the page
    $('.owl-carousel').each(function() {
      //Find each set of dots in this carousel
      $(this).find('.owl-dot').each(function(index) {
        //Add one to index so it starts from 1
        $(this).attr('aria-label', index + 1);
      });
      $(this).find('.owl-prev').each(function(index) {
        //Add one to index so it starts from 1
        $(this).attr('aria-label','Previous');
      });
      $(this).find('.owl-next').each(function(index) {
        //Add one to index so it starts from 1
        $(this).attr('aria-label','Next');
      });
    });
    
});



$('.wealth-text-slider .owl-carousel').owlCarousel({
  loop: true,
  margin: 20,
  nav: false,
  dots: true,
  autoplay: true,
  smartSpeed: 1200,
  responsive: {
    0: {
      items: 1,
    },
    600: {
      items: 1,
    },
    911: {
      items: 1,
    },
    1300: {
      items: 1,
    },
  },
});
$('#edelweissTokioForm1 .row.owl-carousel').owlCarousel({
  loop: false,
  margin: 20,
  nav: true,
  navText: ['<img src="/o/edelweisstokio-theme/images/prev-arrow.svg">', '<img src="/o/edelweisstokio-theme/images/prev-arrow.svg">'],
  dots: true,
  autoplay: false,
  mouseDrag: false,
  touchDrag: false,
  smartSpeed: 1000,
  responsive: {
      0: {
          items: 1
      },
      600: {
          items: 2
      },
      1199: {
          items: 3
      },
      1300: {
          items: 3
      }
  }
});
$('.home-banner-slider .owl-carousel').owlCarousel({
  loop: false,
  margin: 20,
  nav: false,
  dots: true,
  autoplay: false,
  smartSpeed: 1200,
  responsive: {
    0: {
      items: 1,
    },
    600: {
      items: 1,
    },
    911: {
      items: 1,
    },
    1300: {
      items: 1,
    },
  },
});

//To accept only number
$('.vnumber').on("input", function(e) {
	this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
});

//To accept only alphabets
$('.valpha').on("input", function(e) {
	this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');
});

//To accept only alphanumeric
$('.valphanum').on("input", function(e) {
	this.value = this.value.replace(/[^a-zA-Z0-9]/g, '').replace(/(\..*)\./g, '$1');
});

//To accept only alphanumeric with "-", "/" & space
$('.valphanumspecchar').on("input", function(e) {
	this.value = this.value.replace(/[^a-zA-Z0-9-/ ]/g, '').replace(/(\..*)\./g, '$1');
});

//To accept only alphanumeric with "-" & "/"
$('.valphanumslash').on("input", function(e) {
	this.value = this.value.replace(/[^a-zA-Z0-9-/]/g, '').replace(/(\..*)\./g, '$1');
});

//To accept date in dd/mm/yyyy format
$(".vdate").inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy",prefillYear: false, showMaskOnHover: false });