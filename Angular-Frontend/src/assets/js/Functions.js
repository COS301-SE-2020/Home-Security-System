/* Local variables */
var tot = 0;
var elmts = [];

/* enable/disable notification button */
function toggle(){
  var checked = document.getElementById('toggleBtn').checked;
  if(checked){
    document.getElementById('smsNotificationBtn').disabled = true;
    document.getElementById('emailNotification').disabled = true;
  }
  else{
    document.getElementById('smsNotificationBtn').disabled = false;
    document.getElementById('emailNotification').disabled = false;
  }

}

/* Fuction for sending an email */
function sendEmail(notificationTypeE) {
  Email.send({
    Host: "smtp.gmail.com",
    Username : "cos332prac7email@gmail.com",
    Password : "COS3322020",
    To : 'u16078625@tuks.co.za',
    From : "sigmacos301@gmail.com",
    Subject : "Argus home security alert",
    Body : notificationTypeE
  }).then(
    message => alert("mail sent successfully")
  );
}

/* Adding rows to table (a row per notification) */
function addCell(type){

  var table = document.getElementById("notificationsTable");
  var x = table.rows.length;
  var row = table.insertRow();
  var btn = document.createElement("button");

  btn.setAttribute('class', 'DeleteButton');
  btn.setAttribute('id', tot+1);
  btn.innerHTML = "Dismiss";

  btn.onclick = function(){
    var toDel = btn.getAttribute("id");
    var table = document.getElementById('notificationsTable');
    table.deleteRow(toDel);
    tot = table.rows.length - 1;

    for(var i = 1; i < table.rows.length; i++){
      table.rows[i].cells[3].children[0].setAttribute('id', i);
      //alert(table.rows[i].cells[3].children[0].getAttribute("id"));
    }

  }

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
  var date = d + "/" + mo + "/" + y + " " + h + ":" + m + ":" + s;

  var arr = [];
  arr.push(date);
  arr.push("Testing notification system");
  arr.push(type);
  arr.push(btn);
  arr.push(tot);
  elmts.push(arr);
  arr = [];
  console.log(elmts);
  tot += 1;
}
/*
function deleteRow(){

  var table = document.getElementById('notificationsTable');
  table.deleteRow(table.rows.length-1);

}
*/


//LOGIN FUNCTIONS

function loginSlide() {

  $('#login-button').fadeOut("slow", function () {
    $("#loginContainer").fadeIn();
    TweenMax.from("#login-button", .2, {scale: 0, ease: Sine.easeInOut});
    TweenMax.to("#loginContainer", .2, {scale: 1, ease: Sine.easeInOut});
  });
}

// function closeLogin() {
//   $(".login-close-btn").click(function () {
//     TweenMax.from("#loginContainer", .2, {scale: 1, ease: Sine.easeInOut});
//     TweenMax.to("#loginContainer", .2, {left: "0px", scale: 0, ease: Sine.easeInOut});
//     $("#loginContainer, #forgotten-container").fadeOut(800, function () {
//       $("#login-button").fadeIn(800);
//     });
//   });
// }

function forgot() {
  /* Forgotten Password */
  $('#forgotten').click(function () {
    $("#loginContainer").fadeOut(function () {
      $("#forgotten-container").fadeIn();
    });
  });
}

function showPassword(input) {
  var id = "passwordField" + input;
  var iconID = "passwordVisibility" + input;
  var x = document.getElementById(id);
  var icon = document.getElementById(iconID);

  if (x.type === "password") {
    x.type = "text";
    icon.innerHTML = "visibility";
  } else {
    x.type = "password";
    icon.innerHTML = "visibility_off";
  }
}

//Password Checker
function passwordChecker() {
  var myInput = document.getElementById("passwordField1");
  var letter = document.getElementById("letter");
  var capital = document.getElementById("capital");
  var number = document.getElementById("number");
  var length = document.getElementById("length");

// When the user clicks on the password field, show the message box
  document.getElementById("passCheckMsg").style.display = "block";

// When the user clicks outside of the password field, hide the message box
  myInput.onblur = function () {
    document.getElementById("passCheckMsg").style.display = "none";
  }

// When the user starts to type something inside the password field
  myInput.onkeyup = function () {
    // Validate lowercase letters
    var lowerCaseLetters = /[a-z]/g;
    if (myInput.value.match(lowerCaseLetters)) {
      letter.classList.remove("invalid");
      letter.classList.add("valid");
    } else {
      letter.classList.remove("valid");
      letter.classList.add("invalid");
    }

    // Validate capital letters
    var upperCaseLetters = /[A-Z]/g;
    if (myInput.value.match(upperCaseLetters)) {
      capital.classList.remove("invalid");
      capital.classList.add("valid");
    } else {
      capital.classList.remove("valid");
      capital.classList.add("invalid");
    }

    // Validate numbers
    var numbers = /[0-9]/g;
    if (myInput.value.match(numbers)) {
      number.classList.remove("invalid");
      number.classList.add("valid");
    } else {
      number.classList.remove("valid");
      number.classList.add("invalid");
    }

    // Validate length
    if (myInput.value.length >= 8) {
      length.classList.remove("invalid");
      length.classList.add("valid");
    } else {
      length.classList.remove("valid");
      length.classList.add("invalid");
    }
  }
}

function passwordConfirm() {
  var password = document.getElementById("passwordField1");
  var confirm = document.getElementById("passwordField2");

  var match = document.getElementById("passMatch");
  var noMatch = document.getElementById("passNoMatch");

  confirm.onkeyup = function () {
    if(password.value === "" || confirm.value === "")
      noMatch.style.display = "block";

    if (password.value !== confirm.value) {
      noMatch.style.display = "block";
      match.style.display = "none";
    }
    else{
      match.style.display = "block";
      noMatch.style.display = "none";
    }
  }
}
