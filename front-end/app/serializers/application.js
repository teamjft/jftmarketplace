import DS from 'ember-data';

export default DS.JSONAPISerializer.extend({
    serialize(snapshot, options) {
    var json = this._super(...arguments);

    // json.data.attributes.cost = {
    //   amount: json.data.attributes.amount,
    //   currency: json.data.attributes.currency
    // };
    console.log("We are at serialize", snapshot);
    json.name= json.data.attributes.name;
    json.price= json.data.attributes.price;
    json.description= json.data.attributes.description;
    json.features= json.data.attributes.features;

    delete json.data;

    return json;
  },
});
