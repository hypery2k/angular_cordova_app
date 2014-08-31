angular_cordova_app - app
=========================

Simple demo app combining AngularJS with Cordova-based Hybrid Apps.

Based on [mobile-angular-ui](https://github.com/mcasimir/mobile-angular-ui)

## Dev

* Install nodejs and npm (http://nodejs.org/download/)
* Install ruby
  * `(sudo) gem install compass`
* Install Cordova
  * `(sudo) npm install -g cordova`
* Install build tools
  * `(sudo) npm install -g grunt-cli`
  * `(sudo) npm install -g cordova-gen-icon` (optional, see https://bitbucket.org/ntakimura/cordova-gen-icon)
  * `(sudo) npm install -g bower` (for bower dependencies.)
* `grunt server`

Note, that sudo may be required depending on your setup.

Run the app in your browser: http://localhost:9000

## iOS development

The app can be opened in Xcode:
- cordova prepare ios
- open project in Xcode

## Demo

[WebDemo](https://martinreinhardt-online.de/demo/angular_cordova_app/www)

![Image](https://martinreinhardt-online.de/demo/angular_cordova_app/iphone1.png)
![Image](https://martinreinhardt-online.de/demo/angular_cordova_app/iphone2.png)
