angular.module('controllers', ['googlechart','mm.foundation', 'services', '720kb.datepicker' ])
.controller("dataSourceChartController", function($scope, $resource, DataSourceChart){
	
	
	$resource("api/dataSource/list").query(function(data) {
		$scope.dataSources = data;
	});
	
	var chartData = [];
	var dataSources = [];
	$scope.select = function (){
		dataSources.push($scope.selectedDataSource);
		DataSourceChart.select($scope.selectedDataSource, $scope.datepick).then(function(data){
			$scope.chart.data = data;
			
			var query = $scope.selectedDataSource;
			
			$resource("api/dataSource/metaData?list="+$scope.selectedDataSource).query(function(data){
				$scope.metaData = data;
			});
		
		});
		
	};
	
	$scope.chart = DataSourceChart.chart;
	
	$scope.toDay = new Date().toISOString();
	/* Use $scope.metaData for min-date*/
	
	
	$scope.datepick = {
			startDate : undefined,
			endDate : undefined
	}
	
	
	
	
	
	
	$scope.$watch("startDate", function(newVal, oldVal){
		$scope.datepick.startDate = newVal;
		$scope.endDate = undefined;
	})
	
	$scope.$watch("endDate", function(newVal, oldVal){
		$scope.datepick.endDate = newVal;
		if($scope.datepick.startDate && $scope.datepick.endDate){
			dataSources.forEach(function(data){
				DataSourceChart.select(data, $scope.datepick).then(function(data){
					$scope.chart.data = data;
				})})
		}
	})
})

.controller("correlationChartController", function($scope, $resource, CorrelationChart){
	$scope.toDay = new Date().toISOString();
	$scope.matchDate = {
			startDate : undefined,
			endDate : undefined
	}
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
		updateChart($scope.selectedDataSource, resolution,$scope.selectedRegression, $scope.matchDate);
	}
	
	$scope.setRegression = function(regression){	
		$scope.selectedRegression = regression;
		updateChart($scope.selectedDataSource, $scope.selectedResolution, regression, $scope.matchDate);
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
		CorrelationChart.select(selectedDataSource, resolution, regression, $scope.matchDate).then(function(data){
			$scope.chart.data = data;
		});	
	}
	
	$scope.$watch("startDate", function(newVal, oldVal){
		$scope.matchDate.startDate = newVal;
		$scope.endDate = undefined;
	})
	
	$scope.$watch("endDate", function(newVal, oldVal){
		$scope.matchDate.endDate = newVal;
		console.log($scope.matchDate)
		if($scope.matchDate.startDate && $scope.matchDate.endDate){
				resolution = $scope.selectedResolution === "Resolution"?"DAY":$scope.selectedResolution;
				CorrelationChart.select($scope.selectedDataSource, resolution,$scope.selectedRegression, $scope.matchDate).then(function(data){
					$scope.chart.data = data;
			})
		}
	})
	
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
