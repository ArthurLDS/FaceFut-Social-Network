angular.module("faceFutApp").factory("usuarioAPI", function($http){
    
    var _getConvitesEnviados = function(id){
        return $http.get("/pesquisaRest/getUsuariorConvitesEnviados?id=" + id);
    };
    
    var _getAmigos = function(id){
        return $http.get("pesquisaRest/getAmigosUsuario?id=" + id);
    };
    
    return {
        getAmigos : _getAmigos,
        getConvitesEnviados : _getConvitesEnviados
    };
});