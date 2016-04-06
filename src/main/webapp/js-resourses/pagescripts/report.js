$(function() {
	
	function createChart(selector, data, title) {
		selector.highcharts({
			chart: {
			    type: 'column',    
			},
	        title: {
	            text: title
	        },
	        plotOptions: {
	            series: {
	                //borderWidth: 0,
	                dataLabels: {
	                    enabled: true,
	                    y: -4
	                }
	            }
	        },
	        series: data
	    });
	}
	
	$.ajax({
		url: '/SplashPage/report/q1',
		type: 'get',
		success: function (data) {
			createChart($('#q1Chart'), data, 'What do you expected from our company?');
		}
	});
	
	$.ajax({
		url: '/SplashPage/report/q2',
		type: 'get',
		success: function (data) {
			createChart($('#q2Chart'), data, 'How much you want to pay?');
		}
	});
	
	$.ajax({
		url: '/SplashPage/report/q3',
		type: 'get',
		success: function (data) {
			createChart($('#q3Chart'), data, 'What you REALLY needs?');
		}
	});
	
});