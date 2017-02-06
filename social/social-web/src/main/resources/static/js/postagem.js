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

}

postagem.configurarForm = function () {
    $txtPost = $('#txt-post');
    $btnPost = $('#btn-postar');
    
    postagem.boxPosts = $('#box-posts');
    postagem.btnRefreshPosts = $('#btn-refresh-posts');
    
    postagem.atualizarBtnUploadDeImagem();
    $btnPost.click(postagem.postar);
    postagem.btnRefreshPosts.click(postagem.atualizarPosts);
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
                postagem.atualizarPosts();
                
                postagem.esconderLoading(true);
                postagem.desabilitarBtnLoading(false);
            })
            .fail(function () {
                alert('ops!');
            });
};

postagem.atualizarPosts = function(){
    postagem.boxPosts.html("");
    postagem.carregarPosts(postagem.idUsuario, 0);
}

postagem.carregarPosts = function (id, page) {
    var viewCurrent = window.location.href.includes("home") ? "HOME" : "PERFIL";
    var pagina = page - 1;
    if(page == null || page == 'undefined'){
        page = 0;
    }

    $.get("/postagem/carregarPosts", {id, arquivo: viewCurrent, page, size : 3, sort: 'id,desc'})
        .then(function (response) {
                
            $('#btn-load-more-posts-' + pagina).hide();
            postagem.boxPosts.append(response);
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
