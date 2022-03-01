 function addUser() {
     let url = "/retailer/add-retailer";
	//  const formData = new FormData();
	//  const fileField = document.querySelector('input[type="file"]');
	 

	 
	 let loggedIn = "";
	 if (sessionStorage.getItem("ROLE")=="ADMIN") {
		loggedIn = loggedIn+sessionStorage.getItem("USERID")
	 } else {
		loggedIn = loggedIn+email;
	 }


	 

	 const item = {
		email : document.getElementById('email').value,
		password : document.getElementById('password').value,
		retailerShopName : document.getElementById('retailerShopName').value,
		address1 : document.getElementById('address1').value,
		address2 : document.getElementById('address2').value,
		city : document.getElementById('city').value,
		state : document.getElementById('state').value,
		postalCode : document.getElementById('postalCode').value,
		country : document.getElementById('country').value,
		phone : document.getElementById('phone').value,
		roles : "RETAILER",
		recoverQuestion : document.getElementById('recoverqs').value,
		recoverAnswer : document.getElementById('recoverAnswer').value,
		loggedInUser : loggedIn
	 };

	 for (let x in item) {
		if (item[x]==null) {
		  swal({
			title: "Error",
			text: "All fields are mandatory",
			icon: "error",
			button: "Try Again",
		  }).then((value) => {
	   
		   
		  window.location.href = '/adduser';
		   
		 });
		} 
	  }
	 
	//  formData.append('email', email);
	//  formData.append('password', password);
	//  formData.append('retailerShopName', retailerShopName);
	//  formData.append('address1', address1);
	//  formData.append('address2', address2);
	//  formData.append('city', city);
	//  formData.append('state', state);
	//  formData.append('postalCode', postalCode);
	//  formData.append('country', country);
	//  formData.append('phone', phone);
	//  formData.append('roles', roles);
	//  formData.append('recoverQuestion', recoverQuestion);
	//  formData.append('recoverAnswer', recoverAnswer);
	//  formData.append('loggedInUser', loggedInUser);
	//  formData.append('image', fileField.files[0]);

    fetch(url, {
      method: 'POST',
	  headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
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
    url +="/";
  }
  
  window.location.href = url;
}



function checkEmailPassword(String){

}