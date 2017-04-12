
import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
  store1: Ember.inject.service(),

  model: function (params) {
    let data = {};
    data.categories = this.get('store').peekAll('categorylist');
    data.category = this.get('store').findRecord('category', params.cat_id);
    return data;
  },

  actions: {
    editCategory(model) {
      const categories = this.get('store1');
      categories.editCategory(model);
      this.transitionTo('addcategory');
    }
  }

});