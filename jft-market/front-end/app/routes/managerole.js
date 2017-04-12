import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        let users = this.get('store').findAll('registration');
        return users;
    }
});
