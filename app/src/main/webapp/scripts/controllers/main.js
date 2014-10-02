app.controller('ChatController', function($rootScope, $scope, $routeParams, SettingsService, ChatService) {
  var recipient = $routeParams.username,
    settings = SettingsService.load(),
    sender = settings.username,
    connection;

  function initChat() {
    $rootScope.loading = true;
    var self = this;
    $scope.messages = [];
    $scope.sender = sender;
    $scope.recipient = recipient;
    ChatService.receive(function() {
      $rootScope.loading = true;
      var msg = angular.fromJson(event.data);
      $scope.messages.push(msg);
      $rootScope.loading = false;
    });
    ChatService.connected(function() {
      $rootScope.loading = false;
    });
  }

  function sendMessage() {
    $rootScope.loading = true;
    var msg = {
      to: recipient,
      from: sender,
      text: $scope.message
    };
    ChatService.send(JSON.stringify(msg));
    $scope.message = '';
    $rootScope.loading = false;
  }

  // public methods
  $scope.load = initChat;
  $scope.send = sendMessage;

  // auto init
  initChat();

});

app.controller('UsersController', function($rootScope, $scope, UserService) {

  function loadUsers() {
    $rootScope.loading = true;
    UserService.listAllUsers().then(
      function(response) {
        $scope.users = response.data;
        $rootScope.loading = false;
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