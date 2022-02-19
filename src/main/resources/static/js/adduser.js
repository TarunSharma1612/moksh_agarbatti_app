 function addUser() {
     let url = "/retailer/add-retailer";
	 const formData = new FormData();
	 const fileField = document.querySelector('input[type="file"]');
	 

	 const email = document.getElementById('email').value;
	 const password = document.getElementById('password').value;
	 const retailerShopName = document.getElementById('retailerShopName').value;
	 const address1 = document.getElementById('address1').value;
	 const address2 = document.getElementById('address2').value;
	 const city = document.getElementById('city').value;
	 const state = document.getElementById('state').value;
	 const postalCode = document.getElementById('postalCode').value;
	 const country = document.getElementById('country').value;
	 const phone = document.getElementById('phone').value;
	 const roles = "RETAILER";
	 const recoverQuestion = document.getElementById('recoverqs').value;
	 const recoverAnswer = document.getElementById('recoverAnswer').value;
	 let loggedInUser = "";
	 if (sessionStorage.getItem("ROLE")=="ADMIN") {
		 loggedInUser = loggedInUser+sessionStorage.getItem("USERID")
	 } else {
		loggedInUser = loggedInUser+email;
	 }
	 
	 formData.append('email', email);
	 formData.append('password', password);
	 formData.append('retailerShopName', retailerShopName);
	 formData.append('address1', address1);
	 formData.append('address2', address2);
	 formData.append('city', city);
	 formData.append('state', state);
	 formData.append('postalCode', postalCode);
	 formData.append('country', country);
	 formData.append('phone', phone);
	 formData.append('roles', roles);
	 formData.append('recoverQuestion', recoverQuestion);
	 formData.append('recoverAnswer', recoverAnswer);
	 formData.append('loggedInUser', loggedInUser);
	 formData.append('image', fileField.files[0]);

    fetch(url, {
      method: 'POST',
      
      body: formData,
    })
      .then(response => response.json())
      .then(data => {
        if (data.status == ("user added successfully")) {
          swal({
            title: data.status,
            text: "Click ok to proceed !",
            icon: "success",
            button: "Ok",
          }).then((value) => {
			 if (!sessionStorage.getItem("ROLE")=="ADMIN") {
				sessionStorage.setItem("ROLE",roles);
				sessionStorage.setItem("USERID",email);
			 } 
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
