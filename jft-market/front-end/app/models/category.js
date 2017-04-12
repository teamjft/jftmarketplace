// // import DS from 'ember-data';

// // export default DS.Model.extend({

// // });
// import Ember from 'ember';

// export default Ember.Object.extend({
    
// });
import DS from 'ember-data';

export default DS.Model.extend({
    'name': DS.attr('string'),
    'description': DS.attr('string'),
    'uuid': DS.attr('string'),
    'products': DS.hasMany('product')
});
