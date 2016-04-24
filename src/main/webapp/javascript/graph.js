$(document).ready(function () {
    show();
    draw();
});


function show()
{
    var e = document.getElementById("sel1");
    var nodeId = e.options[e.selectedIndex].text;

    $.ajax({
        url: 'plot',
        type: 'get',
        dataType: 'json',
        data: "nodeId=" + nodeId,
        success: function (data) {

            plot1(data);

        }
    });
}

function plot1(data) {
    $('#chart').highcharts({
        chart: {
            zoomType: 'x'
        },
        title: {
            text: 'data'
        },
        subtitle: {
            text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' :
                    'Pinch the chart to zoom in'
        },
        xAxis: {
            type: 'datetime',
            minRange: 300 * 1000
        },
        yAxis: {
            title: {
                text: 'Value'
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            area: {
                fillColor: {
                    linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                },
                marker: {
                    radius: 2
                },
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            }
        },
        series: [{
                name: 'Data',
                data: data
            }]
    });
}

var data1, data2, data3;
function draw()
{

    var nodeId = 3;

    $.ajax({
        url: 'plot',
        type: 'get',
        dataType: 'json',
        data: "nodeId=" + nodeId,
        success: function (data) {

            data1 = data;
        }
    });

    nodeId = 7;
    $.ajax({
        url: 'plot',
        type: 'get',
        dataType: 'json',
        data: "nodeId=" + nodeId,
        success: function (data) {

            data2 = data;

        }
    });
    nodeId = 16;
    $.ajax({
        url: 'plot',
        type: 'get',
        dataType: 'json',
        data: "nodeId=" + nodeId,
        success: function (data) {

            data3 = data;
            plot2();

        }
    });
}
function plot2() {
    $('#container1').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: 'Temperature'
        },
        subtitle: {
            text: 'Irregular time data in Highcharts JS'
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: {// don't display the dummy year
                month: '%e. %b',
                year: '%b'
            },
            title: {
                text: 'Date'
            }
        },
        yAxis: {
            title: {
                text: 'Data'
            },
            min: 0
        },
        tooltip: {
            headerFormat: '<b>{series.name}</b><br>',
            pointFormat: '{point.x:%e. %b}: {point.y:.2f}'
        },
        plotOptions: {
            spline: {
                marker: {
                    enabled: true
                }
            }
        },
        series: [{
                name: 'Sensor 1',
                // Define the data points. All series have a dummy year
                // of 1970/71 in order to be compared on the same x axis. Note
                // that in JavaScript, months start at 0 for January, 1 for February etc.
                data: data1
            }, {
                name: 'Sensor 2',
                data: data2
            }, {
                name: 'Sensor 3',
                data: data3
            }]
    });
}