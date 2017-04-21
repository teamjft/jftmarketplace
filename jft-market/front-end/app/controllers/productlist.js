import Ember from 'ember';

export default Ember.Controller.extend({
    actions: {
        deleteProduct(prodId) {
            console.log("prodId..", prodId);
            let self = this;
            this.get('store').findRecord('productlist', prodId, { reload: true }).then(function (product) {
                product.destroyRecord(); // => DELETE to /posts/
            });
        }
    }
});
