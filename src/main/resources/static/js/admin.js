function addAdmin(){
    let url = "/admin/add-admin";

    const item = {
        username : document.getElementById('email').value,
        password : document.getElementById('password').value,
        role : "ADMIN",
        recoverQuestion : document.getElementById('recoverqs').value,
        recoverAnswer : document.getElementById('recoverAnswer').value,
        loggedInUser : sessionStorage.getItem('USERID')
    }
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

    for (let x in item) {
      if (item[x]==null) {
        swal({
        title: "Error",
        text: "All feilds are mandatory",
        icon: "error",
        button: "Try Again",
        }).then((value) => {
       
         
        window.location.href = '/adduser';
         
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
          if (data.status == ("New Admin added successfully")) {
            swal({
              title: "Success !",
              text: data.status,
              icon: "success",
              button: "Ok",
            }).then((value) => {
                window.location.href = "/adminpage";
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