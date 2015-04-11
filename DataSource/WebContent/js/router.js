angular.module('dataSource', ['ngRoute', 'controllers' ]).config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl : './html/pages/chart.html',
                controller  : 'dataSourceChartController'
            })
    });