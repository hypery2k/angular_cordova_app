var cordova;

describe('main', function() {

  // load the controller's module
  beforeEach(module('angularCordovaApp'));

  describe("AppController", function() {

    var controller,
      scope;


    // Initialize the controller 
    beforeEach(inject(function($rootScope, $controller) {
      scope = $rootScope.$new();
      controller = $controller("AppController", {
        $scope: scope
      });
    }));

    it('Init module failed', function() {
      expect(true).toBe(true);
    });


  });
});