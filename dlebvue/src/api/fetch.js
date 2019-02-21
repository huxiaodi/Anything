import axios from 'axios'
import {MessageError} from '../utils/messageUtil'
import {getLoginPath} from '../router/index'

const instance = axios.create({
  baseURL: process.env.BASE_API
})

// Add a request interceptor
instance.interceptors.request.use(config => {
  // Do something before request is sent
  let token = sessionStorage.getItem('dleb_token')
  config.headers['token'] = token
  return config
}, error => {
  // Do something with request error
  console.log('err' + error)
  return Promise.reject(error)
})

// Add a response interceptor
instance.interceptors.response.use(response => {
  // Do something with response data
  if (response.data) {
    // console.log(response.data)
    if (response.data.code === 103) {
      sessionStorage.removeItem('dleb_token');
      let pathname = location.pathname
      location.href = getLoginPath(pathname)
    }
  }
  return response
}, error => {
  // Do something with response error
  console.log('err' + error)
  MessageError(error.message)
  return Promise.reject(error)
})

export default instance
