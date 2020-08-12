/*==================================================================*/

function ValidateEmail() {
    var inputText = document.getElementById('input');
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if (inputText.value.match(mailformat)) {
        // return true;
    } else {
        // document.getElementById("input").innerHTML = " ";
        alert("That email address doesn't look real... maybe try that again.");
        // //
        // return false;
    }
}

/*================================================================*/

function CheckPassword() {
    var inputtxt = document.getElementById('pass');
    var decimal = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;

    if (inputtxt.value.match(decimal)) {
        // alert('Correct, try another...')
        // return true;
    } else {
        alert('Unfortunately that is an invalid password! It must be 8 to 15 characters long, containing at least one lowercase letter, one uppercase letter, one numeric digit, and one special character');
        ////
        // return false;
    }
}


/*==================================================================*/

function updateProfilePic(event) {
    var image = document.getElementById('output');
    image.src = URL.createObjectURL(event.target.files[0]);
    document.getElementById('profilePicDisplay').style.display = "none";
}

function changePic() {
  var confirmPic = document.getElementById('confirmPic').src;
  var image = document.getElementById('output');
  image.src = confirmPic;
  document.getElementById('profilePicDisplay').style.display = "none";
}

function updateProfilePicUP(event) {
  const image = document.getElementById('previewUP');
  image.src = URL.createObjectURL(event.target.files[0]);
  document.getElementById('profilePicDisplayUP').style.display = "none";
}
/*==================================================================*/

function imgToBase64(imgSrc) {
  console.log("Over here: " + imgSrc);
  // const getImg = document.getElementById('output').src;
  var img = new Image();
  img.src = imgSrc;

  var canvas = document.createElement("canvas");
  canvas.width = img.width;
  canvas.height = img.height;
  var ctx = canvas.getContext("2d");
  ctx.drawImage(img, 0, 0);
  var dataURL = canvas.toDataURL("image/png");
  console.log(dataURL.replace(/^data:image\/(png|jpg);base64,/, ""));
  return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
}

/*==================================================================*/

function checkProfileImage() {
    var temp = document.getElementById('output');
    if (temp.src === '') {
        document.getElementById('profilepic').src = "assets/Images/profile.jpg";
    }
}

/*==================================================================*/

function checkUserImage() {
    var temp = document.getElementById('output');
    if (temp.src === '') {
        alert('Unfortunately you need to upload an image of the user, a .jpeg or .png will do. Smile for the camera :) ');
        ////
    }
}

/*==================================================================*/

function mockLogin() {
    alert("Oh no! Either the Username/Email or Password is wrong. Lets try that one more time maybe...");
}

/*==================================================================*/

function mockAdd() {
    alert("Oh no! Seems like the database didn't like the entry. Lets try that one more time maybe...");
}

/*==================================================================*/

function showPassword() {
    if (document.getElementById("passEye").src.match('assets/Images/hidePass.png')) {
        document.getElementById("passEye").src = "assets/Images/showPass.png";

        if (document.getElementById("pass").type.match("password")) {
            document.getElementById("pass").type = "text";
        } else {
            document.getElementById("pass").type = "password";
        }
    } else if (document.getElementById("passEye").src.match('assets/Images/showPass.png')) {
        document.getElementById("passEye").src = "assets/Images/hidePass.png";

        if (document.getElementById("pass").type.match("password")) {
            document.getElementById("pass").type = 'text';
        } else {
            document.getElementById("pass").type = 'password';
        }
    }

}

/*==================================================================*/

function saveChanges() {
    alert("All changes saved. Welcome to the new you.");
}

/*==================================================================*/
