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
    
    var carregarConvitesEnviadosDoUsuario = async function(id){
        const resp = await usuarioAPI.getConvitesEnviados(id);
        $scope.usuario.emailConvitesEnviados = resp.data.map(c => c.email);
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
            alertify.success("Convite de amizade enviado com sucesso!");
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
            alerttify.success("Amigo removido com sucesso.");
        },
        function(response){
            alert("Erro ao desfazer amizade!");
        });
    }
    
    
});