import Ember from 'ember';
import ResetScrollPositionMixin from 'jft-sale/mixins/reset-scroll-position';

export default Ember.Route.extend(ResetScrollPositionMixin, {
  store1: Ember.inject.service(),

  model() {
      const categories = this.get('store1');
      let model = {};
      let category = this.get('store').findAll('categorylist');
      model.categories = category;
      model.catVal = [];
      model.images = [];
      var img1 = {
        src: "http://www.retailenvironments.org/wp-content/uploads/2016/06/ShopLogo.png",
        width: 300,
        myImageName: 'Image 1'
      },
      img2 = {
        src: "http://cdn.shopclues.net/images/company/141315/C_e-shop-logo.png",
        width: 300,
        myImageName: 'Image 1'
      },
      img3 = {
        src: "http://www.clker.com/cliparts/0/y/N/C/g/c/shopping-cart-md.png",
        width: 300,
        myImageName: 'Image 1'
      },
      img4 = {
        src: "http://sways.in/wp-content/uploads/2011/11/thiruvalla-shopping-logo.png",
        width: 300,
        myImageName: 'Image 1'
      },
      img5 = {
        src: "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTnxGUVBXMgG31DgCyumfMF3ni7aUMC9wPpE2xOSligQR2L6_gDXw",
        width: 300,
        myImageName: 'Image 1'
      };
      for (let i = 0; i < 10; i++) {
        model.images.push(img1);
        model.images.push(img2);
        model.images.push(img3);
        model.images.push(img4);
        model.images.push(img5);
      }
      return model;    
  }
  
});
