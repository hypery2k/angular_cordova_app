var cordova;

describe('main', function() {

  // load the controller's module
  beforeEach(module('angularCordovaApp'));

  describe("AppController", function() {

    var appController, scope;

    beforeEach(function() {
        module(function($provide) {
      	    $provide.constant('APP_CONFIG', { config:'http://localhost:8080/cordova-server-backend/api/app/config' });
        });
	    inject(function($rootScope, $controller) {
	      scope = $rootScope.$new();
	      appController = $controller("AppController", {
	        $scope: scope
	      });
	    })
    });


    it('inject', function() {
      expect(true).toBe(true);
    });

  });
});