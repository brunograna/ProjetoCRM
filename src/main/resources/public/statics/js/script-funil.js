var KanbanTest;
$(document).ready(function (){
    var clienteOfertaUrl = $('#cliente_oferta_url').attr('href');
    var updateClienteOfertaFunilEtapaUrl = $('#cliente_oferta_funil_etapa_url').attr('href');

    function recalculateTotalQuantity(){
        $( ".kanban-board" ).each(function( index ) {
            var boardQuantity = 0;
            $(this).find('.kanban-item').each(function (index) {
                boardQuantity++;
            });
            console.log( $( this ).text() );
            console.log("-----------------")
            $(this).find('.total-qtd').text(boardQuantity);
        });
    }
    function recalculateTotalValue(){
        $( ".kanban-board" ).each(function( index ) {
            var boardPrice = 0.0;
            $(this).find('.kanban-item').each(function (index) {
                boardPrice = boardPrice + parseFloat($(this).find('.item-valor').text());
            });
            console.log( $( this ).text() );
            console.log("-----------------")
            $(this).find('.total-price').text(boardPrice);
        });
    }

    $.get(clienteOfertaUrl, function (data) {
        KanbanTest = new jKanban({
            element : '#myKanban',
            gutter  : '10px',
            dragBoards : false,
            responsivePercentage: false,
            click : function(el){
                //alert(el.innerHTML);
                //alert(el.dataset.eid)
            },
            dropEl : function(el, target, source, sibling){
                recalculateTotalQuantity();
                recalculateTotalValue();
                console.log("Card Movido: ", el, target, source, sibling);
                console.log("Card ["+$(el).attr('data-eid')+"]");
                console.log("Movido para: "+$(target).parent().attr('data-id'));

                var clienteOfertaId = $(el).attr('data-eid');
                var arrayId = clienteOfertaId.split("_");
                var clienteId = arrayId[0];
                var ofertaId = arrayId[1];

                var novoFunilEtapaId = $(target).parent().attr('data-id');
                var update_url = updateClienteOfertaFunilEtapaUrl+"/"+clienteId+"/"+ofertaId+"/"+novoFunilEtapaId;
                $.get(update_url, function (data) {
                    var dataObject = JSON.parse(data);
                    if(dataObject.success){
                        alertify.success('Atualizado com sucesso.');
                    }else{
                        alertify.error('Algo de errado aconteceu.');
                    }
                });
            },
            boards  : data.funilEtapaDtos
        });
        recalculateTotalQuantity();
        recalculateTotalValue();
    });


    // var toDoButton = document.getElementById('addToDo');
    // toDoButton.addEventListener('click',function(){
    //     KanbanTest.addElement(
    //         '_visitante',
    //         {
    //             'title':'<div class="div_item"><h4 class="mrg_0 item_nome"><i class="fa fa-user"></i> Sem Cliente</h4><br><i class="fa fa-money"></i> <span id="valor">R$0</span></div><div class="div_item_icons"><a href="#" data-toggle="modal" data-target="#modal-timeline"><i class="fa fa-undo icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-oferta"><i class="fa fa-edit icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-acao"><i class="fa fa-plus-circle icon_item"></i></a></div>',
    //         }
    //     );
    // });

    // var addBoardDefault = document.getElementById('addDefault');
    // addBoardDefault.addEventListener('click', function () {
    //     KanbanTest.addBoards(
    //         [{
    //             'id' : '_default',
    //             'title'  : '<i class="fa fa-table"></i> Coluna Nova',
    //             //'dragTo':['_todo','_working'],
    //             'class' : 'top_funil',
    //             'item'  : [
    //                 {
    //                     'title':'<div class="div_item"><h4 class="mrg_0 item_nome"><i class="fa fa-user"></i> Sem Cliente</h4><br><i class="fa fa-money"></i> <span id="valor">R$0</span></div><div class="div_item_icons"><a href="#" data-toggle="modal" data-target="#modal-timeline"><i class="fa fa-undo icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-oferta"><i class="fa fa-edit icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-acao"><i class="fa fa-plus-circle icon_item"></i></a></div>',
    //                 },
    //             ]
    //         }]
    //     )
    // });

    // var removeBoard = document.getElementById('removeBoard');
    // removeBoard.addEventListener('click',function(){
    //     KanbanTest.removeBoard('_visitante');
    // });
});

// var KanbanTest = new jKanban({
//     element : '#myKanban',
//     gutter  : '10px',
//     dragBoards : false,
//     responsivePercentage: false,
//     click : function(el){
//         //alert(el.innerHTML);
//         //alert(el.dataset.eid)
//     },
//     dropEl : function(el, target, source, sibling){
//         //console.log("Card Movido");
//         //alert(target);
//     },
//     boards  :[
//         {
//             'id' : '_visitante',
//             'title'  : '<i class="fa fa-table"></i> Visitante <br><br>2 Oportunidade(s) <br> Total: R$24.000 ',
//             'class' : 'top_funil',
//             'item'  : [
//                 {
//                     'id':'visitante_1',
//                     'title':'<div class="div_item"><h4 class="mrg_0 item_nome"><i class="fa fa-user"></i> Fernando</h4><br><i class="fa fa-money"></i> <span id="valor">R$12.000</span></div><div class="div_item_icons"><a href="#" data-toggle="modal" data-target="#modal-timeline"><i class="fa fa-undo icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-oferta"><i class="fa fa-edit icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-acao"><i class="fa fa-plus-circle icon_item"></i></a></div>',
//                 },
//                 {
//                     'id':'visitante_2',
//                     'title':'<div class="div_item"><h4 class="mrg_0 item_nome"><i class="fa fa-user"></i> Bruno</h4><br><i class="fa fa-money"></i> <span id="valor">R$12.000</span></div><div class="div_item_icons"><a href="#" data-toggle="modal" data-target="#modal-timeline"><i class="fa fa-undo icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-oferta"><i class="fa fa-edit icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-acao"><i class="fa fa-plus-circle icon_item"></i></a></div>',
//                 }
//             ]
//         },
//         {
//             'id' : '_lead',
//             'title'  : '<i class="fa fa-table"></i> Lead <br><br>1 Oportunidade(s) <br> Total: R$3.500',
//             'class' : 'top_funil',
//             'item'  : [
//                 {
//                     'title':'<div class="div_item"><h4 class="mrg_0 item_nome"><i class="fa fa-user"></i> Caio</h4><br><i class="fa fa-money"></i> <span id="valor">R$3.500</span></div><div class="div_item_icons"><a href="#" data-toggle="modal" data-target="#modal-timeline"><i class="fa fa-undo icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-oferta"><i class="fa fa-edit icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-acao"><i class="fa fa-plus-circle icon_item"></i></a></div>',
//                 },
//             ]
//         },
//         {
//             'id' : '_inscrito_parcial',
//             //'dragTo' : ['_working'],
//             'title'  : '<i class="fa fa-table"></i> Inscrito Parcial <br><br>3 Oportunidade(s) <br> Total: R$30.000',
//             'class' : 'top_funil',
//             'item'  : [
//                 {
//                     'title':'<div class="div_item"><h4 class="mrg_0 item_nome"><i class="fa fa-user"></i> Lanny</h4><br><i class="fa fa-money"></i> <span id="valor">R$10.000</span></div><div class="div_item_icons"><a href="#" data-toggle="modal" data-target="#modal-timeline"><i class="fa fa-undo icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-oferta"><i class="fa fa-edit icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-acao"><i class="fa fa-plus-circle icon_item"></i></a></div>',
//                 },
//                 {
//                     'title':'<div class="div_item"><h4 class="mrg_0 item_nome"><i class="fa fa-user"></i> Bernardo</h4><br><i class="fa fa-money"></i> <span id="valor">R$10.000</span></div><div class="div_item_icons"><a href="#" data-toggle="modal" data-target="#modal-timeline"><i class="fa fa-undo icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-oferta"><i class="fa fa-edit icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-acao"><i class="fa fa-plus-circle icon_item"></i></a></div>',
//                 },
//                 {
//                     'title':'<div class="div_item"><h4 class="mrg_0 item_nome"><i class="fa fa-user"></i> Renato</h4><br><i class="fa fa-money"></i> <span id="valor">R$10.000</span></div><div class="div_item_icons"><a href="#" data-toggle="modal" data-target="#modal-timeline"><i class="fa fa-undo icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-oferta"><i class="fa fa-edit icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-acao"><i class="fa fa-plus-circle icon_item"></i></a></div>',
//                 },
//             ]
//         },
//         {
//             'id' : '_matriculado',
//             //'dragTo' : ['_working'],
//             'title'  : '<i class="fa fa-table"></i> Matriculado <br><br>1 Oportunidade(s) <br> Total: R$5.000 ',
//             'class' : 'top_funil',
//             'item'  : [
//                 {
//                     'title':'<div class="div_item"><h4 class="mrg_0 item_nome"><i class="fa fa-user"></i> Lucas</h4><br><i class="fa fa-money"></i> <span id="valor">R$5.000</span></div><div class="div_item_icons"><a href="#" data-toggle="modal" data-target="#modal-timeline"><i class="fa fa-undo icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-oferta"><i class="fa fa-edit icon_item"></i></a><a href="#" data-toggle="modal" data-target="#modal-acao"><i class="fa fa-plus-circle icon_item"></i></a></div>',
//                 },
//             ]
//         }
//     ]
// });
