import ApplicationAdapter from './application';

export default ApplicationAdapter.extend({
    urlForFindAll(modelName, snapshot) {
        return this.get('host') + this.get('namespace') + '/product/products';
    },
    urlForDeleteRecord(id, modelName, snapshot) {
        return this.get('host') + this.get('namespace') + '/product/delete/' + id;
    },
    urlForUpdateRecord(id, modelName, snapshot) {
        return this.get('host') + this.get('namespace') + '/product/update/' + id;
    },
    urlForFindRecord(id, modelName, snapshot) {
        return this.get('host') + this.get('namespace') + '/product/' + id;
    }
});
