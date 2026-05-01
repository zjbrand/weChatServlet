/**
 * 
 */
$(function () {
  // Ajax button click
  $('#sh').on('click', function () {
    $.ajax({
      url: '/weChat/Sh',
      type: 'POST',
      data: {
        'email': $('#email').val()
      }
    })
      // Ajaxリクエストが成功した時発動
      .done((data) => {
        $('#res').html(data);
        console.log(data);
      })
      // Ajaxリクエストが失敗した時発動
      .fail((data) => {
        $('#res').html(data);
        console.log(data);
      })

  });
});

function add_Friend() {
  var to = $('#to').text();
  //console.log(to);
  $.ajax({
    url: '/weChat/Add_Friend',
    type: 'POST',
    data: {
      'to': to
    }
  })
    .done((data) => {
      $('#res').html(data);
      alert('友達になりました！');
    })
    .fail((data) => {
      alert('友達になるのに失敗しました。');
    });
}