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
                        contents += "<h4>"+data[i].title+"</h4><br />";
                    }
                    $('#inputDiv').html(contents);
                 },
                 error:function(){
                     alert("통신 실패!!!");
                 }
            });
    });

});