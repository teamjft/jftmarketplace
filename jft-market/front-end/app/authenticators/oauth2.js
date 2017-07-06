/*
import Base from 'ember-simple-auth/authenticators/base';

export default Base.extend({
  restore(data) {
  },

  authenticate(/!*args*!/) {
  },

  invalidate(data) {
  }
});
*/

import OAuth2PasswordGrant from 'ember-simple-auth/authenticators/oauth2-password-grant';

export default OAuth2PasswordGrant.extend({
  serverTokenEndpoint: `http://localhost:9191/market/j_spring_security_check`,

    authenticate: function(options) {
        var self = this;
        return new Ember.RSVP.Promise((resolve, reject) => {
            Ember.$.ajax({
                url: 'http://localhost:9191/market/j_spring_security_check',
                type: 'POST',
                data : {
                    "j_username": options.email,
                    "j_password": options.password
                },
            }).then(function(response, status, xhr){
                console.log('ddddd',xhr);
                console.log('LOGIN OK: ',xhr.getAllResponseHeaders());
                console.log('LOGIN OK....1: ' , document.cookie);
                Ember.run(function(){
                    resolve({
                        token1: response.authenticate(function () {
                            console.log("we are here to sole any thing");
                        }),
                        token: response.auth_token,
                    });
                });
            }, function(xhr, status, error) {
                console.log('LOGIN ERROR: ' + xhr.responseText);
                var response = xhr.responseText;
                Ember.run(function(){
                    reject(response);
                });
            });
        });
    },

    readCookie :function(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
});
