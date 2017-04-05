import Ember from 'ember';
import Product from 'jft-sale/models/products';
import Category from 'jft-sale/models/category';

export default Ember.Service.extend({
    products: 
    // function(){
    //     let pr = this.get('store').findAll('product');
    //         pr.then(function(res){
    //           //self.set('prod', res.objectAt(0));
    //           return res;
    //         }).catch(function(err){
    //            console.log("Error in chooseCategory Action", err);
    //         });
    // },
    [
         Product.create({ id: '1', name: "Xiomi Redmi-Note-2", price: 16000, description: "This is mobile phone with extra feature of AI and PR which is Artificaial Intelligence and Pattern Recegnition Respectively.", category: "Phone, Mobile" }),
         Product.create({ id: '2', name: "Samsung On-5-Pro", price: 6000, description: "This is mobile phone with extra feature of AI and PR which is Artificaial Intelligence and Pattern Recegnition Respectively.", category: "Phone, Mobile" }),
         Product.create({ id: '3', name: "JBL X-203", price: 26000, description: "This is ear phone with extra feature of Sound.", category: "Ear Phone, Head Phone" })
     ],

    categories: [
        { id: "1", name: "Phone, Mobile", description: "This is Mobile, Phone Category" },
        { id: "2", name: "Ear Phone, Head Phone", description: "This is Head Phone Category" }
    ],

    getProducts: function () {
        return this.products;
    },

    addProducts: function (data) {
        let id = this.products.length;
        data.id = id + 1;
        let newProd = Product.create({ id: data.id, name: data.name, price: data.price, description: data.description, category: data.category })
        this.products.pushObject(newProd);
    },

    editProducts: function (data) {
        //TODO: Do some edit Functionality 
    },

    getCategories: function () {
        return this.categories;
    },

    addCategory: function (data) {
        let id = this.categories.length;
        data.id = id + 1;
        let cat = Category.create({ id: data.id, name: data.name, description: data.description });
        this.categories.pushObject(cat);
    },

    editCategory: function (data) {
        //TODO: Do some edit Functionality 
    },

    orderProd: [{ 
        id: '1', 
        name: "Xiomi Redmi-Note-2", 
        price: 16000, 
        description: "This is mobile phone with extra feature of AI and PR which is Artificaial Intelligence and Pattern Recegnition Respectively.", 
        category: "Phone, Mobile" 
    }],

    shipAddr: {
        name: "Raj Ojha",
        houseNo: "183/152",
        street: "Colonelgunj",
        city: "Allahabad",
        state: "UP",
        pincode: "211002"
    },
    cartItems : []
});