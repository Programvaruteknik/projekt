angular.module('controllers', ['googlechart','mm.foundation' ])
.controller("dataSourceChartController", function($scope, $resource){
	
	$scope.dataSources = ['TotalFotballGoals', 'SunAltitudeAtNoon'];
	
	var selectedDataSources = [];
	var chartData = [];
	
	$scope.select = function (){
		
		selected = _.difference($scope.selectedDataSource, selectedDataSources)[0];
		selectedDataSources = $scope.selectedDataSource;
		
		$resource("api/dataSource/:dataSource").get({dataSource:selected}, function(data) {
			console.log(data);
			chartData = [["test","test"]];
			angular.forEach(data, function(apiData, key) {
				chartData.push([key, apiData])
			});
			console.log(chartData);
			$scope.chart.data = chartData;
		});
	};
	
	$scope.chart = {
			  "type": "LineChart",
			  "cssStyle": "height:30em; width:100%;",
			  "displayed": true,
			  "data":[["",""],[0,0]],
			  "options": {
				  title: 'Data Sources',
				  pointSize: 12,
				  interpolateNulls: true
			    
			  }
			}
})

.controller("correlationChartController", function($scope, $resource){
	
	$scope.dataSources = ['TotalFotballGoals', 'SunAltitudeAtNoon'];
	
	$scope.select = function (){
		
		if($scope.selectedDataSource.length ===2)
		{
			$resource("api/dataSource/correlationData/:dataSource1/:dataSource2").get({dataSource1:$scope.selectedDataSource[0],dataSource2:$scope.selectedDataSource[1]}, function(data) {
				
				var chartData = [["test","test"]];
				angular.forEach(data.resultData, function(apiData, key) {
					chartData.push([apiData.x,apiData.y])
				});
				
				$scope.chart.data = chartData;
			});
			
		}
		
	};
	
	$scope.chart = {
			"type": "ScatterChart",
			"cssStyle": "height:30em; width:100%;",
			"displayed": true,
			"data":[["",""],[0,0]],
			"options": {
				title: 'Correlation Chart',
				pointSize: 12,
				legend: 'none'
			}
	}
});