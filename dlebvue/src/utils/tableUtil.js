import _ from 'lodash'

const objectToQueryString = (object) => {
  let str = ''
  _.forIn(object, (value, key) => {
    console.log(key)
    console.log(value)
    if (_.isDate(value)) {
      str += key + '=' + value + '&'
    } else {
      str += key + '=' + value + '&'
    }
  })
  str = _.trimEnd(str, '&')
  console.log(str)
  return str
}
export default {
  objectToQueryString
}
