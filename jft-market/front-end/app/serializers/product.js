import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
    serialize(snapshot, options) {
        console.log('------------->>>>');
        let json = this._super(...arguments);
        json.name = json.data.attributes.name;
        json.price = json.data.attributes.price;
        json.features =  "34G";
        json.description = json.data.attributes.description;
        json.categories = json.data.attributes.categories;
        //json.uuid= json.data.attributes.phone;
        delete json.data;
        return json;
    },
});
