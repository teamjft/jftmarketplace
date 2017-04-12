import Ember from 'ember';

export default Ember.Controller.extend({
    
    store1: Ember.inject.service(),

    actions: {
        chooseCategory(data) {
            Ember.set(this.get('model'), 'category', data);
        },
        editProduct(model) {
            console.log('c',model);
            var post = this.get(model.id);
            this.get('store').findRecord('productlist', model.id).then(function(productResponse) {
                productResponse.set('name', model.get("name"));
                productResponse.set('description', model.get("description"));
                productResponse.set('price', model.get("price"));
                productResponse.save();
            });
            this.transitionToRoute('productlist');
        }
    }
});
