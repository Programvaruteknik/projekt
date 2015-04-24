angular.module('services', [])
.service('CorrelationChart', function($resource, $q) {
  
	var that = this;
	this.select = function (selectedDataSource, resolution){

		var deferred = $q.defer();
		if(selectedDataSource.length ===2)
		{
			$resource("api/dataSource/correlationData?dataSource1=:dataSource1&dataSource2=:dataSource2&resolution=:resolution").get({dataSource1:selectedDataSource[0],dataSource2:selectedDataSource[1], resolution:resolution}, function(data) {
				
				var chartData = [["","x unit per y unit"]];
				angular.forEach(data.resultData, function(apiData, key) {
					chartData.push([apiData.x,apiData.y])
				});
				
				that.chart.options.hAxis.title = data.metaData.xAxisLabel;
				that.chart.options.vAxis.title = data.metaData.yAxisLabel;
				deferred.resolve(chartData);
			});
		}


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
				legend: 'none',
				hAxis:{},
				vAxis:{}
			}
	}
	
})
.service('DataSourceChart', function($resource, $q) {
	
	
	
	this.select = function (selectedDataSource){
		var deferred = $q.defer();
		
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
