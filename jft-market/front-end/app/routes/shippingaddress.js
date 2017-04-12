import Ember from 'ember';

import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
    model() {
        let post = {
            address: {
                name: '',
                houseNo: '',
                stree: '',
                state: '',
                city: '',
                pincode: '',
                phone: '',
                type: ''
            },
            addressTypes: ['Home', 'Office', 'Any Other']
        };
        return post;
    }
});
