import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        localStorage.setItem("isAdmin", true);
        localStorage.setItem("isUser", false);
    }
});
