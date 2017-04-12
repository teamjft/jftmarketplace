import Ember from 'ember';

export default Ember.Controller.extend({

    store1: Ember.inject.service(),

    actions: {
        deleteCategory(data) {
            console.log("edit....",data);
            Ember.get(this.get('store1'),'categories').pop(data);
            this.transitionToRoute('addcategory');
        }
    }
});
