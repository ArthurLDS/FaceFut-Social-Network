/* 
    @Autor: Arthur Lima de Souza
 */

var amigo = {};

$(function(){
    amigo.carregarListaAmigos();
    amigo.atualizarNumeroDeAmigos();
    setInterval(
        function(){
            amigo.carregarListaAmigos();
            amigo.atualizarNumeroDeAmigos();
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
}

