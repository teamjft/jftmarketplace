import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({

    urlForFindRecord(id, modelName, snapshot) {
        
    return 'http://localhost:9191/market/api/v1/product/eb6f8a67-611a-4b88-aa7d-f77ee101aa21';
  },
  urlForCreateRecord(modelName, snapshot) {
    return 'http://localhost:9191/market/api/v1/product/create';
  }
});
