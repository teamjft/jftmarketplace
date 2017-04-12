import DS from 'ember-data';

export default DS.Model.extend({
    'name': DS.attr('string'),
    'price': DS.attr('string'),
    'description': DS.attr('string'),
    'features': DS.attr('string'),
    'uuid': DS.attr('string'),
    'categories': DS.attr()
});