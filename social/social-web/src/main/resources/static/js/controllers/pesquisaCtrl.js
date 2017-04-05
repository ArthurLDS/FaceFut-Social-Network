angular.module("faceFutApp").controller("pesquisaCtrl", function($scope, $http){
    
    $scope.pesquisar = function(pesquisa){
        $scope.pesquisa.resultados = [];
        $http.get("/pesquisaRest/pesquisarAmigo?filtro=" + pesquisa.filtro).then(function(response){
            console.log(response.data);
            $scope.resultados = response.data;
            alert("FOI!");
        },
        function(response){
            alert("Algo deu errado!");
        });
    };
    
    $scope.enviarConvite = function(id){
        $http.put("/amigoRest/enviarConvite", id).then(function(response){
            amigo.desabilitarBtnAdicionar(id);
            alert("Convite enviado com sucesso!");
        },
        function(response){
          alert("Erro ao enviar convite.")  
        });
    };
    
    
});