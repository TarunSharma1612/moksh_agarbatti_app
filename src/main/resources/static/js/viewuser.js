function getRetailers(){
    let url = "/retailer/retailers-list";
	const item = {
         userEmail : sessionStorage.getItem('USERID')
        };
    console.log(item.userEmail);
    fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
      })
        .then(response => response.json())
        .then(data => {
          let usercard = "";
            const arr = data['status'];
           for (let index = 0; index < arr.length; index++) {
               var obj = arr[index];
               usercard +=
            '<div class="col-sm-4 py-2">' + 
                '<div id = '+obj.userId+' class="card">'+
                ' </div>'+
                '<h4 class="bg-dark text-white d-flex justify-content-sm-center" ">'+obj.retailerShopName+'</h4>'+
                '<p class="bg-secondary text-white">'+obj.address1+'&nbsp'+obj.address2+'<br>'+
                 obj.city+','+obj.state+'<br>( '+obj.country+' )<br>'+obj.email+'<br>'+obj.phone+'</p>'+
                '<div class="btn-group">'+
                '<button type="button" class="btn btn-success">Update</button>'+
                '<button type="button" class="btn btn-danger">Delete</button>'+
                '</div> </div></div>'
           }
           sessionStorage.setItem("USERS",usercard);
           window.location.href = '/user';
        })
        .catch(error => {
            swal({
                title: "Only Admin can check all Retailers",
                text: "Click Login to proceed !",
                icon: "error",
                button: "Login",
              }).then((value) => {
               
                window.location.href = '/';
              });
        } );
}

