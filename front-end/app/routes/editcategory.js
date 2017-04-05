
import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
  store1: Ember.inject.service(),

  model: function (params) {
    const categories = this.get('store1');
    let category = categories.getCategories()[params.cat_id - 1];
    let data = {};
    data.categories = Object.create(categories.getCategories());
    data.category = category;
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