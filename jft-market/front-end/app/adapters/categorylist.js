import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
    urlForFindAll(modelName, snapshot) {
        
    return this.get('host')+this.get('namespace') + '/category/categories';
  },

  urlForFindRecord(id, modelName, snapshot) {
    console.log("Reading Category by.......", id);
    return this.get('host')+this.get('namespace') + '/category/' + id;
  },
  urlForDeleteRecord(id, modelName, snapshot) {
     console.log("Deleting Category by.......", id);
    return this.get('host')+this.get('namespace') + 'category/delete/' + id;
  },
});
