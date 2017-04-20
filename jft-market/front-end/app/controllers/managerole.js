import Ember from 'ember';
import sweetAlert from 'ember-sweetalert';

export default Ember.Controller.extend({
    actions: {
        deleteUser(userId) {
            console.log(userId, '.......deleting User');
            let self = this;
            this.get('store').findRecord('registration', userId, { reload: true }).then(function (user) {
                user.destroyRecord();
                 sweetAlert("User Deleted Successfully", "", "success");
            });
        }
    }
});
