import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';
import Application from 'jft-sale/controllers/application';

export default Ember.Route.extend(ResetScrollPositionMixin, {

    model() {
        //Initially setting the invalidUser flag t flase 
        this.controllerFor('login').set("invalidUser", false);
        let post = {
            email: "",
            password: ""
        };
        return post;
    }
});
