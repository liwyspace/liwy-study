const staticApp = require('./static');
const mockApp = require('./mock');
module.exports = function (app) {
    app.use(staticApp(app));
    app.use(mockApp(app));
};
