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
            this.get('store').push('productlist', model);
            // this.get('store').findRecord('productlist', model.id, {reload:true}).then(function(product) {
            //    console.log("=========================", product.get('name'))
            //       product.set('name', model.get("name"));
            //       product.set('description', model.get("description"));
            //       product.set('price', model.get("price"));
            //       product.save();
            //     //console.log("product", product);
            // });
            //this.transitionToRoute('productlist');
        }
    }
});
