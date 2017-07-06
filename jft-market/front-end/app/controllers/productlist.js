import Ember from 'ember';
import sweetAlert from 'ember-sweetalert';

export default Ember.Controller.extend({
    actions: {
        deleteProduct(prodId) {
            console.log("prodId..", prodId);
            let self = this;
            this.get('store').findRecord('productlist', prodId, { reload: true }).then(function (product) {
                product.destroyRecord(); // => DELETE to /posts/
            });
            sweetAlert("Product Deleted Successfully", "", "success");
        }
    }
});
