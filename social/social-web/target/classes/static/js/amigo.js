/* 
    @Autor: Arthur Lima de Souza
 */

var amigo = {};

amigo.enviarConvite = function(id){
    
  $.get('/amigo/enviarConvite', {id})
          .then(function(){
              alert('Foii!!');
          });
};

