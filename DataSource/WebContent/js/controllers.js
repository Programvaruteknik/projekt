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
	$resource("api/dataSource/resolutions").query(function(data) {
		$scope.resolutions = data;
	});
	
	$scope.selectedResolution = "Resolution";
	
	$scope.setResolution = function(resolution)
	{
		$scope.selectedResolution = resolution;
		updateChart($scope.selectedDataSource, resolution);
	}
	
	$scope.select = function (){
		
		resolution = $scope.selectedResolution === "Resolution"?"DAY":$scope.selectedResolution;
		
		updateChart($scope.selectedDataSource, resolution);
	};
	
	updateChart = function(selectedDataSource, resolution)
	{
		CorrelationChart.select(selectedDataSource, resolution).then(function(data){
			
			$scope.chart.data = data;
		});	
	}
	
	$scope.chart = CorrelationChart.chart;
})
.controller("outlineChartController", function($scope, $resource, CorrelationChart, DataSourceChart){
	
	$resource("api/dataSource/list").query(function(data) {
		$scope.dataSources = data;
	});
	
	
	$scope.select = function (){
		
		DataSourceChart.select($scope.selectedDataSource).then(function(data){
			
			$scope.dataSourceChart.data = data;
		});
		
		CorrelationChart.select($scope.selectedDataSource).then(function(data){
			
			$scope.correlationChart.data = data;
		});
		
	};
	
	$scope.dataSourceChart = DataSourceChart.chart;
	$scope.correlationChart = CorrelationChart.chart;
});