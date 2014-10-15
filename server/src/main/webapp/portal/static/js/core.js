// our javascript namespace
var ngcordova = document.ngcordova || {};
ngcordova.logdebug = false; // always as false committed, otherwise errors in


/**
 * Escapes strings for using in JS, like ':'
 * 
 * @param str
 *            string to be escaped
 */
ngcordova.escapeStr = function(str) {
	"use strict";
	if (str)
		return str.replace(/([ #;&,.+*~\':"!^$[\]()=>|\/@])/g, '\\$1');
	else
		return str;
};

/**
 * Helper function to make the login with enter possible
 */
ngcordova.loginWithEnter = function(event) {
	"use strict";
	if (event.keyCode == 13) {
		submitButton.jq.click();
		return false;
	}
};