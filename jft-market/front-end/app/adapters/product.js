import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({

  urlForFindRecord(id, modelName, snapshot) {
    console.log("In urlForFindRecord of product model...."+ id)
    return 'http://localhost:9191/market/api/v1/product/' + id;
  },

  urlForUpdateRecord(id, modelName, snapshot) {
    console.log('.....urlForUpdateRecord.....product model', id);
    
    return 'http://localhost:9191/market/api/v1/product/update/'+ id;
  },
  urlForCreateRecord(modelName, snapshot) {
    return 'http://localhost:9191/market/api/v1/product/create';
  }
});
