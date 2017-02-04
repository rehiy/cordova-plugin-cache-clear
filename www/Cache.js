//////////////////////////////////////////
// Cache.js
// Copyright (C) 2016 Anrip <mail@anrip.com>
//
//////////////////////////////////////////

var exec = require('cordova/exec');

var Cache = {
    clear: function( success, error ) {
        exec(success, error, 'Cache', 'clear', []);
    }
};

module.exports = Cache;
