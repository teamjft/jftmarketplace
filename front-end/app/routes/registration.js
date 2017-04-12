import Ember from 'ember';
//import Register from 'jft-sale/models/registration';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
    model() {
        let register = {
            fname: "",
            lname: "",
            email: "",
            gender: "",
            password: "",
            confirmPassword: ""
        };
        return register;
    },
    actions: {
        registerUser(model) {
            //console.log(Register);
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
            this.get('store').findRecord('registration', 1).then(function (person) {
                console.log(person, "hhhh");
            });
            if (validationFlag) {


                Ember.set(model, 'msg', msg);
                this.transitionTo('registration');  
            }
            console.log(model);
            // let jsondata = {
            //     "name":"APPLE", 
            //     "price":10000,
            //     "description":"Iphone 7",
            //     "features":"64Gb"
            // };

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
            // this.get('store').createRecord('product', jsondata).save().then(function(res){
            //     console.log('fffff',res);
            // });

            //post.save();
            //TODO: Register USer Functionality.
            this.transitionTo('registration');
        }
    }
});
