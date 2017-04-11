import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
  model(params) {
    let model = {
      product: this.get('store').peekRecord('productlist', params.prod_id)
    }
    return model;
  }

});