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
        
    }
});
