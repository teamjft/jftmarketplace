import Ember from 'ember';
import Constant from 'jft-sale/constant';

export default Ember.Controller.extend({

  // isAdmin: Ember.computed('currentRouteName', function () {
  //   if (this.get('currentRouteName') === 'admin') {
  //     return false;
  //   }
  //   return false;
  // }),
  isAdmin: false,

  isUser: true,
  
  isLogin: false,

  Constant: Constant,
  
  cart: 0,

  actions: {
    signOutAction(param) {
      console.info(param, 'ppll');
      this.set('isLogin', false);
      this.set('isAdmin', false);
      this.set('isUser', true);
      this.transitionToRoute('home');
    }
  }

});
