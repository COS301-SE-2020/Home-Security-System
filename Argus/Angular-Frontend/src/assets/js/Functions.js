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
    Username : "cos332prac7email@gmail.com",
    Password : "COS3322020",
    To : 'u16078625@tuks.co.za',
    From : "sigmacos301@gmail.com",
    Subject : "Testing notification system",
    Body : "This is to check if the notification will be sent"
	}).then(
		message => alert("mail sent successfully")
	);
}

function addCell(type){

  var table = document.getElementById("notificationsTable");
  var x = table.rows.length;
  var row = table.insertRow();
  var btn = document.createElement("button");
  btn.setAttribute('class', 'DeleteButton');
  btn.innerHTML = "Delete";

  var cell1 = row.insertCell(0);
  cell1.style.border = "1px solid #dddddd";
  cell1.style.background = "#F9F5F4";
  var cell2 = row.insertCell(1);
  cell2.style.border = "1px solid #dddddd";
  cell2.style.background = "#F9F5F4";
  var cell3 = row.insertCell(2);
  cell3.style.border = "1px solid #dddddd";
  cell3.style.background = "#F9F5F4";
  var cell4 = row.insertCell(3);
  cell4.style.border = "1px solid #dddddd";
  cell4.style.background = "#F9F5F4";

  n =  new Date();
  y = n.getFullYear();
  mo = n.getMonth();
  d = n.getDate();
  var h = n.getHours();
  var m = n.getMinutes();
  var s = n.getSeconds();

  cell1.innerHTML = d + "/" + mo + "/" + y + " " + h + ":" + m + ":" + s;
  cell2.innerHTML = "Testing notification system";
  cell3.innerHTML = type;
  cell4.appendChild(btn);

  table.appendChild( row );
}
function deleteRow(){

  var table = document.getElementById('notificationsTable');
  table.deleteRow(table.rows.length-1);

}
