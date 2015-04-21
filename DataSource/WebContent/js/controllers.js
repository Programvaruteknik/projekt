angular.module('controllers', ['googlechart','mm.foundation', 'services' ])
.controller("dataSourceChartController", function($scope, $resource, DataSourceChart){
	
	
	$resource("api/dataSource/list").query(function(data) {
		$scope.dataSources = data;
	});
	
	var chartData = [];

	
	$scope.select = function (){
		
		DataSourceChart.select($scope.selectedDataSource).then(function(data){
			
			$scope.chart.data = data;
		});
		
	};
	
	$scope.chart = DataSourceChart.chart;
})

.controller("correlationChartController", function($scope, $resource, CorrelationChart){
	
	$resource("api/dataSource/list").query(function(data) {
		$scope.dataSources = data;
	});
	
	
	$scope.select = function (){
		
		CorrelationChart.select($scope.selectedDataSource).then(function(data){
			
			$scope.chart.data = data;
		});
		
	};
	
	$scope.chart = CorrelationChart.chart;
});