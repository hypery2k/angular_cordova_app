var cordova;

describe('Service', function() {

  // load the controller's module
  beforeEach(module('angularCordovaApp'));
  
  describe('UserService', function() {

    var mockedSettingsService, httpBackend, userService, settings, rootScope;

    // Initialize the controller and a settings mock
    beforeEach(function() {
      module(function($provide) {
  	    $provide.constant('APP_CONFIG', { config:'http://localhost:8080/cordova-server-backend/api/app/config' });
        settings = {
          username: 'user',
          password: 'user',
          valid: true,
          rsBackend: 'http://localhost/cordova-app/api',
          timeout: 2000
        };
        mockedSettingsService = {
          load: function() {
            return settings;
          }
        }
        $provide.value('SettingsService', mockedSettingsService);
      })
      inject(function($injector) {
        userService = $injector.get('UserService');
        userService.config(settings);
        httpBackend = $injector.get('$httpBackend');
        rootScope = $injector.get('$rootScope');
        var userList = [{
          username: 'user',
          firstname: 'the',
          lastname: 'lastname'
        }, {
          username: 'user2',
          firstname: 'the second',
          lastname: 'lastname2'
        }];
        var response = {};
        // create a mocked response
        httpBackend.when('GET', 'http://localhost/cordova-app/api/user/all').respond(userList);
      });
    });

    afterEach(function() {
      httpBackend.verifyNoOutstandingExpectation();
      httpBackend.verifyNoOutstandingRequest();
    });
    
    it('list users', function() {
      httpBackend.expectGET('http://localhost/cordova-app/api/user/all');
      var promise = userService.listAllUsers(),
        userList;
      promise.then(function(response) {
        userList = response.data;
        expect(userList.length).toEqual(2);
      }, function(response) {
        fail('There should be no errors');
      });
      rootScope.$apply();
      httpBackend.flush();
    });
  });
});