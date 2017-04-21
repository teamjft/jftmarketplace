import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({

  urlForFindRecord(id, modelName, snapshot) {
    console.log("In urlForFindRecord of product model...."+ id)
    return this.get('host') + this.get('namespace') + '/product/' + id;
  },

  urlForUpdateRecord(id, modelName, snapshot) {
    console.log('.....urlForUpdateRecord.....product model', id);
    
    return this.get('host') + this.get('namespace') + '/product/update/'+ id;
  },
  urlForCreateRecord(modelName, snapshot) {
    return this.get('host') + this.get('namespace') + '/product/create';
  }
});
