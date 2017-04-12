import Ember from 'ember';
import sweetAlert from 'ember-sweetalert';

export default Ember.Controller.extend({

    store1: Ember.inject.service(),
    application: Ember.inject.controller(),

    actions: {
        buyNowFromCart(data) {
            console.info(data.length);
            if (data.length) {
                //Ember.set(this.get('store1'), 'orderProd', data);
                localStorage.setItem('orderProd', JSON.stringify(data));
                this.transitionToRoute('shippingaddress');
            } else {
                sweetAlert("There is no item in your cart", "We can not go with purchase flow", "error");
            }

        },

        incermentQuantity(data) {
            console.log("we are in incermentQuantity action", data);
            let qnt = data.itemQantity;
            let totalPrice = data.totalPrice;
            Ember.set(data, 'itemQantity', qnt + 1);
            Ember.set(data, 'totalPrice', data.attributes.price * (qnt + 1));
            return data;
        },

        removeItemFromCart(item) {
            console.log(" In remove Item from cart action..", item);
            let cartItems = JSON.parse(localStorage.getItem('cartItems'));
            cartItems.popObject(item);
            localStorage.setItem('cartItems', JSON.stringify(cartItems));
            Ember.set(this.get('model'), 'cartItems', JSON.parse(localStorage.getItem('cartItems')));
            let newCartVal = Number(localStorage.getItem('cart')) - 1;
            localStorage.setItem('cart', newCartVal);
            Ember.set(this.get('application'), 'cart', newCartVal)
        },
        decrementQuantity(data){
            console.log("we are in decrementQuantity action", data);
            let qnt = data.itemQantity;
            let totalPrice = data.totalPrice;
            Ember.set(data, 'itemQantity', qnt - 1);
            Ember.set(data, 'totalPrice', data.attributes.price * (qnt - 1));
            return data;
        }
    }
});
