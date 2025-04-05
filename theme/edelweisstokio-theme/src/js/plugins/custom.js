



    		// responsive sab menu
            (function ($) {
                $(document).ready(function () {
    
                    $('#cssmenu li.active').addClass('open').children('ul').show();
                    $('#cssmenu li.has-sub>a').on('click', function () {
                        $(this).removeAttr('href');
                        var element = $(this).parent('li');
                        if (element.hasClass('open')) {
                            element.removeClass('open');
                            element.find('li').removeClass('open');
                            element.find('ul').slideUp(200);
                        }
                        else {
                            element.addClass('open');
                            element.children('ul').slideDown(200);
                            element.siblings('li').children('ul').slideUp(200);
                            element.siblings('li').removeClass('open');
                            element.siblings('li').find('li').removeClass('open');
                            element.siblings('li').find('ul').slideUp(200);
                        }
                    });
                });
            })(jQuery);
        // toggle cross btn js
        $(".toggle-main-wrapper , #toggle_close").on("click", function () {
            $("#sidebar").toggleClass("open")
        });
    
    
        // toggle cross btn js
        $(".toggle-main-wrapper , #toggle_close").on("click", function () {
            $("body").toggleClass("test")
        });
    
    
        $(".arow-icon").on("click", function () {
            $(".collapse-icon").toggleClass("upper")
        });
    
    
    
    
    
    
        $('.main-slider .owl-carousel').owlCarousel({
            loop:true,
            margin:20,
            nav:false,
            dots: true,
            autoplay: false,
            smartSpeed: 1200,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:1
                },
                911:{
                    items:1
                },
                1300:{
                    items:1
                }
                
            }
        })
    
    
    
        // $('#slide-testimonal').owlCarousel({
        //     margin: 30,
        //     center: true,
        //     loop: true,
        //     nav: true,
        //     dots: true,
        //     autoplay: true,
        //     responsive: {
        //     0: {
        //        items: 1
        //     },
        //     768: {
        //        items: 2,
        //         margin: 15,
        //     },
        //     1000: {
        //        items: 3,
        //     }
        //       }
        //   });
    
    
        //   
       
            
        $('.testi-slider .owl-carousel').owlCarousel({
            loop:true,
            margin:20,
            nav:true,
            navText: ['<img src="/o/edelweisstokio-theme/images/prev-arrow.svg">', '<img src="/o/edelweisstokio-theme/images/prev-arrow.svg">'],
            dots: true,
            autoplay: false,
            smartSpeed: 1200,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:1
                },
                911:{
                    items:2
                },
                1300:{
                    items:2
                }
                
            }
        })
            
        // video
    
        $('.video-slider .owl-carousel').owlCarousel({
            loop:true,
            margin:20,
            nav:true,
            navText: ['<img src="/o/edelweisstokio-theme/images/prev-arrow.svg">', '<img src="/o/edelweisstokio-theme/images/prev-arrow.svg">'],
            dots: true,
            autoplay: false,
            smartSpeed: 1200,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:1
                },
                911:{
                    items:2
                },
                1300:{
                    items:3
                }
                
            }
        })
    
        // product slider
    
        $('.edto-box-slider .owl-carousel').owlCarousel({
            // loop:true,
            margin:20,
            nav:true,
            navText: ['<img src="/o/edelweisstokio-theme/images/prev-arrow-blue.svg">', '<img src="/o/edelweisstokio-theme/images/prev-arrow-blue.svg">'],
            dots: true,
            autoplay: false,
            smartSpeed: 1200,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:1
                },
                911:{
                    items:2
                },
                1300:{
                    items:4
                }
                
            }
        })
    
        // reason-main-slider
    
        
        $('.reason-main-slider .owl-carousel').owlCarousel({
            loop:true,
            margin:20,
            nav:false,
            dots: true,
            autoplay: false,
            smartSpeed: 1200,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:1
                },
                911:{
                    items:1
                },
                1300:{
                    items:1
                }
                
            }
        })
    
    
    
    
    
    $('.premium-timeline .line li').click(function () {
    
        var offset = $(this).offset();
    
        //console.log(offset)
    
        $(this).addClass('active').siblings().removeClass('active');
        if (offset.left >= 650) {
            $(this).addClass('right-active').siblings().removeClass('right-active');
        } else {
            $(this).removeClass('right-active').siblings().removeClass('right-active');
        }
    
    });
    
    // menu
    $(document).ready(function() {
        
        function calcWidth() {
            var navwidth = 0;
            var morewidth = $('#myTab .more').outerWidth(true);
            $('#myTab > li:not(.more)').each(function() {
                navwidth += $(this).outerWidth( true );
            });
            
            // var availablespace = $('#nav-main').outerWidth(true) - morewidth;
            var availablespace = $('#nav-main').width() - morewidth;
          
            if (navwidth > availablespace) {
                var lastItem = $('#myTab > li:not(.more)').last();
                lastItem.attr('data-width', lastItem.outerWidth(true));
                lastItem.prependTo($('#myTab .more ul'));
                calcWidth();
            } else {
                
            var firstMoreElement = $('#myTab li.more li').first();
            if (navwidth + firstMoreElement.data('width') < availablespace) {
                firstMoreElement.insertBefore($('#myTab .more'));
            }
        }
          
        if ($('.more li').length > 0) {
            $('.more').css('display','block');
            } else {
                $('.more').css('display','none');
            }
        }
    
        $(window).on('resize load',function(){
            calcWidth();
        });
    });
    
    // toggle
    function myFunction(x) {
        x.classList.toggle("change");
      }
    
    // $(document).ready(function() {
         
    //     // ------------  File upload BEGIN ------------
    //     $('.file__input--file').on('change',function(event){
    //         var files = event.target.files;
    //         for (var i = 0; i < files.length; i++) {
    //             var file = files[i];
    //             $("<div class='file__value'><div class='file__value--text'>" + file.name + "</div><div class='file__value--remove' data-id='" + file.name + "' ></div></div>").insertAfter('#file__input');
    //         }	
    //     });
        
    //     //Click to remove item
    //     $('body').on('click', '.file__value', function() {
    //         $(this).remove();
    //     });
    //     // ------------ File upload END ------------ 
        
        
        
    // });
    
    $(document).ready(function(){
        $(".social-click").click(function(){
          $(".social-open").slideToggle();
        });
          $('body').on('click', function (e) {
              if (!$('.social-click').is(e.target)
                  && $('.social-click').has(e.target).length === 0
                  && $('.open').has(e.target).length === 0
              ) {
                  $('.social-open').slideUp();
              }
          });
      });
      
      $(document).ready(function(){
        $(".review-click").click(function(){
          $(".review-open").slideToggle();
        });
          $('body').on('click', function (e) {
              if (!$('.review-click').is(e.target)
                  && $('.review-click').has(e.target).length === 0
                  && $('.open').has(e.target).length === 0
              ) {
                  $('.review-open').slideUp();
              }
          });
      });