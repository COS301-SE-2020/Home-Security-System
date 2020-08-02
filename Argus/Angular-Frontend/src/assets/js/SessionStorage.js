"use strict";
export class Session{
  createSession(email, passw, id){
    let newUser = {};

    newUser.email = email;
    newUser.userPass = passw;
    newUser.id = id;

    sessionStorage.setItem('userDetails', JSON.stringify(newUser));
    //alert(this.retrieveUserInfo());
  }

  deleteSession(){
    sessionStorage.clear();
  }

  retrieveUserInfo(){
    let retrievedObject = sessionStorage.getItem('userDetails');
    console.log(JSON.parse(retrievedObject));
    return JSON.parse(retrievedObject);
  }
}

export default Session;

