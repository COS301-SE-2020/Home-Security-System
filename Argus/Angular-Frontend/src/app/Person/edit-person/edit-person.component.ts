import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {PersonService} from '../../model/person.service';
import {Person} from '../../model/person';

@Component({
  selector: 'app-edit-person',
  templateUrl: './edit-person.component.html',
  styleUrls: ['./edit-person.component.css']
})
export class EditPersonComponent implements OnInit {
  id: number;
  person: Person;

  constructor(private route: ActivatedRoute, private router: Router,
              private personService: PersonService) { }

  ngOnInit() {
    this.person = new Person();
    this.id = this.route.snapshot.params.id;

    this.personService.getPersonById(this.id)
      .subscribe(data => {
        // console.log(data);
        this.person = data;
      }, error => console.log(error));
  }

  updatePerson() {
    this.personService.updatePerson(this.id, this.person)
      .subscribe(data => {
        // console.log(data);
      }, error => console.log(error));
    this.gotoList();
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
          this.router.navigate(['/people-white']);
        }
        else if ( this.person.personListed === 'Black')
        {
          this.router.navigate(['/people-black']);
        }
        else {
          this.router.navigate(['/people-grey']);
        }
      }, error => console.log(error));
  }
}
