import DS from 'ember-data';

export default DS.Model.extend({
    fname: DS.attr('string'),
    lname: DS.attr('string'),
    email: DS.attr('string'),
    password: DS.attr('string'),
    phone: DS.attr('number'),
    gender: DS.attr('string'),
    uuid: DS.attr('string')
});
