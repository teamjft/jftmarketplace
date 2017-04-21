import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
  urlForCreateRecord(modelName, snapshot) {
    console.log('Checking....', Ember.getOwner(this).lookup("controller:application").isAdmin);
    
    if (Ember.getOwner(this).lookup("controller:application").isAdmin) {
      return this.get('host')+this.get('namespace')+ '/user/create';
    } else {
      return this.get('host')+this.get('namespace')+ '/customer/create';
    }
  },

  urlForFindRecord(id, modelName, snapshot) {
    console.log('Reading a user by..............', id);
    return this.get('host') + this.get('namespace') + '/user/'+ id;
  },

   urlForDeleteRecord(id, modelName, snapshot) {
    console.log('deleting user by..............', id);
    return this.get('host') + this.get('namespace') + '/user/delete/'+ id;
  },
  urlForFindAll(modelName, snapshot) {
    console.log('Reading All Users ..............');
    return this.get('host') + this.get('namespace') + '/user/users  ';
  }
});
