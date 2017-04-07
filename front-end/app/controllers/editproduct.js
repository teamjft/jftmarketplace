import Ember from 'ember';

export default Ember.Controller.extend({
    
    store1: Ember.inject.service(),

    actions: {
        chooseCategory(data) {
            Ember.set(this.get('model'), 'category', data);
        },
        editProduct(model) {
            //const products = this.get('store1');
            //products.editProducts(model);
            this.transitionToRoute('products');
        }
    }
});
