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
	
	alert("test", "test");
	alert("test", "test");
	alert("test", "test");
	alert("test", "test");
	
})

.controller("correlationChartController", function($scope, $resource, CorrelationChart){
	
	$resource("api/dataSource/list").query(function(data) {
		$scope.dataSources = data;
	});
	
	$resource("api/dataSource/resolutions").query(function(data) {
		$scope.resolutions = data;
	});
	
	$scope.availableRegressions = ['linear','exponential','polynomial:2','polynomial:3'];
	$scope.selectedResolution = "Resolution";
	$scope.selectedRegression = "Regression";
	$scope.selectedDataSource="";
	
	$scope.setResolution = function(resolution)
	{
		$scope.selectedResolution = resolution;
		updateChart($scope.selectedDataSource, resolution,$scope.selectedRegression);
	}
	
	$scope.setRegression = function(regression){	
		$scope.selectedRegression = regression;
		updateChart($scope.selectedDataSource, $scope.selectedResolution, regression);
	}
	
	$scope.select = function (){
		resolution = $scope.selectedResolution === "Resolution"?"DAY":$scope.selectedResolution;
		updateChart($scope.selectedDataSource, resolution);
	};
	
	updateChart = function(selectedDataSource, resolution, regression)
	{
		CorrelationChart.select(selectedDataSource, resolution, regression).then(function(data){
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
