export class RecoverPasswordEmail{

  sendEmail(email, pass) {
    Email.send({
      Host: "smtp.gmail.com",
      Username : "cos332prac7email@gmail.com",
      Password : "COS3322020",
      To : email.toString(),
      From : "sigmacos301@gmail.com",
      Subject : "Argus home security alert",
      Body : "<html lang=\"en\">"+"<body> <p> Please click the link below to send an email to confirm your password: </p> <a href=\"mailto:SigmaCOS301@gmail.com?subject=ConfirmPassword&body="+email.toString() + ' ' + pass+"\">\n" +
        "Confirm Password</a></body>"  + "</html>"
    }).then(
      message => alert("Please check your inbox to confirm password reset")
    );
  }
}
