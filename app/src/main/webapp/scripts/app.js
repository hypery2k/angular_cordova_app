var app = angular.module('angularCordovaApp', [
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

var onDeviceReady = function() {
  var $http = angular.injector(['ng']).get('$http'),
    $rootScope = angular.injector(['ng']).get('$rootScope');
  $rootScope = angular.injector(['ng']).get('$rootScope');
  $rootScope.loading = true;
  $http.get('config.json')
    .success(function(data, status, headers, config) {
      var config = data;
      app.constant("APP_CONFIG", config);
      // Add additional services/constants/variables to your app,
      // and then finally bootstrap it:
      angular.bootstrap(document, ['angularCordovaApp']);
      $rootScope.loading = false;
    })
    .error(function(data, status, headers, config) {
      console.error('Server error during reading users');
      navigator.notification.alert('Server did not show valid response.', null, 'Server Error');
      $rootScope.loading = false;
    });
}

// on dev fire up event directly
if (navigator.userAgent.match(/(iPhone|iPod|iPad|Android|BlackBerry)/)) {
  document.addEventListener("deviceready", onDeviceReady, false);
} else {
  onDeviceReady();
}

app.controller('AppController', function($rootScope, $scope, $location, $route, SettingsService, ConfigService) {

  var settings = SettingsService.load();
  if (settings && settings.valid) {
    // auto init config
    ConfigService.configureServices();
  } else {
    // redirect to settings
    // redirect
    $location.path("/settings");
  }

  $rootScope.$on("$routeChangeStart", function() {
    $rootScope.loading = true;
  });

  $rootScope.$on("$routeChangeSuccess", function() {
    $rootScope.loading = false;
  });

});