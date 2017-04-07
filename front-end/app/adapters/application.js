import DS from 'ember-data';

export default DS.JSONAPIAdapter.extend({
    
    namespace: "/api/v1",

    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },

    host: 'http://127.0.0.1:9191/market',
});
