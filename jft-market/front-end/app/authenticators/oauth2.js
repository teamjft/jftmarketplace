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
        return new Ember.RSVP.Promise((resolve, reject) => {
            Ember.$.ajax({
                url: 'http://localhost:9191/market/j_spring_security_check',
                type: 'POST',
                data : {
                    "j_username": options.email,
                    "j_password": options.password
                },
            }).then(function(response){
                console.log('LOGIN OK: ' + response);
                Ember.run(function(){
                    resolve({
                        token: response.auth_token
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
});
