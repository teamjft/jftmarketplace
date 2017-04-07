import Ember from 'ember';
export default Ember.Controller.extend({

    store1: Ember.inject.service(),
    actions: {
        chooseCategory(data) {
            console.log(data);
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
                console.log(model, 'lololo');
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
                this.get('store').createRecord('product', jsondata).save();
                this.transitionToRoute('productlist');
            }
        }
    }
});
