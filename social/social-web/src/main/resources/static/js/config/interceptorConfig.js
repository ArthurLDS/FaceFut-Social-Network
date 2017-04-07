angular.module("faceFutApp").config(function($httpProvider){
   $httpProvider.interceptors.push("loadingInterceptor"); 
});

