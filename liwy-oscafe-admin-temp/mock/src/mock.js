const fs = require('fs');
const url = require('url');
module.exports = function (app) {
    const mockPath = './mock/data';
    return function (req, res, next) {
        function firstUpperCase(str) {
            return str.replace(/( |^)[a-z]/g, (L) => L.toUpperCase());
        }

        function parseUrlToName(url) {
            return url.split('/').map(item => firstUpperCase(item)).join('');
        }

        function getMock() {
            const mock = {};
            if(!fs.existsSync(mockPath)) {
                fs.mkdirSync(mockPath);
            }
            const files = fs.readdirSync(mockPath);
            if(files.length < 1) return mock;
            files.forEach(filePath => {
                console.log(filePath);
                var info = fs.statSync(`${mockPath}/${filePath}`);
                if(!info.isDirectory()) {
                    let data = fs.readFileSync(`${mockPath}/${filePath}`);
                    data = JSON.parse(data);
                    const name = filePath.split('.')[0];
                    mock[name] = data;
                }
            });
            console.log(mock);
            return mock;
        }

        function getSendMockData(req, mockData) {
            let query = req.query;
            let targetData = [];
            mockData["datas"].forEach(item => {
                let pp = true;
                for(let key in query) {
                    if(item[key] !== query[key]) {
                        pp = false;
                    }
                }
                if(pp === true) {
                    targetData.push(item);
                }
            });

            if(mockData["returnType"] === "Object") {
                targetData = targetData[0];
            }
            console.log(targetData);

            return Promise.resolve(targetData);
        }

        app.all('/mock/api/*', function (req, res, next) {
            const mock = getMock();
            const pathname = parseUrlToName(url.parse(req.url).pathname.replace('/mock/api/', '/'));
            console.log(pathname);
            if(mock[pathname]) {
                let mes = mock[pathname];
                getSendMockData(req, mes).then(msg => {
                    res.send(msg);
                });
            }
        });
        next()
    }
};
