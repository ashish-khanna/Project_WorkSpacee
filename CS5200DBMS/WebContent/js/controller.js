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
            success: function(data, textStatus, xhr) {
                console.log(data);
                options.successCallback.call(window, data, textStatus, xhr);
            },
            error: function(xhr, textStatus, errorThrown) {
                console.log(errorThrown);
                options.errorCallback.call(window, xhr, textStatus, errorThrown);
            }
        });
    };

})(jQuery);
