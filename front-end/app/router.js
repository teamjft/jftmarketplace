import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function () {
  this.route('/', {path: "/market/login"})
  this.route('registration', { path: "user/register" });
  this.route('productlist');
  this.route('addproduct', { path: "/product/add" });
  this.route('editproduct', { path: "/product/edit/:prod_id" });
  this.route('addcategory', { path: "/categories" });
  this.route('editcategory', { path: "/category/:cat_id" });
  this.route('rental');
  this.route('login', { path: "user/login" });
  this.route('home');
  this.route('home-page');
  this.route('shippingaddress');
  this.route('summarypage');
  this.route('admin');
  this.route('carts');
  this.route('managerole');
  this.route('saleonjft');
});

export default Router;
