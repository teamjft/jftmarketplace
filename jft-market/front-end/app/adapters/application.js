import DS from 'ember-data';

export default DS.JSONAPIAdapter.extend({
        // shouldReloadRecord: function (store, snapshot) {
        //     return false;
        // },

        // shouldReloadAll: function (store, snapshot) {
        //     return false;
        // },

        // shouldBackgroundReloadRecord: function (store, snapshot) {
        //     return true;
        // },

        // shouldBackgroundReloadAll: function (store, snapshot) {
        //     return true;
        // },
    namespace: "/api/v1",

    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },

    host: 'http://127.0.0.1:9191/market',
});
