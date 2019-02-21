export const Success = (message, result) => {
  return {
    code: 'SUCCESS',
    message: !message ? '' : message,
    result: !result ? null : result
  }
}

export const Error = (message) => {
  return {
    code: 'SUCCESS',
    message: !message ? '' : message,
    result: null
  }
}
