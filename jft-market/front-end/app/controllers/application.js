import Ember from 'ember';
import Constant from 'jft-sale/constant';
import sweetAlert from 'ember-sweetalert';

export default Ember.Controller.extend({

  session: Ember.inject.service('session'),

  isAdmin: false ,

  isUser: true,

  isLogin: false,

  Constant: Constant,

  cart: localStorage.getItem('cart') || 0,

  actions: {
    signOutAction(param) {
      this.get('session').invalidate();
      this.set('isLogin', false);
      this.set('isAdmin', false);
      this.set('isUser', true);
      sweetAlert("You have log-out successfully", "", "success");
      this.transitionToRoute('home');
    }
  }

});
