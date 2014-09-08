app.service('UserService', function($rootScope, $log, $http, $q, SettingsService, Base64) {
    "use strict";

    var settings = SettingsService.load();
    var basicAuth = Base64.encode(settings.username + ":" + settings.password),
        deferred = $q.defer(),
        server = settings.server,
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
                url: 'http://' + server + '/api/user/all',
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

app.service('SettingsService', function($rootScope, localStorageService) {
    "use strict";
    return {
        save: function(config) {
            config.valid = true;
            localStorageService.remove('ngJEE_app.settings');
            localStorageService.add('ngJEE_app.settings', config);
        },
        load: function() {
            console.log("Loading settings from local storage");
            var settings = localStorageService.get('ngJEE_app.settings');
            if (settings) {
                settings.valid = true;
            } else {
                settings = {
                    valid: false,
                    // default properties, use 
                    //server: 'localhost:8080/cordova-app',
                    server: 'cloud01.martinreinhardt-online.de:8080/cordova-app',
                    timeout: 2000
                }
            }
            return settings;
        }
    };
});