
function checkLogin() {
  let url = "/admin/login";

  const item = {
    roles: document.getElementById('role').value,
    username: document.getElementById('email').value,
    password: document.getElementById('password').value
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
          sessionStorage.setItem("ROLE", item.roles);
          sessionStorage.setItem("USERID", item.username);
          newProduct();
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


function myProfile(shopName) {
  let url = "admin/profile";
  if (sessionStorage.getItem("USERID") != null) {

    const item = {};
    if (shopName === undefined) {
      item.userEmail = sessionStorage.getItem("USERID");
    }else{
      item.userEmail = shopName;
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
        var card = "";
        if (arr.role == 'RETAILER') {
          card += '<div class="col-md-4 gradient-custom text-center text-white" style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">'
            + '<img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"'
            + ' alt="Avatar" class="img-fluid my-5" style="width: 80px;"/>'
            + '<h5 id="shopName">' + arr.retailerShopName + '</h5>'
            + '<h3 id="role">' + arr.role + '</h3>'
            + '<br>'
            + '<br>'
            + '</div>'
            + '<div class="col-md-8">'
            + ' <div class="card-body p-4">'
            + '<h6>Information</h6>'
            + '  <hr class="mt-0 mb-4">'
            + '  <div class="row pt-1">'
            + '    <div class="col-6 mb-3">'
            + '      <h6>Email</h6>'
            + '      <p id="email" class="text-muted">' + arr.email + '</p>'
            + ' </div>'
            + ' <div class="col-6 mb-3">'
            + '   <h6 id="phone">Contact</h6>'
            + '   <p class="text-muted">' + arr.phone + '</p>'
            + ' </div>'
            + '</div>'
            + '<h6>Address</h6>'
            + ' <hr class="mt-0 mb-4">'
            + ' <div class="row pt-1">'
            + '  <div class="col-6 mb-3">'
            + '   <p id="address">' + arr.address1 + ' ' + arr.address2 + ' ' + arr.city + '</p>'
            + '   <p class="text-muted"></p>'
            + ' </div>'
            + ' <div class="col-6 mb-3">'
            + '   <h6>State/Country</h6>'
            + '   <p id="state" class="text-muted">' + arr.state + ' ' + '(' + arr.country + ')</p>'
            + ' </div>'
            + '</div>'

            + '<div class="row pt-1">';

            if (shopName === undefined) {
              card +=   ' <div class="col-6 mb-3">'
            + '     <a href="#" class="btn btn-danger" onclick="logout()">'
            + '         <span class="glyphicon glyphicon-log-out" ></span> Log out'
            + '       </a>'
            + ' </div>'
            } 

            card +=  '</div>'

            + '</div>'
            + '</div> ';
        } else {
          card += '<div class="col-md-4 gradient-custom text-center text-white" style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">'
            + '<img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"'
            + ' alt="Avatar" class="img-fluid my-5" style="width: 80px;"/>'
            + '<h5 id="shopName">' + arr.userName + '</h5>'
            + '<h3 id="role">' + arr.role + '</h3>'
            + '<br>'
            + '<br>'
            + '</div>'
            + '<div class="col-md-8">'
            + ' <div class="card-body p-4">'
            + '<h6>Information</h6>'
            + '  <hr class="mt-0 mb-4">'
            + '  <div class="row pt-1">'
            + '    <div class="col-6 mb-3">'
            + '      <h6>Email</h6>'
            + '      <p id="email" class="text-muted">' + arr.userName + '</p>'
            + ' </div>'
            + ' <div class="col-6 mb-3">'
            + '   <h6 id="phone"></h6>'
            + '   <p class="text-muted"></p>'
            + ' </div>'
            + '</div>'
            + '<h6>Address</h6>'
            + ' <hr class="mt-0 mb-4">'
            + ' <div class="row pt-1">'
            + '  <div class="col-6 mb-3">'
            + '   <p id="address"></p>'
            + '   <p class="text-muted"></p>'
            + ' </div>'
            + ' <div class="col-6 mb-3">'
            + '   <h6>State/Country</h6>'
            + '   <p id="state" class="text-muted">India</p>'
            + ' </div>'
            + '</div>'

            + '<div class="row pt-1">';

            if (shopName === undefined) {
              card +=   ' <div class="col-6 mb-3">'
            + '     <a href="#" class="btn btn-danger" onclick="logout()">'
            + '         <span class="glyphicon glyphicon-log-out" ></span> Log out'
            + '       </a>'
            + ' </div>'
            } 

            card +=  '</div>'

            + '</div>'
            + '</div> ';
        }
        sessionStorage.setItem("PROFILE", card);
        window.location.href = '/profile'
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


function logout() {
  sessionStorage.clear();
  localStorage.clear();
  swal({
    title: "Logout Successfully",
    text: "Thank you for visiting our application!",
    icon: "success",
    button: "Ok",
  }).then((value) => {
    window.location.href = '/';
  });

  // window.location.href = '/';

}