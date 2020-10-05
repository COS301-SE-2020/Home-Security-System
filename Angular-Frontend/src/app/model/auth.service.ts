import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import { environment } from '../../environments/environment';
import { map } from "rxjs/operators";
import { JwtResponse } from "./jwt-response";
import { SessionClass } from "./session";
import {RecoveryDetails} from "./recovery-details";
import {Observable} from "rxjs";
import {JwtRequest} from "./jwt-request";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //private baseUrl = `${environment.apiUrl}/api/authenticate`;
  private baseUrl = `${environment.apiUrl}/api/validate`;

  constructor(private httpClient: HttpClient) { }

  validatePassword(value: JwtRequest) : Observable<JwtRequest> {
    return this.httpClient.post<JwtRequest>(`${this.baseUrl}`, value);
  }

  /*authenticate(username, password) {
    return this.httpClient.post<any>(this.baseUrl,{username,password}).pipe(
      map(userData => {
          sessionStorage.setItem('username',username);
          let tokenStr= 'Bearer '+ userData.token;
          sessionStorage.setItem('token', tokenStr);
          return userData;
        }
      )
    );
  }*/

  /*authenticate(credentials: AuthLoginInfo) {
    return this.httpClient.post<JwtResponse>(this.baseUrl, credentials, httpOptions);
  }*/

  isUserLoggedIn() {
    let user = sessionStorage.getItem('userDetails')
    return !(user === null)
  }

  logOut() {
    sessionStorage.clear();
  }

  /*public saveToken(token: string) {
    sessionStorage.removeItem(TOKEN_KEY);
    sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUsername(username: string) {
    sessionStorage.removeItem(USERNAME_KEY);
    sessionStorage.setItem(USERNAME_KEY, username);
  }

  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }*/

  recoverySession(email, ans) {
    sessionStorage.setItem('recovery', email);
    let recoveryDetails = new RecoveryDetails();
    recoveryDetails.email = email;
    recoveryDetails.answer = ans;
    sessionStorage.setItem('recovery', JSON.stringify(recoveryDetails));
  }

  retrieveEmail() {
    let retrievedMail = sessionStorage.getItem('recovery');
    return JSON.parse(retrievedMail);
  }

  createSession(id, role, email, network) {
    let newUser = new SessionClass();
    newUser.id = id;
    newUser.role = role;
    newUser.email = email;
    newUser.network = network;
    window.sessionStorage.setItem('userDetails', JSON.stringify(newUser));
  }

  retrieveUserInfo() {
    let retrievedObject = sessionStorage.getItem('userDetails');
    return JSON.parse(retrievedObject);
  }
}
