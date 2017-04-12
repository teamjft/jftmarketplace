import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
  urlForCreateRecord(modelName, snapshot) {
    console.log('aaaacc', Ember.getOwner(this).lookup("controller:application").isAdmin);
    
    if (Ember.getOwner(this).lookup("controller:application").isAdmin) {
      return this.get('host')+this.get('namespace')+ '/user/create';
    } else {
      return this.get('host')+this.get('namespace')+ '/customer/create';
    }
  },

  urlForFindRecord(id, modelName, snapshot) {
    console.log('Reading a user by..............', id);
    return 'http://localhost:9191/market/api/v1/user/'+ id;
  },

   urlForDeleteRecord(id, modelName, snapshot) {
    console.log('deleting user by..............', id);
    return 'http://localhost:9191/market/api/v1/user/delete/'+ id;
  },
  urlForFindAll(modelName, snapshot) {
    console.log('Reading All Users ..............');
    return 'http://localhost:9191/market/api/v1/user/users  ';
  }
});
