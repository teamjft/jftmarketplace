import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {

  store1: Ember.inject.service(),
  model: function () {
    const products = this.get('store1');
    var post = {
      name: "",
      price: "",
      description: "",
      category: [],
      categories: this.get('store').findAll('category')
    };
    return post;
  }

});