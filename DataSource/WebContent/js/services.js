angular.module('services', [])
.service('CorrelationChart', function($resource, $q) {
  
	
	var deferred = $q.defer();
	
	this.select = function (selectedDataSource){
		
		if(selectedDataSource.length ===2)
		{
			$resource("api/dataSource/correlationData/:dataSource1/:dataSource2").get({dataSource1:selectedDataSource[0],dataSource2:selectedDataSource[1]}, function(data) {
				
				var chartData = [["test","test"]];
				angular.forEach(data.resultData, function(apiData, key) {
					chartData.push([apiData.x,apiData.y])
				});
				
				deferred.resolve(chartData);
			});
			
		}
		deferred.promise.then(function(res){
			console.log(res);
		});
		return deferred.promise;
	};
	
	this.chart = {
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
	
})
.service('DataSourceChart', function($resource, $q) {
	
	
	var deferred = $q.defer();
	
	this.select = function (selectedDataSource){
		
		$resource("api/dataSource/:dataSource").get({dataSource:angular.toJson(selectedDataSource)}, function(data) {
			chartData = [data.header];
			angular.forEach(data.data, function(apiData, key) {
				
				var output = [key];
				
				chartData.push(output.concat(apiData))
			});
			deferred.resolve(chartData);
		});
		return deferred.promise;
	};
	
	this.chart = {
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
	
});