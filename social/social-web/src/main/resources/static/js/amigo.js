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

amigo.desabilitarBtnAdicionar = function(id){
    $btnAdicionar = $('#btn-adicionar-' + id);
    
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
amigo.enviarConvite = function(id){
    
  /*$.get('/amigoRest/enviarConvite', {id})
          .then(function(){
              amigo.desabilitarBtnAdicionar(id);
          });*/
    $.ajax({
       url: '/amigoRest/enviarConvite',
       type: 'PUT',
       data: {id},
       success: function(){
           amigo.desabilitarBtnAdicionar(id);
       }
    });
};

amigo.aceitarConvite = function(id){
    
    $.post("/amigoRest/aceitarConvite", {id})
            .then(function(){
                amigo.carregarConvitesRecebidos();
                amigo.carregarListaAmigos();
                amigo.atualizarNumeroDeAmigos();
                postagem.carregarPosts(postagem.idUsuario);
            });
};

amigo.recusarConvite = function(id){
    
    $.post("/amigoRest/recusarConvite", {id})
            .then(function(){
               amigo.carregarConvitesRecebidos();
               amigo.carregarListaAmigos();
               amigo.atualizarNumeroDeAmigos();
               postagem.carregarPosts(postagem.idUsuario);
            });
};

amigo.desfazerAmizade = function(id){
    
    $.post("/amigoRest/desfazerAmizade", {id})
            .then(function(){
                pesquisa.pesquisar();
                amigo.carregarListaAmigos();
                amigo.atualizarNumeroDeAmigos();
                postagem.carregarPosts(postagem.idUsuario);
            });
};


