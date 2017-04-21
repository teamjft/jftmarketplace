import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({

    serialize(snapshot, options) {
        console.log(" registration serializeer");
        let json = this._super(...arguments);
        json.fname = json.data.attributes.fname;
        json.lname = json.data.attributes.lname;
        json.email = json.data.attributes.email;
        json.password = json.data.attributes.password;
        json.gender = json.data.attributes.gender;
        json.phone = json.data.attributes.phone;
        delete json.data;
        return json;
    },
});
