import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
    urlForFindAll(modelName, snapshot) {
        
    return this.get('host')+this.get('namespace')+ '/product/products';
  },

  // urlForFindRecord(id, modelName, snapshot) {
        
  //   return 'http://localhost:9191/market/api/v1/product/category/'+id;
  // },
  urlForDeleteRecord(id, modelName, snapshot) {
    console.log('delete..............', id);
    return 'http://localhost:9191/market/api/v1/product/delete/'+ id;
  },
urlForUpdateRecord(id, modelName, snapshot) {
    console.log('idddd.....hhhhhhhhh.....', id);
    console.log('idddd.....hhhhhhhhh.....', snapshot);
    console.log('idddd.....hhhhhhhhh.....', modelName);
    return 'http://localhost:9191/market/api/v1/product/update/'+ id;
  },
  
  urlForFindRecord(id, modelName, snapshot) {
        console.log("id........", id);
    return 'http://localhost:9191/market/api/v1/product/'+ id;
  },
});
