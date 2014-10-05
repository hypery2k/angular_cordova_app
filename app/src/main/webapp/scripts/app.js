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

angular.element(document).ready(
  function() {
    var $http = angular.injector(['ng']).get('$http');
    $http.get('config.json').then(
      function(response) {
        var config = response.data;
        app.constant("APP_CONFIG", config);
        // Add additional services/constants/variables to your app,
        // and then finally bootstrap it:
        angular.bootstrap(document, ['angularCordovaApp']);
      }
    );
  }
);

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