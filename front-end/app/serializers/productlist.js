import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    payload.data = payload.data || [];
    payload.forEach(function (product, index) {
        payload.data.pushObject({
            type: 'productlist',
            id: product.id,
            attributes: {
                id:product.id,
                name: product.name,
                price: product.price,
                description: product.description,
                features: product.features
            }
        })
    });
    return this._super(...arguments);
  },
});
