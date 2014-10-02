app.factory('ChatSocket', function(socketFactory, SettingsService) {
    var settings = SettingsService.load();
    var url = 'ws://' + settings.server + '/message/' + sender;
    return socketFactory({
        url: url
    });
})


app.service('ChatService', function($rootScope, $log, SettingsService) {
    "use strict";

    var settings = SettingsService.load(),
        sender = settings.username,
        socket, url;

    function createSocket() {
        url = settings.wsBackend + '/' + sender;
        socket = new ReconnectingWebSocket(url);

        socket.onopen = function() {
            var args = arguments;
            if (service.handlers.connected) {
                $rootScope.$apply(function() {
                    service.handlers.connected.apply(socket, args)
                })
            }
        }

        socket.onmessage = function(data) {
            var args = arguments;
            try {
                args[0].data = JSON.parse(args[0].data);
            } catch (e) {
                // there should be a better way to do this
                // but it is fast
            }
            if (service.handlers.receive) {
                $rootScope.$apply(function() {
                    service.handlers.receive.apply(socket, args);
                })
            }
        }
    }

    var service = {
        handlers: {},
        receive: function(callback) {
            this.handlers.receive = callback;
        },
        send: function(data) {
            var msg = typeof(data) == "object" ? JSON.stringify(data) : data;
            var status = socket.send(msg);
        },
        connected: function(callback) {
            this.handlers.connected = callback;
        }
    };
    createSocket();
    return service;

});


app.service('UserService', function($rootScope, $log, $http, $q, SettingsService, Base64) {
    "use strict";

    var settings = SettingsService.load();
    var basicAuth = Base64.encode(settings.username + ":" + settings.password),
        deferred = $q.defer(),
        server = settings.rsBackend,
        timeout = settings.timeout,
        httpHeader = {
            'Authorization': 'Basic ' + basicAuth,
            'Access-Control-Allow-Origin': 'localhost',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
        };
    return {
        listAllUsers: function() {
            $http({
                method: 'GET',
                url: server + '/user/all',
                timeout: timeout,
                xhrFields: {
                    withCredentials: true
                },
                headers: httpHeader
            }).then(onSuccess, onError);

            function onSuccess(response) {
                deferred.resolve(response);
            }

            function onError(response) {
                deferred.reject(response);
            }
            return deferred.promise;
        }
    }
});

app.service('SettingsService', function($rootScope, $http, APP_CONFIG, Base64, localStorageService) {
    "use strict";
    var configURL=APP_CONFIG.config;
    return {
        save: function(config) {
            localStorageService.remove('ngJEE_app.settings');
            if(config.username && config.password){
                var basicAuth = Base64.encode(config.username + ":" + config.password),
                timeout = settings.timeout,
                httpHeader = {
                    'Authorization': 'Basic ' + basicAuth,
                    'Access-Control-Allow-Origin': 'localhost',
                    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
                };
                $http({
                    method: 'GET',
                    url: configURL,
                    timeout: 2000,
                    xhrFields: {
                        withCredentials: true
                    },
                    headers: httpHeader
                }).then(function(response) {            	
                	angular.forEach(response.data, function(value, key) {
                		config[key] = value;
                	});
                	config.valid=true;
                	config.timeout=2000;
                    localStorageService.add('ngJEE_app.settings', config);
                }, function (error) {
                	config = {
                            valid: false,
                	}
                });}
        },
        load: function() {
            console.log("Loading settings from local storage");
            var settings = localStorageService.get('ngJEE_app.settings');
            if(settings){
                 if(settings.valid){
                    console.log("Found valid settings");
                } 
            } 
            return settings;
        }
    };
});