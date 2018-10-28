const path = require('path');
module.exports = function (app) {
    const rootPath = path.join(__dirname, '../');
    return function (req, res, next) {
        app.get('/mock/static/*', function (req, res) {
            res.sendFile(rootPath + '/data/static/' + req.url.replace('/mock/static/', '/'));
        });
        next();
    }
};
