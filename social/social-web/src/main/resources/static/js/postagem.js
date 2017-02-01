/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global file */

var postagem = {};

$(function () {
    postagem.iniciar();
    
});
postagem.desabilitarBtnLoading = function(opt){
    var $txtButton = $("#txt-button");
    var $btnLoading = $('#btn-postar');
    
    if(opt){
        $txtButton.hide();
        $btnLoading.attr("disabled", true);
    } 
    else{
        $txtButton.show();
        $btnLoading.attr("disabled", false);
    }
}


postagem.esconderLoading = function(opt){
    var loadingIcon = $("#loading-icon"); 
    opt ? loadingIcon.hide() : loadingIcon.show();
}


postagem.iniciar = function (id) {
    postagem.esconderLoading(true);
    postagem.idUsuario = $('#id-usuario').val();
    postagem.configurarForm();
    postagem.carregarPosts(postagem.idUsuario);

    //setInterval(function(){
    //    postagem.carregarPosts();
    //}, 5000);

}

postagem.configurarForm = function () {
    $txtPost = $('#txt-post');
    $btnPost = $('#btn-postar');

    postagem.atualizarBtnUploadDeImagem();
    $btnPost.click(postagem.postar);
};

postagem.postar = function () {
    let file = new FormData($("#upload-file-form")[0]);
    postagem.esconderLoading(false);
    postagem.desabilitarBtnLoading(true);
    
    $.ajax({
        url: "/postagemRest/uploadImagem",
        type: "POST",
        data: file,
        processData: false,
        contentType: false,
        cache: false,
        success: function (response) {
            //setTimeOut TEMPORARIO PARA SIMULAR LENTIDÃO NO SERVIDOR!
            setTimeout(function(){
                postagem.postagemTexto(response);
            }, 1600);
        },
        error: function () {
            alert("Erroo!");
        }
    });
};

postagem.postagemTexto = function (response) {
    $.post("/postagemRest/postar", {caminhoImagem: response, texto: $txtPost.val()})
            .done(function () {
                $txtPost.val('');
                postagem.atualizarBtnUploadDeImagem();
                postagem.carregarPosts(postagem.idUsuario);
                postagem.carregarPosts(postagem.idUsuario);
                
                postagem.esconderLoading(true);
                postagem.desabilitarBtnLoading(false);
            })
            .fail(function () {
                alert('ops!');
            });
};


postagem.carregarPosts = function (id) {
    var viewCurrent = window.location.href.includes("home") ? "HOME" : "PERFIL";

    $.get("/postagem/carregarPosts", {id, arquivo: viewCurrent})
            .then(function (response) {
                $('#box-posts').html(response);
            });
};


postagem.atualizarBtnUploadDeImagem = function () {

    $.get("/postagem/carregarBtnUploadImagem")
            .then(function (response) {
                $('#upload-file-content').html(response);
                document.getElementById("upload-file-input").onchange = function () {
                    document.getElementById("fileuploadurl").value = this.value;
                };
            });

};


postagem.curtir = function(idPost){
    
    $.get("/postagem/curtir", {id:postagem.idUsuario, idPost, curtir: true})
            .done(function(response){
                $('#reacao-content-' + idPost).html(response);
            })
            .fail(function(){
                alert("Deu ruim ao carregar reação");
            });
      
};

postagem.descurtir = function(idPost){
    $.get("/postagem/curtir", {id:postagem.idUsuario, idPost, curtir: false})
            .done(function(response){
                $('#reacao-content-' + idPost).html(response);
            })
            .fail(function(){
                alert("Deu ruim ao carregar reação");
            });
};
