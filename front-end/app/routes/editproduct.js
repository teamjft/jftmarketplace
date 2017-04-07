import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {

  store1: Ember.inject.service(),

  model(params) {
    const products = this.get('store1');
    let product = this.get('store').findRecord('product', 3);//products.getProducts()[params.prod_id - 1];
    //product.set('categories', products.getCategories());
    return product;
  }

});