import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatLegacySnackBar as MatSnackBar } from '@angular/material/legacy-snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form = this.formBuilder.group({
    _id: [''],
    name: ['', [Validators.required,
      Validators.minLength(1),
      Validators.maxLength(100)]],
    category: ['', Validators.required, Validators.minLength(1)]
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
    this.form.setValue({
      _id: course._id,
      name: course.name,
      category: ''    });

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

  getErrorMessage(fieldname: string){
    const field = this.form.get(fieldname);

    if(field?.hasError('required')){
      return 'Required field';
    }
    if(field?.hasError('minlength')){
      const requiredLength:number = field.errors ? field.errors['minlength']['requiredLength'] : 2;
      return `The minimum of characters required is ${requiredLength}`;
    }
    if(field?.hasError('maxlength')){
      const requiredLength:number = field.errors ? field.errors['maxlength']['requiredLength'] : 100;
      return `Exceeded max of ${requiredLength} characters. `;
    }

    return 'Invalid field'
  }

}
