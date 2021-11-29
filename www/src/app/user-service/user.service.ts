import { Injectable } from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http'
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interface/user';

const URL = "http://localhost:8080/api/user"

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private client : HttpClient) { }

  getAll() {
    return this.client.get<User[]>(URL); 
  }
  getID(id: Number) {
   var URLID = "http://localhost:8080/api/user/" + id.toString();
   return this.client.get<User>(URLID);
  }
  deleteID(id: Number) {
    var URLID = "http://localhost:8080/api/user/" + id.toString();
    this.client.delete(URLID).subscribe()
  }
  putUser(user: User) : Observable<User> {
    return this.client.put<User>(URL, user, httpOptions);
  }
  postUser(user: User) : Observable<User> {
    return this.client.post<User>(URL, user, httpOptions);
  }
}
