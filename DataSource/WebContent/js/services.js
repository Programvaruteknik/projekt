angular.module('services', [])
.service('CorrelationChart', function($resource, $q) {
  
	var that = this;
	this.select = function (selectedDataSource, resolution,regression){
		var degree= 1;

		var deferred = $q.defer();
		if(selectedDataSource.length ===2)
		{
			$resource("api/dataSource/correlationData?dataSource1=:dataSource1&dataSource2=:dataSource2&resolution=:resolution").get({dataSource1:selectedDataSource[0],dataSource2:selectedDataSource[1], resolution:resolution}, function(data) {

				if(!data.xMeta.hasData)
				{
					alert(data.xMeta.title + " missing data", "the selected interval contains no data", "warning");
				}
				if(!data.yMeta.hasData)
				{
					alert(data.yMeta.title + " missing data", "the selected interval contains no data", "warning");
				}
				
				var chartData = [["","x unit per y unit"]];
				angular.forEach(data.resultData, function(apiData, key) {
					chartData.push([apiData.x,apiData.y]);
				});
				
				if(regression === "polynomial:2"){
					that.chart.options.trendlines[0].type = 'polynomial';
					that.chart.options.trendlines[0].degree = 2;
				}else if(regression === "polynomial:3"){
					that.chart.options.trendlines[0].type = 'polynomial';
					that.chart.options.trendlines[0].degree = 3;
				}else{
					that.chart.options.trendlines[0].type = regression;
				}
				that.chart.options.hAxis.title = data.xMeta.title;
				that.chart.options.vAxis.title = data.yMeta.title;
				
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
	          chartArea: {width:'50%'},
			"options": {
				title: 'Correlation Chart',
				hAxis:{},
				vAxis:{},
		          trendlines: {
		              0: {
		            	  color: 'green',
		            	  pointSize:2,
		                  type: 'linear',
		                  showR2: true,
		                  visibleInLegend: true
		              }
		            }

			}
	}
	
})
.service('DataSourceChart', function($resource, $q) {
	
	
	
	this.select = function (selectedDataSource){
		var deferred = $q.defer();
		
		$resource("api/dataSource/:dataSource").get({dataSource:angular.toJson(selectedDataSource)}, function(data) {
			
			angular.forEach(data.metaDataList, function(value, key) {
					if(!value.hasData)
					{
						alert(value.title + " missing data", "the selected interval contains no data", "warning");
					}
				  
				});
			
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
				  interpolateNulls: true,
			}
	}
	
});
