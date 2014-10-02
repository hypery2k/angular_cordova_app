var app = angular.module('angularCordovaApp', [
  'fsCordova',
  'ngRoute',
  'ngTouch',
  'mobile-angular-ui',
  'LocalStorageModule'
]);

app.config(function($routeProvider, $locationProvider) {
  $routeProvider
    .when('/', {
      templateUrl: 'views/home.html',
      controller: 'AppController'
    }).when('/settings', {
      templateUrl: 'views/settings.html',
      controller: 'SettingsController'
    }).when('/users', {
      templateUrl: 'views/users.html',
      controller: 'UsersController'
    }).when('/users/:username', {
      templateUrl: 'views/chat.html',
      controller: 'ChatController'      
    }).otherwise({
      redirectTo: '/home'
    });
});

deferredBootstrapper.bootstrap({
	  element: document.body,
	  module: 'angularCordovaApp',
	  resolve: {
	    APP_CONFIG: ['$http', function ($http) {
	      return $http.get('/config.json');
	    }]
	  }
	});

app.controller('AppController', function($rootScope, $scope, CordovaService) {
    $rootScope.loading = true;

	  CordovaService.ready.then(function() {
	    // Cordova is ready
	    $rootScope.loading = false;

	    $rootScope.$on("$routeChangeStart", function() {
	      $rootScope.loading = true;
	    });

	    $rootScope.$on("$routeChangeSuccess", function() {
	      $rootScope.loading = false;
	    });

	  });

	});