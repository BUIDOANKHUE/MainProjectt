app.controller("category-ctrl", function($scope, $http) {
    var url = 'http://localhost:8080/api/categories';

    $scope.form = {}
    $scope.list = []

    $scope.load = function() {
        $http.get(url).then(resp => {
            console.log(resp.data);
            $scope.list = resp.data;
        });
    }
    $scope.edit = function(id) {
        $http.get(`${url}/${id}`).then(resp => {
            console.log(resp.data);
            $scope.form = resp.data;
        });
    }
    $scope.reset = function() {
        $scope.form = {}
    }
    $scope.create = function() {
        var data = angular.copy($scope.form);
        $http.post(url, data).then(resp => {
            $scope.reset();
            $scope.load();
        });
    }
    $scope.update = function() {
        var data = angular.copy($scope.form);
        $http.put(`${url}/${data.id}`, data).then(resp => {
            $scope.load();
        }).catch(error => {
            alert("Không tìm thấy loại hàng để cập nhật");
        });
    }
    $scope.delete = function(id) {
        $http.delete(`${url}/${id}`).then(resp => {
            $scope.reset();
            $scope.load();
        });
    }

    $scope.init = function() {}

    $scope.load();
});