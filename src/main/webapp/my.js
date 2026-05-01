/**
 * 
 */
$(function () {

  $.ajax({
    url: '/weChat/My',
    type: 'POST',
    data: {

    }
  })
    // Ajaxリクエストが成功した時発動
    .done((data) => {
      //console.log(data);
      var str=data.split("/");
      
      $("#email").val(str[0]);
      $("#password").val(str[1]);

    })

});

function save(){	
	$.ajax({
    url: '/weChat/UpdateMy',
    type: 'POST',
    data: {
		'new_email':$('#email').val(),
		'new_password':$('#password').val()
    }
  })
    // Ajaxリクエストが成功した時発動
    .done((data) => {
      $('#res').html(data)

    })
}
