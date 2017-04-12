import Ember from 'ember';

export default Ember.Controller.extend({
    store1: Ember.inject.service(),

    actions: {
        removeOrder(order){ 
            let orders = JSON.parse(localStorage.getItem('orderProd'));
            orders.popObject(order);
            localStorage.setItem('orderProd', JSON.stringify(orders));
            Ember.set(this.get('model'), 'orderProd', JSON.parse(localStorage.getItem('orderProd')));
        }
    }
});
