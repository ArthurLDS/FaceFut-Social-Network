/* 
    @Autor: Arthur Lima de Souza
 */

var amigo = {};

$(function(){
    amigo.carregarListaAmigos();
    amigo.atualizarNumeroDeAmigos();
    amigo.carregarConvitesRecebidos();
    setInterval(
        function(){
            amigo.carregarListaAmigos();
            amigo.atualizarNumeroDeAmigos();
            amigo.carregarConvitesRecebidos();
        }, 5000);
});

amigo.enviarConvite = function(id){
    
  $.get('/amigoRest/enviarConvite', {id})
          .then(function(){
              amigo.desabilitarBtnAdicionar();
          });
};

amigo.desabilitarBtnAdicionar = function(){
    $btnAdicionar = $('.btn-adicionar');
    
    $btnAdicionar.attr('disabled','disabled');
    $btnAdicionar.removeClass('btn-primary');
    $btnAdicionar.addClass('btn-success');
    $btnAdicionar.html('<i class="fa fa-check" aria-hidden="true"></i> Convite enviado');
}


amigo.carregarListaAmigos = function(){
    
    $.get("/amigo/carregarListaAmigos")
            .then(function(response){
                $('#box-lista-amigos').html(response);
            });
};

amigo.atualizarNumeroDeAmigos = function(){
    
    $.get("/amigoRest/atualizarNumAmigos")
            .then(function(response){
                 $('#spam-num-amigos').text(response);
            });
};

amigo.carregarConvitesRecebidos = function(){
    
    $.get("/amigo/carregarConvites")
            .then(function(response){
                 $('#box-listagem-convites').html(response);
            });
};

amigo.aceitarConvite = function(id){
    
    $.post("/amigoRest/aceitarConvite", {id})
            .then(function(){
                amigo.carregarConvitesRecebidos();
            });
};

amigo.recusarConvite = function(id){
    
    $.post("/amigoRest/recusarConvite", {id})
            .then(function(){
               amigo.carregarConvitesRecebidos();
            });
};


