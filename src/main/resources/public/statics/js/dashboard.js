$(document).ready(function () {

    var acaoIndiceUrl = $("#acao_indice_url").attr("href");

    var acaoDonut;
    $.get(acaoIndiceUrl, function (dataChart) {
        // acaoDonut = new Morris.Donut({
        //     element  : 'acoes-chart',
        //     resize   : true,
        //     data     : data,
        //     hideHover: 'auto'
        // });
        new Chart(document.getElementById("acoes-chart").getContext('2d'), {
            type: 'pie',
            data: dataChart,
            options: {
                title: {
                    display: true,
                    text: 'Indice de ações'
                },
                plugins: {
                    datalabels: {
                        formatter: function (value, ctx){
                        var sum = 0;
                            var dataArr = ctx.chart.data.datasets[0].data;
                            dataArr.map(function (data){
                                sum += data;
                        });
                            var percentage = (value*100 / sum).toFixed(2)+"%";
                            return percentage;
                        },
                        color: '#fff',
                    }
                }
            }
        });
    });
});