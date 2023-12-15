app.controller("product-ctrl", function($scope, $http) {
    var url = 'http://localhost:8080/api/products';
    var categoryUrl = 'http://localhost:8080/api/categories';
    var uploadUrl = 'http://localhost:8080/api/files/images';

    $scope.form = {}
    $scope.list = []
    $scope.categories = []

    $scope.load = function() {
        $http.get(url).then(resp => {
            console.log(resp.data);
            $scope.form.image = 'cloud-upload.jpg';
            $scope.list = resp.data;
        });
        $http.get(categoryUrl).then(resp => {
            $scope.categories = resp.data;
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
         $scope.form.image = 'cloud-upload.jpg';
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
            $scope.reset();
            $scope.load();
        }).catch(error => {
            alert("Không tìm thấy sản phẩm để cập nhật");
        });
    }
    $scope.delete = function(id) {
        $http.delete(`${url}/${id}`).then(resp => {
            $scope.reset();
            $scope.load();
        });
    }
	$scope.upload =function(files){
		var data = new FormData();
		data.append('files',files[0]);
		$http.post(uploadUrl,data,{
			transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
		}).then(resp =>{
            $scope.form.image =resp.data;
             alert("thanh cong");
        }).catch(error => {
            alert("Lỗi upload ảnh");
            console.log("Error",error);
        });
	}
	
    $scope.init = function() {}

    $scope.load();
});