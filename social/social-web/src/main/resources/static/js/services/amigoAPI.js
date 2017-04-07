angular.module("faceFutApp").factory("amigoAPI", function($http){
   
   var _enviarConviteAmizade = function(id){
       return $http.put("/amigoRest/enviarConviteReqBody", id);
   };
   
   var _desfazerAmizade = function(id){
       return $http.post("/amigoRest/desfazerAmizadeReqBody/", id);
   };
   
   var _pesquisarAmigos = function(filtro){
       return $http.get("/pesquisaRest/pesquisarAmigo?filtro=" + filtro);
   };
   
   return {
     enviarConviteAmizade : _enviarConviteAmizade,
     desfazerAmizade : _desfazerAmizade,
     pesquisarAmigos : _pesquisarAmigos
   };
   
});