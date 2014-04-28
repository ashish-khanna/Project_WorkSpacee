
(function($) {

    var APP_URLS = {
        LOGIN: 'rest/user/userlogin',
        CHECK_LOGIN: 'rest/user/checkloginstatus',
        LOGOUT: 'rest/user/userlogout',        
        REGISTER: 'rest/user/createuser',
        ALL_SHIFTS: 'rest/vehicleshiftmapping/allmappedshift',
        CREATE_REQ: 'rest/request/createreq',
        VIEW_REQ: 'rest/request/allreq',
        SEARCH_MBTA: 'rest/mbta/searchmbta',
        CANCEL_REQ: 'rest/request/cancelreq',
        STOPS_MBTA: 'rest/mbta/getallstop'
    },

    //set response handler by ID
    RESPONSE_HANDLERS = {
        "loginTemplate": {
            "loginForm": function(event) {
                var theForm = $(this),
                    alertEl = $('#main-container').find('div.alert'),
                    options = {},
                    userEmail = theForm.find('#email').val(),
                    userPassword = theForm.find('#password').val();

                options.url = APP_URLS['LOGIN'];
                options.data = {
                    email: userEmail,
                    password: userPassword
                };

                options.successCallback = function(response, textStatus, xhr) {
                    $(alertEl).hide();
                    if (response.status === 'SUCCESS') {
                        var template = Template.getHomeTemplate();
                        $('#main-container').empty();
                        $('#main-container').append(template);
                        showLoggedMenu(true);
                    } else {
                        //invalid login
                        alertEl.addClass('alert-danger').text(response.msg).show();
                        showLoggedMenu(false);
                    }
                };

                options.errorCallback = function(xhr, textStatus, errorThrown) {
                    alertEl.addClass('alert-danger').text(textStatus).show();
                    showLoggedMenu(false);
                };

                Controller.ajax(options);

                return false;
            }
        },
        "menuTemplate": {
            "register_menu": function(event) {
                event.preventDefault();
                $('#main-container').empty();
                $('#main-container').html(Template.getRegisterTemplate());
            },
            "signout_menu": function(event) {
                Controller.ajax({
                    url: APP_URLS['LOGOUT'],
                    reqType: 'POST',
                    successCallback: function(response, textStatus, xhr) {
                        $('#main-container').empty();
                        $('#main-container').html(Template.getLoginTemplate());
                        showLoggedMenu(false);
                    },
                    errorCallback: function(xhr, textStatus, errorThrown) {
                    	 $('#main-container').empty();
                         $('#main-container').html(Template.getLoginTemplate());
                         showLoggedMenu(false);
                    }
                });            	
            },
            "home_menu": function(event) {
                 $('#main-container').empty();
                 $('#main-container').append(Template.getHomeTemplate());
            },
            "aboutUs_menu": function(event) {
                $('#main-container').empty();
                $('#main-container').append(Template.getAboutUsTemplate());
           },
           "contactUs_menu": function(event) {
               $('#main-container').empty();
               $('#main-container').append(Template.getContactUsTemplate());
          }
            
        },   
        "registerTemplate": {
            "registerForm": function(event) {
                var theForm = $(this),
                    alertEl = $('#main-container').find('div.alert'),
                    options = {},
                    userObj = {
                        fname: theForm.find('#fname').val(),
                        lname: theForm.find('#lname').val(),
                        email: theForm.find('#email').val(),
                        password: theForm.find('#password').val(),
                        role: "Student"
                    };

                options.url = APP_URLS['REGISTER'];
                options.data = userObj;

                options.successCallback = function(response, textStatus, xhr) {
                    $(alertEl).hide();
                    if (response.status === 'SUCCESS') {
                         $('#main-container').empty();
                         $('#main-container').html(Template.getLoginTemplate());
                         $('#main-container').find('div.alert').addClass('alert-success').text('Registration Successful. Please Login.').show();
                    } else {
                        //registration error
                        alertEl.addClass('alert-danger').text(response.msg).show();
                    }
                };

                options.errorCallback = function(xhr, textStatus, errorThrown) {
                    alertEl.addClass('alert-danger').text(textStatus).show();
                };

                Controller.ajax(options);

                return false;
            }
        },
        "homeTemplate": {
            "raiseReq_btn": function(event) {
                var alertEl = $('#main-container').find('div.alert');
                Controller.ajax({
                    url: APP_URLS['ALL_SHIFTS'],
                    reqType: 'GET',
                    successCallback: function(response, textStatus, xhr) {
                        $(alertEl).hide();
                        if (response.status === 'SUCCESS') {
                            $('#main-container').empty();
                            $('#main-container').html(Template.getRaiseReqTemplate(response.data));
                        } else {
                            alertEl.addClass('alert-danger').text(response.status).show();
                        }
                    },
                    errorCallback: function(xhr, textStatus, errorThrown) {
                        alertEl.addClass('alert-danger').text(textStatus).show();
                    }
                });
            },
            "viewReq_btn": function(event) {
               var alertEl = $('#main-container').find('div.alert');
                Controller.ajax({
                    url: APP_URLS['VIEW_REQ'],
                    reqType: 'GET',
                    successCallback: function(response, textStatus, xhr) {
                        $(alertEl).hide();
                        if (response.status === 'SUCCESS') {
                            $('#main-container').empty();
                            $('#main-container').html(Template.getViewReqTemplate(response.data));
                        } else {
                            alertEl.addClass('alert-danger').text(response.status).show();
                        }
                    },
                    errorCallback: function(xhr, textStatus, errorThrown) {
                        alertEl.addClass('alert-danger').text(textStatus).show();
                    }
                });
            },
            "searchMBTA_btn": function(event) {
            	var alertEl = $('#main-container').find('div.alert');
                Controller.ajax({
                    url: APP_URLS['STOPS_MBTA'],
                    reqType: 'GET',
                    successCallback: function(response, textStatus, xhr) {
                        $(alertEl).hide();
                        if (response.status === 'SUCCESS') {
                            $('#main-container').empty();
                            $('#main-container').html(Template.getSearchMBTATemplate(response.data));
                        } else {
                            alertEl.addClass('alert-danger').text(response.status).show();
                        }
                    },
                    errorCallback: function(xhr, textStatus, errorThrown) {
                        alertEl.addClass('alert-danger').text(textStatus).show();
                    }
                });
            }
        },
        "raiseReqTemplate": {
            "raiseReqForm": function(event) {
                var theForm = $(this),
                    alertEl = $('#main-container').find('div.alert'),
                    options = {},
                    shiftDate = theForm.find('#shiftDate').val(),
                    shiftTime = theForm.find('#shiftTime').val(),
                    dropPoint = theForm.find('#dropPoint').val();

                options.url = APP_URLS['CREATE_REQ'];
                options.data = {
                    shiftDate: shiftDate,
                    shiftTime: shiftTime,
                    dropPoint: dropPoint
                };

                options.successCallback = function(response, textStatus, xhr) {
                    $(alertEl).hide();
                    if (response.status === 'SUCCESS') {
                        var msg = 'Your request has been raised and currently in ' + response.data.requestStatus + ' status.'
                        alertEl.addClass('alert-success').text(msg).show();
                    } else {
                        alertEl.addClass('alert-danger').text(response.msg).show();
                    }
                };

                options.errorCallback = function(xhr, textStatus, errorThrown) {
                    alertEl.addClass('alert-danger').text(textStatus).show();
                };

                Controller.ajax(options);

                return false;
            }
        },
        "viewReqTemplate": {
            "cancelReq_btn": function(event) {
                if($('#viewReqContainer').find('input:checked').length)
                   {
                       var alertEl = $('#main-container').find('div.alert'),
                       reqIdArr = $.makeArray($('#viewReqContainer').find('input:checked').map(function(index, domEl){
                            return $(domEl).val();
                       })).join(',');
                        Controller.ajax({
                            url: APP_URLS['CANCEL_REQ'],
                            reqType: 'POST',
                            data: {reqIds: reqIdArr},
                            successCallback: function(response, textStatus, xhr) {
                                $(alertEl).hide();
                                if (response.status === 'SUCCESS') {
                                    $('#main-container').empty();
                                    $('#main-container').html(Template.getViewReqTemplate(response.data));
                                } else {
                                    alertEl.addClass('alert-danger').text(response.status).show();
                                }
                            },
                            errorCallback: function(xhr, textStatus, errorThrown) {
                                alertEl.addClass('alert-danger').text(textStatus).show();
                            }
                        });
                   } 
            }
        },
        "searchMBTATemplate": {
            "searchMBTAForm": function(event){
                var theForm = $(this),
                    alertEl = $('#main-container').find('div.alert'),
                    options = {},
                    sourceStation = theForm.find('#sourceStation').val(),
                    destStation = theForm.find('#destStation').val();

                options.url = APP_URLS['SEARCH_MBTA'];
                options.data = {
                    sourceStation: sourceStation,
                    destStation: destStation
                };

                options.successCallback = function(response, textStatus, xhr) {
                    $(alertEl).hide();
                    if (response.status === 'SUCCESS') {
                        $('#map-canvas').show();
                       MapHelper.init($('#map-canvas')[0]);
                        var data = response.data;
                        $('#searchMBTAContainer').show();
                        $('#searchMBTAContainer').find('.sourceStn').text(sourceStation);
                        $('#searchMBTAContainer').find('.destStn').text(destStation);
                        var target = $('#searchMBTAContainer').find('tbody');
                        target.empty();
                         for(var i=0; i< data.sourceTimes.length; i++)
                        {
                            var thisTime = data.sourceTimes[i],
                            thisDestTime = data.destTime[i],
                            trEl = $('<tr>');

                            $('<td>').text(thisTime).appendTo(trEl);
                            $('<td>').text(thisDestTime).appendTo(trEl);
                            if(i%2)
                            {
                                trEl.addClass('info');
                            }

                            trEl.appendTo(target);
                        }
                        MapHelper.showDirections(data.sourceLat, data.sourceLong, data.destLat, data.destLong);

                    } else {
                        alertEl.addClass('alert-danger').text(response.msg).show();
                    }
                };

                options.errorCallback = function(xhr, textStatus, errorThrown) {
                    alertEl.addClass('alert-danger').text(textStatus).show();
                };

                Controller.ajax(options);

                return false;
            }
        }
    };

    //delegate all event handlers
    $(document).on('click', '#raiseReq_btn', RESPONSE_HANDLERS["homeTemplate"]["raiseReq_btn"]);
    $(document).on('click', '#viewReq_btn', RESPONSE_HANDLERS["homeTemplate"]["viewReq_btn"]);
    $(document).on('click', '#cancelReq_btn', RESPONSE_HANDLERS["viewReqTemplate"]["cancelReq_btn"]);
    $(document).on('click', '#searchMBTA_btn', RESPONSE_HANDLERS["homeTemplate"]["searchMBTA_btn"]);

    $(document).on('submit', '#loginForm', RESPONSE_HANDLERS["loginTemplate"]["loginForm"]);
    $(document).on('submit', '#raiseReqForm', RESPONSE_HANDLERS["raiseReqTemplate"]["raiseReqForm"]);
    $(document).on('submit', '#searchMBTAForm', RESPONSE_HANDLERS["searchMBTATemplate"]["searchMBTAForm"]);
    $(document).on('submit', '#registerForm', RESPONSE_HANDLERS["registerTemplate"]["registerForm"]);

    $(document).on('click', '#register_menu a', RESPONSE_HANDLERS["menuTemplate"]["register_menu"]);
    $(document).on('click', '#aboutUs_menu a', RESPONSE_HANDLERS["menuTemplate"]["aboutUs_menu"]);
    $(document).on('click', '#contactUs_menu a', RESPONSE_HANDLERS["menuTemplate"]["contactUs_menu"]);
    $(document).on('click', '#home_menu a', RESPONSE_HANDLERS["menuTemplate"]["home_menu"]);
    $(document).on('click', '#signout_menu a', RESPONSE_HANDLERS["menuTemplate"]["signout_menu"]);
    
    $(document).on('click', '.navbar-collapse.in', function (e) {
        if ($(e.target).is('a')) {
            $(this).collapse('hide');
        }
    });



    function checkLoginStatus(loggedCallback, notLoggedCallback, error){
        var alertEl = $('#main-container').find('div.alert');
        Controller.ajax({
            url: APP_URLS['CHECK_LOGIN'],
            reqType: 'GET',
            successCallback: function(response, textStatus, xhr) {
                if (response.status === 'SUCCESS') {
                   loggedCallback.call(window, response, textStatus, xhr);
                }
                else
                {
                    notLoggedCallback.call(window, response, textStatus, xhr);
                }    
            },
            errorCallback: function(xhr, textStatus, errorThrown) {
                error.call(window, xhr, textStatus, errorThrown);
            }
        });
     }

    
    function showLoggedMenu(show){
    	if(show === true)
    	{
    		$('#register_menu').hide();
    		$('#home_menu').show();
    		$('#signout_menu').show();
    	}
    	else
    	{
    		$('#register_menu').show();
    		$('#home_menu').hide();
    		$('#signout_menu').hide();
    	}
    	
    }

    $(document).ready(function() {
        checkLoginStatus (function(response, textStatus, xhr){
                //logged in
                var template = Template.getHomeTemplate();
                $('#main-container').empty();
                $('#main-container').append(template);
                showLoggedMenu(true);
            }, 

            function(response, textStatus, xhr){ //not logged in
                $('#main-container').empty();
                $('#main-container').html(Template.getLoginTemplate());
                showLoggedMenu(false);
            },
            
            function(xhr, textStatus, errorThrown){ //error
                $('#main-container').empty();
                $('#main-container').html(Template.getLoginTemplate());
                $('#main-container').find('div.alert').addClass('alert-danger').text(textStatus).show();
                showLoggedMenu(false);
            }
            );
        });

})(jQuery);
