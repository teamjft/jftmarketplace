
import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {

  store1: Ember.inject.service(),

  model() {
    var modelObj = {};
    modelObj.categories = this.get('store').findAll('categorylist');
    var post = {
      name: "",
      description: "",
    };
    modelObj.post = post;
    return modelObj;
  },
  actions: {

  }
});