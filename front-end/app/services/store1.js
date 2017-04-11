import Ember from 'ember';
// import Product from 'jft-sale/models/products';
import Category from 'jft-sale/models/category';

export default Ember.Service.extend({
    products: [
        { id: 1, name: 'Samsung On-5 Pr0', price: 34000, description: 'This is new phone of the samsung.', category: 'Phone, Mobile' },
        { id: 2, name: 'Goole Pizel 64-GB', price: 78000, description: 'Goole pixel in 2nd orginal andriod phone in the market.', category: 'Phone, Mobile' },
        { id: 3, name: 'Apple i-phone 128-GB', price: 84000, description: 'Latest apple iOS iphone-7 launched by Aplle Inc.', category: 'Phone, Mobile' },
        { id: 4, name: 'Bose B-234J Sound', price: 21000, description: 'Best quality ear phone by Bose', category: 'Ear Phone, Head Phone' },
        { id: 5, name: 'JBL L-78Q', price: 4000, description: 'Dual system wired and wire less.', category: 'Ear Phone, Head Phone' },
        { id: 6, name: 'Fijtsu Satellite C-647', price: 62000, description: 'This laptop with Dolby speaker and dual front camera', category: 'Laptop/DeskTop' }
    ],

    categories: [
        { id: "1", name: "Phone, Mobile", description: "This is Mobile, Phone Category" },
        { id: "2", name: "Ear Phone, Head Phone", description: "This is Head Phone Category" },
        { id: "3", name: "Laptop/DeskTop", description: "This is Laptop/DeskTop Category" }
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

    orderProd: [
        //     {
        //     id: '1', 
        //     name: "Xiomi Redmi-Note-2", 
        //     price: 16000, 
        //     description: "This is mobile phone with extra feature of AI and PR which is Artificaial Intelligence and Pattern Recegnition Respectively.", 
        //     category: "Phone, Mobile" 
        // },
        // {
        //     id: '1', 
        //     name: "Xiomi Redmi-Note-2", 
        //     price: 16000, 
        //     description: "This is mobile phone with extra feature of AI and PR which is Artificaial Intelligence and Pattern Recegnition Respectively.", 
        //     category: "Phone, Mobile" 
        // },
        // {
        //     id: '1', 
        //     name: "Xiomi Redmi-Note-2", 
        //     price: 16000, 
        //     description: "This is mobile phone with extra feature of AI and PR which is Artificaial Intelligence and Pattern Recegnition Respectively.", 
        //     category: "Phone, Mobile" 
        // }
    ],

    shipAddr:
    {
        name: "Raj Ojha",
        houseNo: "183/152",
        street: "Colonelgunj",
        city: "Allahabad",
        state: "UP",
        pincode: "211002"
    },
    cartItems: []
});