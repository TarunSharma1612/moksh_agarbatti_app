function addToCart(prodId){
    let url = "/cart/add-cart";
    const item = {
        productId : prodId,
        quantity : document.getElementById('qty'+prodId).value,
        loggedIn : sessionStorage.getItem('USERID')
    }

    for (let x in item) {
      if (item[x]==null || item.quantity == 0) {
        swal({
        title: "Error",
        text: "All feilds are mandatory and quantity should be greater than 0",
        icon: "error",
        button: "Try Again",
        }).then((value) => {
       
         
        window.location.href = '/cart';
         
       });
      } 
      }
      
    fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
      })
        .then(response => response.json())
        .then(data => {
          console.log(data);
          if (data.status == ("cart details added successfully")) {
            swal({
              title: "Success !",
              text: data.status,
              icon: "success",
              button: "Ok",
            }).then((value) => {
             getCart() ;
            });
  
            
          } else {
            swal({
              title: data.status,
              icon: "error",
              button: "Try Again",
            });
             
          }
        })
        .catch(error => swal({
          title: "Unexpected error",
          icon: "error",
          button: "Try Again",
        }));
}




function getCart(){

  let url = "/cart/check-cart";
  const item = {
    userEmail : sessionStorage.getItem('USERID')
  }
  console.log(item);
  fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    })
    .then(response => response.json())
    .then(data => {
      let cart = "";
      const arr = data['status'];
      let total = 0;
      const cartIds = []; 
       for (let index = 0; index < arr.length; index++) {
           var obj = arr[index];
           cartIds.push(obj.cartId);
           total+=obj.cartTotal;
           cart +=
        '<div class="cart-item d-md-flex justify-content-between">' + 
            ' <div class="px-3 my-3">'+
            '<div class="cart-item-product">'+
            '<h4 class="cart-item-product-title" style="margin-top: 11px;">'+obj.productName+'&nbsp;(&nbsp;'+obj.category+'&nbsp;)&nbsp;</h4></div></div><div>'+
            '<div class="px-3 my-3 text-center">'+
            '<div class="cart-item-label">Quantity</div>'+
            '<div class="count-input">'+
            ' <div class="form-group">'+
            '<input type="quantity" class="form-control" id="quantity" aria-describedby="emailHelp" value='+obj.quantity+'>'+
            '  </div></div>'+
            '  <div class="px-3 my-3 text-center">'+
            ' <div class="cart-item-label">Subtotal</div><span class="text-xl font-weight-medium">'+obj.cartTotal+'</span>'+
            '</div><button type="button" value='+obj.cartId+' onclick="removeCart(this.value)" class="btn btn-danger">Delete</button></div></div></div>';
       }
       sessionStorage.setItem("CART",cart);
       sessionStorage.setItem("CARTID",cartIds);
       sessionStorage.setItem("TOTAL",total);
      
       window.location.href="/cart";

    });

}

                   
function removeCart(cartId){
  let param = "?cartId="+cartId;
  let url = "/cart/delete-cart"+param;
  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    }
  })
    .then(response => response.json())
    .then(data => {
      if (data.status == ("cart item deleted successfully")) {
        swal({
          title: "Success !",
          text: data.status,
          icon: "success",
          button: "Ok",
        }).then((value) => {
          getCart();
        });

        
      } else {
        swal({
          title: data.status,
          icon: "error",
          button: "Try Again",
        }).then((value) => {
          getCart();
        });
      }
    })
    .catch(error => swal({
      title: "Unexpected error",
      icon: "error",
      button: "Try Again",
    }));
}                     
          
               
 function updateCart(){
   
 }           