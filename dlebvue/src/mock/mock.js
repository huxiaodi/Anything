import _ from 'lodash'
import axios from '@/api/fetch'
import MockAdapter from 'axios-mock-adapter'
import { Success, Error } from './base.js'
import { LoginUsers, Users } from './data/user'
import { OrderList } from './data/orderList'

export default {
  bootstrap(){
    let mock = new MockAdapter(axios)

    mock.onPost('/login').reply(config => {
      let {userName, userPassword} = JSON.parse(config.data)
      return new Promise((resolve, reject) => {
        let user = null
        setTimeout(() => {
          let hasUser = LoginUsers.some(u => {
            if (u.username === userName && u.password === userPassword) {
              user = JSON.parse(JSON.stringify(u))
              user.password = null
              return true
            }
          })
          if (hasUser) {
            resolve([200, Success('登陆成功', user)])
          } else {
            resolve([200, Error('账户或者密码错误')])
          }
        }, 200)
      })
    })

    mock.onGet('/csp/order').reply(config => {
      console.log(config.params)
      return new Promise((resolve, reject) => {
        let pageNum = config.params && config.params.pageNum ? config.params.pageNum : 1
        let pageSize = config.params && config.params.pageSize ? config.params.pageSize : 10
        let begin = (pageNum - 1) * pageSize
        let end = pageNum * pageSize - 1
        setTimeout(() => {
          let pageInfo = {
            total: OrderList.length,
            list: _.slice(OrderList, begin, end)
          }
          resolve([200, Success('获取成功', pageInfo)])
        }, 200)
      })
    })
  }
}
