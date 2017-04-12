import Ember from 'ember';
import sweetAlert from 'ember-sweetalert';

export default Ember.Controller.extend({
    actions: {
        deleteCategory(catId) {
            console.log("Deleting Category...", catId);
            let self = this;
            this.get('store').findRecord('category', catId, { reload: true }).then(function (category) {
                category.destroyRecord();
                
                Ember.set(self.get('model'), 'post.description', '');
                Ember.set(self.get('model'), 'post.name', '');
                //Ember.set(self.get('model'), 'categories', self.get('store').findAll('category'), { reload: true });
                sweetAlert("Category Deleted Successfully", "", "success");
                self.transitionToRoute('addcategory');
            });
            //Ember.get(this.get('store1'), 'categories').popObject(data);
            //this.transitionToRoute('addcategory');
        },
        addCategory(model) {
            console.log('we are here1');
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
                this.transitionToRoute('addcategory');
            }
            else {
                let self = this;
                this.get('store').createRecord('category', model).save().then(function (res) {
                    Ember.set(self.get('model'), 'post.description', '');
                    Ember.set(self.get('model'), 'post.name', '');
                    //Ember.set(self.get('model'), 'categories', self.get('store').findAll('category'));
                    //self.transitionToRoute('addcategory');
                }).catch(function (err) {
                    console.log("error in addCategory action", err);
                    self.transitionToRoute('addcategory');
                });
            }
        }
    }
});
