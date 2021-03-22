$(function () {
    $('#modulID').click(function () {
        let date = $('#data').val();
        let price = $('#price').val();
        let tool = $('#tool').val();

        $.get('/tools/all', {}
            , function (json) {
                let toolrecord = json.find(function (rec) {
                    return rec.name === tool;
                })
                $.ajax({
                    url: '/info/add',
                    type: "POST",
                    data: JSON.stringify({date, price, toolDto: toolrecord}),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function () {
                        $('#exampleModal').modal('hide');
                        getAll();
                    }
                });
            })

    });


});