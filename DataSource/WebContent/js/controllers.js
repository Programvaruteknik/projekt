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
		updateChart($scope.selectedDataSource, resolution,$scope.selectedRegression);
	}
	
	$scope.setRegression = function(regression){	
		$scope.selectedRegression = regression;
		updateChart($scope.selectedDataSource, $scope.selectedResolution, regression);
	}
	
	$scope.select = function (){
		resolution = $scope.selectedResolution === "Resolution"?"DAY":$scope.selectedResolution;
		updateChart($scope.selectedDataSource, resolution);
		
		var query = $scope.selectedDataSource;
		console.log(query);
		
		$resource("api/dataSource/metaData?list="+$scope.selectedDataSource).query(function(data){
			$scope.metaData = data;
		});
		
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
