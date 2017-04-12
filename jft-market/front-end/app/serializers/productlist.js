import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
    serialize(snapshot, options) {
        console.log("In the serializeer of product list........", this._super(...arguments));
        let json = this._super(...arguments);
        json.name = json.data.attributes.name;
        json.price = json.data.attributes.price;
        json.features = "34G";
        json.description = json.data.attributes.description;
        json.categories = [{
            name: "cat2"
        }];
        //json.uuid= json.data.attributes.phone;
        console.log("JSON.......", json);
        delete json.data;
        return json;
    },
//   normalizeResponse(store, primaryModelClass, payload, id, requestType) {
//     payload.data = payload.data || [];
//     payload.forEach(function (product, index) {
//         payload.data.pushObject({
//             type: 'productlist',
//             id: product.uuid,
//             attributes: {
//                 id:product.uuid,
//                 name: product.name,
//                 price: product.price,
//                 description: product.description,
//                 features: product.features
//             }
//         })
//     });
//     return this._super(...arguments);
//   },
});
