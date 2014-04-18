var Template = {};

(function($) {

    Template.getLoginTemplate = function() {
        return $('#loginTemplate').html();
    };

    Template.getHomeTemplate = function() {
        return $('#homeTemplate').html();
    };

    Template.getRaiseReqTemplate = function(data) {
        var container = $('<div>').html($('#raiseReqTemplate').html()),
        selShiftEl = container.find('select#shiftTime'),
        selLocEl = container.find('select#dropPoint');

        for(var i=0; i< data.shifts.length; i++)
        {
            var optEl = $('<option>').attr('value', data.shifts[i].shift).text(data.shifts[i].shift);
            selShiftEl.append(optEl);
        }


        for(var i=0; i< data.locations.length; i++)
        {
            var optEl = $('<option>').attr('value', data.locations[i].id).text(data.locations[i].locName);
            selLocEl.append(optEl);
        }

        return container.children();
    };


    Template.getViewReqTemplate = function(data) {
        var container = $('<div>').html($('#viewReqTemplate').html()),
        target = container.find('table#viewReqContainer').find('tbody');

        for(var i=0; i< data.requests.length; i++)
        {
            var thisReq = data.requests[i],
            trEl = $('<tr>'),
            tdEl1 = $('<td>');

            $('<input>').attr({'value': thisReq.id, 'type': 'checkbox'}).appendTo(tdEl1);
            trEl.append(tdEl1);
            $('<td>').text(thisReq.shiftDate).appendTo(trEl);
            $('<td>').text(thisReq.shift).appendTo(trEl);
            $('<td>').text(thisReq.status).appendTo(trEl);
            if(thisReq.vehicle)
            {
                $('<td>').text(thisReq.vehicle.regNo).appendTo(trEl);
            }
            else
            {
                $('<td>').text('-').appendTo(trEl);
            }
            $('<td>').text(thisReq.dropPoint.locName).appendTo(trEl);
            
            if(i%2)
            {
                trEl.addClass('info');
            }

            trEl.appendTo(target);
        }

        return container.children();
    };

    Template.getSearchMBTATemplate = function() {
        return $('#searchMBTATemplate').html();
    };

    Template.getRegisterTemplate = function() {
        return $('#registerTemplate').html();
    };
})(jQuery);
