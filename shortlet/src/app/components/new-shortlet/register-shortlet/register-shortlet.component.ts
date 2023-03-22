import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NewShortlet } from 'src/app/interface/shortlet';

@Component({
  selector: 'app-register-shortlet',
  templateUrl: './register-shortlet.component.html',
  styleUrls: ['./register-shortlet.component.css']
})
export class RegisterShortletComponent {

  constructor(){}

  userEmail
  form: NewShortlet

  onSubmit(form: NgForm){
    this.form = form.value
    console.log(form.value)
  }
  
}
