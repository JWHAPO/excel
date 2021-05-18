$(document).ready(function(){

    $("#word_file").change(function(){
        var data=new FormData($("#excelForm")[0]);
        $.ajax({
                url: "/excel/read",
                data: data,
                processData: false,
                contentType: false,
                type: "POST",
                cache:false,
                dataType: "json",
                success:function(data){
                    var contents = "";
                    for(var i=0; i<data.length;i++){
                        contents +=
                        "<div class='control-group'>"+
                        "<div class='form-group floating-label-form-group controls'>"+
                        "<label>"+data[i].title+"</label>"+
                        "<input class='form-control' id='"+data[i].id+"' type='text' placeholder='"+data[i].title+"'/>"+
                        "<p class='help-block text-danger'></p>"+
                        "</div>"+
                        "</div>"
                    }
                    $('#inputDiv').html(contents);
                 },
                 error:function(){
                     alert("파일의 확장자를 확인해주세요.");
                 }
            });
    });

    $("#downBtn").on("click", function(){
        var data=new FormData($("#excelForm")[0]);
        data.append('inputs','aaaaa');
        $.ajax({
            url: "/excel/down",
            dataType: "json",
            type: "POST",
            data: data,
            success: function(data){

            },
            error:function(){
                 alert("파일의 확장자를 확인해주세요.");
             }
        });
    });

});