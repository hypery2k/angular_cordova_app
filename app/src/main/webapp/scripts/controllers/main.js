app.controller('AppController', function($rootScope, $scope) {
  $rootScope.loading = false;

  $rootScope.$on("$routeChangeStart", function() {
    $rootScope.loading = true;
  });

  $rootScope.$on("$routeChangeSuccess", function() {
    $rootScope.loading = false;
  });

});

app.controller('ChatController', function($rootScope, $scope, $routeParams, SettingsService) {
  var recipient = $routeParams.username,
    settings = SettingsService.load(),
    sender = settings.username,
    connection;
  var url = 'ws://' + settings.server + '/message/' + sender;

  function initChat() {
    var self = this;
    $scope.messages = [];
    $scope.sender = sender;
    connection = new WebSocket(url);

    connection.onopen = function() {
      console.log('Connected to chat service');
    }
    connection.onclose = function() {
      console.log('Connection to chat service closed.');
    }
    connection.onerror = function(error) {
      console.log('Error in chat service' + error);
    }
    connection.onmessage = function(event) {
      var msg = angular.fromJson(event.data);
      $scope.$apply(function() {
        $scope.messages.push(msg);
      });
    }
  }

  function sendMessage() {
    var msg = {
      to: recipient,
      from: sender,
      text: $scope.message
    };
    connection.send(JSON.stringify(msg));
    $scope.message = '';
  }

  // public methods
  $scope.load = initChat;
  $scope.send = sendMessage;

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