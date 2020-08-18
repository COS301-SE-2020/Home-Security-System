
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

function jsB64(imgSrc) {
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

// ******************************************************** //
//                    Drag and Drop                         //
// ******************************************************** //
function openDrop() {
  var dropArea = document.getElementById("profilePicInput")

// Prevent default drag behaviors
  ;['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
    dropArea.addEventListener(eventName, preventDefaults, false)
    document.body.addEventListener(eventName, preventDefaults, false)
  })

// Highlight drop area when item is dragged over it
  ;['dragenter', 'dragover'].forEach(eventName => {
    dropArea.addEventListener(eventName, highlight, false)
  })

  ;['dragleave', 'drop'].forEach(eventName => {
    dropArea.addEventListener(eventName, unhighlight, false)
  })

// Handle dropped files
  dropArea.addEventListener('drop', handleDrop, false)

  function preventDefaults(e) {
    e.preventDefault()
    e.stopPropagation()
  }

  function highlight(e) {
    dropArea.classList.add('highlight')
  }

  function unhighlight(e) {
    dropArea.classList.remove('highlight')
  }

  function handleDrop(e) {
    var dt = e.dataTransfer
    var files = dt.files

    handleFiles(files)
  }

  function handleFiles(files) {
    files = [...files]
    // files.forEach(uploadFile)
    files.forEach(previewFile)
  }

  function previewFile(file) {
    var reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onloadend = function () {
      document.getElementById('submitPhoto').setAttribute('src', reader.result);
      // document.getElementById("profilePicDisplay").style.display = "none";
      // var img = document.createElement('img')
      // img.src = reader.result
      // img.width = 150;
      // document.getElementById('preview').appendChild(img)
      // document.getElementById('preview').firstChild.id = 'submitPhoto';
    }
  }
}

// ******************************************************** //
//                    Table Functions                       //
// ******************************************************** //
function searchFunc(tableID, colNum) {
  let input, filter, table, tr, td, i, txtValue;
  input = document.getElementById('searchInput');
  filter = input.value.toUpperCase();
  table = document.getElementById(tableID);
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[colNum];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function changeSearchFunc(tableID ,placeholder ,colNum)
{
  let currentSearch = document.getElementById('searchInput');
  currentSearch.setAttribute('placeholder', placeholder);
  currentSearch.setAttribute('onkeyup', "searchFunc('" + tableID + "', " + colNum + ")")
}

function sortTable(tableID, colNum) {
  let oldArrow = document.getElementById('arrowID');
  if (oldArrow)
    oldArrow.parentNode.removeChild(oldArrow);

  let newArrow = document.createElement('span');
  let currentHead = document.getElementsByTagName("TH")[colNum];
  newArrow.className = 'material-icons align-middle';
  newArrow.id = 'arrowID'

  let table, rows, switching, i, x, y, shouldSwitch, dir, switchCount = 0;
  table = document.getElementById(tableID);
  switching = true;
  // Set the sorting direction to ascending:
  dir = "asc";
  /* Make a loop that will continue until
  no switching has been done: */
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /* Loop through all table rows (except the
    first, which contains table headers): */
    for (i = 0; i < (rows.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      /* Get the two elements you want to compare,
      one from current row and one from the next: */
      x = rows[i].getElementsByTagName("TD")[colNum];
      y = rows[i + 1].getElementsByTagName("TD")[colNum];
      /* Check if the two rows should switch place,
      based on the direction, asc or desc: */
      if (dir === "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          newArrow.innerText = 'arrow_drop_down';
          shouldSwitch = true;
          break;
        }
      } else if (dir === "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          newArrow.innerText = 'arrow_drop_up';
          shouldSwitch = true;
          break;
        }
      }
      currentHead.appendChild(newArrow);
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark that a switch has been done: */
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      // Each time a switch is done, increase this count by 1:
      switchCount++;
    } else {
      /* If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again. */
      if (switchCount === 0 && dir === "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}


