angular.module('directives', [])
.directive('modTabel', function() {
	
	return {
		restrict: 'E',
		templateUrl: './html/directives/modTabel.html',
		controller: function($scope){
			
			$scope.addMod = function(){
				
				$scope.modList.push({
					dataSourceName:"Solens altitud vid 12",
					year:0,
					month:0,
					days:-1
						});
				$scope.updateChart();
			}
		}
	}
});