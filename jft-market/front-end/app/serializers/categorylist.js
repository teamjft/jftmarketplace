import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
    // normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    //     payload.data = payload.data || [];
    //     payload.forEach(function (category, index) {
    //         payload.data.pushObject({
    //             type: 'categorylist',
    //             id: category.uuid,
    //             attributes: {
    //                 id: category.id,
    //                 uuid: category.uuid,
    //                 name: category.name,
    //                 description: category.description,
    //             }
    //         })
    //     });
    //     return this._super(...arguments);
    // },
});
