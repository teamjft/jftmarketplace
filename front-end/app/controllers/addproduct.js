import Ember from 'ember';
import Product from 'jft-sale/models/product';
export default Ember.Controller.extend({
    store1: Ember.inject.service(),
    prod: {},
    prod1: [],
    actions: {
        chooseCategory(data) {
            console.log(data);
            let pr = this.get('store').findAll('product');
            let self = this;
            pr.then(function(res){
                console.log('hhhh', res.objectAt(0) );
              self.set('prod', res.objectAt(0));
              self.set('prod1', res);
            }).catch(function(err){
               console.log("Error in chooseCategory Action", err);
            });
        },
        addProduct(model) {
            let msg = [], validationFlag = false;
            if (model.name == '' || model.name == null) {
                msg.push("Name field can not be Empty.");
                validationFlag = true;
            }
            if (model.price == '' || model.price == null) {
                msg.push("Price field can not be Empty.");
                validationFlag = true;
            }
            if (model.description == '' || model.description == null) {
                msg.push("Description field can not be Empty.");
                validationFlag = true;
            }
            if (validationFlag) {
                Ember.set(model, 'msg', msg);
                this.transitionToRoute('addProduct');
            }else{
                const products = this.get('store1');
                //products.addProducts(model);
                let jsondata = {
                "name":model.name+"", 
                "price":model.price,
                "description":model.description+"",
                "features":"64Gb"
            };
            // $.ajax({
            //     headers: { 
            //      'Accept': 'application/json',
            //      'Content-Type': 'application/json' 
            //  },
            //  type: 'GET',
            //      url: `http://localhost:9191/market/api/v1/product/products`,
            //     dataType: 'json',
            //     data: JSON.stringify(jsondata),
            //     success: function(res){
            //         console.log("iiiiiiiiiii",res);
            //     },
            //     error: function(err){
            //         console.log("ooooooooooo",err);
            //     }
            // })
            // Em.$.ajax({
            //     headers: { 
            //     'Accept': 'application/json',
            //     'Content-Type': 'application/json' 
            // },
            //     type: 'POST',
            //     url: `http://localhost:9191/market/api/v1/product/create`,
            //     dataType: 'json',
            //     data: jsondata
            // }).then(function(data) {
            //     //Ember.run(null, resolve, data);
            //     console.log(data);  
            // });
            this.get('store').createRecord('product', jsondata).save().then(function(res){
                 console.log('fffff',res);
             }).catch(function(err){
                 console.log('gggg',err);
             })

                this.transitionToRoute('products');
            }
            
        }
    }
});
