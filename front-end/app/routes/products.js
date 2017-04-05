import Ember from 'ember';

import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
    store1: Ember.inject.service('store1'),
    
    model() {
        // let products = this.get('store').findAll('product').then(function(res){
        //       return res.content;
        //     }).catch(function(err){
        //        console.log("Error in  ", err);
        //        return err;
        //     });

        let products =
        this.get('store').findAll('product');
        // .then(function(res){
        //     console.log(res.content[0]._data.name);
        //     console.log(products);
        // });
        console.log(products, 'll');
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
        return products;
    }
});