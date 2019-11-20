$(document).ready(function () {

    var acaoIndiceUrl = $("#acao_indice_url").attr("href");
    var clienteValorUrl = $("#cliente_valor_url").attr("href");
    var ofertaEtapaUrl = $("#oferta_etapa_url").attr("href");
    var clienteFechamentoUrl = $("#oferta_fechamento_url").attr("href");

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
                    text: 'Valor total do cliente em cada etapa do Funil'
                },
                plugins: {
                    datalabels: {
                        formatter: function (value, ctx){
                            return "R$ "+value;
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

    $.get(clienteFechamentoUrl, function (dataChart) {
        new Chart(document.getElementById("fechamento-chart").getContext('2d'), {
            type: 'bar',
            data: dataChart,
            options: {
                title: {
                    display: true,
                    text: 'Valor total de fechamento por dia'
                },
                plugins: {
                    datalabels: {
                        formatter: function (value, ctx){
                            return "R$ "+value;
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

    $.get(ofertaEtapaUrl, function (dataResponse) {
        new Chart(document.getElementById("oferta-etapa-chart"), {
            type: 'bubble',
            data: dataResponse,
            options: {
                legend: {
                    display: false,
                },
                title: {
                    display: true,
                    text: 'Quantidade da Oferta em cada Etapa do Funil'
                }, scales: {
                    yAxes: [{
                        scaleLabel: {
                            display: true,
                            labelString: "Oferta"
                        },
                        ticks: {
                            suggestedMin: 1,
                            stepSize: 1  // if i use this it always set it '1', which look very awkward if it have high value  e.g. '100'.
                        }
                    }],
                    xAxes: [{
                        scaleLabel: {
                            display: true,
                            labelString: "Funil Etapa"
                        },
                        ticks: {
                            suggestedMin: 1,
                            stepSize: 1  // if i use this it always set it '1', which look very awkward if it have high value  e.g. '100'.
                        }
                    }]
                }
            }
        });
    })
});