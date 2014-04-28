var Controller = {};

(function($) {

    Controller.ajax = function(options) {
        $.ajax({
            async: true,
            cache: false,
            url: options.url,
            type: options.reqType || 'POST',
            dataType: options.dataType || 'json',
            data: options.data,
            beforeSend: function(){
                $('#loader').show();
            },
            complete: function(){
                $('#loader').hide();
            },
            success: function(data, textStatus, xhr) {
                console.log(data);
                if(typeof (options.successCallback) == 'function')
                {
                    options.successCallback.call(window, data, textStatus, xhr);                	
                }	
            },
            error: function(xhr, textStatus, errorThrown) {
                console.log(errorThrown);
                if(typeof (options.errorCallback) == 'function')
                {
                	options.errorCallback.call(window, xhr, textStatus, errorThrown);
                }
            }
        });
    };

})(jQuery);
