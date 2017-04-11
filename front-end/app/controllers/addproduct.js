import Ember from 'ember';
import Category from 'jft-sale/models/category';

export default Ember.Controller.extend({

    store1: Ember.inject.service(),
    actions: {
        chooseCategory(data) {
            console.log(data);
            this.get('model').category.pushObject({"name": data})
            console.log(this.get('model').category, "cacaxac");

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
                console.log("category", model.category)
                // let category = [];
                // let catObj = //this.get('store').createRecord('category', {name: "Mobile", description: "sddf"});
                // //catObj.name= "Mobile;"
                // {
                //     name: "cat5"
                // }
                // category.pushObject(catObj);
                const products = this.get('store1');
                console.log(model, 'lololo');
                let jsondata = {
                    "name":model.name+"", 
                    "price":model.price,
                    "description":model.description+"",
                    "features":"64Gb",
                    "categories": model.category
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
                let self = this;
                this.get('store').createRecord('product', jsondata).save().then(function(res){
                    self.transitionToRoute('productlist');
                }).catch(function(err){
                    console.log('we are in Error');
                    self.transitionToRoute('productlist');
                });
                
            }
        }
    }
});
