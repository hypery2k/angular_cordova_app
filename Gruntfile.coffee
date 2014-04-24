module.exports = (grunt) ->

  grunt.initConfig
    pkg: grunt.file.readJSON("package.json")


    connect:
      server:
        options:
          hostname: '0.0.0.0'
          port: 3001
          keepalive: true

  grunt.loadNpmTasks "grunt-contrib-connect"