angular.module("faceFutApp").controller("pesquisaCtrl", function($scope, amigoAPI, usuarioAPI){
    $scope.pesquisaAmigos = {};
    $scope.pesquisaAmigos.resultados = [];
    $scope.usuario = {};
    $scope.usuario.convitesEnviados = [];
    
    var getIdUsuarioLogado = function(){
        return $("#id-usuario").val();
    };
    
    var getTxtFiltroPesquisa = function(){
        return $("#txt-pesquisa-amigo").val();
    };
    
    var carregarConvitesEnviadosDoUsuario = function(id){
        usuarioAPI.getConvitesEnviados(id).then(function(response){
            $scope.usuario.emailConvitesEnviados = response.data.map(c => c.email);
        },
        function(response){
            alert("Erro!");
        });
    };
    
    var carregarAmigosUsuario = function(id){
        usuarioAPI.getAmigos(id).then(function(response){
            $scope.usuario.perfilAmigos = response.data.map(p => p.email);
        },
        function(response){
            alert("Erro ao carregar amigos.")
        });
    }
    
    $scope.pesquisar = function(pesquisa){
        let idUsuario = getIdUsuarioLogado();
        
        amigoAPI.pesquisarAmigos(pesquisa.filtro).then(function(response){
            console.log(response.data);
            $scope.pesquisaAmigos.resultados = response.data;
            carregarConvitesEnviadosDoUsuario(idUsuario);
            carregarAmigosUsuario(idUsuario);
        },
        function(response){
            alert("Erro ao pesquisar amigos, tente novamente mais tarde.");
        });
    };
    
    $scope.enviarConvite = function(id){
        let idUsuario = getIdUsuarioLogado();
        amigoAPI.enviarConviteAmizade(id).then(function(response){
            carregarConvitesEnviadosDoUsuario(idUsuario);
            carregarAmigosUsuario(idUsuario);
        },
        function(response){
          alert("Erro ao enviar convite.")  
        });
    };
    
    $scope.desfazerAmizade = function(id){
        let idUsuario = getIdUsuarioLogado();
        amigoAPI.desfazerAmizade(id).then(function(response){
            carregarConvitesEnviadosDoUsuario(idUsuario);
            carregarAmigosUsuario(idUsuario);
        },
        function(response){
            alert("Erro ao desfazer amizade!");
        });
    }
    
    
});