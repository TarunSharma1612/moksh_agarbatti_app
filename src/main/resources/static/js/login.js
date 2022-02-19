

 function checkLogin() {
    let url = "/admin/login";

    const item = {
        roles : document.getElementById('role').value,
        username : document.getElementById('email').value,
        password : document.getElementById('password').value
    };

    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify(item),
    })
      .then(response => response.json())
      .then(data => {
        if (data.status.startsWith("Login")) {
          swal({
            title: data.status,
            text: "Click ok to proceed !",
            icon: "success",
            button: "Ok",
          }).then((value) => {
            sessionStorage.setItem("ROLE",item.roles);
            sessionStorage.setItem("USERID",item.username);
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
  let url = "/";
  if (role == "ADMIN") {
    url = url+"adminpage"
  }else{
    url = url+"userpage"
  } 
  
  window.location.href = url;

}




function myProfile(){
  let url = "admin/profile";
  if (sessionStorage.getItem("USERID") != null) {
    
    const item = {
      userEmail : sessionStorage.getItem("USERID")
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
        const arr = data['status'];
        sessionStorage.setItem("LOGIN",JSON.stringify(arr));
      })
      .catch(error => swal({
        title: "Unexpected error",
        icon: "error",
        button: "Try Again",
      }));
  } else {
    window.location.href = '/';
  }


}


function logout(){
  sessionStorage.clear();
  localStorage.clear();
  window.location.href = '/';
}