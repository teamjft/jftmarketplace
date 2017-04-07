import Ember from 'ember';

export default Ember.Route.extend({
    model(){
        console.log("We are here");
        localStorage.setItem("isAdmin", true);
        localStorage.setItem("isUser", false);
        console.log( localStorage.getItem("isAdmin"));
        console.log( localStorage.getItem("isUser"));
    }
});
