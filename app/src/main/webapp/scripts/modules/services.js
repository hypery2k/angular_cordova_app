app.service('ConfigService', function($rootScope, $http, APP_CONFIG, Base64, SettingsService, UserService, ChatService) {
    "use strict";
    var config;
    return {
        configureServices: function() {
            var settings = SettingsService.load();
            if (settings && settings.valid) {
                UserService.config(settings);
                ChatService.config(settings);
            }
        }
    }
});

app.service('ChatService', function($rootScope, $log) {
    "use strict";

    var wsBackend, sender, socket, url;

    function createSocket() {
        url = wsBackend + '/' + sender;
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

        socket.onconnecting = function() {
            var args = arguments;
            if (service.handlers.connecting) {
                $rootScope.$apply(function() {
                    service.handlers.connecting.apply(socket, args)
                })
            }
        }

        socket.onerror = function() {
            var args = arguments;
            if (service.handlers.error) {
                $rootScope.$apply(function() {
                    service.handlers.error.apply(socket, args)
                })
            }
        }
    }

    var service = {
        handlers: {},
        config: function(settings) {
            wsBackend = settings.wsBackend;
            sender = settings.username;
        },
        open: function() {
            createSocket();
        },
        receive: function(callback) {
            this.handlers.receive = callback;
        },
        send: function(data) {
            var msg = typeof(data) == "object" ? JSON.stringify(data) : data;
            var status = socket.send(msg);
        },
        connected: function(callback) {
            this.handlers.connected = callback;
        },
        connecting: function(callback) {
            this.handlers.connecting = callback;
        },
        error: function(callback) {
            this.handlers.error = callback;
        }
    };
    return service;

});


app.service('UserService', function($rootScope, $log, $http, $q, Base64) {
    "use strict";

    var server, basicAuth, timeout, httpHeader;
    return {
        config: function(settings) {
            basicAuth = Base64.encode(settings.username + ":" + settings.password),
            server = settings.rsBackend,
            timeout = settings.timeout,
            httpHeader = {
                'Authorization': 'Basic ' + basicAuth,
                'Access-Control-Allow-Origin': 'localhost',
                'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
            };
        },
        listAllUsers: function() {
            var deferred = $q.defer();
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


app.service('SettingsService', function($rootScope, $http, $q, Base64, localStorageService, APP_CONFIG) {
    "use strict";
    var configURL = APP_CONFIG.config;
    return {
        save: function(config) {
            var deferred = $q.defer();
            localStorageService.remove('ngJEE_app.settings');
            if (config.username && config.password) {
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
                    config.valid = true;
                    config.timeout = 2000;
                    localStorageService.add('ngJEE_app.settings', config);
                    deferred.resolve(config);
                }, function(error) {
                    config = {
                        valid: false,
                    }
                    deferred.reject(config);
                });
            } else {
                deferred.reject(config);
            }
            return deferred.promise;
        },
        load: function() {
            console.log("Loading settings from local storage");
            var settings = localStorageService.get('ngJEE_app.settings');
            if (settings) {
                if (settings.valid) {
                    console.log("Found valid settings");
                }
            } else {
                settings = {};
                settings.valid = false;
            }
            return settings;
        }
    };
});