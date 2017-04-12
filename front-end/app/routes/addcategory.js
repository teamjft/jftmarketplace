
import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {

  store1: Ember.inject.service(),

  model() {
    //const categories = this.get('store1');
    var modelObj = {};
    modelObj.categories = this.get('store1').getCategories();
    var post = {
      name: "",
      description: "",
    };
    modelObj.post = post;
    return modelObj;
  },

  actions: {
    addCategory(model) {
      let msg = [], validationFlag = false;
      if (model.name == '' || model.name == null) {
        msg.push("Name field can not be Empty.");
        validationFlag = true;
      }
      if (model.description == '' || model.description == null) {
        msg.push("Description field can not be Empty.");
        validationFlag = true;
      }
      if (validationFlag) {
        Ember.set(model, 'msg', msg);
        this.transitionTo('addcategory');
      }
      else {
        const categories = this.get('store1');
        categories.addCategory(model);
        Ember.set(model, 'name', '');
        Ember.set(model, 'description', '');
      }
      this.transitionTo('addcategory');
    }
  }
});