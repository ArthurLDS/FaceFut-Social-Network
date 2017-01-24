/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    $.post("/postagemRest/postar", {texto: $txtPost.val()})
            .then(function () {
                $txtPost.val('');
                postagem.carregarPosts();
            }
            );
};

postagem.carregarPosts = function () {

    $.get("/postagem/carregarPosts")
            .then(function (response) {
                $('#box-posts').html(response);
            });
};

