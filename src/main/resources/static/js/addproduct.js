function addProduct(){
    let url = "/product/add-product";
	 const formData = new FormData();
	 const fileField = document.querySelector('input[type="file"]');
	 
	 const category = document.getElementById('category').value;
	 const productName = document.getElementById('productName').value;
	 const productDescription = document.getElementById('productDescription').value;
	 const price = document.getElementById('price').value;
	 const discount = document.getElementById('discount').value;
	 let loggedInUser = "";
	 if (sessionStorage.getItem("ROLE")=="ADMIN") {
		 loggedInUser = loggedInUser+sessionStorage.getItem("USERID")
	 } else {
		swal({
            title: "Error",
            text: "Only Admin Can Add Product",
            icon: "error",
            button: "Try Again",
          }).then((value) => {
			 
           
          window.location.href = '/';
           
         });

	 }
	 
	 
	 formData.append('category', category);
	 formData.append('productName', productName);
	 formData.append('productDescription', productDescription);
	 formData.append('price', price);
	 formData.append('discount', discount);
	 formData.append('loggedInUser', loggedInUser);
	 formData.append('image', fileField.files[0]);

    fetch(url, {
      method: 'POST',
      
      body: formData,
    })
      .then(response => response.json())
      .then(data => {
        if (data.status == ("product added successfully")) {
          swal({
            title: data.status,
            text: "Click ok to proceed !",
            icon: "success",
            button: "Ok",
          }).then((value) => {
			 
			 loadhome();
            
          });

          
        } else {
          swal({
            title: "Error",
            text: data.status,
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

function loadhome () {
 
  const role = sessionStorage.getItem("ROLE");
  let url = "";
  if (role == "ADMIN") {
    url += "/adminpage";
  }else {
    url +="/userpage";
  }
  
  window.location.href = url;

}