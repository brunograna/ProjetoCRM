var KanbanTest;
$(document).ready(function (){
    /**
     * Variables that store the correct URL path
     */
    var clienteOfertaUrl = $('#cliente_oferta_url').attr('href');
    var updateClienteOfertaFunilEtapaUrl = $('#cliente_oferta_funil_etapa_url').attr('href');
    var getClienteOfertaByClienteOfertaUrl = $('#cliente_oferta_get_url').attr('href');
    var acaoClienteOfertaUrl = $('#cliente_oferta_acao_url').attr('href');

    /**
     * Submit of Acao Modal Form
     */
    $('#acao-form').on('submit', function (e) {
        e.preventDefault();
        $('#modal-acao').modal('hide');
        $.ajax({
            type: 'POST',
            url: acaoClienteOfertaUrl,
            data: $(this).serialize(),
            success: function(result){
                var resultObject = JSON.parse(result);
                if(resultObject.success){
                    alertify.success('Ação registrada com sucesso.');
                }else{
                    alertify.error('Não foi possível salvar esta ação.\nPreencha os campos corretamente.');
                }
            }
        });
    })

    /**
     * Fill the modal Acao Usuario Cliente Oferta
     */
    $(document).on('click', '.acaomodal-open', function (e) {
        e.preventDefault();

        var clienteOfertaId = $(this).closest('.kanban-item').attr('data-eid');
        var arrayId = clienteOfertaId.split("_");
        var clienteId = arrayId[0];
        var ofertaId = arrayId[1];
        var urlViewModal = getClienteOfertaByClienteOfertaUrl + '/' + clienteId + '/' + ofertaId;
        $.get(urlViewModal, function (clienteOferta) {
            $("#acaomodal-cliente-id").val(clienteId);
            $("#acaomodal-oferta-id").val(ofertaId);
            $("#acaomodal-cliente-nome").val(clienteOferta.clienteOfertaId.cliente.clienteNome);
            $("#data-acao").val("");
            $("#descricao-input").val("");
            $("#acaoSelect").prop('selectedIndex', 0);
        });
    })

    /**
     * Fill the modal with ClienteOfertas
     */
    $(document).on('click', '.viewmodal-open', function (e) {
        e.preventDefault();

        var clienteOfertaId = $(this).closest('.kanban-item').attr('data-eid');
        var arrayId = clienteOfertaId.split("_");
        var clienteId = arrayId[0];
        var ofertaId = arrayId[1];
        var urlViewModal = getClienteOfertaByClienteOfertaUrl + '/' + clienteId + '/' + ofertaId;
        $.get(urlViewModal, function (clienteOferta) {
            $('#viewmodal-produto-nome').val(clienteOferta.clienteOfertaId.oferta.ofertaProduto.produtoNome);
            $('#viewmodal-nivelinstrucao-nome').val(clienteOferta.clienteOfertaId.oferta.ofertaProduto.nivelInstrucao.nivelInstrucaoDescricao);
            $('#viewmodal-produto-descricao').val(clienteOferta.clienteOfertaId.oferta.ofertaProduto.produtoDescricao);

            $('#viewmodal-oferta-datainicio').val(clienteOferta.clienteOfertaId.oferta.ofertaDataInicio);
            $('#viewmodal-oferta-datafim').val(clienteOferta.clienteOfertaId.oferta.ofertaDataFim);

            $('#viewmodal-oferta-descricao').val(clienteOferta.clienteOfertaId.oferta.ofertaDescricao);
            $('#viewmodal-oferta-preco').val(clienteOferta.clienteOfertaId.oferta.ofertaPreco.toFixed(2));
            $('#viewmodal-clienteoferta-preco').val(clienteOferta.clienteOfertaPreco.toFixed(2));

            $('#viewmodal-cliente-nome').val(clienteOferta.clienteOfertaId.cliente.clienteNome);
            $('#viewmodal-cliente-sobrenome').val(clienteOferta.clienteOfertaId.cliente.clienteSobrenome);
            $('#viewmodal-cliente-email').val(clienteOferta.clienteOfertaId.cliente.clienteEmail);
            $('#viewmodal-cliente-cpf').val(clienteOferta.clienteOfertaId.cliente.clienteCpf);

            var editUrl = clienteOfertaUrl + "/" + clienteId + "/" + ofertaId;
            $('#viewmodal-edit-link').attr('href', editUrl);
        })
    });

    /**
     * Calculate the total number of cards on board
     */
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

    /**
     * Calculate the total price of cards on board
     */
    function recalculateTotalValue(){
        $( ".kanban-board" ).each(function( index ) {
            var boardPrice = 0.0;
            $(this).find('.kanban-item').each(function (index) {
                boardPrice = boardPrice + parseFloat($(this).find('.item-valor').text());
            });
            console.log( $( this ).text() );
            console.log("-----------------")
            $(this).find('.total-price').text(boardPrice.toFixed(2));
        });
    }

    /**
     * Create Kanban dynamically
     */
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

});