angular.module('controllers', ['googlechart','mm.foundation', 'services' ])
.controller("dataSourceChartController", function($scope, $resource, DataSourceChart){
	
	
	$resource("api/dataSource/list").query(function(data) {
		$scope.dataSources = data;
	});
	
	var chartData = [];

	$scope.select = function (){
		
		DataSourceChart.select($scope.selectedDataSource).then(function(data){
			$scope.chart.data = data;
			
			var query = $scope.selectedDataSource;
			
			$resource("api/dataSource/metaData?list="+$scope.selectedDataSource).query(function(data){
				$scope.metaData = data;
			});
		
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
	
	$scope.availableRegressions = ['linear','exponential','polynomial (degree 2)','polynomial (degree 3)'];
	$scope.selectedResolution = "DAY";
	$scope.selectedRegression = "linear";
	$scope.selectedDataSource="";
	
	$scope.setResolution = function(resolution)
	{
		$scope.selectedResolution = resolution;
		updateChart();
	}
	
	$scope.setRegression = function(regression){	
		$scope.selectedRegression = regression;
		updateChart();
	}
	
	$scope.select = function (){

		updateChart();		
	};
	
	
	updateChart = function()
	{
		var selectedDataSource = $scope.selectedDataSource;
		var resolution = $scope.selectedResolution;
		var regression = $scope.selectedRegression;
		var modList = $scope.modList;
		
		CorrelationChart.select(selectedDataSource, resolution, regression, modList).then(function(data){
			$scope.chart.data = data.chartData;
			$scope.metaData = data.metaData;
		});	
		
		console.log($scope.metaData);
	}
	
	$scope.modList = [{
		dataSourceName:"Totala mål per dag i Allsvenskan",
		year:0,
		month:0,
		days:1
			}];
	
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
