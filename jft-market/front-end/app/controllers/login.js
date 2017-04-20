import Ember from 'ember';

export default Ember.Controller.extend({
    application: Ember.inject.controller(),

    invalidUser: false,

    session: Ember.inject.service('session'),

    actions: {
        userLogin(formData) {
            console.log(formData);
            this.get('session').authenticate('authenticator:oauth2', formData.email, formData.password)
                .then((res) => {
                    console.log('..///////......', res);
                })
                .catch((reason)=>{
                    console.log(reason, '.........error');
                });
            // if (formData.email == 'admin' && formData.password == 'admin') {
            //     Ember.set(this.get('application'), 'isLogin', true);
            //     localStorage.setItem("isAdmin", true);
            //     localStorage.setItem("isUser", false);
            //     Ember.set(this.get('application'), 'isAdmin', true);
            //     Ember.set(this.get('application'), 'isUser', false);
            //     this.transitionToRoute('admin');
            // } else if(formData.email == 'raj' && formData.password == 'raj') {
            //     Ember.set(this.get('application'), 'isLogin', true);
            //     Ember.set(this.get('application'), 'isAdmin', false);
            //     Ember.set(this.get('application'), 'isUser', true);
            //     this.transitionToRoute('home');
            // } else {
            //     this.set('invalidUser', true);
            //     this.transitionToRoute('login');
            // }
        }
    }
});
