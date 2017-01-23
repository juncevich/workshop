var app = angular.module("springDemo", []);

app.controller("AppCtrl", function ($scope, $http) {
    $scope.websites = [];

    $http({method: 'GET', url: 'http://localhost:8099/api/stackoverflow'}).then(function (data) {
        $scope.websites = data;
    });

});
