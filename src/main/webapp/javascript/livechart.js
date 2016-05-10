var chart; // global
var nodeId;
var e;
var sensorName;

$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            timezoneOffset: -3 * 60//GMT+3 reinvent the wheel)
        }
    });
    show();


});

function show() {
    
    e = document.getElementById("sel1");
    nodeId = e.options[e.selectedIndex].text;
    
    $.ajax({
        url: 'sensor/'+nodeId+'/name',
        type: 'get',
        dataType: 'text',
        success: function (data) {
            sensorName=data;
      
        }
    });

    chart = new Highcharts.Chart({
        chart: {
            renderTo: 'chart',
            defaultSeriesType: 'spline',
            events: {
                load: requestData
            }
        },
        title: {
            text: 'Live data'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150,
            maxZoom: 20 * 1000
        },
        yAxis: {
            minPadding: 0.2,
            maxPadding: 0.2,
            title: {
                text: 'Value',
                margin: 80
            }
        },
        series: [{
                name: sensorName,
                data: []
            }]
    });
}

function requestData() {
    $.ajax({
        url: 'livedata',
        data: "nodeId=" + nodeId,
        success: function (point) {
            var series = chart.series[0],
                    shift = series.data.length > 30; // shift if the series is 
            // longer than 20

            // add the point
            chart.series[0].addPoint(point, true, shift);

            // call it again after one second
            setTimeout(requestData, 4000);
        },
        cache: false
    });
}