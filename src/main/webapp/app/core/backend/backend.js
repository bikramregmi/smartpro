import $ from 'jQuery'
var SH = {}
export default SH
SH.ajax = {
  callRemote (api, payload, type, callback) {
    let data = ''
    if (payload) {
      data = JSON.stringify(payload)
    }
    $.ajax({
      url: api,
      headers: {'Accept': 'application/json',
        'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU5Nzk4NTU3Mn0.fp0uNyR3ok-Gkm8OWVc9dF90UjhWHUzmTQR9HvEaTloqTW8ymRV9KvZd1TGM2YLe4pq2emSWmMDLv5UqYfTCLg'},
      type: type,
      contentType: 'application/json',
      data: data,
      success: function (data, textStatus) {
        if (callback) {
          return callback(data, textStatus)
        }
      },
      error: function (data, textStatus, jqXHR) {
        if (callback) {
          return callback(data.responseJSON, textStatus)
        }
      }
    })
  }
}
