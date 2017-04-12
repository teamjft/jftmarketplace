export default {
  CONTEXT_PATH: '/market',
  Constant2: 'cupcakes',
  resourceURI: 'resources/assets/',
  appendContextPath: function (path) {
    return this.CONTEXT_PATH+path;
  }
}
