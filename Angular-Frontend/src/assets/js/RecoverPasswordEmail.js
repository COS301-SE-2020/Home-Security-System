export class RecoverPasswordEmail{

  sendEmail(email, pass) {
    Email.send({
      Host: "smtp.gmail.com",
      Username : "cos332prac7email@gmail.com",
      Password : "COS3322020",
      To : email.toString(),
      From : "sigmacos301@gmail.com",
      Subject : "ALERT: Argus home security password reset",
      /*Body : "<html lang=\"en\">"+"<body> <p> Your password is as follows: " + pass + " </p> <p>Please log in with this email and password now on Argus</p></body>" + "</html>"*/
      Body : "<html lang=\"en\">"+"<body> <p> Your login details for the Argus system are as follows: </p> <p> Email: "
        + email + "</p> <p> Password: " + pass + "</p> <p>Please log into Argus with these details above</p> </body>" + "</html>"
      /*
      Body : "<html lang=\"en\">"+"<body> <p> Please click the link below to send an email to confirm your password: </p> <a href=\"mailto:SigmaCOS301@gmail.com?subject=ConfirmPassword&body="+email.toString() + ' ' + pass+"\">\n" +
        "Confirm Password</a></body>"  + "</html>"
       */
    }).then(
      message => alert("Please check your inbox to confirm password reset")
    );
  }
}
