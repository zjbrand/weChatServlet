/**
 * 
 */
$(function () {

  $.ajax({
    url: '/weChat/Peng',
    type: 'POST',
    data: {

    }
  })
    // Ajaxリクエストが成功した時発動
    .done((data) => {
      $('#res').html(data);

    })

});