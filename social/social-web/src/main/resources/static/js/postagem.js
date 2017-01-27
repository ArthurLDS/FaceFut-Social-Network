/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global file */

var postagem = {};

$(function () {
    postagem.configurarForm();
    postagem.carregarPosts();

    //setInterval(function(){
    //    postagem.carregarPosts();
    //}, 5000);

});

postagem.configurarForm = function () {
    $txtPost = $('#txt-post');
    $btnPost = $('#btn-postar');

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
                postagem.carregarPosts();
                postagem.carregarPosts();
            })
            .fail(function () {
                alert('ops!');
            });
}


postagem.carregarPosts = function () {

    $.get("/postagem/carregarPosts")
            .then(function (response) {
                $('#box-posts').html(response);
            });
};

