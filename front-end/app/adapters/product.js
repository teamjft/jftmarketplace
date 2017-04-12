import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({

    urlForFindRecord(id, modelName, snapshot) {
        
    return 'http://localhost:9191/market/api/v1/product/6c5074a4-8caf-4292-a4ac-2eb4a1db1852';
  },
  urlForCreateRecord(modelName, snapshot) {
    return 'http://localhost:9191/market/api/v1/product/create';
  }
});
