import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
    urlForCreateRecord(modelName, snapshot) {
        
    return this.get('host')+this.get('namespace')+ '/customer/create';
  },
  

    urlForFindAll(modelName, snapshot) {
    return 'http://localhost:9191/market/api/v1/customer/customers  ';
  }
});
