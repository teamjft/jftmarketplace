import Ember from 'ember';
import Constant from 'jft-sale/constant';
import sweetAlert from 'ember-sweetalert';

export default Ember.Controller.extend({
  
  isAdmin: false,

  isUser: true,
  
  isLogin: false,

  Constant: Constant,
  
  cart: 0,

  actions: {
    signOutAction(param) {

      //console.info(localStorage, 'ppll');
      //localStorage.setItem("ddd", "ddddddddd")
      this.set('isLogin', false);
      this.set('isAdmin', false);
      this.set('isUser', true);
      sweetAlert("You have log-out successfully", "", "success");
      this.transitionToRoute('home');
    }
  }

});
