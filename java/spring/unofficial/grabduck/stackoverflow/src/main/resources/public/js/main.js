var app = angular.module("springDemo", []);
app.controller("AppCtrl", function($scope, $http) {

    $scope.websites = [];
    // $http({method: 'GET', url: 'http://localhost:8099/api/stackoverflow'}).then(function (data) {
    //     $scope.websites = data;
    // });
    $http.get('http://localhost:8099/api/stackoverflow').success(function(data) {

        $scope.websites = data;
        console.log(data);
    });
    // $http.get('http://localhost:8099/api/stackoverflow').
    // success(function(data, status, headers, config) {
    //     $scope.websites = data;
    //     console.log(data);
    // }).
    // error(function($data, status, $headers, config) {
    //     alert("error getting data");
    // }
    // );
});
