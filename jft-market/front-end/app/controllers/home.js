import Ember from 'ember';
import sweetAlert from 'ember-sweetalert';

export default Ember.Controller.extend({
    
    store1: Ember.inject.service(),
    
    application: Ember.inject.controller(),

    actions: {
        getProdByCat(data) {
            let self = this;
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                type: 'GET',
                url: 'http://localhost:9191/market/api/v1/product/category/'+ data,
                dataType: 'json'
            }).done(function(res){
                
                console.log('res', res);
                if(res.data.length == 0){
                    sweetAlert("No Products in slected category", "", "error");
                }
                Ember.set(self.get('model'), 'catVal', res.data);
            }).fail(function(err){
                console.log('err', err)
            });
        },

        buyNowAction(data) {
            let orders = [];
            orders.pushObject(data);
            localStorage.setItem('orderProd', JSON.stringify(orders));
            this.transitionToRoute('shippingaddress');
        },

        addToCart(param) {
            console.log(param);
            param.itemQantity = 1;
            param.totalPrice = param.attributes.price;
            let cartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
            cartItems.pushObject(param);
            localStorage.setItem('cartItems', JSON.stringify(cartItems));
            // Setting number of items to purchase initially
            let newCartVal;
            console.log(localStorage.getItem('cart'));
            if(localStorage.getItem('cart')){
                newCartVal = 1+ Number(localStorage.getItem('cart'));
            } else{
                localStorage.setItem('cart', 0);
                newCartVal = 1 + Number(localStorage.getItem('cart'));
            }
            localStorage.setItem('cart', newCartVal);
            Ember.set(this.get('application'), 'cart', newCartVal);
        },
        pickImage(data) {
            console.info(data);
        }
    }
});
