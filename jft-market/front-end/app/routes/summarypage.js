import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
    store1: Ember.inject.service(),

    model(params, transition) {
        console.log(params, transition);
        let model = {
             orderProd: [],           
             shipAddr: {}
        };
        model.orderProd = JSON.parse(localStorage.getItem('orderProd'));
        model.shipAddr = JSON.parse(localStorage.getItem('shipAddr'));
        return model;
    }
});
