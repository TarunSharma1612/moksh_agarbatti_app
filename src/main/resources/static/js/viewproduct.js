function getProduct(category) {

    let url = "/product/product-list";
    let param = "?category="+category;
    url = url + param;
	

    fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then(data => {
          let productcard = "";
          const arr = data['status'];
           
           for (let index = 0; index < arr.length; index++) {
               var obj = arr[index];
               productcard +=
            '<div class="col-md-3">' + 
                '<div class="card p-3">'+
                '<div class="text-center"> <img src= "'+obj.productImagePath + '" width="200"> </div>'+
                '<div class="product-details">'+
                '<h6 class="p-3 mb-2 bg-danger text-white">Rs.' + obj.price+' &nbsp;&nbsp; Discount- '+
                obj.discount+'% </h6>'+
                '<h6 class="p-3 mb-2 bg-warning text-dark"> '+obj.productName+' </h6>'+
                '<div class="buttons d-flex flex-row">'+
                '<div class="row">'+
                '<div class="col">'+
                '<input type="text" id="qty'+obj.productId+'"class="form-control" placeholder="Qty.">'+
                '</div>'+
                '<div class="col">'+
                '<button value='+obj.productId+' onclick="addToCart(this.value)" class="btn btn-success"> Add to Cart</button>'+
                '</div></div></div></div></div></div>';
           }
           sessionStorage.setItem("PRODUCT",productcard);
           window.location.href = '/product';
        })
        .catch(error => {
          console.log('inside catch');
          sessionStorage.removeItem("PRODUCT");
          window.location.href = '/product';
        } );

}


function newProduct(){
  
    let url = "/product/newly-arrived";
	

    fetch(url, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then(data => {
          let productcard = "";
          const arr = data['status'];
           
           for (let index = 0; index < arr.length; index++) {
               var obj = arr[index];
               productcard +=
            '<div class="col-md-3">' + 
                '<div id = '+obj.productId+' class="card p-3">'+
                '<div class="text-center"> <img src= "'+obj.productImagePath + '" width="200"> </div>'+
                '<div class="product-details">'+
                '<h6 class="p-3 mb-2 bg-danger text-white">Rs.' + obj.price+' &nbsp;&nbsp; Discount- '+
                obj.discount+'% </h6>'+
                '<h6 class="p-3 mb-2 bg-warning text-dark"> '+obj.productName+' </h6>'+
                '</div></div></div>';
           }
           sessionStorage.setItem("NEWPRODUCT",productcard);
        })
        .catch(error => {
          console.log('error catch');
        } );

}