/**
@Autor: Arthur Lima de Souza
 **/

var pesquisa = {};

$(function(){
    pesquisa.configurarBotoes();
   }
);

pesquisa.configurarBotoes = function(){
    pesquisa.$btnPesquisar =  $('#btn-pesquisar-amigo');
    pesquisa.$btnPesquisar.click(function(event){
            pesquisa.pesquisar();
            event.preventDefault();
        }
    );
};

pesquisa.pesquisar = function(){
    let filtro = $('#txt-pesquisa-amigo').val();
    
    $.get("/pesquisa/pesquisarAmigo", {filtro})
        .then(function(response){
                pesquisa.renderizarResultados(response);
            }
        )
};

pesquisa.renderizarResultados = function(response){
    $('#box-pesquisaDeAmigos').html(response);
}

