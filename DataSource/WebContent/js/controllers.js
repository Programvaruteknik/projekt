angular.module('controllers', ['googlechart','mm.foundation' ]).controller("dataSourceChartController", function($scope){
	
	 $scope.items = [
	                 "The first choice!",
	                 "And another choice for you.",
	                 "but wait! A third!"
	               ];
	$scope.linkItems = {
		"Google": "http://google.com",
		"AltaVista": "http://altavista.com"
	};
	
	
	$scope.selected = undefined;
	$scope.datasources = ['total football goals', 'the suns altitude at noon'];
	
	
	$scope.chart = {
			  "type": "LineChart",
			  "cssStyle": "height:500px; width:900px;",
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

	
});