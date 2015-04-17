$(document).ready(function () {
    show();
    setInterval('show()', 1000);
    
});
function show()
{
    $.ajax({
        url: 'update',
        type: 'post',
        dataType: 'json',
        data: $('#updateTemperature').serialize(),
        cache: false,
        success: function (data) {
            if (data.isValid) {

                $('#displayTemp').html(data.temperature);
                $('#displayTemp').slideDown(500);
            }
            else {
                alert('Please enter a valid value!');
            }
        }
    });
}


