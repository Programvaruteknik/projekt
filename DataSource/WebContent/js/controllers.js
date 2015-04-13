angular.module('controllers', ['googlechart','mm.foundation' ])
.controller("dataSourceChartController", function($scope){
	
	$scope.selected = undefined;
	$scope.datasources = ['total football goals', 'the suns altitude at noon'];
	
	
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
	
	$scope.onSelect = function(){
		
		if($scope.selected === 'total football goals')
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
		else if($scope.selected === 'the suns altitude at noon')
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

	
})
.controller("correlationChartController", function($scope){
	
	$scope.selected = undefined;
	$scope.selected2 = undefined;
	$scope.datasources = ['total football goals', 'the suns altitude at noon'];
	
	
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
	
	$scope.onSelect = function(){
		if($scope.selected !== undefined && $scope.selected2 !== undefined)
		{

				console.log("Scatter Chart");
				$scope.chart.data = [
				                     ['Date', 'Mål'],
				                     [12,  10],
				                     [5,  0],
				                     [6,  12],
				                     [2,  5],
				                     [2,  3],
				                     ];
				

		}
	};
	
	
});