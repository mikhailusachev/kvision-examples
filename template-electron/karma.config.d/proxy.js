var old = module.exports;

module.exports = function(config) {
    var temp = {};
    temp.set = function(c) {
	temp.conf = c;
    };
    var oldConfig = old(temp);
    temp.conf.proxies = {
	'/tmp/_karma_webpack_/': 'http://localhost:3000/'
    };
    temp.conf.webpackMiddleware = {
	watchOptions: { aggregateTimeout: 1000, poll: 500 }
    };
    config.set(temp.conf);
};
