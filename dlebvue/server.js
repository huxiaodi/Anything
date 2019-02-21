var express = require('express');
var path = require('path');
var history = require('connect-history-api-fallback');
var serveStatic = require('serve-static');
var bodyParser = require('body-parser');
var proxy = require('http-proxy-middleware');

app = express();
app.use(history());
console.log(__dirname)
app.use(serveStatic(__dirname + "/dist"));
app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded
app.post('/posttest', function (req, res) {
  console.log(req.body);

  res.redirect('/dashboard/main');
  // res.render(__dirname + "/dist/index");
});

var apipath = "http://dlebapi.qiatuchina.com/";

var apiProxy = proxy('/api', {
  pathRewrite: {"^/api": ""},
  target: apipath,
  logLevel: 'debug',
  changeOrigin: true   // for vhosted sites
});

app.use(apiProxy);

var port = process.env.PORT || 5000;
app.listen(port);
console.log('server started '+ port);
