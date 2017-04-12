import Ember from 'ember';
import sweetAlert from 'ember-sweetalert';

export default Ember.Controller.extend({
    //needs: ['application'],
    //isAdmin: Ember.computed.alias(Ember.inject.controller('application'))
    application : Ember.inject.controller(),
    actions: {
        selectGender(val) {
            console.log("---------->>>>>", Ember.get(this.get('application'), 'cart'));
        },
        registerUser(model) {
            let msg = [], validationFlag = false;
            if (model.fname == '' || model.fname == null) {
                msg.push("First Name can not be Empty.");
                validationFlag = true;
            }
            if (model.lname == '' || model.lname == null) {
                msg.push("Last Name can not be Empty.");
                validationFlag = true;
            }
            if (model.email == '' || model.email == null) {
                msg.push("Email field can not be Empty.");
                validationFlag = true;
            }
            if (model.password == '' || model.password == null) {
                msg.push("Password field can not be Empty.");
                validationFlag = true;
            }
            if (model.password != model.confirmPassword) {
                Ember.set(model, 'password', '');
                Ember.set(model, 'confirmPassword', '');
                msg.push("Password did not matched. Please try again.");
            }
            if (model.phone == '' || model.phone == null) {
                msg.push("Phone No field can not be Empty.");
                validationFlag = true;
            }
            // this.get('store').findRecord('registration', 1).then(function (person) {
            //     console.log(person, "hhhh");
            // });
            if (validationFlag) {
                Ember.set(model, 'msg', msg);
                this.transitionToRoute('registration');
            } else {
                console.log(model);
                let jsondata = {
                    "fname": model.fname,
                    "lname": model.lname,
                    "email": model.email,
                    "password": model.password,
                    "phone": model.phone,
                    "gender": "m"
                };
                let self = this;
                this.get('store').createRecord('registration', jsondata).save().then(function (res) {
                    Ember.set(model, 'isRegister', false);
                    console.log("---------->>>>>", Ember.get(self.get('application'), 'isAdmin'));
                    if(Ember.get(self.get('application'), 'isAdmin')==true){
                        sweetAlert("User added successfully", "", "success");
                        self.transitionToRoute('managerole');
                    }else{
                        self.transitionToRoute('registration');
                    }
                    
                }).catch(function (err) {
                    console.log('Eorr in response Registration', err);
                    msg.push("There is some error in Registration, Please Try again");
                    self.transitionToRoute('registration');
                });
            }
        }

    }

});
