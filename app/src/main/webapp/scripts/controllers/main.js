app.controller('AppController', function($rootScope, $scope) {
  $rootScope.loading = false;

  $rootScope.$on("$routeChangeStart", function() {
    $rootScope.loading = true;
  });

  $rootScope.$on("$routeChangeSuccess", function() {
    $rootScope.loading = false;
  });

});

app.controller('ChatController', function($rootScope, $scope, $routeParams, UserService, SettingsService) {
  var username = $routeParams.username,
    settings = SettingsService.load();

  function initChat() {
    $scope.sender = settings.username;
    $scope.recipient = username;
  }

  // auto init
  initChat();

});

app.controller('UsersController', function($rootScope, $scope, UserService) {

  function loadUsers() {
    UserService.listAllUsers().then(
      function(response) {
        $scope.users = response.data;
      }
    );
  }

  // auto init
  loadUsers();

});

app.controller('NavigationController', function($rootScope, $scope, SettingsService) {
  $scope.settings = SettingsService.load();
});

app.controller('SettingsController', function($rootScope, $scope, $location, SettingsService) {
  "use strict";


  function loadSettings() {
    $scope.settings = SettingsService.load();
  }

  function saveSettings() {
    SettingsService.save($scope.settings);
    // redirect
    $location.path("/");
    window.location.reload();
  }

  // public methods
  $scope.load = loadSettings;
  $scope.save = saveSettings;

  // auto init
  loadSettings();

});