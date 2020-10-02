"use strict";
export class Session {

  recoverySess(email) {
    let recoveryDetails = {};
    recoveryDetails.email = email;
    sessionStorage.setItem('recovery', JSON.stringify(recoveryDetails));
  }

  retrieveEmail() {
    let retrievedMail = sessionStorage.getItem('recovery');
    return JSON.parse(retrievedMail);
  }

  createSession(id, role, email, network) {
    let newUser = {};
    newUser.id = id;
    newUser.userRole = role;
    newUser.email = email;
    newUser.network = network;
    sessionStorage.setItem('userDetails', JSON.stringify(newUser));
  }

  deleteSession() {
    sessionStorage.clear();
  }

  retrieveUserInfo() {
    let retrievedObject = sessionStorage.getItem('userDetails');
    // console.log(JSON.parse(retrievedObject));
    return JSON.parse(retrievedObject);
  }
}

export default Session;

