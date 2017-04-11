import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
    model() {
        let register = {
            fname: "",
            lname: "",
            email: "",
            gender: "",
            password: "",
            confirmPassword: "",
            isRegister: true
        };
        return register;
    },
    actions: {
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
                this.transitionTo('registration');
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

                // // Em.$.ajax({
                // //     headers: { 
                // //     'Accept': 'application/json',
                // //     'Content-Type': 'application/json' 
                // // },
                // //     type: 'POST',
                // //     url: `http://localhost:9191/market/api/v1/product/create`,
                // //     dataType: 'json',
                // //     data: jsondata
                // // }).then(function(data) {
                // //     //Ember.run(null, resolve, data);
                // //     console.log(data);  
                // // });
                let self = this;
                this.get('store').createRecord('registration', jsondata).save().then(function (res) {
                    Ember.set(model, 'isRegister', false);
                    self.transitionTo('registration');
                }).catch(function (err) {
                    console.log('Eorr in response Registration', err);
                    self.transitionTo('registration');
                    msg.push("There is some error in Registration, Please Try again");
                });
            }
        }
    }
});
