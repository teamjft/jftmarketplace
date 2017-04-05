import Ember from 'ember';

export default Ember.Controller.extend({

    store1: Ember.inject.service(),

    actions: {
        deleteCategory(data) {
            console.log("add...",data);
            Ember.get(this.get('store1'),'categories').popObject(data);
            this.transitionToRoute('addcategory');
        }
    }
});
