import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
  urlForFindRecord(id, modelName, snapshot) {
    console.log("Reading Category by.......", id);
    return 'http://localhost:9191/market/api/v1/category/' + id;
  },
  urlForDeleteRecord(id, modelName, snapshot) {
     console.log("Deleting Category by.......", id);
    return 'http://localhost:9191/market/api/v1/category/delete/' + id;
  },
  urlForCreateRecord(modelName, snapshot) {
    return 'http://localhost:9191/market/api/v1/category/create';
  },

  urlForFindAll(modelName, snapshot) {
    console.log("Reading all Categories.......");
    return this.get('host')+this.get('namespace')+ '/category/categories';
  },
});
