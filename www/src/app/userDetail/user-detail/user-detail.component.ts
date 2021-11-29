import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/interface/user';
import { UserService } from 'src/app/user-service/user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {

  user!: User;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getID(Number(this.route.snapshot.paramMap.get('id'))).subscribe(user => this.user = user);
  }
  bearbeiten() {
    this.router.navigate(['/userBearbeiten', this.user.id]);
  }
  zurueck() {
    this.router.navigate(['/user']);
  }
}
