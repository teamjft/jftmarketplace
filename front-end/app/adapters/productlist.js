import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
    urlForFindAll(modelName, snapshot) {
        
    return this.get('host')+this.get('namespace')+ '/product/products';
  },
});
