import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {PersonService} from '../../model/person.service';
import {Person} from '../../model/person';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-edit-person',
  templateUrl: './edit-person.component.html',
  styleUrls: ['./edit-person.component.css']
})
export class EditPersonComponent implements OnInit {
  id: number;
  person: Person;

  constructor(private route: ActivatedRoute, private router: Router,
              private SpinnerService: NgxSpinnerService, private personService: PersonService) { }

  ngOnInit() {
    this.person = new Person();
    this.id = this.route.snapshot.params.id;

    this.personService.getPersonById(this.id)
      .subscribe(data => {
        // console.log(data);
        this.person = data;
      });
  }

  updatePerson() {
    this.SpinnerService.show();
    this.personService.updatePerson(this.id, this.person)
      .subscribe(() => {
        setTimeout(() => {
          this.SpinnerService.hide();
          this.gotoList();
        }, 500);
      });
  }

  onSubmit() {
    this.updatePerson();
  }

  gotoList() {
    this.id = this.route.snapshot.params.id;

    this.personService.getPersonById(this.id)
      .subscribe(data => {
        // console.log(data);
        this.person = data;
        if ( this.person.personListed === 'White')
        {
          this.router.navigate(['/people-cleared']);
        }
        else if ( this.person.personListed === 'Black')
        {
          this.router.navigate(['/people-threat']);
        }
        else {
          this.router.navigate(['/people-unknown']);
        }
      });
  }
}
