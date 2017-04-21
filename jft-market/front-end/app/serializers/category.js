import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({

    serialize(snapshot, options) {
        let json = this._super(...arguments);
        json.name = json.data.attributes.name;
        json.description = json.data.attributes.description;
        delete json.data;
        return json;
    },
});
