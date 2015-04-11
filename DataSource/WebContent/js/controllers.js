angular.module('controllers', ['googlechart' ]).controller("dataSourceChartController", function($scope){
	
	 $scope.chart = {
			  "type": "LineChart",
			  "cssStyle": "height:500px; width:900px;",
			  "displayed": true,
			  "data": [
			           		['Year', 'Sales', 'Expenses'],
							['2004',  1000,      400],
							['2005',  1170,      460],
							['2006',  660,       1120],
							['2007',  1030,      540]
			         ],
			  "options": {
				  title: 'Company Performance',
		          curveType: 'function',
		          legend: { position: 'bottom' }
			    
			  }
			}


});