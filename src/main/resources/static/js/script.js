var editableTable;

function onBeforeDelete(row) {
    console.log(row);
    // call API here.
    // use 'row' element to access the columns.
    $('#myModalDelete').modal('show');
    $('#del').one("click", function () {
        let rowId = $(row).attr('data-id');
        $.ajax({
            url: '/info/' + rowId,
            type: "DELETE",
            contentType: "application/json; charset=utf-8",
            success: function () {
                $('#myModalDelete').modal('hide');
                getAll();
            }
        });

    })
}

$(function () {

    editableTable = new BSTable("mytable", {
        editableColumns: "0,2",
        onEdit: function (row) {
            console.log(row);
            // call API here.
            // use 'row' element to access the columns.
            let rowId = $(row).attr('data-id');
            let columns = $(row).find('td');
            console.log(columns);
            let rowDate = columns.eq(0).text();
            let rowPrice = columns.eq(2).text();
            $.ajax({
                url: '/info/update',
                type: "POST",
                data: JSON.stringify({id: rowId, date: rowDate, price: rowPrice}),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function () {

                    getAll();
                }
            });
        },
        advanced: {
            columnLabel: 'Actions',
            buttonHTML: `<div class="btn-group pull-right">
                   <button  type="button" id="bEdit" class="btn btn-sm btn-default" >
                        <span class="fa fa-edit" > </span>
                  </button>
                      
                   <button  type="button" id="bElim" class="btn btn-sm btn-default" onclick="onBeforeDelete(this.parentElement.parentElement.parentElement);">
                         <span class="fa fa-trash" > </span>
                    </button>
                     <button id="bAcep" type="button" class="btn btn-sm btn-default" style="display:none;">
                        <span class="fa fa-check-circle" > </span>
                    </button>
                    <button id="bCanc" type="button" class="btn btn-sm btn-default" style="display:none;" >
                        <span class="fa fa-times-circle" > </span>
                    </button>
                </div>`
        },

    });
    editableTable.init();
    getAll();
});

function getAll() {
    let tablebody = $("#table-block"),
        graphic = $("#graphic-block");


    $.get(
        "/info/all",
        {},
        function (json) {
            console.log(json);
            drawTable(json, tablebody);
            drawGraphic(json, graphic);
        }
    );
}

function drawTable(json, tablebody) {

    let output = "";


    for (let i = 0; i < json.length; i++) {
        output += "<tr data-id=" + json[i].id + ">";
        output += "<td>" + json[i].date + "</td>";
        output += "<td>" + json[i].toolDto.name + "</td>";
        output += "<td>" + json[i].price + "</td>";
        output += "</tr>";
    }

    tablebody.html(output);
    editableTable.refresh();
}


function getData(json) {
    let data = {};

    for (let i = 0; i < json.length; i++) {
        if (data[json[i].toolDto.name] === undefined) {
            data[json[i].toolDto.name] = [];
        }
        data[json[i].toolDto.name].push(json[i]);
    }

    return data;
}

function randCol() {
    return Math.round(Math.random() * 255);
}

function drawGraphic(json, graphic) {

    let myChart;
    let data = getData(json);
    console.log(data);
    let time = [];
    let itemDates = json.map(function (item) {
        return item.date;
    });
    itemDates = [...new Set(itemDates)];

    myChart = new Chart(graphic, {
        type: 'line',
        data: {
            labels: itemDates,
            datasets: Object.keys(data).map(function (tool) {
                return {
                    label: tool,
                    data: itemDates.map(function (date) {
                        let rec = data[tool].find(function (p) {
                            return p.date === date;
                        });
                        return rec ? rec.price : null;
                    }),
                    backgroundColor: [
                        'rgba(' + randCol() + ', ' + randCol() + ', ' + randCol() + ', 0.0)',
                    ],
                    borderColor: [
                        'rgba(' + randCol() + ', ' + randCol() + ', ' + randCol() + ', 1)',
                    ],
                    borderWidth: 1,
                    spanGaps: true
                }
            }),
        },


        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

}

