import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({

    serialize(snapshot, options) {
      let json = this._super(...arguments);
      json.customerName= json.data.attributes.customername;
      json.email= json.data.attributes.email;
      json.password= json.data.attributes.password;
      json.gender= json.data.attributes.gender;
      json.phone= json.data.attributes.phone;
      delete json.data;
      return json;
  },

   normalizeResponse(store, primaryModelClass, payload, id, requestType) {
     console.log(payload);
    payload.data = payload.data || [];
    payload.forEach(function (customer, index) {
        payload.data.pushObject({
            type: 'registration',
            id: customer.uuid,
            attributes: {
                id:customer.uuid,
                customername: customer.customerName,
                email: customer.email,
                phone: customer.phone,
                //features: product.features
            }
        })
    });
    return this._super(...arguments);
   }
});
