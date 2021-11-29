import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/interface/user';
import { UserService } from 'src/app/user-service/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
@Injectable({
  providedIn: 'root'
})
export class UserComponent implements OnInit {

  user: User [] = []
  
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getAll().subscribe(user => this.user = user)
  }
  clicked(user: User) {
    this.router.navigate(['/user', user.id]);
  }
  loeschen(userr: User) {
    this.userService.deleteID(userr.id);
    this.user = this.user.filter(user => user.id != userr.id);
  }
  userNeu(user: User) {
    this.userService.postUser(user).subscribe(user => {
      this.user.push(user)
      this.router.navigate(['/user']);
    })
  }
  userBearbeiten(user: User) {
    this.userService.putUser(user).subscribe(user => {
      this.user.push(user)
      this.router.navigate(['/user', user.id]);
    })
  }
  hinzufuegen() {
    this.router.navigate(['/userHinzufuegen'])
  }
}
