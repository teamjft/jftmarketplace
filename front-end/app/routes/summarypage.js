import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
    store1: Ember.inject.service(),

    model(params, transition) {
        console.log(params, transition);
        let model = {
            shipAddr: Ember.get(this.get('store1'), 'shipAddr'),
            orderProd: Ember.get(this.get('store1'), 'orderProd')

        };
        return model;
    }
});
