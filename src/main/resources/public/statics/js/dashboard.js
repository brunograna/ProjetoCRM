$(document).ready(function () {

    var acaoIndiceUrl = $("#acao_indice_url").attr("href");

    var acaoDonut;
    $.get(acaoIndiceUrl, function (data) {
        acaoDonut = new Morris.Donut({
            element  : 'acoes-chart',
            resize   : true,
            data     : data,
            hideHover: 'auto'
        });
    });
});