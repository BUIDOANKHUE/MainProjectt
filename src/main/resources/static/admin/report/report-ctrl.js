app.controller("report-ctrl", function($scope, $http) {
    var url = 'http://localhost:8080/api/report/revenue';

    $scope.list = []

    $scope.load = function() {
        $http.get(url).then(resp => {
            $scope.list = resp.data;
        });
    }

    $scope.load();
});