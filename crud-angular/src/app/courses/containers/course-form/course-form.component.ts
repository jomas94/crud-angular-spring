import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { CoursesService } from '../../services/courses.service';
import { Course } from '../../model/course';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form = this.formBuilder.group({
    _id: [''],
    name: [''],
    category: ['']
  });

  constructor(private formBuilder: NonNullableFormBuilder,
              private service:CoursesService,
              private snackBar: MatSnackBar,
              private location: Location,
              private route: ActivatedRoute  ) {

  }

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['course'];
    console.log(course);
    this.form.setValue(course);

  }

  onSubmit(){

    this.service.save(this.form.value)
    .subscribe(result=> this.onSucess(), error => this.onError()   );
    // console.log(this.form.value);

  }

  onCancel(){
    this.location.back();

  }
  private onSucess(){
    this.snackBar.open('Course saved.', '', {duration: 3000});
    this.onCancel();
  }

  private onError(){
    this.snackBar.open('Error saving course', '', {duration: 3000})
  }

}
