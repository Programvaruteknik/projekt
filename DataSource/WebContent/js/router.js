var dataSource = angular.module('dataSource', ['ngRoute']);

	dataSource.config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl : './pages/home.html',
                controller  : 'homeController'
            })
    });