import Ember from 'ember';

export default Ember.Controller.extend({
    needs: ['application'],
    //isAdmin: Ember.computed.alias(Ember.inject.controller('application'))
});
