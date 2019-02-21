import Mock from 'mockjs'
import mock from '../mock'

const OrderList = []
for (let i = 0; i < 100; i++) {
  OrderList.push({
    id: Mock.Random.guid(),
    orderNo: Mock.Random.natural(),
    indeed: Mock.Random.boolean(),
    to: Mock.Random.city(),
    time: Mock.Random.date(),
    consigneeContact: Mock.Random.city(),
    consigneeName: Mock.Random.city(),
    Mobile: Mock.Random.city(),
    isPick: Mock.Random.boolean(),
    pickTime: Mock.Random.date(),
    pickAdd: Mock.Random.city(),
    isDelivery: Mock.Random.boolean(),
    deliveryAdd: Mock.Random.city(),
    cargoName: Mock.Random.city(),
    orderQty: Mock.Random.natural(),
    orderDimensions: Mock.Random.natural(),
    orderWeight: Mock.Random.natural(),
    orderVolume: Mock.Random.natural(),
    quote: Mock.Random.city(),
    transhipQty: Mock.Random.natural(),
    transhipWeight: Mock.Random.natural(),
    transhipVolume: Mock.Random.natural(),
    transhipPrice: Mock.Random.natural(),
    receiptReq: Mock.Random.city()
  })
}
export { OrderList }
