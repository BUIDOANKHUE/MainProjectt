var app = angular.module("estore", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/admin/home", {
            templateUrl: "home/index.html",
            controller: "home-ctrl"
        })
        .when("/admin/category", {
            templateUrl: "category/index.html",
            controller: "category-ctrl"
        })
        .when("/admin/product", {
            templateUrl: "product/index.html",
            controller: "product-ctrl"
        })
        .when("/admin/report", {
            templateUrl: "report/index.html",
            controller: "report-ctrl"
        })
        .when("/admin/authority", {
             templateUrl: "authority/index.html",
            controller: "authority-ctrl",
        })
        .otherwise({
            template: "<h1>Unknown Page</h1>"
        });
});