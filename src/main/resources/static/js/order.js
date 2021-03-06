function addOrder() {
  let url = "/order/add-order";
  console.log()
  const item = {
    cartId: sessionStorage.getItem("CARTID"),
    loggedIn: sessionStorage.getItem('USERID')
  }

  for (let x in item) {
    if (item[x] == null || item[quantity] == 0) {
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
      if (data.status == ("orders details added successfully")) {
        swal({
          title: "Success !",
          text: data.status,
          icon: "success",
          button: "Ok",
        }).then((value) => {
          myOrder();
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



function myOrder() {
  let url = "/order/my-order";
  const item = {
    userEmail: sessionStorage.getItem('USERID')
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
      let orderCard = "";
      const arr = data['status'];

      for (let index = 0; index < arr.length; index++) {
        var obj = arr[index];
        orderCard += '<tr>' +
          '<td>' + obj.orderBy + '</td>' +
          '<td>' + obj.category + '</td>' +
          '<td>' + obj.productName + '</td>' +
          '<td>' + obj.quantity + '</td>' +
          '<td>' + obj.total + '</td>' +
          '<td>' + obj.orderDate + '</td>' +
          '<td>' + obj.status + '</td>' +
          '</tr>';
      }

      sessionStorage.setItem("MYORDER", orderCard);
      window.location.assign("/order");
    })
    .catch(error => swal({
      title: "Unexpected error",
      icon: "error",
      button: "Try Again",
    }));
}

function adminOrder(status) {
  let url = "/order/all-order?orderStatus=" + status;

  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    }
  })
    .then(response => response.json())
    .then(data => {
      let orderCard = "";
      const arr = data['status'];

      for (let index = 0; index < arr.length; index++) {
        var obj = arr[index];
        orderCard += '<tr>' +
          '<td><a href ="javascript:myProfile(\'' + obj.orderBy + '\')">' + obj.orderBy + '</a></td>' +
          '<td>' + obj.category + '</td>' +
          '<td>' + obj.productName + '</td>' +
          '<td>' + obj.quantity + '</td>' +
          '<td>' + obj.total + '</td>' +
          '<td>' + obj.orderDate + '</td>' +
          '<td>' + obj.status + '</td>';

        if (status == "PENDING_WITH_ADMIN") {
          orderCard += '<td><button type="button" value=' + obj.orderId + ' onclick="completeOrder(this.value)" class="btn btn-success">Complete</button></td>'
          '</tr>';
        } else {
          orderCard += '</tr>';
        }
      }

      sessionStorage.setItem("MYORDER", orderCard);
      window.location.assign("/order");
    })
    .catch(error => swal({
      title: "Unexpected error",
      icon: "error",
      button: "Try Again",
    }));
}

function completeOrder(id) {
  let url = "/order/complete-order";
  const item = {
    loggedIn: sessionStorage.getItem("USERID"),
    orderId: id
  };

  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(item),
  })
    .then(response => response.json())
    .then(data => {
      if (data.status == ("order completed successfully")) {
        swal({
          title: "Success !",
          text: data.status,
          icon: "success",
          button: "Ok",
        }).then((value) => {
          adminOrder("COMPLETED");
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