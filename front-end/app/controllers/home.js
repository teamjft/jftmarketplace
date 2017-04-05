import Ember from 'ember';

export default Ember.Controller.extend({
    store1: Ember.inject.service(),
    
    application: Ember.inject.controller(),

    actions: {
        getProdByCat(data) {
            const serviceObj = this.get('store1');
            let products = serviceObj.getProducts();
            let catVal = products.filter(function (element) {
                return data === element.category;
            });
            Ember.set(this.get('model'), 'catVal', catVal);
        },

        buyNowAction(data) {
            Ember.set(this.get('store1'), 'orderProd', data);
            this.transitionToRoute('shippingaddress');
        },

        addToCart(param) {
            console.log(param);
            let cartItems = Ember.get(this.get('store1'), 'cartItems');
            cartItems.pushObject(param);
            Ember.set(this.get('store1'), 'cartItems', cartItems);
            let newCartVal = Ember.get(this.get('application'), 'cart');
            Ember.set(this.get('application'), 'cart', ++newCartVal)
        },
        pickImage(data) {
            console.info(data);
        }
    }
});
