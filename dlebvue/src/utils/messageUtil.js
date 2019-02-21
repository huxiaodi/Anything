import { Message } from 'element-ui'

const Duration = 5 * 1000

export const MessageSuccess = (msg) => {
  Message({
    showClose: true,
    message: msg,
    type: 'success',
    duration: Duration
  })
}

export const MessageError = (msg) => {
  Message({
    showClose: true,
    message: msg,
    type: 'error',
    duration: Duration
  })
}

export const MessageWarning = (msg) => {
  Message({
    showClose: true,
    message: msg,
    type: 'warning',
    duration: Duration
  })
}

export const MessageInfo = (msg) => {
  Message({
    showClose: true,
    message: msg,
    type: 'info',
    duration: Duration
  })
}

export default {
  Succes: MessageSuccess,
  Error: MessageError,
  Warning: MessageWarning,
  Info: MessageInfo
}
