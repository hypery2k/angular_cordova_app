app.controller('ChatController', function($rootScope, $scope, $routeParams, SettingsService, ChatService) {
  "use strict";
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
    ChatService.open();
    ChatService.receive(function(event) {
      $rootScope.loading = true;
      var msg = angular.fromJson(event.data);
      $scope.messages.push(msg);
      $rootScope.loading = false;
    });
    ChatService.connected(function() {
      $rootScope.loading = false;
    });
    ChatService.error(function() {
      $rootScope.loading = false;
      console.error('Server error during reading connectiong to WebSocket');
      navigator.notification.alert('Server did not respond.', null, 'Messaging Error');
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

app.controller('UsersController', function($rootScope, $scope, SettingsService, UserService) {
  "use strict";
  $scope.settings = SettingsService.load();

  function loadUsers() {
    $rootScope.loading = true;
    UserService.listAllUsers().then(
      function(response) {
        var allUsers = response.data,
          users = [];
        var count = 0;
        for (var itemIndex in allUsers) {
          var user = allUsers[itemIndex];
          // don't show current user in list
          if (user.username != $scope.settings.username) {
            $scope.users;
            users.push(user);
          }
        }
        $scope.users = users;
        $rootScope.loading = false;
      }, function(errorResponse) {
        console.error('Server error during reading users');
        navigator.notification.alert('Server did not show valid response.', null, 'Server Error');
        $rootScope.loading = false;
      }
    );
  }

  // public methods
  $scope.allUsers = loadUsers;

  // auto init
  loadUsers();

});

app.controller('NavigationController', function($rootScope, $scope, SettingsService) {
  "use strict";

  function init() {
    $scope.settings = SettingsService.load();
    $scope.$on('handleConfigUpdate', function(event, settings) {
      $scope.settings = settings;
      console.log("Reloading nav settings");
    });
  }
  // public methods
  $scope.init = init;

  // auto init
  init();

});

app.controller('SettingsController', function($rootScope, $scope, $location, $route, SettingsService, ConfigService) {
  "use strict";

  function loadSettings() {
    $scope.settings = SettingsService.load();
  }

  function saveSettings() {
    $rootScope.loading = true;
    SettingsService.save($scope.settings).then(function() {
      $rootScope.$broadcast('handleConfigUpdate', $scope.settings);
      // redirect
      $location.path("/").replace();
      $route.reload();
      // update config
      ConfigService.configureServices();
      $rootScope.loading = false;
    }, function(reason) {
      console.error('Server error during saving settings');
      $rootScope.loading = false;
      navigator.notification.alert('Server did not show valid response.', null, 'Server Error');
    });
  }

  // public methods
  $scope.load = loadSettings;
  $scope.save = saveSettings;

  // auto init
  loadSettings();

});