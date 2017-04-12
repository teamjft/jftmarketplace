import Ember from 'ember';

import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {

    store1: Ember.inject.service('store1'),

    model() {
        let products = this.get('store').findAll('productlist');
        return products;
        // $.ajax({
        //         headers: { 
        //          'Accept': 'application/json',
        //          'Content-Type': 'application/json' 
        //      },
        //      type: 'GET',
        //          url: `http://localhost:9191/market/api/v1/customer/customers`,
        //         dataType: 'json',
        //         //data: JSON.stringify(jsondata),
        //         // success: function(res){
        //         //     console.log("iiiiiiiiiii",res);
        //         // },
        //         // error: function(err){
        //         //     console.log("ooooooooooo",err);
        //         // }
        //     }).done(function(res){
        //         console.log("iiiiiiiiiii",res[0].customerName);
        //         return res;
        //     }).fail(function(err){
        //          console.log("ooooooooooo",err);
        //     });
    }
});