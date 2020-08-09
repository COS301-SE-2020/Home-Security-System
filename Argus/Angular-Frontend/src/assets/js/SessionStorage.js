"use strict";
export class Session{
  createSession(email, passw, id, role){
    let newUser = {};

    newUser.email = email;
    newUser.userPass = passw;
    newUser.id = id;
    newUser.userRole = role;

    sessionStorage.setItem('userDetails', JSON.stringify(newUser));
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

