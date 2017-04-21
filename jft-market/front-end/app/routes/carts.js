import Ember from 'ember';

export default Ember.Route.extend({
    store1: Ember.inject.service(),

    model() {
        let post = {
            cartItems: JSON.parse(localStorage.getItem('cartItems'))
        }
        return post;
    }
});
