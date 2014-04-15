(function($) {

    var APP_URLS = {
        LOGIN: 'login.json',
        ALL_SHIFTS: 'rest/shifttime/allshift',
        CREATE_REQ: 'rest/request/createreq',
        VIEW_REQ: 'rest/request/allreq',
        SEARCH_MBTA: 'rest/mbta/searchmbta'
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
                    } else {
                        //invalid login
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
                $('#main-container').empty();
                $('#main-container').html(Template.getSearchMBTATemplate());
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
            "cancelReq_btn": function() {

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
                        var data = response.data;
                         $('#searchMBTAContainer').show();
                        $('#searchMBTAContainer').find('.sourceStn').text(sourceStation);
                        $('#searchMBTAContainer').find('.destStn').text(destStation);
                        var target = $('#searchMBTAContainer').find('tbody');

                         for(var i=0; i< data.sourceTimes.length; i++)
                        {
                            var thisTime = data.sourceTimes[i],
                            trEl = $('<tr>');

                            $('<td>').text(thisTime).appendTo(trEl);
                            $('<td>').text('-').appendTo(trEl);
                            if(i%2)
                            {
                                trEl.addClass('info');
                            }

                            trEl.appendTo(target);
                        }

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
    $(document).on('click', '#searchMBTA_btn', RESPONSE_HANDLERS["homeTemplate"]["searchMBTA_btn"]);

    $(document).on('submit', '#loginForm', RESPONSE_HANDLERS["loginTemplate"]["loginForm"]);
    $(document).on('submit', '#raiseReqForm', RESPONSE_HANDLERS["raiseReqTemplate"]["raiseReqForm"]);
    $(document).on('submit', '#searchMBTAForm', RESPONSE_HANDLERS["searchMBTATemplate"]["searchMBTAForm"]);

    $(document).ready(function($) {
        $('#main-container').empty();
        $('#main-container').html(Template.getLoginTemplate());
    });
})(jQuery);
