function sendNotification(){
  alert("Testing");
}

function toggle(){
  var checked = document.getElementById('toggleBtn').checked;
  if(checked){
    document.getElementById('localNotificationBtn').disabled = true;
    document.getElementById('emailNotification').disabled = true;
  }
  else{
    document.getElementById('localNotificationBtn').disabled = false;
    document.getElementById('emailNotification').disabled = false;
  }

}

function sendEmail() {
  Email.send({
    Host: "smtp.gmail.com",
    Username : "sigmacos301@gmail.com",
    Password : "COS301Sigma",
    To : 'sigmacos301@gmail.com',
    From : "sigmacos301@gmail.com",
    Subject : "Testing notification system",
    Body : "This is to check if the notification will be sent",
  })
  .then(function(message){
    alert("mail sent successfully")
  });
}
