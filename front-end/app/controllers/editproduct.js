import Ember from 'ember';

export default Ember.Controller.extend({
    
    store1: Ember.inject.service(),

    actions: {
        chooseCategory(data) {
            Ember.set(this.get('model'), 'category', data);
        },
        editProduct(formData) {
            console.log('c',formData);
            this.get('store').findRecord('productlist', formData.id).then(function(product) {
                 product.get('name');
                 product.set('name', 'A new post');

                 product.save();
                console.log("product", product);
            });
            this.transitionToRoute('productlist');
        }
    }
});
