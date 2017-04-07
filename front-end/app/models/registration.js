import DS from 'ember-data';

export default DS.Model.extend({
    customername: DS.attr('string'),
    email: DS.attr('string'),
    password: DS.attr('string'),
    phone: DS.attr('number'),
    gender: DS.attr('string')
});
