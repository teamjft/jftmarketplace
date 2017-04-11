import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
    urlForFindRecord(id, modelName, snapshot) {
    console.log("we are in caregory adapter", id);
    return 'http://localhost:9191/market/api/v1/category/'+ id;
  },

   urlForCreateRecord(modelName, snapshot) {
    return 'http://localhost:9191/market/api/v1/category/create';
  }
});
