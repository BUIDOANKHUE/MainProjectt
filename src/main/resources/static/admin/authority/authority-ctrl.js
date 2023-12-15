app.controller("authority-ctrl", function($scope, $http) {
    $scope.authorities = [];
    $scope.roles = [];
    $scope.masters = [];

    $scope.loadAuthorities = function() {
        var authorityUrl = 'http://localhost:8080/api/authorities';
        $http.get(authorityUrl).then(resp => {
            $scope.authorities = resp.data;
        });
    }
    $scope.load = function() {
        var roleUrl = 'http://localhost:8080/api/roles';
        $http.get(roleUrl).then(resp => {
            $scope.roles = resp.data;
        });

        var masterUrl = 'http://localhost:8080/api/masters';
        $http.get(masterUrl).then(resp => {
            $scope.masters = resp.data;
        });

        $scope.loadAuthorities();
    }
    $scope.granted = function(role, master) {
        return $scope.find(role, master);
    }
    $scope.find = function(role, master) {
        return $scope.authorities.find(a => {
            return (a.role.id == role.id && a.account.username == master.username);
        });
    }
    $scope.change = function(role, master) {
        var authority = $scope.find(role, master);
        if (authority) { // hủy bỏ
            var authorityUrl = `http://localhost:8080/api/authorities/${authority.id}`;
            $http.delete(authorityUrl).then(resp => {
                $scope.loadAuthorities();
            });
        } else { // cấp mới
            authority = {
                role: role,
                account: master
            }
            var authorityUrl = `http://localhost:8080/api/authorities`;
            $http.post(authorityUrl, authority).then(resp => {
                $scope.loadAuthorities();
            });
        }
    }

    $scope.load();
});