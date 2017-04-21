import Ember from 'ember';

export default Ember.Controller.extend({
    
    store1: Ember.inject.service(),

    actions: {
        chooseAddressType(params) {
            console.info(params);
        },
        
        shipAddress(formData) {
            localStorage.setItem('shipAddr', JSON.stringify(formData));
            this.transitionToRoute('summarypage');
        }
    }
});
