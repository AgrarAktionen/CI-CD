import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/interface/user';
import { UserComponent } from 'src/app/user-table/user/user.component';

@Component({
  selector: 'app-user-hinzufuegen',
  templateUrl: './user-hinzufuegen.component.html',
  styleUrls: ['./user-hinzufuegen.component.scss']
})
export class UserHinzufuegenComponent implements OnInit {

  user: User = {
    id: undefined,
    username: '',
    email: '',
    passwort: ''
  }
  constructor(private userComponent: UserComponent, private router: Router) { }

  ngOnInit(): void {
  }

  zurueck() {
    this.router.navigate(['/user']);
  }
  istOk(username: String, email: String) {
    if(username == "" || email == "") {
      (document.getElementById('speichern') as HTMLInputElement).disabled = true;
    } else {
      (document.getElementById('speichern') as HTMLInputElement).disabled = false;
    }
  }
  speichern(username: String, email: String) {
      this.user.username = username;
      this.user.email = email;

      this.userComponent.userNeu(this.user);
  }
}
