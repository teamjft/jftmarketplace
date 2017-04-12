import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({

    serialize(snapshot, options) {
        let json = this._super(...arguments);
        json.name = json.data.attributes.name;
        json.description = json.data.attributes.description;
        delete json.data;
        return json;
    },
    // normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    //     if (payload.status == 'success') {
    //         payload.data = {
    //             type: 'category',
    //             id: new Date(),
    //             attributes: {
    //             }
    //         }
    //     } else {
    //         payload.data = {
    //             type: 'category',
    //             id: payload.uuid,
    //             attributes: {
    //                 id: payload.uuid,
    //                 name: payload.name,
    //                 description: payload.description
    //             }
    //         }
    //     }
    //     return this._super(...arguments);
    // },
});
