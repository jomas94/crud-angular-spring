import { Course } from './../model/course';
import { CoursesService } from './../services/courses.service';
import { Injectable } from '@angular/core';
import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoursesResolver implements Resolve<Course> {

  constructor(private service: CoursesService){}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Course> {
    if(route.params && route.params['id']){
      return this.service.findById(route.params['id']);
    }
    return of({_id:'', name: '', category:'', lessons:[] });
  }
}
