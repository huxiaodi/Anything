'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"/api"',
  //API_QTPAY: 'http://dlebapi.qiatuchina.com/'
  API_QTPAY: 'http://localhost:8099/'

})
