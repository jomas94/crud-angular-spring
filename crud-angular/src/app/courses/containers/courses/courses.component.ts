import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';


@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {


  courses$:Observable <Course[]> | null = null;

  constructor(
      private coursesService:CoursesService,
      private dialog:MatDialog,
      private router: Router,
      private routeAct: ActivatedRoute,
      private snackBar: MatSnackBar
  ) {
    this.refresh();

  }

  refresh(){
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

  onDelete(course: Course){

    this.coursesService.delete(course._id).subscribe(
      () => {
        this.snackBar.open('Course deleted.', 'X',{
          duration: 3000,
          verticalPosition: 'top',
          horizontalPosition: 'center'
        });
        this.refresh();
      },
      error => this.onError("Error on try to delete course.")

    );
  }

}
