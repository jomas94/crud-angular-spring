import { Component, OnInit } from '@angular/core';
import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {


  courses$:Observable <Course[]> ;

  constructor(
      private coursesService:CoursesService,
      private dialog:MatDialog,
      private router: Router,
      private routeAct: ActivatedRoute
  ) {

    this.courses$ = this.coursesService.list()
    .pipe(
        catchError( error => {
           this.onError('Error to load data.')
          return of([]);
        })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, { data: errorMsg} );
  }

  ngOnInit(): void {
  }

  onAdd(){
    this.router.navigate(['new'], { relativeTo: this.routeAct});
  }

  onEdit(course: Course){
    this.router.navigate(['edit', course._id], { relativeTo: this.routeAct});
  }

}
