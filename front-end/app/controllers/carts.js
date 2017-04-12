import Ember from 'ember';

export default Ember.Controller.extend({

    store1: Ember.inject.service(),

    actions: {
        buyNowAction(data) {
            console.info(data);
            Ember.set(this.get('store1'), 'orderProd', data);
            this.transitionToRoute('shippingaddress');
        },
    }
});
