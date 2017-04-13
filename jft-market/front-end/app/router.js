import Ember from 'ember';
import config from './config/environment';
import Constant from 'jft-sale/constant';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function () {
  this.route('/', {path: Constant.CONTEXT_PATH});
  this.route('registration', { path: "/market/user/register" });
  this.route('productlist', { path: "/market/productlist" });
  this.route('addproduct', { path: "/market/product/add" });
  this.route('editproduct', { path: "/market/product/edit/:prod_id" });
  this.route('addcategory', { path: "/market/categories" });
  this.route('editcategory', { path: "/market/category/:cat_id" });
  this.route('rental');
  this.route('login', { path: "/market/user/login" });
  this.route('home', { path: "/market/home" });
  this.route('home-page');
  this.route('shippingaddress');
  this.route('summarypage');
  this.route('admin', { path: "/market/admin" });
  this.route('carts');
  this.route('managerole');
  this.route('saleonjft');
});

export default Router;
