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
            //swal("Here's a message!")
            // swal({
            //     title: "Are you sure?",
            //     text: "Your will not be able to recover this imaginary file!",
            //     type: "warning",
            //     showCancelButton: true,
            //     confirmButtonClass: "btn-danger",
            //     confirmButtonText: "Yes, delete it!"
            //     // closeOnConfirm: false
            // },
            //     function () {
            //         swal("Deleted!", "Your imaginary file has been deleted.", "success");
            //     });

        }
    }
});
