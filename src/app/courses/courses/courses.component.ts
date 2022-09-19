import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {


  courses$:Observable <Course[]> ;
  displayedColumns = ['name', 'category'];

  constructor(private coursesService:CoursesService ) {

    this.courses$ = this.coursesService.list()
    .pipe(
        catchError( error => {
          return of([]);
        })
    );
  }

  ngOnInit(): void {
  }

}
