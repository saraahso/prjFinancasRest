angular.module('prjFinancasWeb.banco', []).factory('prjFinancas', function($resource){
    return $resource('http://localhost:8080/banco/:id', {id: '@id'}, {update:{
            method: 'PUT'
        }
    });
});

