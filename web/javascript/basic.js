$(document).ready(function(){
    $('#updateTemperature').submit(function(){
        $.ajax({
            url: 'update',
            type: 'post',
            dataType: 'json',
            data: $('#updateTemperature').serialize(),
            success: function(data){
                if(data.isValid){
                    
                    $('#displayTemp').html('Temperature is: '+ data.temperature);
                    $('#displayTemp').slideDown(500);
                }
                else{
                    alert('Please enter a valid value!');
                }
            }
        });
        return false;
    });
});

