import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent  {

  form = this.formBuilder.group({
    name: [''],
    category: ['']
  });

  constructor(private formBuilder: NonNullableFormBuilder,
              private service:CoursesService,
              private snackBar: MatSnackBar,
              private location: Location  ) {

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
