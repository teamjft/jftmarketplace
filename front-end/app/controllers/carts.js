import Ember from 'ember';
import sweetAlert from 'ember-sweetalert';

export default Ember.Controller.extend({

    store1: Ember.inject.service(),

    actions: {
        buyNowAction(data) {
            console.info(data.length);
            if(data.length){
                Ember.set(this.get('store1'), 'orderProd', data);
                this.transitionToRoute('shippingaddress');
            }else{
                sweetAlert("There is no item in your cart", "We can not go with purchase flow", "error");
            }
            
        },

        incermentQuantity(data){
            console.log("we are in incermentQuantity action",data);
            let qnt = data.itemQantity;
            let price = data.price;
            Ember.set(data, 'itemQantity', qnt+1);
            Ember.set(data, 'price', price*(qnt+1));
            return data;
        }
    }
});
