var chart; // global
var e;
var sensorName;
var defaultSelectedItem = 'SensorNode1/Pressure_BMP180';

$(document).ready(function () {

    var element = document.getElementById('sel1');
    element.value = defaultSelectedItem;

    Highcharts.setOptions({
        global: {
            timezoneOffset: -3 * 60//GMT+3 reinvent the wheel)
        }
    });
    show();


});

function show() {

    e = document.getElementById("sel1");
    sensorName = e.options[e.selectedIndex].text;



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
        data: "sensorName=" + sensorName,
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