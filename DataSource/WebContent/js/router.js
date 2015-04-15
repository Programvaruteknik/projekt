angular.module('dataSource', ['ngRoute', 'ngResource', 'controllers', 'localytics.directives' ]).config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl : './html/pages/dataSourceChart.html',
                controller  : 'dataSourceChartController'
            })
            .when('/dataSourceChart', {
            	templateUrl : './html/pages/dataSourceChart.html',
            	controller  : 'dataSourceChartController'
            })
            .when('/correlationChart', {
            	templateUrl : './html/pages/correlationChart.html',
            	controller  : 'correlationChartController'
            })
    });