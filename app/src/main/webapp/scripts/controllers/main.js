app.controller('AppController', function($rootScope, $scope, CordovaService) {

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

app.controller('ChatController', function($rootScope, $scope, $routeParams, SettingsService) {
  var recipient = $routeParams.username,
    settings = SettingsService.load(),
    sender = settings.username,
    connection;

  // TODO use ngSocket https://github.com/angular/ngSocket
  var url = 'ws://' + settings.server + '/message/' + sender;

  function initChat() {
    $rootScope.loading = true;
    var self = this;
    $scope.messages = [];
    $scope.sender = sender;
    $scope.recipient = recipient;
    connection = new WebSocket(url);

    connection.onopen = function() {
      console.log('Connected to chat service');
      $scope.$apply(function() {
        $rootScope.loading = false;
      });
    }
    connection.onclose = function() {
      console.log('Connection to chat service closed.');
    }
    connection.onerror = function(error) {
      console.log('Error in chat service' + error);
      if (navigator.notification) {
        navigator.notification.alert('Could not connect to chat service backend. Please choose chat partner again.', null, 'Error in chat service', 'OK');
      }
    }
    connection.onmessage = function(event) {
      $rootScope.loading = true;
      var msg = angular.fromJson(event.data);
      $scope.$apply(function() {
        $scope.messages.push(msg);
        $rootScope.loading = false;
      });
    }
  }

  function sendMessage() {
    $rootScope.loading = true;
    var msg = {
      to: recipient,
      from: sender,
      text: $scope.message
    };
    connection.send(JSON.stringify(msg));
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