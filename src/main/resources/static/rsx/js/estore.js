var app = angular.module("estore-app", []);

app.controller("cart-ctrl", function($scope, $http){
	$scope.cart = {
		items: {},
		
		//add
		add(id){
			var item = this.items.find(item => item.id == id);
			
			if(item){
				item.qty++;
				this.saveToLocalStorage();
			}else{
				$http.get('/rest/product/${id}').then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage;
				})	
				
			}
			
		},
		update(id, quantity){},
		remove(id){},
		clear(){},
		
		//amount
		get amount(){
				return this.items.map(item => item.qty*item.price)
								 .reduce((total,qty)=> total+=qty,0);
			
		},
		
		//count
		get count(){
			return this.items.map(item => item.qty)
							 .reduce((total,qty)=>total+=qty,0);
			
		},
		amountOf(id){},
		
		//luu
		saveToLocalStorage(){
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart",json);
		},
		
		
		//load
		loadFromLocalStorage(){
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json):[];
		}
		
		
	};		$scope.cart.loadFromLocalStorage();




	$scope.order = {
		createDate: new Date(),
		address: "",
		purchase(){
			alert("dat hang")
		}
		
	}
	
});