import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/interface/user';
import { UserService } from 'src/app/user-service/user.service';
import { UserComponent } from 'src/app/user-table/user/user.component';

@Component({
  selector: 'app-user-bearbeiten',
  templateUrl: './user-bearbeiten.component.html',
  styleUrls: ['./user-bearbeiten.component.scss']
})
export class UserBearbeitenComponent implements OnInit {

  user!: User;
  userIstGeandert!: User;
  
  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private userComponent: UserComponent) { }

  ngOnInit(): void {
    this.userService.getID(Number(this.route.snapshot.paramMap.get('id'))).subscribe(user => this.user = user);
    this.userService.getID(Number(this.route.snapshot.paramMap.get('id'))).subscribe(userIstGeandert => this.userIstGeandert = userIstGeandert);
  }
  zurueck() {
    this.router.navigate(['/user', this.user.id]);
  }
  speichern(username: String, email: String) {
      this.user.username = username;
      this.user.email = email;

      this.userComponent.userBearbeiten(this.user);
  }
  istOk(username: String, email: String) {
    if(username == "" || email == "") {
      (document.getElementById('speichern') as HTMLInputElement).disabled = true;
    } else {
      if(this.userIstGeandert.username == username && this.userIstGeandert.email == email) {
        (document.getElementById('speichern') as HTMLInputElement).disabled = true;
      } else {
        (document.getElementById('speichern') as HTMLInputElement).disabled = false;
      }
    }
  }
}
