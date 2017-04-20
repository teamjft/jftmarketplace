import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
  model(params) {
    let model =  this.get('store').findRecord('productlist', params.prod_id);
    return model;
  }

});
