angular.module('controllers', ['googlechart','mm.foundation' ])
.controller("dataSourceChartController", function($scope){
	
	$scope.dataSources = ['TotalFotballGoals', 'SunAltitudeAtNoon'];
	
	var selectedDataSources = [];
	
	$scope.select = function (){
		
		selected = _.difference($scope.selectedDataSource, selectedDataSources)[0];
		selectedDataSources = $scope.selectedDataSource;
		
		if(selected === 'TotalFotballGoals')
		{
			console.log("goals");
			$scope.chart.data = [
			                     ['Date', 'Mål'],
			                     ['2015-01-01',  10],
			                     ['2015-01-03',  0],
			                     ['2015-01-05',  12],
			                     ['2015-01-07',  5],
			                     ['2015-01-08',  3],
			                     ];
			
			
		}
		else if(selected === 'SunAltitudeAtNoon')
		{
			console.log("altitude");
			$scope.chart.data = [
				                     ['Date', 'altitude'],
				                     ['2015-01-01',  47],
				                     ['2015-01-04',  42],
				                     ['2015-01-06',  44],
				                     ['2015-01-08',  43],
				                     ];
			
		}
	};
	
	
//	$scope.selected = undefined;
//	$scope.datasources = ['total football goals', 'the suns altitude at noon'];
//	
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
//	
//	$scope.onSelect = function(){
//		
//		if($scope.selected === 'total football goals')
//		{
//			console.log("goals");
//			$scope.chart.data = [
//			                     ['Date', 'Mål'],
//			                     ['2015-01-01',  10],
//			                     ['2015-01-03',  0],
//			                     ['2015-01-05',  12],
//			                     ['2015-01-07',  5],
//			                     ['2015-01-08',  3],
//			                     ];
//			
//			
//		}
//		else if($scope.selected === 'the suns altitude at noon')
//		{
//			console.log("altitude");
//			$scope.chart.data = [
//				                     ['Date', 'altitude'],
//				                     ['2015-01-01',  47],
//				                     ['2015-01-04',  42],
//				                     ['2015-01-06',  44],
//				                     ['2015-01-08',  43],
//				                     ];
//			
//		}
//	};

	
})
.controller("correlationChartController", function($scope, $resource){
	

	
	$scope.dataSources = ['TotalFotballGoals', 'SunAltitudeAtNoon'];
	
	$scope.select = function (){
		
		if($scope.selectedDataSource.length ===2)
		{
			$resource("api/dataSource/correlationData/:dataSource1/:dataSource2").get({dataSource1:$scope.selectedDataSource[0],dataSource2:$scope.selectedDataSource[1]}, function(data) {
				
				var chartData = [["test","test"]];
				angular.forEach(data.resultData, function(ApiData, key) {
					chartData.push([ApiData.x,ApiData.y])
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