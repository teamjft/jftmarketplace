import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
    serialize(snapshot, options) {
        console.log('------------->>>>');
        let json = this._super(...arguments);
        json.name = json.data.attributes.name;
        json.price = json.data.attributes.price;
        json.features = "34G";
        json.description = json.data.attributes.description;
        json.categories = json.data.attributes.categories;
        //json.uuid= json.data.attributes.phone;
        delete json.data;
        return json;
    },
    // normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    //     if (payload.status == 'success') {
    //         payload.data = {
    //             type: 'product',
    //             id: payload.id,
    //             attributes: {
    //             }
    //         }
    //     } else {
    //         payload.data = {
    //             type: 'product',
    //             id: payload.uuid,
    //             attributes: {
    //                 id: payload.uuid,
    //                 name: payload.name,
    //                 price: payload.price,
    //                 description: payload.description,
    //                 features: payload.features
    //             }
    //         }
    //     }
    //     return this._super(...arguments);
    // },
});
