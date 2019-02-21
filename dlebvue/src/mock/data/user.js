import Mock from 'mockjs'

const LoginUsers = [
  {
    id: 1,
    userame: 'hugo',
    password: '123',
    name: ''
  }
]
const Users = []
for (let i = 0; i < 100; i++) {
  Users.push({
    id: Mock.Random.guid(),
    name: Mock.Random.cname(),
    birth: Mock.Random.date()
  })
}
export { LoginUsers, Users }
