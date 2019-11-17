$(document).ready(function () {

    var acaoIndiceUrl = $("#acao_indice_url").attr("href");
    var clienteValorUrl = $("#cliente_valor_url").attr("href");

    $.get(acaoIndiceUrl, function (dataChart) {
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

    $.get(clienteValorUrl, function (dataChart) {
        new Chart(document.getElementById("clientevalor-chart").getContext('2d'), {
            type: 'bar',
            data: dataChart,
            options: {
                title: {
                    display: true,
                    text: 'Valor do cliente na etapa do Funil'
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
                },
                scales: {
                    yAxes: [
                        {
                            ticks: {
                                beginAtZero: true,
                                stepSize: 150  // if i use this it always set it '1', which look very awkward if it have high value  e.g. '100'.
                            }
                        }
                    ]
                },
                legend: {
                    display: false,
                },
            }
        });
    });
});