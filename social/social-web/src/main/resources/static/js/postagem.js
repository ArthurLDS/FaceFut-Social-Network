/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global file */

var postagem = {};

$(function () {
    postagem.idUsuario = $('#id-usuario').val();
    postagem.iniciar(postagem.idUsuario);

});

postagem.iniciar = function (id) {
    
    postagem.configurarForm();
    postagem.carregarPosts(id);

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

    $.ajax({
        url: "/postagemRest/uploadImagem",
        type: "POST",
        data: file,
        processData: false,
        contentType: false,
        cache: false,
        success: function (response) {
            postagem.postagemTexto(response);
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
